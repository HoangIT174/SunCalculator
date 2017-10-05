package suntime.swindroid.suncalculatorp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import suntime.swindroid.suncalculatorp.R;
import suntime.swindroid.suncalculatorp.adapter.SunAdapter;
import suntime.swindroid.suncalculatorp.util.Const;

/**
 * Created by giapmn on 10/5/17.
 */

public class Task2Activity extends Activity implements View.OnClickListener {

    private ListView lvSun;
    private Button btnAddNewLocation;
    private Button btnMyLocation;
    private Button btnViewMoreLocations;

    private SunAdapter sunAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_2);
        initViews();
        initData();
    }


    private void initViews() {
        btnAddNewLocation = (Button) findViewById(R.id.btn_add_new_location);
        btnAddNewLocation.setOnClickListener(this);
        btnMyLocation = (Button) findViewById(R.id.btn_search_my_location);
        btnMyLocation.setOnClickListener(this);
        btnViewMoreLocations = (Button) findViewById(R.id.btn_view_more_locations);
        btnViewMoreLocations.setOnClickListener(this);

        lvSun = (ListView) findViewById(R.id.lv_infor_sun);

    }

    private void initData() {
        sunAdapter = new SunAdapter(Task2Activity.this);
        lvSun.setAdapter(sunAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_new_location:
                Intent intent = new Intent(Task2Activity.this, AddLocationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_search_my_location:
                startMapActivity(Const.MAP_TYPE_MY_LOCATION);
                break;
            case R.id.btn_view_more_locations:
                startMapActivity(Const.MAP_TYPE_MORE_LOCATION);
                break;
        }
    }

    private void startMapActivity(int type) {
        Intent intenMap = new Intent(Task2Activity.this, MapActivity.class);
        intenMap.putExtra(Const.KEY_TYPE_MAP, type);
        startActivity(intenMap);
    }
}
