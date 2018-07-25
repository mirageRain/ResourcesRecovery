package com.danfeng.util;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;



public class DB2 {
	
	private static final String DATABASENAME="resources_recovery";
	private static final String CHARSET="?characterEncoding=utf-8&useUnicode=true&useSSL=false";
	private static final String USERNAME="root";
	private static final String PASSWORD="*963.Mirage";
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/";
	
	
	//数据库链接对象
    private static java.sql.Connection conn;
    //数据库命令执行对象
    private static PreparedStatement pstmt;
   
    
	//静态代码块
    static{
        //1、加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
    }
    
    public static void getConnection(){
        if(conn == null){
            try {
                conn = DriverManager.getConnection(url+DATABASENAME+CHARSET, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
  /** 
   * 执行读操作SQL语句
   * @param query 要执行的SQL语句
   * @param params 要传入的SQL语句中的参数
   * @return
   */
    public static java.sql.ResultSet executeQuery(String query,
                    List<Object> params){
    	java.sql.ResultSet rs = null;
        getConnection();
        try {
            //创建命令执行对象
        	System.out.println(query);
            pstmt = conn.prepareStatement(query);
            //执行
            if(params!=null && params.size()>0){
                for(int i=0;i<params.size();i++){
                	System.out.println(params.get(i));
                    pstmt.setObject(i+1, params.get(i));
                }
            }
            rs = pstmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    /**
     * 执行更新SQL语句
     * @param query 要执行的SQL语句
     * @param params 要传入的SQL语句中的参数
     * @return
     */
    public static int executeUpdate(String query, List<Object> params){
    	int reuslt=0;
    	java.sql.ResultSet rs;
    	getConnection(); //连接数据库
    	try {
			pstmt=conn.prepareStatement(query); //创建命令操作对象
			if(pstmt!=null&&params.size()>0){
				for(int i=0;i<params.size();i++){
					pstmt.setObject(i+1, params.get(i));
				}
			}
			reuslt=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
    	
		return 0;
    	
    }
    /**
     * 关闭资源
     */
    public static void close(){        
            try {
                if(pstmt!=null){
                    pstmt.close();
                    pstmt = null;
                }
                if(conn!=null){
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        
        }

}
