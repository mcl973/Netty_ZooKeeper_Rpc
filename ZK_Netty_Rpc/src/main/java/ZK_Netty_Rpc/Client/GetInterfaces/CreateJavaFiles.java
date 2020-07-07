package ZK_Netty_Rpc.Client.GetInterfaces;

import ZK_Netty_Rpc.Info.TypeTranser;
import ZK_Netty_Rpc.Info.argsInfo;

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
            /**
             * 接口的实现类，只是简单地实现了一下主要是为了动态代理会方便
             */
            String replace = data.replace("interface", "class");
            String[] split = replace.split("\\{");
            String s1 = split[0];
            String replace1 = argsInfo.ClientRpcMethodPath.replaceAll("/", "\\.");

            s1 = s1.replace(classname,classname+argsInfo.aftername);
            String replace2 = s1.replace(";", "." + argsInfo.aftername + ";\n" + "import " + argsInfo.Projectname + replace1 + "." + classname + ";\n");
            s1 = replace2;
            s1 += "implements "+classname+" {";

            /**
             * 这里要区分一下了，看看返回值到底是什么类型的
             * s设置返回值，这一步必不可少。
             */
            String s2 = split[1];
            String s3 = "";
            String split2 = s2.split("}")[0];
            String[] split1 = split2.split(";");
            for (int i = 0; i < split1.length; i++) {
                String[] s = split1[i].split(" ");
                String[] ss = new String[s.length];
                int count=0;
                /**
                 * 剔除“”和\r\n只保留有效的字符串
                 */
                for (int i1 = 0; i1 < s.length; i1++) {
                    if (!s[i1].equals("\r\n")&&!s[i1].equals(""))
                        ss[count++] = s[i1];
                }
                /**
                 * 满足  public,returntype,methodname(....)
                 */
                if (count>=3) {
                    Class type = TypeTranser.getType(ss[1]);
                    s3 += split1[i] + "{ return " + TypeTranser.getRealObject(type.getName(), "0") + ";}";
                }
            }
            s3+="\n}";
            /**
             * 字符串的拼装
             */
            String Impleclass = s1+s3;
            /**
             * 开始创建接口的实现类
             */
            File file2 = new File(path+"/"+argsInfo.aftername);
            if(!file2.exists())
                file2.mkdirs();
            file2 = new File(path+"/"+argsInfo.aftername+"/"+classname+argsInfo.aftername+".java");
            fw = new FileWriter(file2);
            fw.write(Impleclass);
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
