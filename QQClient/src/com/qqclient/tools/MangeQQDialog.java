package com.qqclient.tools;

import com.qqclient.view.QQClientDiaLog;

import java.util.HashMap;

public class MangeQQDialog {
    private static HashMap hashMap = new HashMap<String, QQClientDiaLog>();

    public static void addQQDialog(String userIDAndFriendID, QQClientDiaLog qqClientDiaLog) {
        hashMap.put(userIDAndFriendID, qqClientDiaLog);

    }

    public static QQClientDiaLog getQQDialog(String userIDAndFriendID) {
        return (QQClientDiaLog) hashMap.get(userIDAndFriendID);

    }
}
