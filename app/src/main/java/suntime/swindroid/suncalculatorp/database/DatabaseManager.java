package suntime.swindroid.suncalculatorp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import suntime.swindroid.suncalculatorp.item.ItemLocation;

/**
 * Created by giapmn on 10/5/17.
 */

public class DatabaseManager {
    public static final String DATA_PATH = Environment.getDataDirectory().getPath() + "/data/" + "suntime.swindroid.suncalculatorp/database";
    public static final String DATA_NAME = "SunCalculator.sqlite";

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseManager(Context context) {
        this.context = context;
        copyDatabase();
    }

    private void copyDatabase() {
        new File(DATA_PATH).mkdir();
        File file = new File(DATA_PATH + "/" + DATA_NAME);
        if (file.exists()) {
            return;
        } else {
            try {
                file.createNewFile();
                InputStream input = context.getAssets().open(DATA_NAME);
                FileOutputStream output = new FileOutputStream(file);

                byte data[] = new byte[1024];
                int len = 0;

                while ((len = input.read(data)) > 0) {
                    output.write(data, 0, len);
                }

                input.close();
                output.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void opendDatabase() {
        if (sqLiteDatabase == null || sqLiteDatabase.isOpen() == false) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(DATA_PATH + "/" + DATA_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        }
    }

    public void closeDatabase() {
        if (sqLiteDatabase.isOpen() == true || sqLiteDatabase != null) {
            sqLiteDatabase.close();
        }
    }


    public ArrayList<ItemLocation> getLocation() {
        ArrayList<ItemLocation> arrLocation = new ArrayList<>();
        opendDatabase();

        String sql = "SELECT * FROM LOCATION";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor == null) {

        } else {
            cursor.moveToFirst();

            while (cursor.isAfterLast() == false) {
                String cityName = cursor.getString(cursor.getColumnIndex("CityName"));
                String latitude = cursor.getString(cursor.getColumnIndex("Latitude"));
                String longitude = cursor.getString(cursor.getColumnIndex("Longitude"));
                String timeZone = cursor.getString(cursor.getColumnIndex("TimeZone"));

                arrLocation.add(new ItemLocation(cityName.trim(), latitude.trim(), longitude.trim(), timeZone.trim()));
                cursor.moveToNext();
            }
        }
        return arrLocation;
    }
}
