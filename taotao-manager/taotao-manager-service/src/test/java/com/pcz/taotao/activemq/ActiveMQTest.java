package com.pcz.taotao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

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

    @Test
    public void queueConsumerTest() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("test-queue");
        MessageConsumer messageConsumer = session.createConsumer(queue);
        messageConsumer.setMessageListener(message -> {
            try {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage.getText());
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageConsumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void topicProducerTest() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageProducer messageProducer = session.createProducer(topic);

        TextMessage textMessage = session.createTextMessage("hello activemq topic");
        messageProducer.send(textMessage);

        messageProducer.close();
        session.close();
        connection.close();
    }

    @Test
    public void topicConsumerTest() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("test-topic");
        MessageConsumer messageConsumer = session.createConsumer(topic);
        messageConsumer.setMessageListener(message -> {
            try {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    System.out.println(textMessage.getText());
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        messageConsumer.close();
        session.close();
        connection.close();
    }
}
