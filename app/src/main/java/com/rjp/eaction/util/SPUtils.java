package com.rjp.eaction.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.rjp.eaction.baseAF.App;

/**
 * author : Gimpo create on 2018/7/10 12:33
 * email  : jimbo922@163.com
 */
public class SPUtils {

    private final SharedPreferences.Editor editor;
    private final SharedPreferences sp;

    private SPUtils(){
        sp = App.getContext().getSharedPreferences("eaction", Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    private static SPUtils instance = null;

    public static SPUtils getInstance(){
        if(instance == null){
            synchronized (SPUtils.class){
                if(instance == null){
                    instance = new SPUtils();
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
        }else {
            editor.putString(key, (String) value);
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

    //--------------------------------------------------------------以下是扩展部分-----------------------------------------
}
