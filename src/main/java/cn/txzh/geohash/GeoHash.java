package cn.txzh.geohash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by leizhao_3 on 2019/1/7.
 */
public class GeoHash {
    private LocationBean location;
    /**
     * 1 2500km;2 630km;3 78km;4 30km
     * 5 2.4km; 6 610m; 7 76m; 8 19m
     */
    private int hashLength = 6; //经纬度转化为geohash长度
    private int latLength = 20; //纬度转化为二进制长度
    private int lngLength = 20; //经度转化为二进制长度

    private double minLat;//每格纬度的单位大小
    private double minLng;//每个经度的大小

    private static final char[] CHARS = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm', 'n',
            'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};


    public GeoHash(double lat, double lng) {
        location = new LocationBean(lat, lng);
        setHashLength(hashLength);
        setMinLatLng();
    }

    public GeoHash(double lat, double lng, int hashLength) {
        location = new LocationBean(lat, lng);
        setHashLength(hashLength);
        setMinLatLng();
    }

    public int getHashLength() {
        return hashLength;
    }

    /**
     * 设置经纬度的最小单位
     */
    private void setMinLatLng() {
        minLat = LocationBean.MAX_LAT - LocationBean.MIN_LAT;
        for (int i = 0; i < latLength; i++) {
            minLat /= 2.0;
        }
        minLng = LocationBean.MAX_LNG - LocationBean.MIN_LNG;
        for (int i = 0; i < lngLength; i++) {
            minLng /= 2.0;
        }
    }

    /**
     * 求所在坐标点及周围点组成的九个
     * @return 当前GeoHash区域及其周围8个区域
     */
    public List<String> getGeoHashBase32For9() {
        double leftLat = location.getLat() - minLat;
        double rightLat = location.getLat() + minLat;
        double upLng = location.getLng() - minLng;
        double downLng = location.getLng() + minLng;
        List<String> base32For9 = new ArrayList<String>();
        //左侧从上到下 3个
        String leftUp = getGeoHashBase32(leftLat, upLng);
        if (!(leftUp == null || "".equals(leftUp))) {
            base32For9.add(leftUp);
        }
        String leftMid = getGeoHashBase32(leftLat, location.getLng());
        if (!(leftMid == null || "".equals(leftMid))) {
            base32For9.add(leftMid);
        }
        String leftDown = getGeoHashBase32(leftLat, downLng);
        if (!(leftDown == null || "".equals(leftDown))) {
            base32For9.add(leftDown);
        }
        //中间从上到下 3个
        String midUp = getGeoHashBase32(location.getLat(), upLng);
        if (!(midUp == null || "".equals(midUp))) {
            base32For9.add(midUp);
        }
        String midMid = getGeoHashBase32(location.getLat(), location.getLng());
        if (!(midMid == null || "".equals(midMid))) {
            base32For9.add(midMid);
        }
        String midDown = getGeoHashBase32(location.getLat(), downLng);
        if (!(midDown == null || "".equals(midDown))) {
            base32For9.add(midDown);
        }
        //右侧从上到下 3个
        String rightUp = getGeoHashBase32(rightLat, upLng);
        if (!(rightUp == null || "".equals(rightUp))) {
            base32For9.add(rightUp);
        }
        String rightMid = getGeoHashBase32(rightLat, location.getLng());
        if (!(rightMid == null || "".equals(rightMid))) {
            base32For9.add(rightMid);
        }
        String rightDown = getGeoHashBase32(rightLat, downLng);
        if (!(rightDown == null || "".equals(rightDown))) {
            base32For9.add(rightDown);
        }
        return base32For9;
    }

    /**
     * 获取周围二十五格(以该点为中心)hashkey
     * @return 当前GeoHash区域及其周围24个区域
     */
    public List<String> getGeoHashBase32For25() {
        List<String> base32For25 = new ArrayList<String>();
        for (int row = -2; row <= 2; row++) {
            for (int column = -2; column <= 2; column++) {
                double lng = location.getLng() + minLng * row;
                double lat = location.getLat() + minLat * column;
                String hashKey = getGeoHashBase32(lat, lng);
                if (!(hashKey == null || "".equals(hashKey))) {
                    base32For25.add(hashKey);
                }
            }
        }
        return base32For25;
    }

    /**
     * 设置经纬度转化为geoHash长度
     * @param length geoHash长度
     * @return 设置成功
     */
    private boolean setHashLength(int length) {
        if (length < 1) {
            return false;
        }
        hashLength = length;
        latLength = (length * 5) / 2;
        if (length % 2 == 0) {
            lngLength = latLength;
        } else {
            lngLength = latLength + 1;
        }
        setMinLatLng();
        return true;
    }

    /**
     * 获取经纬度的base32字符串
     * @return
     */
    public String getGeoHashBase32() {
        return getGeoHashBase32(location.getLat(), location.getLng());
    }

    /**
     * 获取经纬度的base32字符串
     *
     * @param lat
     * @param lng
     * @return
     */
    private String getGeoHashBase32(double lat, double lng) {
        boolean[] bools = getGeoBinary(lat, lng);
        if (bools == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bools.length; i = i + 5) {
            boolean[] base32 = new boolean[5];
            for (int j = 0; j < 5; j++) {
                base32[j] = bools[i + j];
            }
            char cha = getBase32Char(base32);
            if (' ' == cha) {
                return null;
            }
            sb.append(cha);
        }
        return sb.toString();
    }

    /**
     * 将五位二进制转化为base32
     * @param base32
     * @return
     */
    private char getBase32Char(boolean[] base32) {
        if (base32 == null || base32.length != 5) {
            return ' ';
        }
        int num = 0;
        for (boolean bool : base32) {
            num <<= 1;
            if (bool) {
                num += 1;
            }
        }
        return CHARS[num % CHARS.length];
    }

    /**
     * 获取坐标的geo二进制字符串
     * @param lat
     * @param lng
     * @return
     */
    private boolean[] getGeoBinary(double lat, double lng) {
        boolean[] latArray = getHashArray(lat, LocationBean.MIN_LAT, LocationBean.MAX_LAT, latLength);
        boolean[] lngArray = getHashArray(lng, LocationBean.MIN_LNG, LocationBean.MAX_LNG, lngLength);
        return merge(latArray, lngArray);
    }

    /**
     * 合并经纬度二进制
     * @param latArray
     * @param lngArray
     * @return
     */
    private boolean[] merge(boolean[] latArray, boolean[] lngArray) {
        if (latArray == null || lngArray == null) {
            return null;
        }
        boolean[] result = new boolean[lngArray.length + latArray.length];
        Arrays.fill(result, false);
        for (int i = 0; i < lngArray.length; i++) {
            result[2 * i] = lngArray[i];
        }
        for (int i = 0; i < latArray.length; i++) {
            result[2 * i + 1] = latArray[i];
        }
        return result;
    }

    /**
     * 将数字转化为geohash二进制字符串
     * @param value
     * @param min
     * @param max
     * @param length
     * @return
     */
    private boolean[] getHashArray(double value, double min, double max, int length) {
        if (value < min || value > max) {
            return null;
        }
        if (length < 1) {
            return null;
        }
        boolean[] result = new boolean[length];
        for (int i = 0; i < length; i++) {
            double mid = (min + max) / 2.0;
            if (value > mid) {
                result[i] = true;
                min = mid;
            } else {
                result[i] = false;
                max = mid;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GeoHash g = new GeoHash(40.222012, 116.248283, 5);
        System.out.println(g.getGeoHashBase32());
        System.out.println((g.getGeoHashBase32For9()));
    }

}
