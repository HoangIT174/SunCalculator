package suntime.swindroid.suncalculatorp.item;

/**
 * Created by Hoang on 10/05/2017.
 */

public class ItemSun {

    private String date;
    private String sunRise;
    private String sunSet;

    public ItemSun(String date, String sunRise, String sunSet) {
        this.date = date;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunRise() {
        return sunRise;
    }

    public void setSunRise(String sunRise) {
        this.sunRise = sunRise;
    }

    public String getSunSet() {
        return sunSet;
    }

    public void setSunSet(String sunSet) {
        this.sunSet = sunSet;
    }
}
