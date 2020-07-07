package ZK_Netty_Rpc.Client;

import ZK_Netty_Rpc.Client.GetInterfaces.GetInterfaces;
import ZK_Netty_Rpc.Info.argsInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.Callable;

@Deprecated
public class Client implements Runnable {
    private EventLoopGroup workers;
    private Bootstrap bootstrap;
    private String ip;
    private int port;
    private Channel channel;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void groupinit(){
        workers = new NioEventLoopGroup();
    }
    private void BootStrapInit(){
        bootstrap = new Bootstrap()
                .group(workers)
                .channel(NioSocketChannel.class)
                .handler(new ClientallHandlers());
    }
    public void startrun() {
        groupinit();
        BootStrapInit();
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect(ip,port).sync();
            System.out.println("////////////////////////////");
            channel = channelFuture.channel();
            channel.writeAndFlush("//////////////////sdnvdfnkdbf");
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workers.shutdownGracefully();
        }
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public void run() {
        startrun();
    }
}
