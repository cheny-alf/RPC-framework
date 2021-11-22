package com.cheny.rpc.registry.zookeeper;

/**
 * @ClassName Constant
 * @Description
 * @Author cheny
 * @Date 2021/11/22 20:24
 * @Version 1.0
 **/
public class Constant {

    static final int ZK_SESSION_TIMEOUT = 5000;//会话超时时间
    static final int ZK_CONNECTION_TIMEOUT = 1000;//连接超时时间

    static String ZK_REGISTRY_PATH = "/registry";//注册地址

}
