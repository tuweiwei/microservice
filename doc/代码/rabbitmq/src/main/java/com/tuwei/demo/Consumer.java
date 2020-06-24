/**
 * Copyright (C), 2020, XXX公司
 * FileName: Consumer
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.tuwei.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: rabbitmq
 * @description:
 * @author: tuwei
 * @create: 2020-05-15 10:32
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Const.host);
        connectionFactory.setPort(Const.port);
        connectionFactory.setVirtualHost(Const.vHost);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("123");
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        // 创建队列 与 routingkey对应
        String queueName = "test001";
        channel.queueDeclare(queueName, true, false, false, null);

        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        channel.basicConsume(queueName, true, queueingConsumer);

        while (true){

            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            String body = new String(delivery.getBody());
            System.out.println(body);

        }

    }
}
