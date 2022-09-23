package com.example.kafka;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	private Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

	private static final String topic = "vitalsigns";

	@SuppressWarnings("rawtypes")
	@Autowired
	private KafkaTemplate kafkaTemplate;

	@SuppressWarnings("unchecked")
	public void sendMessage(JSONObject jsonObject) {
		logger.info("Producing kafka message: Message is " + jsonObject);
		this.kafkaTemplate.send(topic, jsonObject.toString());
	}

}
