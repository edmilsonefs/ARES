

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class MainProducerCEP {

	private MainProducerCEP() { }

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
  
	    payload = "{\"contextElement\":{\"type\" : \"RULECEP\",\"id\" : \"Rule7\","
	    		+ "\"attributes\" : [{ \"name\"  : \"RULE\","
	    		                    + "\"type\"  : \"String\","
	    		                    + "\"value\" : \"select temperature from Termometro.win:time(5 sec)\"},"//ver a virgula aqui depois!!!!
	    		                    + "{\"name\"  : \"QUEUE_1\","
	    	    		            + "\"type\"  : \"QUEUE\","
	    	    		            + "\"value\" : \"si.cep.queue\"}"
	    		                    + "]}}";
	    

	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
        System.out.println("ProducerCEP");
	}
}

