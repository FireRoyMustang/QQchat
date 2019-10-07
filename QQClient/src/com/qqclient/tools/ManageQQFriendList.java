package com.qqclient.tools;
/**
 * 管理各种好友列表
 */

import com.qqclient.view.QQClientFriendList;

import java.util.HashMap;

public class ManageQQFriendList {
    public static HashMap hashMap = new HashMap();

    public static void addQQFriendList(String userID, QQClientFriendList qqClientFriendList) {
        hashMap.put(userID, qqClientFriendList);
    }

    public static QQClientFriendList getQQFriendList(String userID) {
        return (QQClientFriendList) hashMap.get(userID);
    }
}
