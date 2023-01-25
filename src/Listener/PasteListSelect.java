package Listener;

import app.APP;
import app.FileNode;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PasteListSelect implements ActionListener {
    FileSystemView fileSystemView=FileSystemView.getFileSystemView();
    //        File filet=new File(newFileName);
//        FileNode childFileNode = new FileNode(fileSystemView.getSystemDisplayName(filet), fileSystemView.getSystemIcon(filet), filet, false);
//        DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childFileNode);
//        APP.defaultMutableTreeNode.add(childTreeNode);
//        DefaultTreeModel treeModel1 = (DefaultTreeModel)APP.fileTree.getModel();
//        treeModel1.nodeStructureChanged(APP.defaultMutableTreeNode);
    @Override
    public void actionPerformed(ActionEvent e) {
            String sPath1= APP.defaultListModel1.get(APP.num[0]).toString();

            //通过判断来确定时复制还是剪切 true为剪切
            if(!ShearListSelect.NaPAth){
                for(int i=0;i<copyListSelect.sun.length;i++){
                    String sPAth=copyListSelect.sun[i];
                    try {
                        File file2=new File(sPAth);
                        File file1=new File(sPath1);
                        copyPasteDir(file2,file1);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }else {
                for (int i = 0; i < ShearListSelect.ShearPath.length; i++) {
                    String sPAth = ShearListSelect.ShearPath[i];
                    try {
                        //file2 为要粘贴的  file1 为要粘贴到位置
                        File file2 = new File(sPAth);
                        File file1 = new File(sPath1);
                        copyPasteDir(file2, file1);
                        delBatchFile(file2);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
    }
    private static void copyPasteDir(File copyDir, File pasteDir) throws IOException {
        //异常判断 并在控制台抛出
        if (copyDir == null || pasteDir == null) {
            throw new RuntimeException("参数不能为空！");
        }
        if (pasteDir.isFile()) {
            throw new RuntimeException("目标路径不能是文件！");
        }
        //判断是文件还是目录
        //如果是文件
        if (copyDir.isFile()) {
            //获取要复制的文件名称，在目标路径中创建同名文件
            String name = copyDir.getName();
            File newFile = new File(pasteDir, name);
            //调用复制文件方法，参数为本文件和目标路径新创建的同名文件
            copyPasteFile(copyDir, newFile);
        }
        //如果是目录
        else if (copyDir.isDirectory()) {
            //获取要复制的文件夹名称，在目标路径中创建同名文件夹
            String name = copyDir.getName();
            File newFile = new File(pasteDir, name);
            //如果该文件夹不存在，创建该文件夹
            if (!newFile.exists()) {
                newFile.mkdirs();
            }
            //把本目录的所有文件或文件夹存储到File数组中
            File[] dir = copyDir.listFiles();
            //循环递归调用复制文件夹方法，直到复制完毕所有的File对象
            for (File childDirs : dir) {
                copyPasteDir(childDirs, newFile);
            }
        }
    }

    //复制文件方法
    private static void copyPasteFile(File copyFile, File pasteFile) throws IOException {
        //创建字节数输入流和字节输出流（字符流不可以复制图片视频等）
        FileInputStream fis = new FileInputStream(copyFile);
        FileOutputStream fos = new FileOutputStream(pasteFile);
        //实现文件复制就是循环边读边写的过程
        int data;
        while ((data = fis.read()) != -1) {
            fos.write(data);//边读边写
            fos.flush();//刷新流
        }
        //关闭流
        fis.close();
        fos.close();
    }
    public static void delBatchFile(File ff) {
        //遍历project下所所有文件和文件夹
        if(ff.isDirectory()) {
            File[] files = ff.listFiles();
            for (File file : files) {
                //如果是文件直接删除
                if (file.isFile()) {
                    System.out.println("删除了" + file.getName());
                    file.delete();
                }
                //如果是文件夹 则当成file对象调用本方法进如该文件夹执行
                if (file.isDirectory()) {
                    delBatchFile(file);
                }
            }
        }else{
            ff.delete();
        }
        //遍历完成删除空的文件夹
        ff.delete();
    }


}
