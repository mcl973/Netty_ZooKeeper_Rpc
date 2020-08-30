package ZK_Netty_Rpc.Client.ConnectToServerPool.DeEncoder.MyLengthDeEncoder;

import ZK_Netty_Rpc.Info.MessageForNetty;
import ZK_Netty_Rpc.Info.MyDeEncoderProtocol;
import ZK_Netty_Rpc.Info.SerializableAndUnSerializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyLengthDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int len = byteBuf.readInt();
        byte[] content = new byte[len];
        byteBuf.readBytes(content);
        MessageForNetty result = (MessageForNetty) SerializableAndUnSerializable.ByteToString(content);
        MyDeEncoderProtocol myDeEncoderProtocol = new MyDeEncoderProtocol(result);
        list.add(myDeEncoderProtocol);
    }
}
