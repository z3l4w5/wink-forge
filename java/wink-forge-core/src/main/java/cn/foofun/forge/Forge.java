package cn.foofun.forge;

import cn.foofun.forge.domain.DateRange;
import cn.foofun.forge.json.JsonArraySource;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Forge {

    public static <T> Source<T> random(Source<T> source, int count) {
        return new RandomSource<T>(source, count);
    }

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

    public static Source<JSONObject> jsonFromClassPath(String fileName) throws IOException {

        InputStream inputStream = Forge.class.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new ForgeException("无法找到文件");
        }

        JSONArray jsonArray = JSON.parseArray(inputStream);

        Source<JSONObject> source = new JsonArraySource(jsonArray);

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

        return new SimpleTwoSources<T>(source1, source2, rate1, rate2);
    }

    public static <T> Source<T> valuesInRate(List<T> values, List<Integer> rates) {

        return new ValuesInRateSource<T>(values, rates);
    }

    public static <T> Source<T> buildInValues(T... values) {

        return new ValueListSource<T>(Arrays.asList(values));
    }

    public static <T> Source<T> buildFromList(List<T> list) {

        return new ValueListSource<T>(list);
    }

    public static Source<DateRange> buildInDays(Date begin, Date end, int minDays, int maxDays) {

        return new SimpleDateRangeSource(begin, end, minDays, maxDays);
    }

    public static <T> Source<T> buildBySql(Connection connection, String sql, String columnName, Class<T> clazz) throws SQLException {

        ResultSet resultSet = connection.createStatement().executeQuery(sql);

        List<T> values = new ArrayList<T>();

        while (resultSet.next()) {
            T v = resultSet.getObject(columnName, clazz);
            values.add(v);
        }

        return new ValueListSource<T>(values);
    }
}
