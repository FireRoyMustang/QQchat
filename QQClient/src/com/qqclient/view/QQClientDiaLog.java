package com.qqclient.view;
/**
 * 与好友聊天的界面
 */

import com.common.Message;
import com.qqclient.model.QQClientConnectServer;
import com.qqclient.tools.ClientConnectServerThread;
import com.qqclient.tools.ManageClientServerThread;


import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

public class QQClientDiaLog extends JFrame implements ActionListener {
    public static void main(String[] args) {
        QQClientDiaLog qqClientDiaLog = new QQClientDiaLog("2", "1", null);

    }

    JTextField jTextField;
    JTextArea jTextArea;
    JButton jButton;
    JPanel jPanel;
    JScrollPane jScrollPane;
    String friendName;
    String ownerID;
    QQClientConnectServer qqClientConnectServer;

    public QQClientDiaLog(String friendName, String ownerID, QQClientConnectServer qqClientConnectServer) {
        this.qqClientConnectServer = qqClientConnectServer;
        this.ownerID = ownerID;
        this.friendName = friendName;
        jTextArea = new JTextArea();
        jTextField = new JTextField(20);
        jButton = new JButton("发送");
        jButton.addActionListener(this);
        jScrollPane = new JScrollPane(jTextArea);
        jPanel = new JPanel();
        jPanel.add(jTextField);
        jPanel.add(jButton);
        this.add(jScrollPane, "Center");
        this.add(jPanel, "South");
        this.setIconImage((new ImageIcon("image/qq.gif")).getImage());
        this.setTitle("你正在和" + friendName + "聊天");
        //this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButton) {
            Message message = new Message();
            message.setSender(ownerID);
            message.setReceiver(friendName);
            message.setContent(this.jTextField.getText());
            message.setSendTime((new Date()).toString());
            try {
                ObjectOutputStream objectOutputStream =
                        new ObjectOutputStream(
                                ManageClientServerThread.getClientServerThread(ownerID).getSocket().getOutputStream());
                objectOutputStream.writeObject(message);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            this.jTextArea.append("我: " + this.jTextField.getText() + "\r\n");
            //qqClientConnectServer.sendMessage(message);
            this.jTextField.setText("");
        }
    }

    public void addText(String content) {
        this.jTextArea.append(friendName + ":" + content + "\r\n");
    }


}
