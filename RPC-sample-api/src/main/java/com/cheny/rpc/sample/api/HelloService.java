package com.cheny.rpc.sample.api;

/**
 * @ClassName HelloService
 * @Description
 * @Author cheny
 * @Date 2021/11/23 15:02
 * @Version 1.0
 **/
public interface HelloService {

    String hello(String name);

    String hello(Person person);

}
