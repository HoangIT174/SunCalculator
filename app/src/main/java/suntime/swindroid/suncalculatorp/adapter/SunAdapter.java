package suntime.swindroid.suncalculatorp.adapter;

import android.content.Context;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import suntime.swindroid.suncalculatorp.R;
import suntime.swindroid.suncalculatorp.item.ItemSun;

/**
 * Created by Hoang on 10/05/2017.
 */

public class SunAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ItemSun> arrData = new ArrayList<>();
    private LayoutInflater inflater;

    public SunAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        arrData.add(new ItemSun("01/10/2017", "05:53", "17:49"));
        arrData.add(new ItemSun("02/10/2017", "05:54", "17:50"));
        arrData.add(new ItemSun("03/10/2017", "05:55", "17:51"));
        arrData.add(new ItemSun("04/10/2017", "05:56", "17:52"));
        arrData.add(new ItemSun("05/10/2017", "05:57", "17:53"));


    }


    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public ItemSun getItem(int position) {
        return arrData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_sun, null, false);
            holder = new ViewHolder();

            holder.tvDate = (TextView) view.findViewById(R.id.tv_date);
            holder.tvSunRise = (TextView) view.findViewById(R.id.tv_sun_rise);
            holder.tvSunSet = (TextView) view.findViewById(R.id.tv_sun_set);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ItemSun itemSun = arrData.get(position);

        holder.tvDate.setText(itemSun.getDate());
        holder.tvSunRise.setText(itemSun.getSunRise());
        holder.tvSunSet.setText(itemSun.getSunSet());


        return view;
    }

    private class ViewHolder {
        private TextView tvDate;
        private TextView tvSunRise;
        private TextView tvSunSet;

    }
}
