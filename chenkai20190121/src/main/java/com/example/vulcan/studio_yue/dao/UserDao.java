package com.example.vulcan.studio_yue.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vulcan.studio_yue.bean.PerBean;
import com.example.vulcan.studio_yue.sql.SqliteHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库添加查询
 */
public class UserDao {

    private final SQLiteDatabase database;

    public UserDao(Context context){
        SqliteHolder holder = new SqliteHolder(context);
        database = holder.getReadableDatabase();
    }


    /**
     * 添加
     */
    public void add(String ivs,String tes,String tee){
        ContentValues values = new ContentValues();
        values.put("ivs",ivs);
        values.put("tes",tes);
        values.put("tee",tee);
        database.insert("user",null,values);
    }


    /**
     * 查询
     */
    public List<PerBean.DataBean> query(){
        Cursor query = database.query("user", null, null, null, null, null, null);
        List<PerBean.DataBean> list = new ArrayList<PerBean.DataBean>();
        while (query.moveToNext()){
            String ivs = query.getString(query.getColumnIndexOrThrow("ivs"));
            String tes = query.getString(query.getColumnIndexOrThrow("tes"));
            String tee = query.getString(query.getColumnIndexOrThrow("tee"));
            PerBean.DataBean dataBean = new PerBean.DataBean(ivs,tes,tee);
            list.add(dataBean);
        }
        return list;
    }


}
