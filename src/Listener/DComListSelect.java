package Listener;
import app.APP;
import app.FileNode;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class DComListSelect implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String string=APP.defaultListModel1.get(0).toString();
        // FileNode fileNode=(FileNode) APP.defaultMutableTreeNode.getUserObject();
        FileSystemView fileSystemView=FileSystemView.getFileSystemView();


            File filePAth;
            String sPath;
            String sName;
            String PathFather= APP.defaultListModel1.get(0).toString();
            File file=new File(PathFather);
            String newFileName;
            for(int i=0;i<APP.num.length;i++){
                sPath=APP.defaultListModel1.get(APP.num[i]).toString();
                filePAth=new File(sPath);
                sName=filePAth.getName();
                sName=sName.substring(0,sName.length()-4);
                newFileName=PathFather+sName;
           //     System.out.println("sPath为："+sPath+"   newFileName为"+newFileName);
                File[]pa=file.listFiles();
                boolean have=true;
                for(File file1:pa){
           //         System.out.println("sName为:"+sName);
             //       System.out.println("file1.getName为："+file1.getName());
                    String sNt=file1.getName();
                    if(sName.equals(sNt)){
                        have=false;
                        break;
                    }
                }
                if(have!=true){
                    System.out.println(sPath+"此文件以被压缩");
                }else {
                    try {
                        decompressionFile(sPath, newFileName);
                        File filet=new File(newFileName);
                        FileNode childFileNode = new FileNode(fileSystemView.getSystemDisplayName(filet), fileSystemView.getSystemIcon(filet), filet, false);
                        DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childFileNode);
                        APP.defaultMutableTreeNode.add(childTreeNode);
                        DefaultTreeModel treeModel1 = (DefaultTreeModel)APP.fileTree.getModel();
                        treeModel1.nodeStructureChanged(APP.defaultMutableTreeNode);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }

            }


        System.out.println("粘贴成功");
    }
    public static void decompressionFile(String srcPath, String outPath) throws IOException {
        //简单判断解压路径是否合法
        if (!new File(srcPath).isDirectory()) {
            //判断输出路径是否合法
            File file=new File(outPath);
           file.mkdir();

          //  if (/*new File(outPath).isDirectory()*/blt) {
                if (!outPath.endsWith(File.separator)) {
                    outPath += File.separator;
                }
                //zip读取压缩文件
                FileInputStream fileInputStream = new FileInputStream(srcPath);
                ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);
                //解压文件
                decompressionFile(outPath, zipInputStream);
                //关闭流
                zipInputStream.close();
                fileInputStream.close();
            } else {
                throw new RuntimeException("输出路径不合法!");
            }
//        } else {
//            throw new RuntimeException("需要解压的文件不合法!");
//        }
    }

    private static void decompressionFile(String outPath, ZipInputStream inputStream) throws IOException {
        //读取一个目录
        ZipEntry nextEntry = inputStream.getNextEntry();
        //不为空进入循环
        while (nextEntry != null) {
            String name = nextEntry.getName();
            File file = new File(outPath + name);
            //如果是目录，创建目录
            if (name.endsWith("/")) {
                file.mkdir();
            } else {
                //文件则写入具体的路径中
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                int n = 0;
                byte[] bytes = new byte[1024];
                while (true) {
                    try {
                        if (!((n = inputStream.read(bytes)) != -1)) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    bufferedOutputStream.write(bytes, 0, n);
                }
                //关闭流
                bufferedOutputStream.close();
                fileOutputStream.close();
            }
            //关闭当前布姆
            inputStream.closeEntry();
            //读取下一个目录，作为循环条件
            nextEntry = inputStream.getNextEntry();
        }
    }
}
