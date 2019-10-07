package com.qqclient.view;

/**
 * 我的好友列表，包括陌生人，黑名单
 */

import com.common.Message;
import com.qqclient.model.QQClientConnectServer;
import com.qqclient.tools.MangeQQDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.Socket;

public class QQClientFriendList extends JFrame implements ActionListener, MouseListener {
    public static void main(String[] args) {
        QQClientFriendList qqClientFriendList = new QQClientFriendList("shen", null);
    }

    JButton jButtonFriend1, jButtonStranger1, jButtonBlackList1, jButtonFriend2, jButtonStranger2, jButtonBlackList2, jButtonFriend3, jButtonStranger3, jButtonBlackList3;
    JPanel jCard1Panel1, jCard2Panel1, jCard2Panel2, jCard3Panel1, jCard3Panel2;
    JPanel jCard1Panel, jCard2Panel, jCard3Panel;
    JPanel jPanelBottom1, jPanelBottom2, jPanelBottom3;
    JScrollPane jScrollPane1, jScrollPane2, jScrollPane3;
    int friendNums, strangerNums, blackListNums;
    JLabel[] jLabelsFriend;
    JLabel[] jLabelsStranger;
    JLabel[] jLabelsBlackList;
    CardLayout cardLayout;
    String userID;
    QQClientConnectServer qqClientConnectServer;


    public QQClientFriendList(String userID, QQClientConnectServer qqClientConnectServer) {
        this.qqClientConnectServer = qqClientConnectServer;
        this.userID = userID;
        friendNums = 50;
        strangerNums = 20;
        blackListNums = 10;
        //处理第一张卡片
        jCard1Panel = new JPanel(new BorderLayout());
        jCard1Panel1 = new JPanel(new GridLayout(friendNums, 1, 4, 4));
        jPanelBottom1 = new JPanel(new GridLayout(2, 1));
        jScrollPane1 = new JScrollPane(jCard1Panel1);
        jButtonFriend1 = new JButton("我的好友");
        jButtonStranger1 = new JButton("陌生人");
        jButtonStranger1.addActionListener(this);
        jButtonBlackList1 = new JButton("黑名单");
        jLabelsFriend = new JLabel[friendNums];
        jPanelBottom1.add(jButtonStranger1);
        jPanelBottom1.add(jButtonBlackList1);
        for (int i = 0; i < friendNums; i++) {
            jLabelsFriend[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
            jCard1Panel1.add(jLabelsFriend[i]);
            jLabelsFriend[i].addMouseListener(this);
            if (jLabelsFriend[i].getText().equals(userID)) {
                jLabelsFriend[i].setEnabled(true);
            } else {
                jLabelsFriend[i].setEnabled(false);

            }
        }
        jCard1Panel.add(jScrollPane1, "Center");
        jCard1Panel.add(jButtonFriend1, "North");

        jCard1Panel.add(jPanelBottom1, "South");


        //处理第二张卡片
        jCard2Panel = new JPanel(new BorderLayout());
        jCard2Panel1 = new JPanel(new GridLayout(2, 1));
        jCard2Panel2 = new JPanel(new GridLayout(strangerNums, 1, 4, 4));
        jPanelBottom2 = new JPanel(new GridLayout(1, 1));
        jScrollPane2 = new JScrollPane(jCard2Panel2);
        jButtonFriend2 = new JButton("我的好友");
        jButtonFriend2.addActionListener(this);
        jButtonStranger2 = new JButton("陌生人");
        jButtonBlackList2 = new JButton("黑名单");
        jLabelsStranger = new JLabel[strangerNums];
        for (int i = 0; i < strangerNums; i++) {
            jLabelsStranger[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
            jCard2Panel2.add(jLabelsStranger[i]);
            jLabelsStranger[i].addMouseListener(this);
        }
        //jScrollPane.add(jCard1Panel);
        jCard2Panel1.add(jButtonFriend2);
        jCard2Panel1.add(jButtonStranger2);
        jPanelBottom2.add(jButtonBlackList2);
        jCard2Panel.add(jCard2Panel1, "North");
        jCard2Panel.add(jScrollPane2, "Center");
        jCard2Panel.add(jPanelBottom2, "South");

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.add(jCard1Panel, "1");
        this.add(jCard2Panel, "2");

        this.setSize(200, 500);
        this.setLocation(200, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("好友列表");


    }

    public void updateFriend(Message message) {
        String onLineFriend[] = message.getContent().split(" ");
        for (int i = 0; i < onLineFriend.length; i++) {
            jLabelsFriend[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jButtonStranger1) {
            cardLayout.show(this.getContentPane(), "2");
        }
        if (e.getSource() == jButtonFriend2) {
            cardLayout.show(this.getContentPane(), "1");
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //响应用户双击的事件，并获得好友的编号
        if (e.getClickCount() == 2) {
            String friendName = ((JLabel) e.getSource()).getText();
            QQClientDiaLog qqClientDiaLog = new QQClientDiaLog(friendName, this.userID, this.qqClientConnectServer);
            MangeQQDialog.addQQDialog(this.userID + " " + friendName, qqClientDiaLog);
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel jLabelHighlight = (JLabel) e.getSource();
        jLabelHighlight.setForeground(Color.ORANGE);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel jLabelHighlight = (JLabel) e.getSource();
        jLabelHighlight.setForeground(Color.BLACK);


    }
}
