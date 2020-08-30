package ZK_Netty_Rpc.Server.DeEncoder.MySplitDeEncoder;

import ZK_Netty_Rpc.Info.MessageForNetty;
import ZK_Netty_Rpc.Info.MyDeEncoderProtocol;
import ZK_Netty_Rpc.Info.SerializableAndUnSerializable;
import ZK_Netty_Rpc.Info.argsInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * 自定义编码器
 * 将MessageForNetty序列化成字节数组
 */
public class MySplitEncoder extends MessageToByteEncoder<MyDeEncoderProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MyDeEncoderProtocol msg, ByteBuf out) throws Exception {
        byte[] bytes = SerializableAndUnSerializable.StringToByte(msg.getMessageForNetty());
        out.writeBytes(bytes);
        out.writeBytes(argsInfo.FrameSplit.getBytes());
    }
}
