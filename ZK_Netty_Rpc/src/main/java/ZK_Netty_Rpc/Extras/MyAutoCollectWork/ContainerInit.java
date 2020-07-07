package ZK_Netty_Rpc.Extras.MyAutoCollectWork;


public class ContainerInit {
    public ContainerInit() throws Exception {
        new ScannerFile().ScannerClassFile("");
        new CreateBean().createBean();
        new ExportInterfaces().export();
    }
}
