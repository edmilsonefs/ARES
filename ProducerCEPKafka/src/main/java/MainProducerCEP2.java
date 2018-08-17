

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class MainProducerCEP2 {

	private MainProducerCEP2() { }

public static void main(final String... args) throws InterruptedException {

		
	@SuppressWarnings("resource")
	final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
	kafkaTemplate.setDefaultTopic("si.ceprule.queue");


	    String payload = "";
	
	    payload = "{\"contextElement\":{\"type\" : \"RULECEP\",\"id\" : \"Rule2\","
	    		+ "\"attributes\" : [{ \"name\"  : \"RULE\","
	    		                    + "\"type\"  : \"String\","
	    		                    + "\"value\" : \"select id from Entity.win:time(5 sec) where cast(id, double)= 2\"}"
	    		                    + "{\"name\" : \"QUEUE_2\","
	    	    		            + "\"type\"  : \"QUEUE\","
	    	    		            + "\"value\" : \"si.cep.queue2\"}"
	    		                    + "]}}";
	
	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
	    System.out.println("ProducerCEP2");
	}
}
