package com.example.rental_ui_1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {

    public String userName;
    private static String DBName="rental.db";
    private static String USER_TABLE ="user_table";
    private static String PROPERTY_TABLE ="property_table";

    //user table
    private static String USER_ID ="user_id";
    private static String USER_NAME ="user_name";
    private static String EMAIL ="email";
    private static String PASSWORD ="password";
    private static String GENDER="gender";
    private static String PHONE_NO ="phone_no";

    //property table
    private static String REF_ID ="ref_id";
    private static String PROPERTY_TYPE ="property_type";
    private static String BEDROOM ="bed_room";
    private static String PRICE="price";
    private static String LOCATION="location";
    private static String FURNITURE_TYPE ="furniture_type";
    private static String REMARK ="remark";
    private static String REPORTER_NAME ="reporter_name";
    private static String ASSIGN_DATE ="date";

    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String user_table_create="create table "+ USER_TABLE +"("+
                USER_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                USER_NAME +" text,"+
                EMAIL +" text,"+
                PASSWORD +" text,"+
                GENDER +" text,"+
                PHONE_NO +" text)";
        sqLiteDatabase.execSQL(user_table_create);

        String property_table_create = "CREATE TABLE " + PROPERTY_TABLE + "(" +
                REF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PROPERTY_TYPE + " TEXT," +
                BEDROOM + " TEXT," +
                PRICE + " TEXT," +
                LOCATION + " TEXT," +
                FURNITURE_TYPE + " TEXT," +
                REMARK + " TEXT," +
                REPORTER_NAME + " TEXT," +
                ASSIGN_DATE + " TEXT)";
        sqLiteDatabase.execSQL(property_table_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ PROPERTY_TABLE);
    }

    public void addUser(String userName,String password,String email,String gender,String phoneNo){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(USER_NAME,userName);
        cv.put(PASSWORD,password);
        cv.put(EMAIL,email);
        cv.put(GENDER,gender);
        cv.put(PHONE_NO,phoneNo);
        db.insert(USER_TABLE,null,cv);
        db.close();
    }

    public void addProperty(String propertyType, String bedRoom, String price,String location,String furnitureType,String remark,String reporterName){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String timestamp = date.format(new Date());

        cv.put(PROPERTY_TYPE,propertyType);
        cv.put(BEDROOM,bedRoom);
        cv.put(PRICE,price);
        cv.put(LOCATION,location);
        cv.put(FURNITURE_TYPE,furnitureType);
        cv.put(REMARK,remark);
        cv.put(REPORTER_NAME,reporterName);
        cv.put(ASSIGN_DATE,timestamp);
        db.insert(PROPERTY_TABLE,null,cv);
        db.close();
    }

    public ArrayList<PropertyModel> readProperty() throws ParseException {
        SQLiteDatabase db=getReadableDatabase();
        Cursor property_cursor=db.rawQuery("SELECT * FROM "+PROPERTY_TABLE,null);
        ArrayList<PropertyModel> propertyModelArrayList=new ArrayList<>();
        if(property_cursor.moveToFirst()){
            do{
                propertyModelArrayList.add(new PropertyModel(
                    property_cursor.getInt(0),
                        property_cursor.getString(1),
                        property_cursor.getString(2),
                        property_cursor.getString(3),
                        property_cursor.getString(4),
                        property_cursor.getString(5),
                        property_cursor.getString(6),
                        property_cursor.getString(7),
                        property_cursor.getString(8)
                ));
            }while (property_cursor.moveToNext());
        }
        return propertyModelArrayList;
    }

    public ArrayList<PropertyModel> searchProperty(String userName,String ref_id){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=null;
        cursor=db.rawQuery("SELECT * FROM "+PROPERTY_TABLE+" WHERE "+REPORTER_NAME+"=? AND "+REF_ID+"=?",new String[]{userName,ref_id});
        ArrayList<PropertyModel> propertySearchArrayList = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                propertySearchArrayList.add(new PropertyModel(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8)
                ));
            }while (cursor.moveToNext());
        }
        return propertySearchArrayList;
    }
    @SuppressLint("Range")
    public String getUserName(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        cursor = db.rawQuery("SELECT " + USER_NAME + " FROM " + USER_TABLE + " WHERE " + EMAIL + "=? AND " + PASSWORD + "=?", new String[]{email, password});

        if (cursor != null && cursor.moveToFirst()) {
            userName = cursor.getString(cursor.getColumnIndex(USER_NAME));
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return userName;
    }
    public void deleteProperty(String name,String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(PROPERTY_TABLE,"reporter_name=? AND ref_id=?",new String[]{name,id});
        db.close();
    }

    public void updateProperty(String ref_no,String propertyType, String bedRoom, String price,String location,String furnitureType,String remark){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(PROPERTY_TYPE,propertyType);
        cv.put(BEDROOM,bedRoom);
        cv.put(PRICE,price);
        cv.put(LOCATION,location);
        cv.put(FURNITURE_TYPE,furnitureType);
        cv.put(REMARK,remark);
        db.update(PROPERTY_TABLE,cv,"REF_ID=?",new String[]{ref_no});
        db.close();
    }
}
