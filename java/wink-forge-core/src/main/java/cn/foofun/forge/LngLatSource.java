package cn.foofun.forge;

import cn.foofun.forge.domain.Point;

import java.math.BigDecimal;

public class LngLatSource implements Source<Point> {
    @Override
    public Point next() {
        return randomLonLat(1.2, 1.2, 1.4, 1.4);
    }

    /**
     * @param MinLon：最新经度 MaxLon： 最大经度   MinLat：最新纬度   MaxLat：最大纬度
     * @return
     * @throws
     * @Title: randomLonLat
     * @Description: 在矩形内随机生成经纬度
     */
    public Point randomLonLat(double MinLon, double MaxLon, double MinLat, double MaxLat) {
        BigDecimal db = new BigDecimal(Math.random() * (MaxLon - MinLon) + MinLon);

        BigDecimal lon = db.setScale(6, BigDecimal.ROUND_HALF_UP);// 小数后6位

        db = new BigDecimal(Math.random() * (MaxLat - MinLat) + MinLat);

        BigDecimal lat = db.setScale(6, BigDecimal.ROUND_HALF_UP);

        return new Point(lon, lat);
    }
}
