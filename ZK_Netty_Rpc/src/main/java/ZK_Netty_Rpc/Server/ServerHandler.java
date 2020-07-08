package ZK_Netty_Rpc.Server;

import ZK_Netty_Rpc.Info.MessageForNetty;
import ZK_Netty_Rpc.Info.MessagePrepare;
import ZK_Netty_Rpc.Info.SerializableAndUnSerializable;
import ZK_Netty_Rpc.Server.HandlerRpcMethod.ReceiveMessageAndExcute;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import ZK_Netty_Rpc.Extras.*;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ThreadPoolExecutor;

public class ServerHandler extends SimpleChannelInboundHandler<MessageForNetty> {
    private ThreadPoolExecutor threadPoolExecutor = MyThreadPool.getDefaultThreadPoolExcutor();

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("注册"+ctx.channel().remoteAddress().toString());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("取消注册"+ctx.channel().remoteAddress().toString());
    }

    /**
     * 此时这个msg应该是一个类似于字典的格式或是一个按照遗憾函数的排列的方式来进行
     * 如  protected void channelRead0(ChannelHandlerContext ctx, String msg) 像是这样，然后再在后面拥有的
     * 是每一个参数对应的具体的值。
     *并且msg也需要说明这是哪一个类得函数。
     * 所以总结下来就是需要类名，函数名，返回值,参数具体类型，参数值这六项。
     *
     * 这里是server端和client端沟通的接口
     * @param ctx 上下文
     * @param msg 消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx,MessageForNetty msg) throws Exception {
        System.out.println("///////////////////////");
        threadPoolExecutor.submit(new ReceiveMessageAndExcute(msg.getMessagePrepare(),ctx.channel()));
    }
}
