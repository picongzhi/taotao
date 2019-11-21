package com.pcz.taotao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

import javax.jms.*;

public class ActiveMQTest {
    @Test
    public void queueProducerTest() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageProducer messageProducer = session.createProducer(queue);

//        TextMessage textMessage = new ActiveMQTextMessage();
//        textMessage.setText("hello world");
        TextMessage textMessage = session.createTextMessage("hello world");
        messageProducer.send(textMessage);

        messageProducer.close();
        session.close();
        connection.close();
    }
}
