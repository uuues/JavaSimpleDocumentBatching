package app;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FileTree extends JTree {
    public TreePath mouseInPath;
    protected FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    public int sun = 1;

    public FileTree() {
        setRootVisible(false);
        //当带点击时展示他的子目录  将要展开时的事件处理
        addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {


                DefaultMutableTreeNode lastTreeNode = (DefaultMutableTreeNode) event.getPath().getLastPathComponent();
                lastTreeNode.removeAllChildren();

                //getUserObject返回与此节点关联的Object的值
                FileNode fileNode = (FileNode) lastTreeNode.getUserObject();


                if (!fileNode.isInit) {          //boolean默认值为false
                    File[] files;
                    if (sun == 1) {
                        files = File.listRoots();
                        sun++;
                    } else if (fileNode.isDummyRoot) { //
                        //得到根 系统上的所用根分区 window及返回Desktop
                        files = fileSystemView.getRoots();
                    } else {
                        //getFiles获取显示文件列表
                        files = fileSystemView.getFiles(((FileNode) lastTreeNode.getUserObject()).file, false);
                    }
                    for (int i = 0; i < files.length; i++) {


                        FileNode childFileNode = new FileNode(fileSystemView.getSystemDisplayName(files[i]), fileSystemView.getSystemIcon(files[i]), files[i], false);
                        DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childFileNode);
                        lastTreeNode.add(childTreeNode);
                    }
                    //通知模型节点发生变化
                    DefaultTreeModel treeModel1 = (DefaultTreeModel) getModel();
                    treeModel1.nodeStructureChanged(lastTreeNode);
                }
                //更改标识，避免重复加载
                fileNode.isInit = false;
            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {

            }
        });
        //鼠标监听事件public void mouseMoved(MouseEvent e)
        //鼠标光标移动到组件上但无按键按下时调用
        //鼠标移动上产生红底等等
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                TreePath path = getPathForLocation(e.getX(), e.getY());

                if (path != null) {
                    if (mouseInPath != null) {
                        Rectangle oldRect = getPathBounds(mouseInPath);
                        mouseInPath = path;
                        repaint(getPathBounds(path).union(oldRect));
                    } else {
                        mouseInPath = path;
                        Rectangle bounds = getPathBounds(mouseInPath);
                        repaint(bounds);
                    }
                } else if (mouseInPath != null) {
                    Rectangle oldRect = getPathBounds(mouseInPath);
                    mouseInPath = null;
                    repaint(oldRect);
                }
            }
        });

    }
}
