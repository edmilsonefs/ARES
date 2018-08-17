

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

public final class MainProducerCEP3 {

	private MainProducerCEP3() { }

public static void main(final String... args) throws InterruptedException {
        

		@SuppressWarnings("resource")
		final AbstractApplicationContext context =	new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		KafkaTemplate<String, String> kafkaTemplate = (KafkaTemplate) context.getBean("template");
		kafkaTemplate.setDefaultTopic("si.ceprule.queue");
	   

	    String payload = "";
	    //Terceiro passo
	    //O tipo RULECEP indica REGRA a ser Adicionada o typo QUEUE indica que tera uma fila de destino no broker
	    payload = "{\"contextElement\":{\"type\" : \"EDIT_RULECEP\",\"id\" : \"Rule3\","
			    		+ "\"attributes\" : [{ \"name\"  : \"RULE_ID\","
		                + "\"type\"  : \"String\","
		                + "\"value\" : \"cf943226-be26-4e80-a9ad-516554ddcfbe\"}"
		                + "{\"name\"  : \"RULE\","
			            + "\"type\"  : \"String\","
			            + "\"value\" : \"select temperature from Termometro.win:time(10 sec)\"}"
			            + "{\"name\"  : \"QUEUE_1\","
    		            + "\"type\"  : \"QUEUE\","
    		            + "\"value\" : \"si.cep.queue2\"}"
	                    + "]}}";
	
	    kafkaTemplate.sendDefault(payload);
	    context.registerShutdownHook();
	    System.out.println("ProducerCEP3");
	}
}
