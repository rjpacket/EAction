package com.rjp.eaction.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.rjp.eaction.baseAF.App;

import java.util.Set;

/**
 * author : Gimpo create on 2018/7/10 12:33
 * email  : jimbo922@163.com
 */
public class SPUtils {
    public static final String USER_ID = "d8db84c5-401b-441a-b53e-f046162888f2";

    public static final String USER_NAME = "robot.username";
    public static final String PASSWORD = "robot.password";
    public static final String IS_LOGIN = "robot.islogin";

    private final SharedPreferences.Editor editor;
    private final SharedPreferences sp;

    private SPUtils(Context mContext){
        sp = mContext.getSharedPreferences("robot", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private static SPUtils instance = null;

    public static SPUtils getInstance(Context mContext){
        if(instance == null){
            synchronized (SPUtils.class){
                if(instance == null){
                    instance = new SPUtils(mContext);
                }
            }
        }
        return instance;
    }

    /**
     * 保存字段
     * @param key
     * @param value
     */
    public boolean save(String key, Object value){
        if(value instanceof Boolean){
            editor.putBoolean(key, (Boolean) value);
        }else if(value instanceof Float){
            editor.putFloat(key, (Float) value);
        }else if(value instanceof Integer){
            editor.putInt(key, (Integer) value);
        }else if(value instanceof Long){
            editor.putLong(key, (Long) value);
        }else if(value instanceof String){
            editor.putString(key, (String) value);
        }else{
            editor.putStringSet(key, (Set<String>) value);
        }
        return editor.commit();
    }

    /**
     * 以下获取字段
     * @param key
     * @return
     */
    public String getString(String key){
        return getString(key, "");
    }

    public String getString(String key, String defVal){
        return sp.getString(key, defVal);
    }

    public Boolean getBoolean(String key){
        return getBoolean(key, false);
    }

    public Boolean getBoolean(String key, boolean defVal){
        return sp.getBoolean(key, defVal);
    }

    public Float getFloat(String key){
        return getFloat(key, 0f);
    }

    public Float getFloat(String key, float defVal){
        return sp.getFloat(key, defVal);
    }

    public Integer getInt(String key){
        return getInt(key, 0);
    }

    public Integer getInt(String key, int defVal){
        return sp.getInt(key, defVal);
    }

    public Long getLong(String key){
        return getLong(key, 0);
    }

    public Long getLong(String key, long defVal){
        return sp.getLong(key, defVal);
    }

    public Set<String> getSet(String key){
        return sp.getStringSet(key, null);
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        editor.remove(key);
        editor.commit();
    }

    /**
     * 清除所有数据
     */
    public void clear() {
        editor.clear();
        editor.commit();
    }

    /**
     * 查询某个key是否存在
     */
    public boolean contain(String key) {
        return sp.contains(key);
    }


    //--------------------------------------------------------------以下是扩展部分-----------------------------------------
}
