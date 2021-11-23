package com.cheny.rpc.sample.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Boostrap
 * @Description
 * @Author cheny
 * @Date 2021/11/23 15:22
 * @Version 1.0
 **/
public class RPCBootstrap {
    private static final Logger LOGGER = LoggerFactory.getLogger(RPCBootstrap.class);

    public static void main(String[] args) {
        LOGGER.debug("start server");
        new ClassPathXmlApplicationContext("spring.xml");
    }

}
