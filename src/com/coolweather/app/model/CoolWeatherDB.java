package com.coolweather.app.model;


import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.db.CoolWeatherOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	public static final String DB_NAME="cool_weather";
	
	public static final int VERSION=1;
	
	private static CoolWeatherDB coolWeatherDB;
	
	private SQLiteDatabase db;
	
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper coolWeatherOpenHelper = new CoolWeatherOpenHelper(context, DB_NAME, null, VERSION);
		db = coolWeatherOpenHelper.getWritableDatabase();
	}
	
	public synchronized static CoolWeatherDB getInstance(Context context){
		if(coolWeatherDB==null){
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}
	
	public void saveProvince(Province province){
		if(province !=null){
			ContentValues values  = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}
	
	public List<Province> loadProvince(){
		List<Province> lists = new ArrayList<Province>();
		Cursor cursor = db.query("Province", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				Province province = new Province();
				province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				lists.add(province);
			}while(cursor.moveToNext());
		}
		return lists;
	}
	
	public void saveCity(City city){
		if(city !=null){
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}
	
	public List<City> loadCity(){
		List<City> lists = new ArrayList<City>();
		Cursor cursor = db.query("City", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				City city = new City();
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
				lists.add(city);
			}while(cursor.moveToNext());
		}
		return lists;
	}
	
	public void saveCounty(County county){
		if(county !=null){
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}
	
	public List<County> loadCounty(){
		List<County> lists = new ArrayList<County>();
		Cursor cursor = db.query("County", null, null, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				County county = new County();
				county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
				county.setCityId(cursor.getInt(cursor.getColumnIndex("city_id")));
				lists.add(county);
			}while(cursor.moveToNext());
		}
		return lists;
	}
}
