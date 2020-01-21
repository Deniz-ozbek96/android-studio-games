package com.loopwiki.shadowsgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Sqliteopenhelper sınıfı getreadabledatabase ile okuma getwriteabledatabasele yazma yapar database objelerinde
public class SqliteHelper extends SQLiteOpenHelper {

    //Veritabnı adı
    public static final String DATABASE_NAME = "loopwiki.com";

    //Database versiyonu
    public static final int DATABASE_VERSION = 1;

    //Tablo adı
    public static final String TABLE_USERS = "users";

    //TABLE USERS COLUMNS
    //ID sütunu @primaryKey
    public static final String KEY_ID = "id";

    //user name sütunu
    public static final String KEY_USER_NAME = "username";

    //email sütunu
    public static final String KEY_EMAIL = "email";

    //password sütunu
    public static final String KEY_PASSWORD = "password";

    //SQL için kullanıcı tablosu oluşturulur
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT"
            + " ) ";


    public SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //veritabanı yoksa oluşturulması sağlanır onCreate metoduyla
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    //veritabanı versiyonunda güncelleme varsa veritabanı şemasını günceller onUpgrade metoduyla
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //bu metodla kullanıcıları kullanıcı tablosuna ekleriz
    public void addUser(User user) {

        //yazılanları veritabanına getirir
        SQLiteDatabase db = this.getWritableDatabase();

        //içerik değerleri oluşturur insert(ekleme)yapmak için
        ContentValues values = new ContentValues();

        //username i  @values içine koyar
        values.put(KEY_USER_NAME, user.userName);

        //email i  @values içine koyar
        values.put(KEY_EMAIL, user.email);

        //password u  @values içine koyar
        values.put(KEY_PASSWORD, user.password);

        // row(satır)insert edilir
        long todo_id = db.insert(TABLE_USERS, null, values);
    }
    //kimlik doğrulaması
    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Tablo seçimi
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//sorgulanmak istenen sütun seçilir
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where seçimi
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //eğer cursor objesi bir değere sahipse kullanıcı veritabanında bu verilen e-posta ile ilişkili kullanıcı var demektir
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Her iki şifreyi eşleştirip aynı olup olmadıklarını kontrol edilir
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        //kullanıcı şifresi eşleşmiyorsa veya bu e-postada bir kayıt yoksa, geri dönün. @false
        return null;
    }
    //Email var mı yok mu kontrolu
    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Tablo seçimi
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//sorgulamak istenen sütun seçilir
                KEY_EMAIL + "=?",
                new String[]{email},//Where seçimi
                null, null, null);

        if (cursor != null && cursor.moveToFirst() && cursor.getCount() > 0) {
            //eğer cursor objesi bir değere sahipse kullanıcı veritabanında bu verilen e-posta ile ilişkili kullanıcı var demektir yani doğru döndür
            return true;
        }

        //eğer yoksa yanlış döndür
        return false;
    }
}
