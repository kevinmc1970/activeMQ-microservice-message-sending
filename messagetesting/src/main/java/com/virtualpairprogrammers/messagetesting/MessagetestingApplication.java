package com.virtualpairprogrammers.messagetesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class MessagetestingApplication {

	public static void main(String[] args) {
		// from youtube tutorial https://www.youtube.com/watch?v=eqFgNouG490
		// and https://www.youtube.com/watch?v=QTVmsI-oYd0
		// simple testing of messaging in this class rather than a 'proper' application class
		// can get spring context from .run call below and then get JmsTemplate using context i.e. get the JMSTemplate from Spring context
		// provided by spring-boot-starter-activemq in pom.xml
		ApplicationContext ctx = SpringApplication.run(MessagetestingApplication.class, args);
		
		// JMSTemplate used to send/recieve messages from the MQ
		JmsTemplate jmsTemplate = ctx.getBean(JmsTemplate.class);
		
		// spring will convert second param to JMS message i.e. to a TextMessage or convert a hashmap into a MapMessage
		// message sent to queue via broker (configured in applicaiton.properties BTW)
		jmsTemplate.convertAndSend("positionQueue", "this is a test");
		
		// N.B. springboot will create an ActiveMQ embedded queue if haven't create an external broker (i.e. "positionQueue")
		// need to edit application.properties
		// spring.activemq.broker-url = localhost:61616
		
		// each time run this springboot app the the 'this is a test' message will appear in the queue 
		// http://localhost:8161/admin/browse.jsp?JMSDestination=positionQueue
		// after running broker C:\Users\becky\Documents\apache-activemq-5.15.8\bin>activemq start
		// this queue is persisted BTW as created in admin console
		
		// CAN then run my receivetesting springboot app which consumes the same queue
	}

}

