package app;

import Listener.DComListSelect;
import Listener.MyListSelect;
import Listener.Search;
import Listener.compressListSelect;
import Listener.copyListSelect;
import Listener.PasteListSelect;
import Listener.DeleteListSelect;
import Listener.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.*;
import java.awt.*;
import java.io.File;


public class APP {
    public static File[] files;
    public static DefaultListModel defaultListModel1 = new DefaultListModel<>();
    public static int[] num;
    public static DefaultMutableTreeNode defaultMutableTreeNode;
    public static FileTree fileTree = new FileTree();
    public static DefaultMutableTreeNode defaultMutableTreeNode1;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("简易文件的批处理");
        jFrame.setLayout(new BorderLayout());
        Container container;
        Dimension Di1 = new Dimension(250, 100);

        JPanel jPanel = new JPanel(new GridLayout(1, 2));
        JPanel jPanel1 = new JPanel(new GridLayout(1, 8));
        JPanel jPanel2 = new JPanel(new BorderLayout());
        JPanel jPanel3 = new JPanel(new FlowLayout());

        //设置搜索框的大小
        JTextField jTf = new JTextField();
        jTf.setPreferredSize(new Dimension(352, 28));

        //将按钮添加到j1,j1添加在jFrame的BorDerLayout.NORTH也就是上面
        JButton jButton7 = new JButton("搜索");
        JButton jButton = new JButton("复制");
        JButton jButton1 = new JButton("粘贴");
        JButton jButton2 = new JButton("删除");
        JButton jButton3 = new JButton("重命名");
        JButton jButton4 = new JButton("剪切");
        JButton jButton5 = new JButton("合并");
        JButton jButton6 = new JButton("压缩");
        JButton jButton8 = new JButton("解压");
        jPanel1.add(jButton);
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        jPanel1.add(jButton3);
        jPanel1.add(jButton4);
        jPanel1.add(jButton5);
        jPanel1.add(jButton6);
        jPanel1.add(jButton8);

        PasteListSelect pasteListSelect=new PasteListSelect();
        jButton1.addActionListener(pasteListSelect);

        DeleteListSelect deleteListSelect=new DeleteListSelect();
        jButton2.addActionListener(deleteListSelect);

        RenListSelect renListSelect=new RenListSelect();
        jButton3.addActionListener(renListSelect);

        ShearListSelect shearListSelect =new ShearListSelect();
        jButton4.addActionListener(shearListSelect);

        CombineListSelect combineListSelect=new CombineListSelect();
        jButton5.addActionListener(combineListSelect);

        DComListSelect deComListSelect = new DComListSelect();
        jButton8.addActionListener(deComListSelect);

        compressListSelect compressListSelect=new compressListSelect();
        jButton6.addActionListener(compressListSelect);

        copyListSelect copyListSelect=new copyListSelect();
        jButton.addActionListener(copyListSelect);
        //  FileTree fileTree=new FileTree();
        FileTreeModel model = new FileTreeModel(new DefaultMutableTreeNode(new FileNode("root", null, null, true)));
        APP.fileTree.setModel(model);
        APP.fileTree.setCellRenderer(new FileTreeRenderer());
        //创建两个带滚动条的面板分别显示树和搜索内容


        //   DefaultListModel defaultListModel=new DefaultListModel<>();

        JList jList = new JList(APP.defaultListModel1);
        jList.setCellRenderer(new MyListCell());
        jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);     //可以选择不相邻的几项

        ListSelectionModel listSelectionModel = jList.getSelectionModel();
        listSelectionModel.addListSelectionListener(new MyListSelect());


        JScrollPane Jsp = new JScrollPane(APP.fileTree);
        JScrollPane Jsp1 = new JScrollPane(jList);
        Jsp.setSize(Di1);
        Jsp.setMaximumSize(Di1);
        Jsp.setPreferredSize(Di1);
        Jsp1.setSize(Di1);
        Jsp1.setMaximumSize(Di1);
        Jsp1.setPreferredSize(Di1);
        //
        Jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        Jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Jsp1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        Jsp1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        //任意多选
        APP.fileTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        APP.fileTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {

                //每次使用清空
                APP.defaultListModel1.removeAllElements();
                //  System.out.println("进入索引"+"           "+APP.defaultListModel1.capacity());
                int s1 = 0;
                //记录点击根时file当点击根时不反应
                FileSystemView fileSystemView1 = FileSystemView.getFileSystemView();
                File[] files1 = fileSystemView1.getRoots();
                FileNode childFileNode = new FileNode(fileSystemView1.getSystemDisplayName(files1[0]), fileSystemView1.getSystemIcon(files1[0]), files1[0], false);
                DefaultMutableTreeNode childTreeNode = new DefaultMutableTreeNode(childFileNode);

                File[] files2;
                files2 = fileSystemView1.getFiles(((FileNode) childTreeNode.getUserObject()).file, false);


                DefaultMutableTreeNode lastTreeNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                APP.defaultMutableTreeNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
                //返回文件系统视图
                FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                File[] files;
                //获取显示（即未隐藏）文件列表  getUserObject返回与此节点关联的Object值   useFileHiding为false包含隐藏文件
                files = fileSystemView.getFiles(((FileNode) lastTreeNode.getUserObject()).file, false);

                //获取所点击的地址
                File file = ((FileNode) lastTreeNode.getUserObject()).file;

                //   s[0] =file.toString();
                //  System.out.println(s[0]);

                if (files.length != 0) {
                    String sk = files[0].toString();
                    String sk1 = files2[0].toString();

                    if (!sk1.equals(sk)) {
                        APP.defaultListModel1.add(s1++, file.toString());
                        // System.out.println(APP.defaultListModel1.get(0)+"          "+APP.defaultListModel1.capacity());
                        for (File file1 : files) {
                            // String sin = file1.getName();
                            // System.out.println(file1.toString());
                            APP.defaultListModel1.add(s1++, file1.toString());
                        }
                    }
                }
            }
            //         }
        });

        String s = "C:\\Users\\雨\\Desktop";

        Search search = new Search(jTf);
        jButton7.addActionListener(search);

        container = jFrame.getContentPane();
        jPanel.add(Jsp, BorderLayout.WEST);
        jPanel3.add(jTf);
        jPanel3.add(jButton7);
        jPanel2.add(jPanel3, BorderLayout.NORTH);
        jPanel2.add(Jsp1, BorderLayout.CENTER);

        jFrame.setSize(700, 500);
        jFrame.setLocation(260, 150);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        container.add(jPanel, BorderLayout.WEST);
        container.add(jPanel1, BorderLayout.NORTH);
        container.add(jPanel2, BorderLayout.EAST);
    }
}