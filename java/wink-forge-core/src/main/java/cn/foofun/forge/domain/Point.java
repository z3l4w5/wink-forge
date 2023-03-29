package cn.foofun.forge.domain;

import java.math.BigDecimal;

/**
 * 经纬度点
 */
public class Point {
    private BigDecimal lng;

    private BigDecimal lat;

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public Point(BigDecimal lng, BigDecimal lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
