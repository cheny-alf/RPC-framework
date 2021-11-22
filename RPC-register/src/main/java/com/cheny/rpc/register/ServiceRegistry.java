package com.cheny.rpc.register;

/**
 * @ClassName ServerRegistry
 * @Description
 * @Author cheny
 * @Date 2021/11/22 19:45
 * @Version 1.0
 **/
public interface ServiceRegistry  {
    /**
     * 注册服务名称与服务地址
     *
     * @param serviceName    服务名称
     * @param serviceAddress 服务地址
     * */
    void register(String serviceName,String serviceAddress);

}
