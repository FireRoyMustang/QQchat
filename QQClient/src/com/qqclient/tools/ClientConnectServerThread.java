package com.qqclient.tools;

import com.common.Message;
import com.common.MessageType;
import com.qqclient.view.QQClientDiaLog;
import com.qqclient.view.QQClientFriendList;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * 客户端与服务器保持通讯的线程
 */
public class ClientConnectServerThread extends Thread {
    public Socket getSocket() {
        return socket;
    }

    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        while (true) {
            //不断地读取从服务器发来的消息
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(this.socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();
                QQClientDiaLog qqClientDiaLog =
                        MangeQQDialog.getQQDialog(message.getReceiver() + " " + message.getSender());
                if (message.getMessageType().equals(MessageType.message_common)) {
                    qqClientDiaLog.addText(message.getContent());

                } else if (message.getMessageType().equals(MessageType.message_get_onLineFriend)) {
                    QQClientFriendList qqClientFriendList = ManageQQFriendList.getQQFriendList(message.getReceiver());
                    qqClientFriendList.updateFriend(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
