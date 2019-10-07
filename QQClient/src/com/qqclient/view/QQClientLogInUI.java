package com.qqclient.view;

import com.common.Message;
import com.common.MessageType;
import com.common.User;
import com.qqclient.model.QQClientBottom;
import com.qqclient.tools.ManageClientServerThread;
import com.qqclient.tools.ManageQQFriendList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * QQ登陆界面
 */
public class QQClientLogInUI extends JFrame implements ActionListener {
    public static void main(String[] args) {
        QQClientLogInUI qqClientLogIn = new QQClientLogInUI();
    }

    //北边布局
    JLabel jLabelNorth;
    //中部布局
    //由选项卡窗口管理
    JTabbedPane jTabbedPane;
    //选项卡1
    JPanel jPanelCard1, jPanelCard2, jPanelCard3;
    JLabel jCard1Label1, jCard1Label2, jCard1Label3, jCard1Label4;
    JCheckBox jCard1CheckBox1, jCard1CheckBox2;
    JButton jCard1Button1;
    JTextField jTextField1;
    //南部布局
    JPanel jPanelBottom;
    JButton jPanelBottomButton1, jPanelBottomButton2, jPanelBottomButton3;
    JPasswordField jPasswordField;
    JCheckBox jCheckBox1, jCheckBox2;
    QQClientBottom qqClientBottom;

    public QQClientLogInUI() {
        jLabelNorth = new JLabel(new ImageIcon("image/tou.gif"));
        jTabbedPane = new JTabbedPane();
        //中部布局
        jCard1Label1 = new JLabel("QQ号码", JLabel.CENTER);
        jCard1Label2 = new JLabel("QQ密码", JLabel.CENTER);
        jCard1Label3 = new JLabel("忘记密码", JLabel.CENTER);
        jCard1Label3.setForeground(Color.blue);
        jCard1Label4 = new JLabel("申请密码保护", JLabel.CENTER);
        jCard1CheckBox1 = new JCheckBox("隐身登陆");
        jCard1CheckBox2 = new JCheckBox("记住密码");
        jCard1Button1 = new JButton(new ImageIcon("image/clear.gif"));
        jTextField1 = new JTextField(20);
        jPasswordField = new JPasswordField(20);
        jPanelCard1 = new JPanel();
        jPanelCard1.setLayout(new GridLayout(3, 3));
        jPanelCard1.add(jCard1Label1);
        jPanelCard1.add(jTextField1);
        jPanelCard1.add(jCard1Button1);
        jPanelCard1.add(jCard1Label2);
        jPanelCard1.add(jPasswordField);
        jPanelCard1.add(jCard1Label3);
        jPanelCard1.add(jCard1CheckBox1);
        jPanelCard1.add(jCard1CheckBox2);
        jPanelCard1.add(jCard1Label4);
        jTabbedPane = new JTabbedPane();
        jPanelCard2 = new JPanel();
        jPanelCard3 = new JPanel();
        jTabbedPane.add("QQ号码", jPanelCard1);
        jTabbedPane.add("手机号码", jPanelCard2);
        jTabbedPane.add("电子邮件", jPanelCard3);


        jPanelBottom = new JPanel();
        jPanelBottomButton1 = new JButton(new ImageIcon("image/denglu.gif"));
        jPanelBottomButton1.addActionListener(this);
        jPanelBottomButton2 = new JButton(new ImageIcon("image/quxiao.gif"));
        jPanelBottomButton3 = new JButton(new ImageIcon("image/xiangdao.gif"));


        jPanelBottom.add(jPanelBottomButton1);
        jPanelBottom.add(jPanelBottomButton2);
        jPanelBottom.add(jPanelBottomButton3);
        this.add(jLabelNorth, "North");
        this.add(jPanelBottom, "South");
        this.add(jTabbedPane, "Center");
        this.setSize(350, 240);
        this.setLocation(200, 200);
        this.setTitle("QQ");
        this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jPanelBottomButton1) {
            //用户点击登陆按钮
            qqClientBottom = new QQClientBottom();
            User user = new User();
            user.setUserName(jTextField1.getText().trim());
            user.setPasswd(new String(jPasswordField.getPassword()));//返回char数组，需要new String
            if (qqClientBottom.checkLogIn(user)) {
                //发送要求返回在线好友的请求包
                try {
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream
                            (ManageClientServerThread.getClientServerThread(user.getUserName()).getSocket().getOutputStream());
                    QQClientFriendList qqClientFriendList = new QQClientFriendList(user.getUserName(), qqClientBottom.getConnetction());
                    ManageQQFriendList.addQQFriendList(user.getUserName(), qqClientFriendList);
                    Message message = new Message();
                    message.setSender(user.getUserName());
                    message.setMessageType(MessageType.message_request_onLineFriend);
                    objectOutputStream.writeObject(message);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "用户名或者密码错误!");
            }


        }


    }
}
