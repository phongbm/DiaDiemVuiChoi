package com.phongbm.diadiemvuichoi;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class DiaDiemDBManager {
    private static final String TAG = "DiaDiemDBManager";

    private static final String DATA_PATH = Environment.getDataDirectory()
            + "/data/com.phongbm.diadiemvuichoi/databases/";
    private static final String DATA_NAME = "DiaDiemVuiChoi";

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DiaDiemDBManager(Context context) {
        this.context = context;
        this.copyDatabaseFile();
    }

    private void copyDatabaseFile() {
        new File(DATA_PATH).mkdir();
        File file = new File(DATA_PATH + DATA_NAME);
        if (file.exists()) {
            return;
        }
        try {
            file.createNewFile();
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(DATA_NAME);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            int length = -1;
            byte[] data = new byte[1024];
            while ((length = inputStream.read(data)) > 0) {
                fileOutputStream.write(data, 0, length);
            }
            inputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATA_PATH + DATA_NAME, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDatabase() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public ArrayList<DiaDiem> getAllData() {
        this.openDatabase();
        ArrayList<DiaDiem> diaDiems = new ArrayList<>();
        int indexKhuVuc, indexTenDiaDiem, indexLinkHinh, indexDiaChi;
        String khuVuc, tenDiaDiem, linkHinh, diaChi;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM DiaDiemVuiChoi", null);
        if (cursor == null)
            return null;
        cursor.moveToFirst();
        indexKhuVuc = cursor.getColumnIndex("khu_vuc");
        indexTenDiaDiem = cursor.getColumnIndex("ten_dia_diem");
        indexLinkHinh = cursor.getColumnIndex("link_hinh");
        indexDiaChi = cursor.getColumnIndex("dia_chi");
        while (!cursor.isAfterLast()) {
            khuVuc = cursor.getString(indexKhuVuc);
            tenDiaDiem = cursor.getString(indexTenDiaDiem);
            linkHinh = cursor.getString(indexLinkHinh);
            diaChi = cursor.getString(indexDiaChi);
            diaDiems.add(new DiaDiem(khuVuc, tenDiaDiem, linkHinh, diaChi));
            cursor.moveToNext();
        }
        cursor.close();
        return diaDiems;
    }

    public ArrayList<DiaDiem> getHaNoiData() {
        this.openDatabase();
        ArrayList<DiaDiem> diaDiems = new ArrayList<>();
        int indexKhuVuc, indexTenDiaDiem, indexLinkHinh, indexDiaChi;
        String khuVuc, tenDiaDiem, linkHinh, diaChi;
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM DiaDiemVuiChoi WHERE khu_vuc LIKE 'Hà Nội'", null);
        if (cursor == null)
            return null;
        cursor.moveToFirst();
        indexKhuVuc = cursor.getColumnIndex("khu_vuc");
        indexTenDiaDiem = cursor.getColumnIndex("ten_dia_diem");
        indexLinkHinh = cursor.getColumnIndex("link_hinh");
        indexDiaChi = cursor.getColumnIndex("dia_chi");
        while (!cursor.isAfterLast()) {
            khuVuc = cursor.getString(indexKhuVuc);
            tenDiaDiem = cursor.getString(indexTenDiaDiem);
            linkHinh = cursor.getString(indexLinkHinh);
            diaChi = cursor.getString(indexDiaChi);
            diaDiems.add(new DiaDiem(khuVuc, tenDiaDiem, linkHinh, diaChi));
            cursor.moveToNext();
        }
        cursor.close();
        return diaDiems;
    }

    public ArrayList<DiaDiem> getHCMData() {
        this.openDatabase();
        ArrayList<DiaDiem> diaDiems = new ArrayList<>();
        int indexKhuVuc, indexTenDiaDiem, indexLinkHinh, indexDiaChi;
        String khuVuc, tenDiaDiem, linkHinh, diaChi;
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM DiaDiemVuiChoi WHERE khu_vuc LIKE 'Thành phố Hồ Chí Minh'", null);
        if (cursor == null)
            return null;
        cursor.moveToFirst();
        indexKhuVuc = cursor.getColumnIndex("khu_vuc");
        indexTenDiaDiem = cursor.getColumnIndex("ten_dia_diem");
        indexLinkHinh = cursor.getColumnIndex("link_hinh");
        indexDiaChi = cursor.getColumnIndex("dia_chi");
        while (!cursor.isAfterLast()) {
            khuVuc = cursor.getString(indexKhuVuc);
            tenDiaDiem = cursor.getString(indexTenDiaDiem);
            linkHinh = cursor.getString(indexLinkHinh);
            diaChi = cursor.getString(indexDiaChi);
            diaDiems.add(new DiaDiem(khuVuc, tenDiaDiem, linkHinh, diaChi));
            cursor.moveToNext();
        }
        cursor.close();
        return diaDiems;
    }


}