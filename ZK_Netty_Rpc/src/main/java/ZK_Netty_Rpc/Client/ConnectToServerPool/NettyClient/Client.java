package ZK_Netty_Rpc.Client.ConnectToServerPool.NettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.SynchronousQueue;

public class Client extends Thread{
    private EventLoopGroup workers;
    private Bootstrap bootstrap;
    private String ip;
    private int port;
    private Channel channel;
    public SynchronousQueue<Object> queue = new SynchronousQueue<>();

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
                .handler(new ClientallHandlers(queue));
    }
    public void startrun() {
        groupinit();
        BootStrapInit();
        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect(ip,port).sync();
            channel = channelFuture.channel();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public EventLoopGroup getWorkers() {
        return workers;
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public void run() {
        startrun();
    }

}
