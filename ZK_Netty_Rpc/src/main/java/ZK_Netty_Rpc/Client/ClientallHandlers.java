package ZK_Netty_Rpc.Client;

import ZK_Netty_Rpc.Server.DeEncoder.MyDecoder;
import ZK_Netty_Rpc.Server.DeEncoder.MyEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
@Deprecated
public class ClientallHandlers extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyEncoder());
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new MyClientHander());
    }
}
