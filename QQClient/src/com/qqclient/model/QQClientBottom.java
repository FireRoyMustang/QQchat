package com.qqclient.model;

import com.common.Message;
import com.common.User;


public class QQClientBottom {

    QQClientConnectServer qqClientConnectServer;

    public boolean checkLogIn(User u) {
        return qqClientConnectServer.sendLogInfo(u);
    }

    public QQClientConnectServer getConnetction() {
        return qqClientConnectServer;
    }

    public QQClientBottom() {
        qqClientConnectServer = new QQClientConnectServer();
    }
}
