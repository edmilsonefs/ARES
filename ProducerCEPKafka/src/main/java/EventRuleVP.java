
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class EventRuleVP {

	
	private EventRuleVP() { }

public static void main(final String... args) throws InterruptedException {
   
		
		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
		kafkaTemplate.setDefaultTopic("si.ceprule.queue");
	   

	    String payload = "";
	    //primeiro passo
	    //O tipo ADD_EVENT indiga EVENTO a ser capturado pelo engine CEP
	    payload = "{\"contextElement\":{\"type\" : \"ADD_EVENT\",\"id\" : \"VoltsPhase\","
	    		+ "\"attributes\" : [{ \"name\"  : \"id\","
	    		                    + "\"type\"  : \"Double\","
	    		                    + "\"value\" : \"0\"}"
	    		                    + "{\"name\" : \"metering\","
	    	    		            + "\"type\"  : \"Double\","
	    	    		            + "\"value\" : \"0\"}"
	    		                    + "]}}";

	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
	    System.out.println("Producer CEP VoltsPhase Event");
	}
}
