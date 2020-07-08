package ZK_Netty_Rpc.Service;

import ZK_Netty_Rpc.Service.show;

import java.util.ArrayList;

public class showImpl implements show{
    static  ArrayList<String> list = new ArrayList<>();
    static {
        for (int i = 0; i < 1000; i++) {
            list.add("今天天气正好加"+i);
        }
    }
    public String showmore() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s+"\n");
        }
        return stringBuilder.toString();
    }
    public int state() {
        return list.size();
    }

    @Override
    public void setname(String name) {
        System.out.println(name);
    }
}
