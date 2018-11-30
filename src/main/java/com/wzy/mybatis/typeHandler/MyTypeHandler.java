package com.wzy.mybatis.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTypeHandler extends BaseTypeHandler<String> {

    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i , parameter + " with my handlers");
    }

    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
