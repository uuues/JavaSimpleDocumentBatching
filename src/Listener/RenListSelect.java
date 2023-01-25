package Listener;

import app.APP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


//重命名
public class RenListSelect implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
         String str01 = JOptionPane.showInputDialog(null, "请输入要修改的文字");
         String str02 = JOptionPane.showInputDialog(null, "请输入修改的文字");

         String newString =str02;//新字符串,如果是去掉前缀后缀就留空，否则写上需要替换的字符串
         String oldString =str01;//要被替换的字符串
         String der= APP.defaultListModel1.get(APP.num[0]).toString();
                recursiveTraversalFolder(der,oldString,newString);
    }
    public static void recursiveTraversalFolder(String path ,String oldString,String newString) {
        File folder = new File(path);
        if (folder.exists()) {
            File[] fileArr = folder.listFiles();
            if (null == fileArr || fileArr.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                File newDir = null;//文件所在文件夹路径+新文件名
                String newName = "";//新文件名
                String fileName = null;//旧文件名
                File parentPath = new File("");//文件所在父级路径
                for (File file : fileArr) {
                    if (file.isDirectory()) {//是文件夹，继续递归，如果需要重命名文件夹，这里可以做处理
                        System.out.println("文件夹:" + file.getAbsolutePath() + "，继续递归！");
                        recursiveTraversalFolder(file.getAbsolutePath(),oldString,newString);
                    } else {//是文件，判断是否需要重命名
                        fileName = file.getName();
                        parentPath = file.getParentFile();
                        if (fileName.contains(oldString)) {//文件名包含需要被替换的字符串
                            newName = fileName.replaceAll(oldString, newString);//新名字
                            newDir = new File(parentPath + "/" + newName);//文件所在文件夹路径+新文件名
                            file.renameTo(newDir);//重命名
                            System.out.println("修改后：" + newDir);
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
}
