

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class MainProducerCEP4 {
     
	private MainProducerCEP4() { }

public static void main(final String... args) throws InterruptedException {
        

		
		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
		kafkaTemplate.setDefaultTopic("si.ceprule.queue");
	   

	    String payload = "";
	    //Quarto passo
	    //O tipo DEL_RULE indiga REGRA a ser Deletada
	    payload = "{\"contextElement\":{\"type\" : \"DEL_RULE\",\"id\" : \"DelRule3\","
	    		+ "\"attributes\" : [{ \"name\"  : \"RULE_ID\","
	    		                    + "\"type\"  : \"String\","
	    		                    + "\"value\" : \"263f47f1-ecc1-4aea-8ced-c132b5177fc8\"}"
	    		                    + "]}}";
	
	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
	    System.out.println("ProducerCEP4");
	}
}
