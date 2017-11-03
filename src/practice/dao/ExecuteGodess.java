package practice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import practice.jdbc.DBUtil;
import practice.model.Godess;

public class ExecuteGodess {
	
	public void addGodess(Godess godess) {
		
		String sql = ""+
				"insert practice (username,sex,age,birthday,email,mobile,create_user,"+
				"create_date,update_user,update_date,isdel) values (?,?,?,?,?,?,?,"+
				"current_date(),?,current_date(),?)";
		// 获得数据库连接
		Connection conn = DBUtil.getConnection();
		// 操作数据库
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			prep.setString(1, godess.getUsername());
			prep.setString(2, godess.getSex());
			prep.setInt(3, godess.getAge());
			prep.setDate(4, new Date(godess.getBirthday().getTime()));
			prep.setString(5, godess.getEmail());
			prep.setString(6, godess.getMobile());
			prep.setString(7, godess.getCreate_user());
			prep.setString(8, godess.getUpdate_user());
			prep.setBoolean(9, godess.isDel());
			
			prep.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	
	public List<Godess> query() throws SQLException{
		
		//获得数据库连接
		Connection conn = DBUtil.getConnection();
		//操作数据库
		StringBuilder sb = new StringBuilder();
		sb.append("select * from practice");
		
		PreparedStatement  state = conn.prepareStatement(sb.toString());
		
		ResultSet rset = state.executeQuery();
		//创建集合
		List<Godess> godessList = new ArrayList<>();
		Godess g = null;
		//设置Goddess对象属性
		while (rset.next()) {
			g = new Godess();
			g.setId(rset.getInt("id"));
			g.setUsername(rset.getString("username"));
			g.setSex(rset.getString("sex"));
			g.setAge(rset.getInt("age"));
			g.setBirthday(rset.getDate("birthday"));
			g.setEmail(rset.getString("email"));
			g.setMobile(rset.getString("mobile"));
			g.setCreate_user(rset.getString("create_user"));
			g.setCreate_date(rset.getDate("create_date"));
			g.setUpdate_user(rset.getString("update_user"));
			g.setUpdate_date(rset.getDate("update_date"));
			g.setDel(rset.getBoolean("isdel"));
			godessList.add(g);
		}
		
		return godessList;
	}
	
	public List<Godess> query(List<Map<String,Object>> params) throws SQLException{
		
		//获得数据库连接
		Connection conn = DBUtil.getConnection();
		//操作数据库
		StringBuilder sb = new StringBuilder();
		sb.append("select * from practice where 1=1 ");
		
		for (int i = 0; i < params.size(); i++) {
			Map<String, Object> map = params.get(i);
			sb.append(" and "+" "+map.get("name")+" "+map.get("rela")+" "+map.get("value")+" ");
		}
		
		PreparedStatement  state = conn.prepareStatement(sb.toString());
		System.out.println(sb.toString());
		
		ResultSet rset = state.executeQuery();
		//创建集合
		List<Godess> godessList = new ArrayList<>();
		Godess g = null;
		//设置Goddess对象属性
		while (rset.next()) {
			g = new Godess();
			g.setId(rset.getInt("id"));
			g.setUsername(rset.getString("username"));
			g.setSex(rset.getString("sex"));
			g.setAge(rset.getInt("age"));
			g.setBirthday(rset.getDate("birthday"));
			g.setEmail(rset.getString("email"));
			g.setMobile(rset.getString("mobile"));
			g.setCreate_user(rset.getString("create_user"));
			g.setCreate_date(rset.getDate("create_date"));
			g.setUpdate_user(rset.getString("update_user"));
			g.setUpdate_date(rset.getDate("update_date"));
			g.setDel(rset.getBoolean("isdel"));
			godessList.add(g);
		}
		
		return godessList;
	}
	
	public void upGodess(Godess godess) {
		String sql = ""+
				"update practice set"
				+ " username = ?,sex = ?,age = ?,birthday = ?,email = ?,mobile = ?,"
				+ "update_user = ?,update_date = current_date(),isdel = ?"
				+ " where id = ?";
		// 获得数据库连接
		Connection conn = DBUtil.getConnection();
		// 操作数据库
		try {
			PreparedStatement prep = conn.prepareStatement(sql);
			
			prep.setString(1, godess.getUsername());
			prep.setString(2, godess.getSex());
			prep.setInt(3, godess.getAge());
			prep.setDate(4, new Date(godess.getBirthday().getTime()));
			prep.setString(5, godess.getEmail());
			prep.setString(6, godess.getMobile());
			prep.setString(7, godess.getUpdate_user());
			prep.setBoolean(8, godess.isDel());
			prep.setInt(9, godess.getId());
			
			prep.execute();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
	}
	
	public void delGodess(Integer id) {
		String sql = "delete from practice where id = ?";
		//连接数据库
		Connection conn = DBUtil.getConnection();
		
		try {
			PreparedStatement  prep = conn.prepareStatement(sql);
			prep.setInt(1, id);
			
			prep.execute();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	public Godess queOneGodess(Integer id) throws SQLException {
		String sql = "select * from practice where id = ?";
		
		//连接数据库
		Connection conn = DBUtil.getConnection();
		
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.setInt(1, id);

		ResultSet rset = prep.executeQuery();
		Godess g = null;
		while (rset.next()) {
			g = new Godess();
			g.setId(id);
			g.setUsername(rset.getString("username"));
			g.setSex(rset.getString("sex"));
			g.setAge(rset.getInt("age"));
			g.setBirthday(rset.getDate("birthday"));
			g.setEmail(rset.getString("email"));
			g.setMobile(rset.getString("mobile"));
			g.setCreate_user(rset.getString("create_user"));
			g.setCreate_date(rset.getDate("create_date"));
			g.setUpdate_user(rset.getString("update_user"));
			g.setUpdate_date(rset.getDate("update_date"));
			g.setDel(rset.getBoolean("isdel"));
		}
		return g;
	}
}
