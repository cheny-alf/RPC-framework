package com.cheny.rpc.common.codec;

import com.cheny.rpc.common.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName RPCDecode
 * @Description
 * @Author cheny
 * @Date 2021/11/21 15:21
 * @Version 1.0
 **/
public class RPCDecoder extends ByteToMessageDecoder {

    private Class<?> genericClass;

    public RPCDecoder(Class<?> genericClass){
        this.genericClass = genericClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if(in.readableBytes() < 4){
            /*ByteBuf分为两部分，一部分为消息头head，一部分为消息体body，消息头head中保存的是可读信息的长度(一个int类型的值)，
             * 当可读信息小于4byte(一个int类型的值)时，认为消息头中没有刻可读信息的长度，
             * 即消息体body长度不存在，或者ByteBuf结构异常。放弃解码*/
            return;
        }
        in.markReaderIndex();//标记一下当前的读索引的位置
        int dataLength = in.readInt();//获取信息的总长度，这里需要注意的是ByteBuf的readInt()方法会让他的readIndex增加4
        if(in.readableBytes() < dataLength){
            //由于ByteBuf的readInt()方法会让他的readIndex增加4，此时可读区域就会比原来小4，这个时候就需要将可读索引恢复到4之前的位置
            //如果可读信息的长度小于信息的总长度，
            // 还原读索引的位置(resetReaderIndex这个方法是配合markReaderIndex使用的。把readIndex重置到mark的地方)
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        out.add(SerializationUtil.deserialize(data,genericClass));

    }
}
