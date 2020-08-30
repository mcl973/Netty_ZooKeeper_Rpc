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
    /**
     * 为每一个类的每一个函数都设置一个SynchronousQueue，防止出现多线程情况下的函数拿去其他函数的结果的情况。
     */
    public ConcurrentHashMap<String,SynchronousQueue<Object>> mapqueue = new ConcurrentHashMap<>();

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
