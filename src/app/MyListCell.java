package app;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.io.File;

public class MyListCell extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus) {        //重写渲染器的方法
                    //value 信息     cellHasFocus 列表中是否有焦点
      //  System.out.println( "value为"+(String) value);
        File file=new File((String)value);
        FileSystemView fileSystemView=FileSystemView.getFileSystemView();
        setText("<html>"+file.getName()+"<br/>"+file);
        Icon icon=fileSystemView.getSystemIcon(file);
        setIcon(icon);
        if(isSelected){     //当一个元素被选中
            setForeground(Color.WHITE);     //前景色
            setBackground(Color.BLUE);      //背景色
        }else{
            setForeground(Color.BLACK);
            setBackground(Color.WHITE);
        }
        return this;

    }
}
