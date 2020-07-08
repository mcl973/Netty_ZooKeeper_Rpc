package ZK_Netty_Rpc.Server;

import ZK_Netty_Rpc.Server.DeEncoder.MyDecoder;
import ZK_Netty_Rpc.Server.DeEncoder.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerAllHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyEncoder());
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new ServerHandler());
    }
}
