package com.service.middleware.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;

import com.service.middleware.cep.handler.MonitorEventHandler;
import com.service.middleware.model.CollectType;
import com.service.middleware.model.Entity;
import com.service.middleware.model.ParserJson;

public class InitRules {

	@Autowired
	MonitorEventHandler monitorEventHandler;

	public void getRules() throws Exception {
		monitorEventHandler = (MonitorEventHandler) ApplicationContextProvider.getBean("monitorEventHandler");

		MongoDbFactory mongoDbFactory = (MongoDbFactory) ApplicationContextProvider.getBean("mongoDbFactory");
		List listEntity = mongoDbFactory.getDb().getCollection("ruleData").find().toArray();
		if (listEntity.size() > 0) {
			for (Object object : listEntity) {
				Entity myEntity = ParserJson.parseMongo("{\"contextElement\":" + String.valueOf(object) + "}");
				if (myEntity.getType().equals(CollectType.ADD_RULE_TYPE.getName())) {
					monitorEventHandler.createRequestMonitorExpression(myEntity);
				}

			}

		}

	}

}
