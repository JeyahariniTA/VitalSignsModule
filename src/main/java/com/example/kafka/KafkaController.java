package com.example.kafka;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

	@Autowired
	private KafkaProducer kafkaProducer;

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(@RequestBody JSONObject json) {
		kafkaProducer.sendMessage(json);
	}

}
