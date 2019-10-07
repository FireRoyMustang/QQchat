package com.qqclient.model;

import com.common.Message;
import com.common.User;
import com.qqclient.tools.ClientConnectServerThread;
import com.qqclient.tools.ManageClientServerThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class QQClientConnectServer {
    public static void main(String[] args) {
        QQClientConnectServer qqClientSocket = new QQClientConnectServer();
    }

    public static Socket socket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Message message;

    public void sendMessage(Message message) {
        try {
            //socket = new Socket("127.0.0.1", 9999);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());//这句话很必要，我也不知道为什么
            objectOutputStream.writeObject(message);
            //socket.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public boolean sendLogInfo(User user) {
        boolean flag = false;
        try {
            socket = new Socket("127.0.0.1", 9999);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(user);
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            message = (Message) objectInputStream.readObject();
            if (message.getMessageType().equals("1")) {
                flag = true;
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(this.socket);
                clientConnectServerThread.start();//启动该线程
                ManageClientServerThread.addClientServerThread(user.getUserName(), clientConnectServerThread);
            }
            //socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
