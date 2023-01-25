package Listener;

import app.APP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

public class Search implements ActionListener {

    JTextField jTf;
    public Search(JTextField jTf){
        this.jTf=jTf;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int sun=0;
        String s=this.jTf.getText();
        String s1="(.*)"+s+"(.*)";
//        System.out.println(s);
//        System.out.println("已进入");
//        getName().matches(s) 正则表达式
//        System.out.println("s1为"+s1);
        String nu=APP.defaultListModel1.get(0).toString();
        File file=new File(nu);
      //   System.out.println("nu为"+nu);
        APP.defaultListModel1.removeAllElements();
        APP.defaultListModel1.add(sun++,nu);
        //listFiles是获取该目录下所有文件和目录的绝对路径
        File[] files=file.listFiles();
        Queue<File>queue=new LinkedList<>();
//        APP.files=files;
        for(File f:files){
            //判断是否时文件夹且不是隐藏文件
            if(f.isDirectory()&&!f.isHidden()){
                queue.offer(f);
            }else{
                if(f.getName().matches(s1)){
                        //APP.defaultListModel1.add(sun++,"<html>"+f.getName()+"<br/>"+f+"<html/>");
                    APP.defaultListModel1.add(sun++,f.toString());
                }
            }
        }
        while(queue.peek()!=null){
            File fQueue=queue.poll();

            if(fQueue.getName().matches(s1)&&!fQueue.isHidden()){
               // APP.defaultListModel1.add(sun++,"<html>"+fQueue.getName()+"<br/>"+fQueue+"<html/>");
                APP.defaultListModel1.add(sun++,fQueue.toString());
            }
            File[] files1=fQueue.listFiles();
            if(files1==null){
                continue;
            }

            //System.out.println("fQueue="+fQueue);
            for(File file1:files1){
            //    System.out.println("file1为"+f);
                if(file1.isDirectory()&&!file1.isHidden()){
                    queue.offer(file1);
                }
              //  System.out.println("files="+files1[sun]);
              //  System.out.println("file1="+file1);
                if(file1.getName().matches(s1)&&!file1.isHidden()){
                  //  System.out.println("file1.getName()为"+file1.getName());
                  //  APP.defaultListModel1.add(sun++,"<html>"+file1.getName()+"<br/>"+file1+"<html/>");
                    APP.defaultListModel1.add(sun++,file1.toString());
                }
            }
        }
     //   System.out.println("已退出");
    }
}
