package ZK_Netty_Rpc.Info;

import ZK_Netty_Rpc.App;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;

public class argsInfo {
    public static String Projectname = "";
    static {
        Package aPackage = App.class.getPackage();
        Projectname = aPackage.getName().split("\\.")[0];
    }
    //获取项目的绝对地址
    public static String basepath = Class.class.getResource("/").getPath().split("target")[0]+"/src/main/java/"+Projectname+"/";

    public static String ClientRpcMethodPath = "/Client/RpcInterfaces";
    //    这个是建立本地的通信服务，用于和python的时间的通知
    public static int port = 8888;
    public static String hostname = "localhost";
    //    nettyServer之间的热备
    public final static String NettyServer_BackUp_Path = "/NettyServer_Ha/NettyServer_BackUp_Path";

    //    zookeeper集群的连接ip+port
//    public static String connectString = "node2:2181,node3:2181,node4:2181";
    public static String connectString = "192.168.127.5:2181,192.168.127.6:2181,192.168.127.7:2181";
    //    需要创建的节点
    public static String path = "/ZK_Netty_Rpc";
//    这个而是用于接口文件的分割的，如将本地的接口文件组合使用这个并上传到zookeeper上，将数据从zookeeper上下载到本地使用这个
//    分隔符来划分不同的接口文件
    public static String FileSplit = "#####";
    //  设置分割大小,默认是1M,这个值需要根据自己的业务需求来设定上限值。
    final public static int FrameSplitSize = 1024*1024;
//    这里不使用网上经常使用的\t的原因是可能类的序列化成的字节数组中也包含了\t，所以这里使用的是自定义的分隔符即五个#
    public static String FrameSplit = "#####";
    public static ByteBuf BasedFrame = Unpooled.copiedBuffer(FrameSplit.getBytes());

//    线程池的默认的线程数
    /**
     *  根据公式：线程数 = ncpu*（1+w/c），其中ncpu是指的是cpu的核数，w指的是等待是事件wait time，c指的是计算compute。
     *  如果是计算密集型的那么这里的就是c>>w,那么w/c就是接近于0，那么线程数就是ncpu。
     *  如果是io密集型的那么这里的就是w>>c,那么w/c就是大于1 的那么线程数可以近似为2*ncpu。
     */
    public static final int Default_Thread_Number = Runtime.getRuntime().availableProcessors()*2;

    public static final String aftername = "Impl";
    
    @Deprecated
    public static int maxReadSize = 1024*1024;

/**
 * //    public static int port2 = 8889;
 *     //默认的线程池的线程数，使用的是netty的参数，默认是cpu核数的2倍
 * //    protected static final int Default_Thread_Number = Math.max(1, SystemPropertyUtil.getInt("io.netty.eventLoopThreads", NettyRuntime.availableProcessors()));
 * //    统计了一下，总共的线程一共三个，其中两个是固定不变的，另一个是可变的
 *     public static final int Default_Thread_Number = 5;
 *     //    Object和byte[]互转时byte数组的大小
 *     public final static int ObjectToByteSize = 1024;
 *     //    hdfs的块大小
 *     public final static int BlockSize = 1024*1024;
 *     //    备份的数量
 *     public final static short BlockBackUpNum = 2;
 *
 *     //    这个路径下专门用来存放节点的关于client和zookeeper连接的对象的数据
 *     public final static String HdfsSavePath = "usr/mao/ObjectToBytes/";
 *
 *     //    针对于hdfs的操作，获取数据和保存数据
 *     public final static int GETOBJECT = 0;
 *     public final static int SAVEOBJECT = 1;
 *     public final static int SAVESESSIONID = 2;
 *     public final static int GETSESSIONID = 3;
 *     //    hdfs存储correct_seq的路径
 *     public final static String correct_seq_path = "usr/mao/correct_seq/";
 *
 *     //    创建节点
 *     final public static int CREATE = 0;
 *     //    删除节点
 *     final public static int DELETE = 1;
 *     //    修改节点
 *     final public static int MODIFY = 2;
 *     //    查看节点数据
 *     final public static int SELECT = 3;
 *     //    返回同步的数据
 *     final public static int SYNDATA = 4;
 *     //    保存同步的数据，以HdfsSavePath为前缀，seq为后缀撞见同步的数据文件
 *     final public static int SAVESYNDATA = 5;
 *
 *     //    设置节点监听器
 *     final public static int WATCHNODE = 11;
 *     //    设置路径或是子节点监听器
 *     final public static int WATCHCHILDREN = 12;
 *     //    包含了节点和子节点的监听器
 *     final public static int WATCHTREE = 13;
 *         //    计时器的时间大小,5秒
 *     final public static int keeptime = 5000;
 *     //    一个所有的client节点都会监听的zookeeper节点
 *     final public static String ClientIsDown = "/NettyServer_Ha/ClientIsDown";
 *     //    用于通知各个client节点数据以同步，可以将数据持久化到本地的磁盘中
 *     final public static String PhoneEachClient = "/NettyServer_Ha/PhoneEachClient";
 */
}
