package com.qqclient.tools;

import java.util.HashMap;

/**
 * 管理通讯客户端与服务器保持通讯的线程
 */
public class ManageClientServerThread {
    private static HashMap hashMap = new HashMap<String, ClientConnectServerThread>();

    public static void addClientServerThread(String userID, ClientConnectServerThread clientConnectServerThread) {
        hashMap.put(userID, clientConnectServerThread);
    }

    public static ClientConnectServerThread getClientServerThread(String userID) {
        return (ClientConnectServerThread) hashMap.get(userID);
    }


}
