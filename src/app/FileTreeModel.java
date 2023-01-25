package app;

import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.io.File;

//维护树JTree使用DefaultTreeModel，对节点进行增删、拖拽和展开操作，以及跨平台文件拖拽的方法详细介绍
//DefaultMutableTreeNode(Object userObject)
public class FileTreeModel extends DefaultTreeModel {
    public FileTreeModel(TreeNode root) {
        super(root);

        //files.length为1，files[0].getName为Desktop
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File[] files = fileSystemView.getRoots();

        //files.length为1；files[0]和files[0].toString()为C:\Users\雨\Desktop
        for (int i = 0; i < files.length; i++) {
            //fileSystemView.getSystemDisplayName(files[0])为Desktop
            FileNode childFileNode = new FileNode(fileSystemView.getSystemDisplayName(files[i]), fileSystemView.getSystemIcon(files[i]), files[i], false);

            DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childFileNode);

            ((DefaultMutableTreeNode) root).add(childTreeNode);
        }
    }

    @Override
    //判断是否有子节点 没有则返回true
    public boolean isLeaf(Object node) {
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
        //getUserObject 返回此节点用户存储的信息
        FileNode fileNode = (FileNode) treeNode.getUserObject();

        if (fileNode.isDummyRoot) return false;
        //isFile()测试此抽象路径名表示的文件是否是一个标准文件 ,当且仅当表示此抽象路径名的文件是一个文件该方法返回true，否则该方法返回false。
        return fileNode.file.isFile();
    }
}
