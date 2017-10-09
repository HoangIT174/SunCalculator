package suntime.swindroid.suncalculatorp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import suntime.swindroid.suncalculatorp.R;
import suntime.swindroid.suncalculatorp.database.DatabaseManager;

/**
 * Created by Hoang on 10/05/2017.
 */

public class AddLocationActivity extends Activity implements View.OnClickListener {

    private EditText edtCityName;
    private EditText edtLatitude;
    private EditText edtLongitude;
    private EditText edtTimeZone;
    private Button btnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_location);
        initViews();
    }

    private void initViews() {
        edtCityName = (EditText) findViewById(R.id.edi_city_name);
        edtLatitude = (EditText) findViewById(R.id.edt_latitude);
        edtLongitude = (EditText) findViewById(R.id.edt_longitude);
        edtTimeZone = (EditText) findViewById(R.id.edt_time_zone);
        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String cityName = edtCityName.getText().toString().trim();
        String latitude = edtLatitude.getText().toString().trim();
        String longitude = edtLongitude.getText().toString().trim();
        String timeZone = edtTimeZone.getText().toString().trim();

        if (!cityName.equals("")
                && !latitude.equals("")
                && !longitude.equals("")
                && !timeZone.equals("")) {
            boolean result = DatabaseManager.getInstance(AddLocationActivity.this).addNewLocation(cityName, latitude, longitude, timeZone);
            if (result) {
                Toast.makeText(AddLocationActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                edtCityName.setText("");
                edtLatitude.setText("");
                edtLongitude.setText("");
                edtTimeZone.setText("");
            } else {
                Toast.makeText(AddLocationActivity.this, "Error. Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(AddLocationActivity.this, "Please input data", Toast.LENGTH_SHORT).show();
        }
    }


}
