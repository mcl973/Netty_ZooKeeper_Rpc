package ZK_Netty_Rpc.Extras.MyAutoCollectWork;

import ZK_Netty_Rpc.Info.argsInfo;
import ZK_Netty_Rpc.ZK.DistributeZK;
import org.apache.zookeeper.ZooKeeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 *  暴露接口的类
 */
public class ExportInterfaces extends AbstractBean {
    private static DistributeZK distributeZK;

    static {
        try {
            distributeZK = new DistributeZK();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将接口暴露给zk集群，也就是向zk集群注册
     * @throws Exception
     */
    public void export() throws Exception {
//        获取项目路径
        String basepath = argsInfo.basepath;
        String javaFileToString = "";
        /**
         * 获取接口的代码
         */
        for(Map.Entry<String,Object> maps:MyIoc.entrySet()){
            String s = maps.getKey().replaceAll("\\.", "/");
            String replace = s.replace(argsInfo.Projectname, "");
            javaFileToString = getJavaFileToString(basepath+replace+".java");
            javaFileToString+="#####";
        }
        distributeZK.CreateNodeAndGetPersistentNodeName(argsInfo.path+"/"+argsInfo.hostname+":"+argsInfo.port,javaFileToString);
    }

    /**
     *
     * @param path  路径
     * @return   返回原生的接口，不包含接口的包
     * @throws Exception
     */
    private String getJavaFileToString(String path) throws Exception {
        File file = new File(path);
        FileReader fileReader = new FileReader(file);
        char[] chs = new char[1024];
        int read = fileReader.read(chs);
        if (read>0){
            String string = new String(chs,0,chs.length);
            return string.split("#####")[1];
        }
        return null;
    }
}
