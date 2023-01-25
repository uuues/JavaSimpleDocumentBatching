package Listener;

import app.APP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
//剪切
public class ShearListSelect implements ActionListener {
    public static String[] ShearPath;
    public static boolean NaPAth=false;
    @Override
    public void actionPerformed(ActionEvent e) {
        int len=APP.num.length;
        ShearListSelect.ShearPath=new String[len];
        for (int i=0;i<len;i++){
            ShearListSelect.ShearPath[i]=APP.defaultListModel1.get(APP.num[i]).toString();
            ShearListSelect.NaPAth=true;
        }

    }
}
