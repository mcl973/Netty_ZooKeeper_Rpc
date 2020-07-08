package ZK_Netty_Rpc.Server;

import ZK_Netty_Rpc.Info.argsInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server{
    private EventLoopGroup boss,workers;
    private ServerBootstrap serverBootstrap;

    /**
     * 初始化主和工作组
     */
    public void GroupInit(){
        boss = new NioEventLoopGroup();
        workers = new NioEventLoopGroup();
    }

    /**
     * 初始化serverBootstrap
     */
    public void BootStrapIntit(){
        serverBootstrap = new ServerBootstrap()
                .group(boss,workers)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerAllHandler());
    }

    /**
     * 开启服务
     */
    public void StartServer(){
        ChannelFuture channelFuture = null;
        try {
            channelFuture = serverBootstrap.bind(argsInfo.hostname, argsInfo.port).sync();

            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workers.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    /**
     * 开启一个线程来处理这个业务
     */
    public  Server() {
        GroupInit();
        BootStrapIntit();
        StartServer();
    }
}
