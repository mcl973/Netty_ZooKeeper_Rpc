package ZK_Netty_Rpc.Extras.MyAutoCollectWork;

import ZK_Netty_Rpc.Extras.MyAnnonation.Export;
import ZK_Netty_Rpc.Info.argsInfo;

public class CreateBean extends AbstractBean implements CreateBeans{

    public void createBean(){
        for (String s : FileName) {
            if (s.contains(".class")){
                String classanme = s.split(".class")[0];
                try {
                    Class<?> aClass = Class.forName(classanme);
                    if (aClass.isAnnotationPresent(Export.class)){
                        Class<?> aClass1 = Class.forName(classanme + argsInfo.aftername);
                        MyIoc.put(aClass.getName(),aClass1.newInstance());
                        String[] split = aClass.getName().split("\\.");
                        ClassnameToReferenceName.put(split[split.length-1],aClass.getName());
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }else continue;
        }
    }
}
