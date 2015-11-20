package com.catfish.cracksignature;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.util.Log;

public class Crack {
    private final static String TAG = "catfish";

    public static void start() {
        Crack instance = new Crack();
        instance.crackActivityThread();
    }

    private void crackActivityThread() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = activityThreadClass.getDeclaredMethod("currentActivityThread",
                    (Class[]) null);
            currentActivityThread.setAccessible(true);
            Object activitythread = currentActivityThread.invoke(null);

            Field fs[] = activityThreadClass.getDeclaredFields();
            Field ipacakgemanager = null;
            for (Field f : fs) {
                if (f.toGenericString().contains("IPackageManager")) {
                    ipacakgemanager = f;
                    ipacakgemanager.setAccessible(true);
                    break;
                }
            }
            if (ipacakgemanager == null) {
                Log.e(TAG, "package manager isn't found in ActivityThread");
                return;
            }

            Object newPackageManager = Proxy.newInstance(ipacakgemanager.get(activitythread));
            ipacakgemanager.set(activitythread, newPackageManager);
        } catch (ClassNotFoundException e) {
            Log.e(TAG, e.toString());
        } catch (IllegalAccessException e) {
            Log.e(TAG, e.toString());
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.toString());
        } catch (NoSuchMethodException e) {
            Log.e(TAG, e.toString());
        } catch (InvocationTargetException e) {
            Log.e(TAG, e.toString());
        }
    }
}
