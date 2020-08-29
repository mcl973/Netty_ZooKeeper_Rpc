package ZK_Netty_Rpc.Client.GetInterfaces;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateJavaFiles {
    /**
     *  先创建接口文件，在已经接口文件创建java实现类的文件。
     *  1.将接口的interface改为class
     *  2.将接口的名字改为接口名+Impl
     *  3.添加implements 接口
     *  4.将所有的接口中的方法中的分号；变为{ return xxx；}的形式，这个xxx需要依靠方法的返回值来决定，所以还需要对于方法的分析。
     *  5.使用空格将方法分割，饭后去除无效的字符串，保留有效的字符串，如public String  getString(...)所以最终的返回值一定是第二个
     *  6.依据返回值确定返回类型
     *  7.依据返回类型书写返回值，按照String到其他类型的转换，依次转换。
     *  8.拼接字符串为一个完整的java文件
     *  9.添加Packetage，接口和java文件都要。
     *  10.java文件添加import，将接口导入进来。
     *  11.写入文件。
     * @param filename  接口的文件名
     * @param data   接口数据
     * @param classname 接口的名字
     * @param path 接口所在的路径
     * @throws IOException
     */
    public void CreateJavaFile(String filename,String data,String classname,String path) throws IOException {
        File file = new File(filename);
        if (!file.exists())
            file.createNewFile();
        FileWriter fw=  null;
        try {
//            创建接口文件
            fw = new FileWriter(file);
            fw.write(data);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
