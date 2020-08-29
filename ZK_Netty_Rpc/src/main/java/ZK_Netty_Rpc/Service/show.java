package ZK_Netty_Rpc.Service;

import ZK_Netty_Rpc.Extras.MyAnnonation.Export;

/**
 * 一个接口对应一个实现类，不可多个对用，主要是用来实现rpc的远程调用的
 * 上面的规范仅限于这个借口里的方法，其他的接口不做规范
 */
@Export
//下面的#####分隔符在argsInfo中有了明确的定义了，名为 FileSplit="#####"
//#####
public interface show {
    public  String showmore();
    public  int state();
    public void setname(String name);
}
