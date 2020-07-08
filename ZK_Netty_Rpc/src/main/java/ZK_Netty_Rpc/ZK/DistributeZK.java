package ZK_Netty_Rpc.ZK;

import ZK_Netty_Rpc.Client.GetInterfaces.GetInterfaces;
import ZK_Netty_Rpc.Info.argsInfo;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//import java.nio.channels.SocketChannel;

/**
 * 一个对于zookeeper的api的封装
 */
public class DistributeZK implements Serializable{
//    为了序列化而设置的字段，不然会出现以下情况
        /**
         *java.io.InvalidClassException: com.*.*;   local class incompatible: stream classdesc serialVersionUID = 5590259895198052390, local class serialVersionUID = 7673969121092229700
         */
        private static final  long serialVersionUID = 1L;

        private transient CuratorFramework client;
        private String nodename = "";
        private transient SocketChannel socketChannel;
        private boolean isWatchSet = false;


        public void CreateNodeAndGetPersistentNodeName(String pathname,String str){
            if (client == null){
                reset();
            }
            try {
                nodename = client.create().creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).forPath(pathname,str.getBytes());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
//
//        /**
//         * 发送数据
//         * @param socketChannel socketchannel
//         * @param data  发送的数据
//         */
//        public void sendData(SocketChannel socketChannel,String data){
//            ByteBuffer byteBuffer = ByteBuffer.allocate(argsInfo.ByteBufferSize);
//            char[] chars = data.toCharArray();
//            for (char aChar : chars) {
//                byteBuffer.putChar(aChar);
//            }
//            byteBuffer.flip();
//            try {
//                socketChannel.write(byteBuffer);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        public void sendDataInNetty(SocketChannel socketChannel,String data){
//            socketChannel.writeAndFlush(data);
//        }
//        public void sendDataInNetty(String data){
//            socketChannel.writeAndFlush(data);
//        }
//
//        public void sendData(String data){
//            ByteBuffer byteBuffer = ByteBuffer.allocate(argsInfo.ByteBufferSize);
//            char[] chars = data.toCharArray();
//            for (char aChar : chars) {
//                byteBuffer.putChar(aChar);
//            }
//            byteBuffer.flip();
//            try {
//                socketChannel.write(byteBuffer);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        public void DeleteNodeByName(String nodename){
            if (client == null){
                reset();
            }
            try {
                client.delete().deletingChildrenIfNeeded().forPath(nodename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public Stat ModifyNodeDataByName(String nodename, String data){
            Stat stat = null;
            if (client == null){
                reset();
            }
            try {
                stat = client.setData().forPath(nodename, data.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stat;
        }

        /**
         *  查询数据
         * @param nodename  节点的名字
         */
        public String SelectNodeDataByName(String nodename)  {
            if (client == null){
                reset();
            }

            byte[] bytes = null;
            try {
                bytes = client.getData().forPath(nodename);
                return getString(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    /**
     * 获得节点的子节点的信息
     * @param nodename 节点名称
     * @return
     */
    public List<String> getNodeChildern(String nodename){
            if (client == null)
                reset();
            List<String> strings = null;
            try {
                strings = client.getChildren().forPath(nodename);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return strings;
        }
        /**
         * 设置子节点的监听器
         * @param nodename
         */
        public void setChildrenWatch(final String nodename){
            if (client == null){
                reset();
            }
            System.out.println("设置");
            PathChildrenCache pc = new PathChildrenCache(client,nodename,true);
            pc.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    List<String> strings;
                    String string = "";
                    switch(event.getType()){
                        case CHILD_REMOVED:
                            string = nodename + ",child removed,and left children are ";
                            break;
                        case CHILD_ADDED:
                            string = nodename+",child add";
                            break;
                        case CHILD_UPDATED:
                            string = nodename+",child add";
                            /**
                             * 数据跟新，那么就需要对应的去修改本地的文件
                             * 这里就需要进行区分了，如果实在服务器上的话，那么这里什么都不需要些
                             * 但是在client上就需要编写相应的代码了，如更新也饿无逻辑代码
                             * 1.拿到节点名称，更新接口和实现类文件
                             * 2.更新ioc容器
                             */
                            ChildData data = event.getData();
                            System.out.println(data.getPath()+"下的数据发生了改变，改变的内容是："+new String(data.getData()));
//                            以下是client才需要编写的代码，由于我将client和server卸载了同一个项目中，
//                            所以就有些尴尬了，所以以下代码将会被注释掉，在后面分开始在将其恢复

//                            new GetInterfaces();

                            break;
                        default:
                            string = nodename+"other things happened";
                            break;
                    }
                    strings = client.getChildren().forPath(nodename);
                    string = getString(strings.iterator(), string);
                }
            });
            try {
                pc.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void reset(){
            client = Rpc_ZKFactory.Curator_zookeeper_By_NewClient(argsInfo.connectString);
            client.start();
        }

        /**
         *  判断给定节点存不存在
         *  存在返回true，否则返回false；
         * @param pathname 节点名称
         * @return
         */
        public boolean isNodeExists(String pathname){
            boolean isexist = false;
            if (client == null)
                reset();
            try {
                Stat stat = client.checkExists().forPath(pathname);
                if (stat != null)
                    isexist = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isexist;
        }

        /**
         *  获取当前节点的状态
         */
        public Stat getStat(String pathname){
            Stat stat = null;
            if (client == null)
                reset();
            try {
                stat = client.checkExists().forPath(argsInfo.NettyServer_BackUp_Path+"/"+pathname);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stat;
        }

        /**
         * 获取分布式锁
         * @param path  需要抢占的节点
         * @return
         */
        public InterProcessMutex getDistributeLock(String path){
            if (client == null)
                reset();
            return new InterProcessMutex(client, path);
        }


        /**
         *
         * @throws IOException
         */
        public DistributeZK() throws IOException {
            reset();
        }



        public String getNodename() {
            return nodename;
        }

        public void setNodename(String nodename) {
            this.nodename = nodename;
        }

        public CuratorFramework getClient() {
            return client;
        }

        public void setClient(CuratorFramework client) {
            this.client = client;
        }

        public SocketChannel getSocketChannel() {
            return socketChannel;
        }

        public void setSocketChannel(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        public String getString(String string){
            return new String(string.getBytes(), CharsetUtil.UTF_8);
        }

        public byte[] getvalidbytes(byte[] bytes){
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                if (bytes[i] == 0)
                {
                    k = i;
                    break;
                }
            }
            byte[] bytes2 = new byte[k];
            System.arraycopy(bytes,0,bytes2,0,k);
            return bytes2;
        }

        public String getString(byte[] bytes){
            return new String(getvalidbytes(bytes),CharsetUtil.UTF_8);
        }
        public String getString(Iterator<String> iterator, String startdata){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(startdata);
            while (iterator.hasNext()){
                stringBuilder.append(iterator.next()).append("\n");
            }
            return stringBuilder.toString();
        }


        public boolean isWatchSet() {
            return isWatchSet;
        }

        public void setWatchSet(boolean watchSet) {
            isWatchSet = watchSet;
        }
    }

