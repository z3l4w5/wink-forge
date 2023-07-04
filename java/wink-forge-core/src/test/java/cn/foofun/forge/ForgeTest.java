package cn.foofun.forge;

import cn.foofun.forge.domain.DateRange;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ForgeTest {

    @Test
    void mobileSource() {
        MobileSource source = new MobileSource();

        for (int i = 0; i < 100; i++) {
            String next = source.next();
            System.out.println(next);
        }
    }

    @Test
    void buildInt() {

        Source<Integer> source = Forge.buildInt(8, 80);

        for (int i = 0; i < 100; i++) {
            int next = source.next();
            System.out.println(next);
        }
    }

    @Test
    void buildFromClass() throws IOException {

        Source<String> source = Forge.buildFromClassPath("/forge/岗位名称.txt");

        for (int i = 0; i < 100; i++) {
            String next = source.next();
            System.out.println(next);
        }
    }

    @Test
    void buildInValues() {

        Source<String> source = Forge.buildInValues("全职", "兼职", "实习", "临时", "小时工");

        for (int i = 0; i < 100; i++) {
            String next = source.next();
            System.out.println(next);
        }
    }

    @Test
    void combineSources() throws IOException {

        Source<String> source = Forge.combineSources(Forge.buildFromClassPath("/forge/男名.txt"), Forge.buildFromClassPath("/forge/女名.txt"), 2, 1);

        for (int i = 0; i < 100; i++) {
            String next = source.next();
            System.out.println(next);
        }
    }

    @Test
    void buildInDays() {

        Date begin = new Date();
        Date end = new Date(begin.getTime() + 10 * 1000 * 60 * 60 * 24);

        Source<DateRange> source = Forge.buildInDays(begin, end, 30, 31);

        for (int i = 0; i < 100; i++) {
            DateRange next = source.next();
            System.out.println(next);
        }
    }

    @Test
    void buildBySql() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://***:63306/***?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8", "***", "***");

        Source<String> source = Forge.buildBySql(connection, "select ZDMC from aac01 where ZDLB ='ACA111'", "ZDMC", String.class);

        for (int i = 0; i < 100; i++) {
            String next = source.next();
            System.out.println(next);
        }

        connection.close();
    }

    @Test
    void buildRateTest() {

        Source<String> source = Forge.valuesInRate(Arrays.asList("A", "B"), Arrays.asList(1, 5));

        for (int i = 0; i < 100; i++) {
            String next = source.next();
            System.out.println(next);
        }
    }
}