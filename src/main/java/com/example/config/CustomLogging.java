package com.example.config;

import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.kafka.KafkaProducer;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class CustomLogging {

	@Autowired
	KafkaProducer kafkaProducer;

	Date startDate;
	Date endDate;

	private Logger logger = LoggerFactory.getLogger(CustomLogging.class);

	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		startDate = new Date();
		Object[] args = proceedingJoinPoint.getArgs();
		if (args.length > 0) {
			logger.info("Arguments passed: ");
			for (int i = 0; i < args.length; i++) {
				logger.info("arg " + (i + 1) + ": " + args[i]);
			}
		}
		Object result = proceedingJoinPoint.proceed(args);
		endDate = new Date();
		logger.info("Method Signature: " + proceedingJoinPoint.getSignature());
		logger.info("Returning result" + result);
		logger.info("Method Execution Time: " + ((endDate.getTime() - startDate.getTime()) / 10000) + " ms");
		return result;
	}

	@Around("@annotation(LogAuditPayload)")
	public Object logAuditPayload(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		JSONObject json = new JSONObject();
//		String action = logAuditPayload.action();
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = signature.getMethod();
		LogAuditPayload anno = method.getAnnotation(LogAuditPayload.class);
		logger.info("method name: " + method.getName());
		String action = anno.action();
		String description = anno.description();
		json.put("action", action);
		json.put("description", description);

		Object[] args = proceedingJoinPoint.getArgs();
		logger.info("args: " + args);
		JSONObject obj = new JSONObject();
		if (args != null) {
			if (action.equalsIgnoreCase("delete")) {
				logger.info("args[0]: " + args[0]);
				json.put("entityId", args[0]);
			} else if (action.equalsIgnoreCase("add") || action.equalsIgnoreCase("update")) {
				obj = new JSONObject(args[0]);
				logger.info("args obj: " + obj);
				obj.remove("rowVersion");
				obj.remove("createdOn");
				obj.remove("updatedOn");
				obj.remove("updatedBy");
				obj.remove("createdBy");
				if (obj.has("id")) {
					json.put("entityId", obj.getInt("id"));
					obj.remove("id");
				}
				json.put("patientId", obj.optInt("patientId"));
				obj.remove("patientId");
				JSONObject valueJson = new JSONObject();
				valueJson.put("newValue", obj);
				json.put("attributes", valueJson.toString());
			}
		}
		json.put("entityType", "vitalSigns");
		json.put("username",
				SecurityContextHolder.getContext().getAuthentication() != null
						? SecurityContextHolder.getContext().getAuthentication().getName()
						: "Harini");
		Object result = proceedingJoinPoint.proceed(args);
		if (action.equalsIgnoreCase("add") || action.equalsIgnoreCase("update")) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.findAndRegisterModules();
			String jsonString = mapper.writeValueAsString(result);
			JSONObject resultJson = new JSONObject(jsonString);
			json.put("entityId", resultJson.optInt("id"));
		}
		logger.info("message from custom logging: " + json);
		kafkaProducer.sendMessage(json);
		return result;
	}
}
