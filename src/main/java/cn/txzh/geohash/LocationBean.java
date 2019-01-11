package cn.txzh.geohash;

/**
 * Created by leizhao_3 on 2019/1/7.
 */
public class LocationBean {
    public static final double MIN_LAT = -90;
    public static final double MAX_LAT = 90;
    public static final double MIN_LNG = -180;
    public static final double MAX_LNG = 180;

    private double lat;//纬度[-90,90]
    private double lng;//经度[-180,180]

    public LocationBean(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLng() {
        return lng;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }
}
