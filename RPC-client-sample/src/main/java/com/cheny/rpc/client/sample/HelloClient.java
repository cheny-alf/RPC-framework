package com.cheny.rpc.client.sample;

import com.cheny.rpc.client.RPCProxy;
import com.cheny.rpc.sample.api.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName HelloClient
 * @Description
 * @Author cheny
 * @Date 2021/11/23 15:42
 * @Version 1.0
 **/
public class HelloClient {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        RPCProxy proxy = context.getBean(RPCProxy.class);

        HelloService helloservice = proxy.create(HelloService.class);
        String world = helloservice.hello("world");
        System.out.println(world);

        HelloService new_world = proxy.create(HelloService.class, "new world");
        String result = new_world.hello("世界");
        System.out.println(result);


        System.exit(0);
    }

}
