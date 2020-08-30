package ZK_Netty_Rpc.Client.ConnectToServerPool.DeEncoder.MySplitDeEncoder;

import ZK_Netty_Rpc.Info.MessageForNetty;
import ZK_Netty_Rpc.Info.MyDeEncoderProtocol;
import ZK_Netty_Rpc.Info.SerializableAndUnSerializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
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
        if (messageForNetty!=null)
            out.add(myDeEncoderProtocol);
    }
}
