package com.qqserver.model;

/**
 * QQ服务器，监听，等待QQ客户端连接
 * 1、服务器为了完成信息转发，可以把服务器得到的每个Socket和客户端保存在Hashmap，用客户的id标识改Socket
 * 2、当服务器和某个客户端形成一个连接（Socket）时，服务器端单开一个线程与该客户端进行通讯
 */

import com.common.Message;
import com.common.User;

import java.io.*;
import java.net.*;

public class QQServerSocket {
    public static void main(String[] args) {
        QQServerSocket qqServerSocket = new QQServerSocket();
    }

    ServerSocket serverSocket;
    User user;
    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    public QQServerSocket() {
        try {
            serverSocket = new ServerSocket(9999);
            while (true) {
                socket = serverSocket.accept();
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                user = (User) objectInputStream.readObject();
                System.out.println("服务器得到到用户名:" + user.getUserName() + ", 密码:" + user.getPasswd());
                Message message = new Message();
                if (user.getPasswd().equals("123456")) {
                    message.setMessageType("1");
                    objectOutputStream.writeObject(message);
                    //单开一个线程，让该线程与该客户端保持通讯
                    ServerConnectThread serverConnectThread = new ServerConnectThread(socket);
                    ManageClientThread.addClientThread(user.getUserName(), serverConnectThread);
                    //启动与该客户端通讯的线程
                    serverConnectThread.start();
                } else {
                    message.setMessageType("2");
                    objectOutputStream.writeObject(message);
                    socket.close();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                //serverSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
