package com.ym.ssm.chapter4.typehandler;

import com.ym.ssm.chapter4.enumeration.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SexEnumTypeHandler implements TypeHandler<SexEnum> {


	public void setParameter(PreparedStatement ps, int i, SexEnum parameter,
			JdbcType jdbcType) throws SQLException {
		ps.setInt(i, parameter.getId());
	}


	public SexEnum getResult(ResultSet rs, String columnName)
			throws SQLException {
		int id = rs.getInt(columnName);
		return SexEnum.getSexById(id);
	}


   public SexEnum getResult(ResultSet rs, int columnIndex) throws SQLException {
		int id = rs.getInt(columnIndex);
		return SexEnum.getSexById(id);
	}


	public SexEnum getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		int id = cs.getInt(columnIndex);
		return SexEnum.getSexById(id);
	}

}
