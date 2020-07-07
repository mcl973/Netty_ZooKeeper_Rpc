package ZK_Netty_Rpc.Client;

import ZK_Netty_Rpc.Info.argsInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 *  客户端的channel选择这个是有原因的。
 *  1.netty开启麻烦，容易造成吃层序的死锁
 *  2.需要平凡的开启关闭，netty不太适合
 *  3.需要为每一个服务器的接口配置一个接口
 *  4.netty对于函数的返回值不能够很好的适配，需要借助其他的工具来实现数据的传递如队列，但是使用bio就可以很好地完成这个问题、
 *  5.所以综上所述选择bio最合适。，并且实测效果还不错。
 */
public class BioSocket {
    private Socket socket;
    private String host;
    private int port;
    private OutputStream out;
    private InputStream in;

    public BioSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 建立连接
     */
    public void connect(){
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host,port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送数据
     * @param data  数据
     * @throws IOException
     */
    public void sendData(byte[] data) throws IOException {
        out = socket.getOutputStream();
        out.write(data);
        out.flush();
    }

    /**
     *  读取数据
     * @return  返回数据
     */
    public byte[] receiveData(){
        byte[] bytes = null;
//            由于read的阻塞性，所以最好一次性将数据读取完
        byte[] date = new byte[argsInfo.maxReadSize];
        int read = 0;
        try {
            /**
             * 1.获取输入流
             * 2.读物数据并返回
             * 3.将独到的数据放入一个新的数组中。
             * 4.返回
             */
            in = socket.getInputStream();
            read = in.read(date);
            ArrayList<Byte> lists= new ArrayList<>();
            if (read>0){
                bytes = new byte[read];
                System.arraycopy(date,0,bytes,0,read);
                date = null;
                return bytes;
            }
        } catch (IOException e) {
            System.out.println("异常，退出");
        }finally{
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
}
