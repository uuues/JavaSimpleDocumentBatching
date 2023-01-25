package Listener;

import app.APP;

import javax.swing.tree.TreePath;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DeleteListSelect implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i< APP.num.length; i++){
            String path=APP.defaultListModel1.get(APP.num[i]).toString();
            File ff=new File(path);
            delBatchFile(ff);
            TreePath treePath=new TreePath(path);
            APP.fileTree.removeSelectionPath(treePath);
        }
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
