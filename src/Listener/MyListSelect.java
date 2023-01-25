package Listener;

import app.APP;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



public class MyListSelect implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel listSelectionModel=(ListSelectionModel) e.getSource();
            //路径位置的数组
        APP.num=listSelectionModel.getSelectedIndices();

        //测试是否能准确的输出选中的值
//        System.out.println("\n\n\n");
//        for(int i=0;i<APP.num.length;i++){
//            System.out.println("选择的数为："+APP.defaultListModel1.get(APP.num[i]).toString());
//            File f=new File(APP.defaultListModel1.get(APP.num[i]).toString());
//            System.out.println(f.getName());
//            System.out.println("E:\\FileManager.zip");   //输出E:\FileManager.zip  应为\\转译为\
//            System.out.println("选择的位置："+APP.num[i]);
//        }
    }
}