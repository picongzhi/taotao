package com.pcz.taotao.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml"})
public class SpringActiveMQTest {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private ActiveMQQueue activeMQQueue;

    @Test
    public void jmsTemplateTest() throws JmsException {
        jmsTemplate.send(activeMQQueue, session -> session.createTextMessage("hello spring active mq"));
    }
}
