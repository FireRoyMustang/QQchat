package com.qqserver.model;

import com.common.Message;
import com.common.MessageType;

import java.net.*;
import java.io.*;
import java.util.Iterator;

/**
 * 完成客户端与服务器通讯的线程建立
 */
public class ServerConnectThread extends Thread {

    Socket socket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Message message;

    public ServerConnectThread(Socket s) {
        this.socket = s;
    }

    public void run() {
        while (true) {
            //这里该线程可以接收客户端的信息
            try {

                objectInputStream = new ObjectInputStream(socket.getInputStream());
                message = (Message) objectInputStream.readObject();
                if (message.getMessageType().equals(MessageType.message_common)) {
                    ServerConnectThread serverConnectThread = ManageClientThread.getClientThread(message.getReceiver());
                    objectOutputStream = new ObjectOutputStream(serverConnectThread.socket.getOutputStream());
                    objectOutputStream.writeObject(message);
                    System.out.println(message.getSender() + "发送了" + message.getContent() + "给" + message.getReceiver() + "。");
                } else if (message.getMessageType().equals(MessageType.message_request_onLineFriend)) {
                    String onLineContent = ManageClientThread.getOnlineList();
                    Iterator iterator = ManageClientThread.hashMap.keySet().iterator();
                    while (iterator.hasNext()) {
                        String getterId = iterator.next().toString();
                        ServerConnectThread serverConnectThread = ManageClientThread.getClientThread(getterId);
                        objectOutputStream = new ObjectOutputStream(serverConnectThread.socket.getOutputStream());
                        Message message_user = new Message();
                        message_user.setMessageType(MessageType.message_get_onLineFriend);
                        message_user.setReceiver(getterId);
                        message_user.setContent(onLineContent);
                        objectOutputStream.writeObject(message_user);
                    }
//                    ServerConnectThread serverConnectThread = ManageClientThread.getClientThread(message.getSender());
//                    objectOutputStream = new ObjectOutputStream(serverConnectThread.socket.getOutputStream());
//                    Message message_user = new Message();
//                    message_user.setMessageType(MessageType.message_get_onLineFriend);
//                    message_user.setReceiver(message.getSender());
//                    message_user.setContent(ManageClientThread.getOnlineList());
//                    objectOutputStream.writeObject(message_user);
                }
            } catch (Exception e) {

            }
        }
    }
}
