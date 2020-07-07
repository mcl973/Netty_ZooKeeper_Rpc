package ZK_Netty_Rpc.Client;

import ZK_Netty_Rpc.Client.GetInterfaces.ChannelManager;
import ZK_Netty_Rpc.Client.GetInterfaces.IPPort;
import ZK_Netty_Rpc.Info.MessageForNetty;
import ZK_Netty_Rpc.Info.MessageResult;
import ZK_Netty_Rpc.Info.SerializableAndUnSerializable;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ConcurrentHashMap;
@Deprecated
public class MyClientHander extends SimpleChannelInboundHandler<MessageForNetty> {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,MessageForNetty msg) throws Exception {
        MessageResult mr = msg.getMessageResult();
        System.out.println(mr.toString());
        System.out.println(mr.toString());
    }
}
