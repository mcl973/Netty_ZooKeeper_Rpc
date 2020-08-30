package ZK_Netty_Rpc.Client.ConnectToServerPool.NettyClient;

import ZK_Netty_Rpc.Client.GetInterfaces.ChannelManager;
import ZK_Netty_Rpc.Client.GetInterfaces.IPPort;
import ZK_Netty_Rpc.Info.MessageForNetty;
import ZK_Netty_Rpc.Info.MessageResult;
import ZK_Netty_Rpc.Info.MyDeEncoderProtocol;
import ZK_Netty_Rpc.Info.SerializableAndUnSerializable;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;

public class MyClientHander extends SimpleChannelInboundHandler<MyDeEncoderProtocol> {
    ConcurrentHashMap<String,SynchronousQueue<Object>> queue;
    public MyClientHander(ConcurrentHashMap<String,SynchronousQueue<Object>> queue){
        this.queue = queue;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,MyDeEncoderProtocol msg) throws Exception {
        MessageResult mr = msg.getMessageForNetty().getMessageResult();
        String key = (mr.getClassname()+"."+mr.getMethodname()).trim();
        if (queue.containsKey(key))
            queue.get(key).put(mr.getObject());
        else {
            SynchronousQueue<Object> synchronousQueue = new SynchronousQueue<>();
            synchronousQueue.put(mr.getObject());
            queue.put(key,synchronousQueue);
        }
    }
}
