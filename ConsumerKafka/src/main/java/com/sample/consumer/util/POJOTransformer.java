package com.sample.consumer.util;

import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

@MessageEndpoint
public class POJOTransformer {
	/*
	 * @Transformer public Entity toTemp(String s){ Entity myEntity =
	 * ParserJson.parseEntity(s); return myEntity; }
	 */
	// ---------------to teste !!!!------------------------------
	@Transformer
	public String toTemp(String s) {
		// Entity myEntity = ParserJson.parseEntity(s);
		return s;
	}
}
