package com.cheny.rpc.sample.server;

import com.cheny.rpc.sample.api.HelloService;
import com.cheny.rpc.sample.api.Person;
import com.cheny.rpc.server.RPCService;

/**
 * @ClassName HelloServiceImpl
 * @Description
 * @Author cheny
 * @Date 2021/11/23 15:18
 * @Version 1.0
 **/
@RPCService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello" + name;
    }

    @Override
    public String hello(Person person) {
        return "hello" + person.getFirstName() + person.getLastName();
    }
}
