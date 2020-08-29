package ZK_Netty_Rpc.Extras.MyAutoCollectWork;

import java.io.File;
import java.net.FileNameMap;
import java.net.URL;

public class ScannerFile extends AbstractBean implements Scanner{
    //扫描整个项目，将所有的文件都拉取到filesname列表中
    public void ScannerClassFile(String path){
        //获得路径的url
        URL resource = this.getClass().getClassLoader().getResource(path.replaceAll("\\.", "/"));
        if (resource==null)
            return;
        String filename = resource.getFile();
        File file = new File(filename);
        if (file.isDirectory()){
            String[] filenames = file.list();
            if (filenames!=null) {
                for (String s : filenames) {
                    File file1 = new File(filename +"\\"+ s);
                    if (file1.isDirectory()) {
                        //递归查询
                        if (path.equals(""))
                            ScannerClassFile(s);
                        else
                            ScannerClassFile(path + "." + s);
                    } else {
                        if (path.equals(""))
                            FileName.add(s);
                        else
                            FileName.add(path + "." + s);
                    }
                }
            }
        }
    }

}
