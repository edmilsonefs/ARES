

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class QueryRuleVP {

	private QueryRuleVP() { }

public static void main(final String... args) throws InterruptedException {
        

		
		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    	KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
    	kafkaTemplate.setDefaultTopic("si.ceprule.queue");

	    
	    /*
	      OBS.: Toda represenatacao de valores deve ser representada de maneira numerica ex.: flags(0,1) ou Att1(1), Att2(2) da mesma maneira que 
	      aplicacoes de classificadores de IA.
	    */

	    String payload = "";
  
	    payload = "{\"contextElement\":{\"type\" : \"RULECEP\",\"id\" : \"Rule4\","
	    		+ "\"attributes\" : [{ \"name\"  : \"RULE\","
	    		                    + "\"type\"  : \"String\","
	    		                    + "\"value\" : \"select metering from VoltsPhase where metering >= 4180.4\"},"//ver a virgula aqui depois!!!!
	    		                    + "{\"name\"  : \"QUEUE_7\","
	    	    		            + "\"type\"  : \"QUEUE\","
	    	    		            + "\"value\" : \"si.cep.queue\"}"
	    		                    + "]}}";
	    

	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
        System.out.println("QueryRuleVP");
	}
}

