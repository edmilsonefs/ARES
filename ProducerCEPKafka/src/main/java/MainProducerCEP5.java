
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class MainProducerCEP5 {

	
	private MainProducerCEP5() { }

public static void main(final String... args) throws InterruptedException {
   
		
		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
		kafkaTemplate.setDefaultTopic("si.ceprule.queue");
	   

	    String payload = "";
	    //primeiro passo
	    //O tipo ADD_EVENT indiga EVENTO a ser capturado pelo engine CEP
	    payload = "{\"contextElement\":{\"type\" : \"ADD_EVENT\",\"id\" : \"Termometro\","
	    		+ "\"attributes\" : [{ \"name\"  : \"id\","
	    		                    + "\"type\"  : \"String\","
	    		                    + "\"value\" : \"0\"}"
	    		                    + "{\"name\" : \"temperature\","
	    	    		            + "\"type\"  : \"Double\","
	    	    		            + "\"value\" : \"0\"}"
	    		                    + "]}}";

	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
	    System.out.println("ProducerCEP5");
	}
}
