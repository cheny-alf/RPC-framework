package com.cheny.rpc.registry.zookeeper;

import com.cheny.rpc.common.util.CollectionUtil;
import com.cheny.rpc.register.ServiceDiscovery;
import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//基于zookeeper的服务发现接口实现
public class ZooKeeperServiceDiscovery implements ServiceDiscovery {
 
    //日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(ZooKeeperServiceDiscovery.class);
        
    private String zkAddress;//zookeeper地址
    
    public ZooKeeperServiceDiscovery(String zkAddress){
        this.zkAddress = zkAddress;
    }
    
    @Override
    public String discover(String serviceName) {
        //创建zookeeper客户端
        ZkClient zkClient = new ZkClient(zkAddress,Constant.ZK_SESSION_TIMEOUT,Constant.ZK_CONNECTION_TIMEOUT);
        LOGGER.debug("connect zookeeper");
        
        try {
            //获取service节点
            String servicePath = Constant.ZK_REGISTRY_PATH + "/" + serviceName;
            if(!zkClient.exists(servicePath)){
                //如果service不存在，则报错
                 throw new RuntimeException(String.format("can not find any service node on path: %s", servicePath));
            }
            //获取service节点下的所有子节点
            List<String> addressList = zkClient.getChildren(servicePath);
            if (CollectionUtil.isEmpty(addressList)) {
                 //如果service节点下的所有子节点为空，则报错
                 throw new RuntimeException(String.format("can not find any address node on path: %s", servicePath));
            }
            
            //获取address节点
            String address;
            int size = addressList.size();
            if(size==1){
                //如果只有一个地址，则获取改地址
                address = addressList.get(0);
                LOGGER.debug("get only address node: {}", address);
            }else{
                //如果存在多个地址，则随机获取改地址
                address = addressList.get(ThreadLocalRandom.current().nextInt(size));
                LOGGER.debug("get random address node: {}", address);
            }
            //获取address节点的值
            String addressPath = servicePath + "/" + address;
            return zkClient.readData(addressPath);
        } finally{
            zkClient.close();//关闭zookeeper连接
        }
    }
 
}
