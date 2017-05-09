package com.catfish.cracksignature;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.util.Log;
import android.widget.BaseAdapter;

public class Handle {
    private static final String TAG = "catfish";
    private static final String USER_0 = "weixin";
    private static final String USER_1 = "3561626421@chatroom";
    private static Set<String> sBlackSet;
    static {
        sBlackSet = new HashSet<String>();
        sBlackSet.add(USER_0);
        sBlackSet.add(USER_1);
    }

    private static List<Integer> sConversationItems = new ArrayList<Integer>();

    public static int handleGetCount(Object adapter, int count) {
        sConversationItems.clear();
        for (int i = 0; i < count; i++) {
            Object item = ((BaseAdapter) adapter).getItem(i);
            try {
                Class ah = Class.forName("com.tencent.mm.e.b.ah");
                Field name = ah.getField("field_username");
                name.setAccessible(true);
                if (!sBlackSet.contains((String) name.get(item))) {
                    sConversationItems.add(i);
                }
            } catch (ClassNotFoundException e) {
            } catch (NoSuchFieldException e) {
            } catch (IllegalAccessException e) {
            } catch (IllegalArgumentException e) {
            }
        }
        return sConversationItems.size();
    }

    public static int handleER(Object adapter, int pos) {
        int ret = pos;
        try {
            ret = sConversationItems.get(pos);
        } catch (Exception e) {
            Log.w(TAG, e.toString());
        }
        return ret;
    }
}
