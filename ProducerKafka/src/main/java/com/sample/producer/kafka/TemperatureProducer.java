package com.sample.producer.kafka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public class TemperatureProducer{

	public void TemperatureProducer() {

		// @SuppressWarnings("resource")
		final AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

		double temperatures[] = getTemps("/Temp//temperatures_1m.txt");
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
		kafkaTemplate.setDefaultTopic("si.test.queue");

		String payload = "";
		for (int i = 0; i < temperatures.length; i++) {
			payload = "{\"contextElement\":{\"type\" : \"Termometro\",\"id\" : \"" + 1
					+ "\",\"attributes\" : [{ \"name\" : \"temperature\",\"type\" : \"Double\",\"value\" : \""
					+ temperatures[i] + "\"}]}}";
			kafkaTemplate.sendDefault(payload);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		context.registerShutdownHook();

	}

	public static double[] getTemps(String csvFile) {
		String line = "";
		int i = 0;
		double[] result = new double[1000000];
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			while ((line = br.readLine()) != null) {
				// use comma as separator
				result[i] = Double.valueOf(line);
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}