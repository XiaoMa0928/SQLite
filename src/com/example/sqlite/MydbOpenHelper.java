package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MydbOpenHelper extends SQLiteOpenHelper {

	public MydbOpenHelper(Context context) {
		super(context, "my_db", null, 2);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table stu_info(_id integer primary key autoincrement,stu_name text,stu_age integer)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists stu_info");
		this.onCreate(db);
	}

}
