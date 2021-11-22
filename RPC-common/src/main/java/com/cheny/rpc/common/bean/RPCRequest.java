package com.cheny.rpc.common.bean;

/**
 * @ClassName RPCRequest
 * @Description
 * @Author cheny
 * @Date 2021/11/21 12:44
 * @Version 1.0
 **/
public class RPCRequest {
    //请求id
    private String requestId;
    //接口名称
    private String interfaceName;
    //服务版本
    private String serviceVersion;
    //需要调用的方法名
    private String methodName;
    //方法参数类型
    private Class<?>[] parameterTypes;
    //方法参数
    private Object[] parameters;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
