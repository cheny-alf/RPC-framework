package com.cheny.rpc.common.codec;

import com.cheny.rpc.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName RPCencoder
 * @Description
 * @Author cheny
 * @Date 2021/11/21 15:25
 * @Version 1.0
 **/

public class RPCEncoder extends MessageToByteEncoder {
    private Class<?> genericClass;

    public RPCEncoder(Class<?> genericClass){
        this.genericClass = genericClass;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
        if(genericClass.isInstance(in)){//检测Object这个对象能不能被转化为这个类
            byte[] data = SerializationUtil.serialize(in);//将Object序列化为byte
            out.writeInt(data.length);//标记写入信息的长度
            out.writeBytes(data);//写入信息
        }
    }
}
