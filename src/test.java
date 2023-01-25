import java.io.*;
import java.util.Vector ;
import java.awt.event.WindowEvent ;
import java.awt.event.WindowAdapter ;
import java.awt.event.WindowListener ;
import javax.swing.event.ListSelectionListener ;
import javax.swing.event.ListSelectionEvent ;
import java.awt.Container ;
import java.awt.GridLayout ;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.JFrame ;
import javax.swing.JList ;
import javax.swing.JScrollPane ;
import javax.swing.BorderFactory ;
import javax.swing.AbstractListModel ;
import javax.swing.ListSelectionModel ;

//class MyListModel extends AbstractListModel
//{
//    private String brothers[] = {"大哥","呆萌","小三","小四","小五"} ;
//    public Object getElementAt(int index)
//    {
//        if(index<brothers.length)
//            return brothers[index] ;
//        else return null ;
//    }
//
//    public int getSize()
//    {
//        return brothers.length ;
//    }
//}
//
//class MyList implements ListSelectionListener
//{
//    JFrame frame = new JFrame() ;
//    Container con = frame.getContentPane() ;
//    private JList list = null ;
//    public MyList()
//    {
//        this.frame.setLayout(new GridLayout(1,3)) ;
//        this.list = new JList(new MyListModel()) ;
//        this.list.setBorder(BorderFactory.createTitledBorder("你排行老几啊？")) ;
//        this.list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION) ;
//        this.con.add(new JScrollPane(list)) ;
//
//        this.frame.setSize(400,400) ;
//        this.frame.setVisible(true) ;
//        this.list.addListSelectionListener(this) ;
//
//        this.frame.addWindowListener(new WindowAdapter(){
//            public void windowClosing(WindowEvent a){
//                System.exit(1) ;
//            }
//        }) ;
//    }
//
//    public void valueChanged(ListSelectionEvent e)
//    {
//        int temp[] = list.getSelectedIndices() ;
//        //int temp[] = list1.getSelectedIndices() ;
//        System.out.println("选中的内容是：") ;
//        for(int i=0;i<temp.length;i++)
//        {
//            System.out.println(list.getModel().getElementAt(i)) ;
//        }
//    }
//}
//
//class Tester
//{
//    public static void main(String args[])
//    {
//        new MyList() ;
//    }
//}
public class test {
    public static void main(String[] arg) {

        try {
            decompressionFile("E:\\FileManager.zip","E:\\1");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decompressionFile(String srcPath, String outPath) throws IOException {
        System.out.println("srcPath为："+srcPath);
        //简单判断解压路径是否合法
        if (!new File(srcPath).isDirectory()) {
            //判断输出路径是否合法
            if (new File(outPath).isDirectory()) {
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
        } else {
            throw new RuntimeException("需要解压的文件不合法!");
        }
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
//    public static void createNode(File Path, DefaultMutableTreeNode node){
//            if(Path.isFile())return;
//            File[] files=Path.listFiles();
//            if(files==null)return;
//            for(File file:files){
//                    if(file.isHidden())continue;
//                    DefaultMutableTreeNode tmp=new DefaultMutableTreeNode(file.getName());
//                    node.add(tmp);
//                    createNode(file,tmp);
//            }
//
//    }
//记录点击时的file
//getLast pathComponent返回此路径的最后一个元素

//                            String sti=new String();
//                            if (!fileTree.isSelectionEmpty()) {// 查看是否存在被选中的节点
//                                    // 获得所有被选中节点的路径,当时多选时selectionPaths大于1
//                                    TreePath[] selectionPaths = fileTree.getSelectionPaths();
//                                    for (int i = 0; i < selectionPaths.length; i++) {
//                                            // 获得被选中节点的路径
//                                            TreePath treePath = selectionPaths[i];
//                                            // 以Object数组的形式返回该路径中所有节点的对象 第一个是根
//                                            Object[] path = treePath.getPath();
//                                            for (int j = 1; j < path.length; j++) {
//                                                    DefaultMutableTreeNode node;// 获得节点
//                                                    node = (DefaultMutableTreeNode) path[j];
//                                                    String s = node.getUserObject()
//                                                            + (j == (path.length - 1) ? "" : "\\\\");
//                                                    sti = sti + s;
//                                                    System.out.println(s);// 输出节点标签
//                                            }
//                                    }
//                            }
//返回路径数
//    int sumPath=e.getPath().getPathCount();



//            if(APP.defaultListModel1.capacity()>0){
//                s=APP.defaultListModel1.get(0).toString();
//            }
