package com.qqserver.model;

import java.util.HashMap;
import java.util.Iterator;

public class ManageClientThread {
    public static HashMap hashMap = new HashMap<String, ServerConnectThread>();

    public static void addClientThread(String uid, ServerConnectThread serverConnectThread) {
        hashMap.put(uid, serverConnectThread);
    }

    public static ServerConnectThread getClientThread(String uid) {
        return (ServerConnectThread) hashMap.get(uid);
    }

    public static String getOnlineList() {
        Iterator iterator = hashMap.keySet().iterator();
        String result = "";
        while (iterator.hasNext()) {
            result += iterator.next().toString() + " ";
        }
        return result;
    }
}
