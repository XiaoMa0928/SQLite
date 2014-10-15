package com.example.sqlite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.sqlite.R;


public class MainActivity extends Activity implements OnClickListener {

	private Button btn_insert;
	private Button btn_query;
	private Button btn_update;
	private Button btn_delete;

	private Button btn_insert_api;
	private Button btn_query_api;
	private Button btn_update_api;
	private Button btn_delete_api;

	private MydbOpenHelper mdbOpenHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mdbOpenHelper = new MydbOpenHelper(getApplicationContext());

		setContentView(R.layout.activity_main);
		btn_insert = (Button) findViewById(R.id.btn_insert);
		btn_insert.setOnClickListener(this);
		btn_query = (Button) findViewById(R.id.btn_query);
		btn_query.setOnClickListener(this);
		btn_update = (Button) findViewById(R.id.btn_update);
		btn_update.setOnClickListener(this);
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_delete.setOnClickListener(this);

		btn_insert_api = (Button) findViewById(R.id.btn_insert_api);
		btn_insert_api.setOnClickListener(this);
		btn_query_api = (Button) findViewById(R.id.btn_query_api);
		btn_query_api.setOnClickListener(this);
		btn_update_api = (Button) findViewById(R.id.btn_update_api);
		btn_update_api.setOnClickListener(this);
		btn_delete_api = (Button) findViewById(R.id.btn_delete_api);
		btn_delete_api.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_insert:
			insertSQL();
			Toast.makeText(getApplicationContext(), "Insert 插入数据成功", 1000)
					.show();
			break;
		case R.id.btn_query:
			querySQL();

			Toast.makeText(getApplicationContext(), "Query 查询数据成功", 1000)
					.show();
			break;
		case R.id.btn_update:
			updateSQL();
			Toast.makeText(getApplicationContext(), "Update 更新数据成功", 1000)
					.show();
			break;
		case R.id.btn_delete:
			deleteSQL();
			Toast.makeText(getApplicationContext(), "Delete 删除数据成功", 1000)
					.show();
			break;

		case R.id.btn_insert_api:
			insertAPI();
			Toast.makeText(getApplicationContext(), "Insert-API 插入数据成功", 1000)
					.show();
			break;
		case R.id.btn_query_api:
			queryAPI();

			Toast.makeText(getApplicationContext(), "Query-API 查询数据成功", 1000)
					.show();
			break;
		case R.id.btn_update_api:
			updateAPI();
			Toast.makeText(getApplicationContext(), "Update-API 更新数据成功", 1000)
					.show();
			break;
		case R.id.btn_delete_api:
			deleteAPI();
			Toast.makeText(getApplicationContext(), "Delete-API 删除数据成功", 1000)
					.show();
			break;

		default:
			break;
		}

	}

	/**
	 * SQL插入数据库
	 */

	private void insertSQL() {

		// 获取一个SQLiteDatabase一个操作可操作数据库的对象
		SQLiteDatabase db = mdbOpenHelper.getWritableDatabase();
		// 调用execSQL()方法实现插入操作
		db.execSQL("insert into stu_info values(null,'xiaoqiang',22)");
		db.execSQL("insert into stu_info values(null,'liuling',21)");
		db.execSQL("insert into stu_info values(null,'wangyan',21)");
		db.execSQL("insert into stu_info values(null,'xiaosan',20)");
		db.execSQL("insert into stu_info values(null,'zilong',23)");
		// 数据库操作对象关闭数据库
		db.close();

	}

	/**
	 * SQL查询数据库
	 */
	private void querySQL() {

		SQLiteDatabase db = mdbOpenHelper.getReadableDatabase();
		// 获取一个光标对象
		Cursor cursor = db.rawQuery("select * from stu_info", null);

		// 使用ArrayList保存多个HashMap数据对象
		List<HashMap<String, Object>> stu_list = new ArrayList<HashMap<String, Object>>();
		// 使用HashMap保存一条语句
		HashMap<String, Object> map = null;
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			map = new HashMap<String, Object>();
			// 通过“数据库”字段获取对应的数据
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			map.put("_id", _id);
			// 通过“数据库”字段获取对应的数据
			String stu_Name = cursor.getString(cursor
					.getColumnIndex("stu_name"));
			// 通过“数据库”字段获取对应的数据
			map.put("stu_Name", stu_Name);
			int stu_age = cursor.getInt(cursor.getColumnIndex("stu_age"));
			map.put("stu_age", stu_age);
			stu_list.add(map);
			cursor.moveToNext();
			Log.i("Yongke.pan", "_id=" + _id + ",stu_Name=" + stu_Name
					+ ",stu_age=" + stu_age);
		}

		cursor.close();
		db.close();

	}

	private void updateSQL() {
		SQLiteDatabase db = mdbOpenHelper.getReadableDatabase();
		db.execSQL("update stu_info set stu_name='zidong',stu_age=30 where _id=4 or _id=9");
		db.close();
	}

	/**
	 * SQL删除数据
	 */

	private void deleteSQL() {

		SQLiteDatabase db = mdbOpenHelper.getReadableDatabase();
		db.execSQL("delete from stu_info where _id=4 or _id=9");
		db.close();

	}

	private void insertAPI() {

		SQLiteDatabase db = mdbOpenHelper.getReadableDatabase();
		ContentValues values = new ContentValues();

		// values.put("_id", 0);
		values.put("stu_name", "12345");
		values.put("stu_age", 102);
		db.insert("stu_info", null, values);

		values = new ContentValues();

		// values.put("_id", 0);
		values.put("stu_name", "23456");
		values.put("stu_age", 234);
		db.insert("stu_info", null, values);
		db.close();

	}

	private void queryAPI() {
		SQLiteDatabase db = mdbOpenHelper.getReadableDatabase();

		Cursor cursor = db
				.query("stu_info", null, null, null, null, null, null);

		// 使用ArrayList保存多个HashMap数据对象
		List<HashMap<String, Object>> stu_list = new ArrayList<HashMap<String, Object>>();
		// 使用HashMap保存一条语句
		HashMap<String, Object> map = null;
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			map = new HashMap<String, Object>();
			// 通过“数据库”字段获取对应的数据
			int _id = cursor.getInt(cursor.getColumnIndex("_id"));
			map.put("_id", _id);
			// 通过“数据库”字段获取对应的数据
			String stu_Name = cursor.getString(cursor
					.getColumnIndex("stu_name"));
			// 通过“数据库”字段获取对应的数据
			map.put("stu_Name", stu_Name);
			int stu_age = cursor.getInt(cursor.getColumnIndex("stu_age"));
			map.put("stu_age", stu_age);
			stu_list.add(map);
			cursor.moveToNext();
			Log.i("Yongke.pan", "_id=" + _id + ",stu_Name=" + stu_Name
					+ ",stu_age=" + stu_age);
		}

		cursor.close();
		db.close();

	}

	private void updateAPI() {
		SQLiteDatabase db = mdbOpenHelper.getReadableDatabase();
		ContentValues values = new ContentValues();
		values.put("stu_name", "AAAA");
		values.put("stu_age", 1000);
		db.update("stu_info", values, "_id<3", null);
		db.close();

	}

	private void deleteAPI() {
		SQLiteDatabase db = mdbOpenHelper.getReadableDatabase();
		db.delete("stu_info", "_id>10", null);
		db.close();

	}

}
