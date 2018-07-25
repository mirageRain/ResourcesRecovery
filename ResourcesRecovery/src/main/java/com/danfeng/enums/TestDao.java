//package com.danfeng.enums;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.danfeng.util.DB;
//import com.sun.org.apache.regexp.internal.recompile;
//
//public class TestDao {
//
//	public static int addUser(String username){
//		String sql="insert into user_info(name) values(?)";
//		List<Object> params = new ArrayList<Object>();
//		params.add(username);
//		return  DB.executeUpdate(sql, params);
//	}
//	
//	public static List<String> getStationByRouteId(int routeId){
//		String sql="SHOW VARIABLES WHERE Variable_name LIKE 'character_set_%' OR Variable_name LIKE 'collation%';";
//		List<String> routeList= new ArrayList<String>();
//		List<Object> params = new ArrayList<Object>();
//		ResultSet rs = DB.executeQuery(sql, params);
//		if(rs==null) return null;
//		try {
//			while(rs != null && rs.next()) {
//				System.out.println(rs.getString("Variable_name"));
//				System.out.println(rs.getString("Value"));
//			}
//			return routeList;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			DB.close();
//		}
//		return routeList;
//	}
//}
