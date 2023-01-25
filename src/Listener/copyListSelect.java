package Listener;

import app.APP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class copyListSelect implements ActionListener {
    public static String [] sun;    //记录要复制文件的路径
    @Override
    public void actionPerformed(ActionEvent e) {
        int len= APP.num.length;
        copyListSelect.sun=new String[len];
        for(int i=0;i<APP.num.length;i++){
            copyListSelect.sun[i]=APP.defaultListModel1.get(APP.num[i]).toString();
            ShearListSelect.NaPAth=false;
        }
//        System.out.println("输出选择的文件：");
//        for (int i=0;i<copyListSelect.sun.length;i++){
//            System.out.println(copyListSelect.sun[i]);
//        }
    }
}
