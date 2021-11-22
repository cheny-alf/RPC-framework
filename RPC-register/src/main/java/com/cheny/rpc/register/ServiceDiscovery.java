package com.cheny.rpc.register;

/**
 * @ClassName ServiceDiscovery
 * @Description
 * @Author cheny
 * @Date 2021/11/22 19:47
 * @Version 1.0
 **/
public interface ServiceDiscovery {
    /**
     * 根据服务名称查找服务地址
     *
     * @param serviceName 服务名称
     * @return 服务地址
     * */
    String discover(String serviceName);
}
