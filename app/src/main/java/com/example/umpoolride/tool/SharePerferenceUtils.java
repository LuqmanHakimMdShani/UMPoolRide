package com.example.umpoolride.tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class SharePerferenceUtils {

    private static SharedPreferences sp;
    private static Gson gson = new Gson();

    // 1,存储boolean变量方法
    public static void putBoolean(Context ctx, String key, boolean value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    // 2,读取boolean变量方法
    public static boolean getBoolean(Context ctx, String key, boolean defValue) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    public static void putString(Context ctx, String key, String value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context ctx, String key, String defValue) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

    public static void putStringList(Context ctx, String key, List<String> value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, gson.toJson(value)).commit();
    }

    public static List<String> getStringList(Context ctx, String key){
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        String str = sp.getString(key,"");
        if(TextUtils.isEmpty(str)){
            return new ArrayList<>();
        }
        return gson.fromJson(str, new TypeToken<List<String>>(){}.getType());
    }

    /**
     * @param ctx
     *            上下文环境
     * @param key
     *            要从config.xml移除节点的name的名称
     */
    public static void removeKey(Context ctx, String key) {
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).commit();
    }

    // 反射(扩展)
    //
    public static void putInt(Context ctx, String key, int value) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).commit();
    }

    public static int getInt(Context ctx, String key, int defValue) {
        // name存储文件名称
        if (sp == null) {
            sp = ctx.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }

}
