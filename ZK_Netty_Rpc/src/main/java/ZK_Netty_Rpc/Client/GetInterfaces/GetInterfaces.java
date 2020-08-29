package ZK_Netty_Rpc.Client.GetInterfaces;

import ZK_Netty_Rpc.Client.JdkAuto.GetNewProxyInstance;
import ZK_Netty_Rpc.Client.JdkAuto.MyInvokeHandler;
import ZK_Netty_Rpc.Info.argsInfo;
import ZK_Netty_Rpc.ZK.DistributeZK;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class GetInterfaces {
    private String basepath = argsInfo.path;
    private DistributeZK distributeZK = new DistributeZK();
    private CreateJavaFiles cjf = new CreateJavaFiles();
    private ChannelManager channelManager = new ChannelManager();

    /**
     * 获取节点下的所有的子节点，没有孙辈的节点
     * 然后获取所有子节点的信息
     * 1.创建接口文件
     * 2.创建java实现类文件
     * 3.jdk代理java实现类文件
     * 4.将其装载到容器中方便使用
     * @throws IOException
     */
    public GetInterfaces() throws Exception {
//        设置监听器
        distributeZK.setChildrenWatch(basepath);
//        获取节点的子节点列表
        List<String> nodeChildern = distributeZK.getNodeChildern(basepath);
//        挨个的获取每一个子节点下的数据，并将其变现为java文件，并加载到内存中
        for (String s : nodeChildern) {
            String pathname = argsInfo.path+"/"+s.trim();
            String data = distributeZK.SelectNodeDataByName(pathname);
//            分割数据，获取到最有用的数据
            String[] split = data.split(argsInfo.FileSplit);
//             开始获取java文件的url地址
            String javafileurl = argsInfo.basepath+argsInfo.ClientRpcMethodPath+"/";
//            没有就创建路径
            File file = new File(javafileurl);
            if (!file.exists())
                file.mkdirs();

            for (int i = 0; i < split.length; i++) {
                String str = split[i];
                /**
                 * 获取文件名
                 */
                String[] s1 = str.split("\\{")[0].split(" ");
                String classname = s1[s1.length-1];
//                添加包
                String packages = argsInfo.ClientRpcMethodPath.replaceAll("/", "\\.");
                String javafile = "package "+argsInfo.Projectname+packages+";\n"+str;
                /**
                 * 创建接口及其实现类
                 */
                cjf.CreateJavaFile(javafileurl+classname+".java",javafile,classname,javafileurl);
                /**
                 * 1.拿取实现类的权限定类名
                 * 2.通过类名反射创建对象
                 * 3.jdk动态代理这个类
                 * 4.将ip，port，代理类封装带一个IPPort中，再将这个加入到一个容器当中。
                 * 现在java文件和接口文件都已经建立好了，那么现在就可以开始进行动态代理了，隐藏底层的具体的业务逻辑了
                 */
//                1
                String javaimplname = argsInfo.Projectname+packages+"."+classname;
//                2
                Class<?> aClass = Class.forName(javaimplname);
//                Object o = aClass.newInstance();
//                3
                MyInvokeHandler myInvokeHandler = new MyInvokeHandler(aClass);
                Object newinstance = GetNewProxyInstance.newinstance(aClass, myInvokeHandler);
                /**
                 * 下面就是将这个装载到容器中其，方便进一步的使用。
                 * 这个容器不能和服务器端的容器混用。
                 */
//                4
                channelManager.SetAndGetSocket(s,classname,newinstance);
            }
        }
    }

}
