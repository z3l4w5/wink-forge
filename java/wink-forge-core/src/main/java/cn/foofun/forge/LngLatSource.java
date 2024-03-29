package cn.foofun.forge;

import cn.foofun.forge.domain.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LngLatSource implements Source<Point> {
    @Override
    public Point next() {
        return randomLonLat(1.2, 1.2, 1.4, 1.4);
    }

    public Point randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat) {
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);

        BigDecimal lon = db.setScale(6, RoundingMode.HALF_UP);// 小数后6位

        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);

        BigDecimal lat = db.setScale(6, RoundingMode.HALF_UP);

        return new Point(lon, lat);
    }
}
