package com.sample.producer.kafka;
//import org.apache.log4j.Logger;

public final class MainProducerKafka {

	private MainProducerKafka() { }

	public static void main(final String... args) {
		System.out.println("Producer_kafka");
		new TemperatureProducer().start();
	 
        
	}
}