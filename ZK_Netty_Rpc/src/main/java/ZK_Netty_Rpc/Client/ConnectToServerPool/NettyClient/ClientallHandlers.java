package ZK_Netty_Rpc.Client.ConnectToServerPool.NettyClient;

import ZK_Netty_Rpc.Client.ConnectToServerPool.DeEncoder.MyDecoder;
import ZK_Netty_Rpc.Client.ConnectToServerPool.DeEncoder.MyEncoder;
import ZK_Netty_Rpc.Info.argsInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

import java.util.concurrent.SynchronousQueue;

public class ClientallHandlers extends ChannelInitializer<SocketChannel> {
    SynchronousQueue<Object> queue;
    public ClientallHandlers(SynchronousQueue<Object> queue){
        this.queue = queue;
    }
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        pipeline.addLast(new FixedLengthFrameDecoder(argsInfo.ByteBufferSize));
        pipeline.addLast(new DelimiterBasedFrameDecoder(argsInfo.FrameSplitSize,argsInfo.BasedFrame));
        pipeline.addLast(new MyEncoder());
        pipeline.addLast(new MyDecoder());
        pipeline.addLast(new MyClientHander(queue));
    }
}
