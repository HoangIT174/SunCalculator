package suntime.swindroid.suncalculatorp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import suntime.swindroid.suncalculatorp.R;
import suntime.swindroid.suncalculatorp.calc.AstronomicalCalendar;
import suntime.swindroid.suncalculatorp.calc.GeoLocation;
import suntime.swindroid.suncalculatorp.database.DatabaseManager;
import suntime.swindroid.suncalculatorp.dialog.DialogNotification;
import suntime.swindroid.suncalculatorp.item.ItemLocation;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAGG = MainActivity.class.getSimpleName();

    private AutoCompleteTextView edtInputLocation;
    private Button btnSeach;
    private TextView tvLocation;
    private TextView tvSunRise;
    private TextView tvSunSet;
    private DatePicker datePicker;

    private Button btnAdvance;


    private DatabaseManager databaseManager;
    private ArrayList<ItemLocation> arrDataLocation;
    private List<String> dataLocation;

    private String cityName;
    private double latitude, longitude;
    private TimeZone timeZone;
    private int day;
    private int year;
    private int month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();

    }

    private void initViews() {
        edtInputLocation = (AutoCompleteTextView) findViewById(R.id.edt_input_location);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, dataLocation);
        edtInputLocation.setThreshold(1);
        edtInputLocation.setAdapter(adapter);
        edtInputLocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                for (ItemLocation item : arrDataLocation) {
                    if (edtInputLocation.getText().toString().trim().equals(item.getCityName())) {
                        cityName = item.getCityName();
                        latitude = Double.parseDouble(item.getLatitude());
                        longitude = Double.parseDouble(item.getLongitude());
                        timeZone = TimeZone.getTimeZone(item.getTimeZone());
                    }
                }

                updateTime(year, month, day);
            }
        });

        btnSeach = (Button) findViewById(R.id.btn_search_location);
        btnSeach.setOnClickListener(this);
        btnAdvance = (Button) findViewById(R.id.btnAdvance);
        btnAdvance.setOnClickListener(this);

        tvLocation = (TextView) findViewById(R.id.tv_display_location);
        tvSunRise = (TextView) findViewById(R.id.tv_display_time_sun_rise);
        tvSunSet = (TextView) findViewById(R.id.tv_display_time_sun_set);

        datePicker = (DatePicker) findViewById(R.id.date_picker);

        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year, month, day, dateChangeHandler);


        timeZone = TimeZone.getDefault();
        cityName = "Melbourne";
        latitude = -37.50;
        longitude = 145.01;
        updateTime(year, month, day);
    }

    private void initData() {
        databaseManager = new DatabaseManager(this);
        arrDataLocation = new ArrayList<>();
        dataLocation = new ArrayList<>();
        arrDataLocation.addAll(databaseManager.getLocation());
        for (ItemLocation item : arrDataLocation) {
            dataLocation.add(item.getCityName());
        }
    }


    private void updateTime(int year, int monthOfYear, int dayOfMonth) {
        GeoLocation geolocation = new GeoLocation(cityName, latitude, longitude, timeZone);
        AstronomicalCalendar ac = new AstronomicalCalendar(geolocation);
        ac.getCalendar().set(year, monthOfYear, dayOfMonth);
        Date srise = ac.getSunrise();
        Date sset = ac.getSunset();

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        tvSunRise.setText(sdf.format(srise));
        tvSunSet.setText(sdf.format(sset));
        tvLocation.setText(cityName);
        Log.d(TAGG, dayOfMonth + "/" + monthOfYear + "/" + year + ", (" + cityName + ", "
                + latitude + ", " + longitude + ", " + timeZone.getDisplayName() + ")");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search_location:
                actionSearchLocation();
                break;
            case R.id.btnAdvance:
                Intent intent = new Intent(MainActivity.this, Task2Activity.class);
                startActivity(intent);
                break;
        }
    }

    private void actionSearchLocation() {
        boolean isResult = false;
        for (ItemLocation item : arrDataLocation) {
            if (edtInputLocation.getText().toString().trim().toLowerCase().equals(item.getCityName().toLowerCase())) {
                isResult = true;
                cityName = item.getCityName();
                latitude = Double.parseDouble(item.getLatitude());
                longitude = Double.parseDouble(item.getLongitude());
                timeZone = TimeZone.getTimeZone(item.getTimeZone());
            }
        }

        if (!isResult) {
            DialogNotification dialogNotification = new DialogNotification(MainActivity.this);
            dialogNotification.setTitle("Information");
            dialogNotification.setContentMessage("Location not found. Please try again!");
            dialogNotification.setHiddenBtnExit();
            dialogNotification.show();
        } else {
            updateTime(year, month, day);
        }
    }

    private DatePicker.OnDateChangedListener dateChangeHandler = new DatePicker.OnDateChangedListener() {
        public void onDateChanged(DatePicker dp, int year, int monthOfYear, int dayOfMonth) {
            MainActivity.this.year = year;
            MainActivity.this.month = monthOfYear;
            MainActivity.this.day = dayOfMonth;
            updateTime(year, monthOfYear, dayOfMonth);
        }
    };


}
