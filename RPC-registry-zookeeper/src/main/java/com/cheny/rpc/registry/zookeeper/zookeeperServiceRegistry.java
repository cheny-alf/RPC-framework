package com.cheny.rpc.registry.zookeeper;

import com.cheny.rpc.register.ServiceRegistry;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName zookeeperServiceRegistry
 * @Description
 * @Author cheny
 * @Date 2021/11/22 20:27
 * @Version 1.0
 **/
public class zookeeperServiceRegistry implements ServiceRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(zookeeperServiceRegistry.class);

    private final ZkClient zkClient;

    public zookeeperServiceRegistry(String zkAddress) {
        zkClient = new ZkClient(zkAddress,Constant.ZK_SESSION_TIMEOUT,Constant.ZK_CONNECTION_TIMEOUT);
        LOGGER.debug("connect zookeeper");
    }

    @Override
    public void register(String serviceName, String serviceAddress){

        //创建registry节点(持久)
        String registryPath = Constant.ZK_REGISTRY_PATH;
        if(!zkClient.exists(registryPath)){
            //判断节点是否存在，节点不存在时，创建相关节点
            zkClient.createPersistent(registryPath);//创建持久化节点
            LOGGER.debug("create registry node:{}",registryPath);
        }
        //创建service节点(持久)
        String servicePath = registryPath + "/" + serviceName;
        if(!zkClient.exists(servicePath)){
            zkClient.createPersistent(servicePath);//创建持久化节点
            LOGGER.debug("create service node:{}",servicePath);
        }
        //创建address节点(临时)
        String addressPath  = servicePath + "/address-";
        String addressNode = zkClient.createEphemeralSequential(addressPath, serviceAddress);
        LOGGER.debug("create address node:{}",addressNode);


    }
}
