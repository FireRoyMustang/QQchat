package com.qqserver.view;

/**
 * QQ服务器界面
 */

import com.qqserver.model.QQServerSocket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QQServerUI extends JFrame implements ActionListener{
    public static void main(String[] args) {
        QQServerUI qqServerUI = new QQServerUI();
    }

    JPanel jPanel1;
    JButton jButton1, jButton2;

    public QQServerUI() {
        jButton1 = new JButton("启动服务器");
        jButton1.addActionListener(this);
        jButton2 = new JButton("关闭服务器");
        jPanel1 = new JPanel();
        jPanel1.add(jButton1);
        jPanel1.add(jButton2);
        this.add(jPanel1);
        this.setSize(800, 600);
        this.setTitle("QQ服务器");
        this.setIconImage((new ImageIcon("image/qq.gif")).getImage());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jButton1)
        {
            QQServerSocket qqServerSocket=new QQServerSocket();
        }
    }
}
