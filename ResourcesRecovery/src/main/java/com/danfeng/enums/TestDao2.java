package com.danfeng.enums;

import java.sql.SQLException;

import java.util.List;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Configuration
public class TestDao2 {

	// public static int addUser(String username){
	// String sql="insert into user_info(user_id,display_name) values(?,?)";
	// List<Object> params = new ArrayList<Object>();
	// params.add(55);
	// params.add(username);
	// return DB.executeUpdate(sql, params);
	// }
	//
	// public static List<String> getStationByRouteId(int routeId){
	// String sql="SHOW VARIABLES WHERE Variable_name LIKE 'character_set_%' OR
	// Variable_name LIKE 'collation%';";
	// List<String> routeList= new ArrayList<String>();
	// List<Object> params = new ArrayList<Object>();
	// ResultSet rs = DB.executeQuery(sql, params);
	// if(rs==null) return null;
	// try {
	// while(rs != null && rs.next()) {
	// System.out.println(rs.getString("Variable_name"));
	// System.out.println(rs.getString("Value"));
	// }
	// return routeList;
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }finally {
	// try {
	// rs.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// DB.close();
	// }
	// return routeList;
	// }

//	@Autowired
//	private DataSource dataSource;
//
//	@Bean
//	public List<String> test() throws SQLException {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		// jdbcTemplate.execute("SHOW VARIABLES WHERE Variable_name LIKE
//		// 'character_set_%' OR Variable_name LIKE 'collation%';");
//		//System.out.println(dataSource.getLoginTimeout());
//		return jdbcTemplate.queryForList("select display_name from user_info", String.class);
//
//	}
//	
//	@Bean
//	public int test2() throws SQLException {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		// jdbcTemplate.execute("SHOW VARIABLES WHERE Variable_name LIKE
//		// 'character_set_%' OR Variable_name LIKE 'collation%';");
//		//System.out.println("test2"+dataSource.getLoginTimeout());
//		return jdbcTemplate.update("insert into user_info(user_id,display_name) values (66,'想念🍓')");
//
//	}
}
