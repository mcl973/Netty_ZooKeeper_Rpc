package ZK_Netty_Rpc.Server.DeEncoder.MySplitDeEncoder;

import ZK_Netty_Rpc.Info.MessageForNetty;
import ZK_Netty_Rpc.Info.MyDeEncoderProtocol;
import ZK_Netty_Rpc.Info.SerializableAndUnSerializable;
import com.sun.org.apache.xml.internal.serializer.utils.Utils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;


import java.util.List;

/**
 * 自定义解码器
 * 将字节数组解码成MessageForNetty类
 */
public class MySplitDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        byte[] array = new byte[msg.readableBytes()];
        msg.readBytes(array);
        MessageForNetty messageForNetty = (MessageForNetty)SerializableAndUnSerializable.ByteToString(array);
        MyDeEncoderProtocol myDeEncoderProtocol = new MyDeEncoderProtocol(messageForNetty);
        out.add(myDeEncoderProtocol);
    }
}
