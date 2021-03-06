package com.service.middleware.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.messaging.handler.annotation.Payload;

import com.service.middleware.cep.handler.MonitorEventHandler;
import com.service.middleware.model.Entity;
import com.service.middleware.model.ParserJson;

public class KafkaConsumerListener implements MessageListener<Integer, String> {
	// FALTA DEFINIR COM CALMA COMO VAI SER A L�GICA DO SISTEMA AQUI. IDEIA
	// PEGAR O TOPICO E REDIRECIONAR PRA O RESPONSAVEL

	@Autowired
	MonitorEventHandler monitorEventHandler;

	@Override
	public void onMessage(ConsumerRecord<Integer, String> record) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println("=============kafkaConsumer============="); String
		 * topic = record.topic(); Integer key = record.key(); String value =
		 * record.value(); long offset = record.offset(); int partition =
		 * record.partition(); System.out.println("-------------topic:"+topic);
		 * System.out.println("-------------value:"+value);
		 * System.out.println("-------------key:"+key);
		 * System.out.println("-------------offset:"+offset);
		 * System.out.println("-------------partition:"+partition);
		 * System.out.println("~~~~~~~~~~~~~kafkaConsumer");
		 */
		String topicStream = null;
		String topicRule = null;
		try {
			topicRule = AppConfigProperties.getInstance().getProperties("queue.rule.cep");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			topicStream = AppConfigProperties.getInstance().getProperties("queue.streaming.data");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (record.topic().equals(topicStream)) {
			try {
				fireEvent(record.value());
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (record.topic().equals(topicRule)) {
			toTemp(record.value());
		} else {
			System.out.println("no event!");
		}
	}

	public void fireEvent(@Payload String payload) throws NumberFormatException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		try {
			if (monitorEventHandler == null) {
				monitorEventHandler = (MonitorEventHandler) ApplicationContextProvider.getBean("monitorEventHandler");
			}
			Entity event = ParserJson.parseEntity(payload);
			monitorEventHandler.handleEntity(event);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

	}

	public Entity toTemp(@Payload String payload) {
		Entity myEntity = ParserJson.parseEntity(payload);
		try {
			if (monitorEventHandler == null) {
				monitorEventHandler = (MonitorEventHandler) ApplicationContextProvider.getBean("monitorEventHandler");
			}
			monitorEventHandler.createRequestMonitorExpression(myEntity);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

		return myEntity;
	}
}