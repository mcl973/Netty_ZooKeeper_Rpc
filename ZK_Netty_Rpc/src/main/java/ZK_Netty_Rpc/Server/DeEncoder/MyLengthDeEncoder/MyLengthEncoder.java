package ZK_Netty_Rpc.Server.DeEncoder.MyLengthDeEncoder;

import ZK_Netty_Rpc.Info.MessageForNetty;
import ZK_Netty_Rpc.Info.MyDeEncoderProtocol;
import ZK_Netty_Rpc.Info.SerializableAndUnSerializable;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyLengthEncoder  extends MessageToByteEncoder<MyDeEncoderProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MyDeEncoderProtocol myDeEncoderProtocol, ByteBuf byteBuf) throws Exception {
        byte[] bytes = SerializableAndUnSerializable.StringToByte(myDeEncoderProtocol.getMessageForNetty());
        int len = bytes.length;
        byteBuf.writeInt(len);
        byteBuf.writeBytes(bytes);
    }
}
