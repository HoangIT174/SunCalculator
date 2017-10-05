package suntime.swindroid.suncalculatorp.item;

/**
 * Created by giapmn on 10/5/17.
 */

public class ItemLocation {

    String cityName;
    String latitude;
    String longitude;
    String timeZone;

    public ItemLocation(String cityName, String latitude, String longitude, String timeZone) {
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeZone = timeZone;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
