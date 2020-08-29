package ZK_Netty_Rpc.Server;

import ZK_Netty_Rpc.Info.argsInfo;
import ZK_Netty_Rpc.Server.DeEncoder.MyDecoder;
import ZK_Netty_Rpc.Server.DeEncoder.MyEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

public class ServerAllHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new DelimiterBasedFrameDecoder(argsInfo.FrameSplitSize,argsInfo.BasedFrame));
        pipeline.addLast(new MyEncoder());
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new ServerHandler());
    }
}
