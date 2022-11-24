package cn.foofun.forge;

import cn.foofun.forge.domain.DateRange;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Forge {

    public static CompositeSourceBuilder buildMap(String key, Source<?> source) {
        CompositeSourceBuilder compositeSourceBuilder = new CompositeSourceBuilder();
        compositeSourceBuilder.add(key, source);

        return compositeSourceBuilder;
    }

    public static Source<Integer> buildInt(int min, int max) {

        return new IntSource(min, max, 1);
    }

    public static Source<String> buildFromClassPath(String fileName) throws IOException {

        InputStream inputStream = Forge.class.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new ForgeException("无法找到文件");
        }

        Source<String> source = new FileLinesStringSource(inputStream, Charset.defaultCharset(), 1);

        inputStream.close();

        return source;
    }

    public static Source<String> buildFromClassPath2(String fileName) throws IOException {

        InputStream inputStream = Forge.class.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new ForgeException("无法找到文件");
        }

        Source<String> source = new FileLinesStringSource(inputStream, Charset.defaultCharset(), 2);

        inputStream.close();

        return source;
    }

    public static <T> Source<T> combineSources(Source<T> source1, Source<T> source2, int rate1, int rate2) {

        return new SimpleTwoSources<>(source1, source2, rate1, rate2);
    }

    public static <T> Source<T> buildInValues(T... values) {

        return new ValueListSource<>(Arrays.asList(values));
    }

    public static Source<DateRange> buildInDays(Date begin, Date end, int minDays, int maxDays) {

        return new SimpleDateRangeSource(begin, end, minDays, maxDays);
    }

    public static <T> Source<T> buildBySql(Connection connection, String sql, String columnName, Class<T> clazz) throws SQLException {

        ResultSet resultSet = connection.createStatement().executeQuery(sql);

        List<T> values = new ArrayList<>();

        while (resultSet.next()) {
            T v = resultSet.getObject(columnName, clazz);
            values.add(v);
        }

        return new ValueListSource<>(values);
    }
}
