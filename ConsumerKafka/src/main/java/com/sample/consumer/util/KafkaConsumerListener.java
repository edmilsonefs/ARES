package com.sample.consumer.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

public class KafkaConsumerListener implements MessageListener<Integer, String> {
	
	public  static final int QUEUE_SIZE = 24;
	
	private static List<Double> myList;
	static int count = 0;

	
	public void onMessage(ConsumerRecord<Integer, String> record) {
		// TODO Auto-generated method stub
		
        System.out.println("=============kafkaConsumer=============");
        String  topic     = record.topic();
        Integer key       = record.key();
        String  value     = record.value();
        long    offset    = record.offset();
        int     partition = record.partition();
          
        System.out.println(value);
                
	}
}