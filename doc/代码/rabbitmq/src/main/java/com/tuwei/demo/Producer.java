/**
 * Copyright (C), 2020, XXX公司
 * FileName: Producer
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.tuwei.demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: rabbitmq
 * @description:
 * @author: tuwei
 * @create: 2020-05-15 10:32
 */
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {


        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(Const.host);
        connectionFactory.setPort(Const.port);
        connectionFactory.setVirtualHost(Const.vHost);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("123");

        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();

        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {

            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {

            }
        });

        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {

            }
        });
        String msg = "hello";
        //String exchange, String routingKey, AMQP.BasicProperties props, byte[] body
        for (int i = 0; i <= 3; i++) {
            channel.basicPublish("aaa", "test001", null, msg.getBytes());
        }

        channel.close();
        connection.close();



    }

}
