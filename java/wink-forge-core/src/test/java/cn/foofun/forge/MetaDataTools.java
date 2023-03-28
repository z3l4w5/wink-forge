package cn.foofun.forge;

import cn.foofun.forge.jdbc.ColumnMetaData;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetaDataTools {

    @Test
    void buildBySql() throws SQLException, ClassNotFoundException {


        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://223.65.201.234:63306/yzjycy?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8", "yzjycy", "yzjycy");

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        ResultSet tableResultSet = databaseMetaData.getTables(null, null, "%", null);

        while (tableResultSet.next()) {
            String tableName = tableResultSet.getString("TABLE_NAME");

            readTable(databaseMetaData, tableName);
        }

        connection.close();
    }

    @Test
    void readTable() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://223.65.201.234:63306", "yzjsjxygl", "winksoft@2022");

        DatabaseMetaData databaseMetaData = connection.getMetaData();

        List<ColumnMetaData> columns = readTable(databaseMetaData, "jsj_corp_main");

        for (ColumnMetaData column : columns) {
            System.out.println(column.getName());
        }

        System.out.println();

        for (ColumnMetaData column : columns) {
            System.out.println(column.getType());
        }

        System.out.println();

        for (ColumnMetaData column : columns) {
            System.out.println(column.getLength());
        }

        System.out.println();

        for (ColumnMetaData column : columns) {
            System.out.println(column.getComment());
        }
    }

    private List<ColumnMetaData> readTable(DatabaseMetaData databaseMetaData, String tableName) throws SQLException {
        ResultSet columnResultSet = databaseMetaData.getColumns(null, "%", tableName, "%");

        List<ColumnMetaData> columns = new ArrayList<ColumnMetaData>();

        while (columnResultSet.next()) {

            ColumnMetaData column = new ColumnMetaData();

            String columnName = columnResultSet.getString("COLUMN_NAME");
            column.setName(columnName);

            int columnType = columnResultSet.getInt("DATA_TYPE");
            String typeName = JDBCType.valueOf(columnType).toString();
            column.setType(typeName);

            int size = columnResultSet.getInt("COLUMN_SIZE");
            column.setLength(size);

            int nullable = columnResultSet.getInt("NULLABLE");
            column.setNullable(nullable > 0);

            String remark = columnResultSet.getString("REMARKS");
            column.setComment(remark);

            columns.add(column);
        }

        return columns;
    }
}