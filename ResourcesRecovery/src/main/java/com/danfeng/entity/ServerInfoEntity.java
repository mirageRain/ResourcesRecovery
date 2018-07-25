package com.danfeng.entity;

import java.util.Date;
import java.util.List;

public class ServerInfoEntity {
	private String serverIP;        //服务器IP  
    private String serverURL;       //服务器Url 
    private int serverPort;		//服务器端口
    private String serverType;      //服务器类型  
    private Date serverTime;        //服务器时间  
      
    private String osName;          //操作系统名称  
    private String osVersion;       //操作系统版本  
    private String userName;        //用户名称  
    private String userHome;        //用户主目录  
    private String osTimeZone;      //操作系统时区  
    
    
    private String computerName;    //计算机名
    private String userDomain;		//计算机域名
      
    private String memTotal;        //物理内存总量  
    private String memUsed;         //已使用的物理内存  
    private String memFree;         //物理内存剩余量  
      
    private int cpuNum;             //cpu总数  
    private String cpuUsageRate;    //cpu使用率
    private String cpuWaitRate;		//CPU等待率
    
    
    private String javaPath;        //java安路径  
    private String javaVendor;      //java运行时供应商  
    private String javaVersion;     //java版本  
    private String javaName;        //java运行时名称  
    private String jvmVersion;      //java虚拟机版本  
    private String jvmTotalMemory;  //java虚拟机总内存  
    private String jvmFreeMemory;   //java虚拟机剩余内存  
      
    private String databaseType;    //数据库类型  
    private String databaseVersion; //数据库类型  
    private String databaseDriver;  //数据库驱动  
    private String driverVersion;   //数据库驱动版本  
    private String jdbcUrl;         //数据库连接url  
	public String getServerIP() {
		return serverIP;
	}
	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}
	public String getServerURL() {
		return serverURL;
	}
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public Date getServerTime() {
		return serverTime;
	}
	public void setServerTime(Date serverTime) {
		this.serverTime = serverTime;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
	public String getOsVersion() {
		return osVersion;
	}
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserHome() {
		return userHome;
	}
	public void setUserHome(String userHome) {
		this.userHome = userHome;
	}
	public String getOsTimeZone() {
		return osTimeZone;
	}
	public void setOsTimeZone(String osTimeZone) {
		this.osTimeZone = osTimeZone;
	}
	public String getMemTotal() {
		return memTotal;
	}
	public void setMemTotal(String memTotal) {
		this.memTotal = memTotal;
	}
	public String getMemUsed() {
		return memUsed;
	}
	public void setMemUsed(String memUsed) {
		this.memUsed = memUsed;
	}
	public String getMemFree() {
		return memFree;
	}
	public void setMemFree(String memFree) {
		this.memFree = memFree;
	}
	public int getCpuNum() {
		return cpuNum;
	}
	public void setCpuNum(int cpuNum) {
		this.cpuNum = cpuNum;
	}

	public String getJavaPath() {
		return javaPath;
	}
	public void setJavaPath(String javaPath) {
		this.javaPath = javaPath;
	}
	public String getJavaVendor() {
		return javaVendor;
	}
	public void setJavaVendor(String javaVendor) {
		this.javaVendor = javaVendor;
	}
	public String getJavaVersion() {
		return javaVersion;
	}
	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}
	public String getJavaName() {
		return javaName;
	}
	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	public String getJvmVersion() {
		return jvmVersion;
	}
	public void setJvmVersion(String jvmVersion) {
		this.jvmVersion = jvmVersion;
	}
	public String getJvmTotalMemory() {
		return jvmTotalMemory;
	}
	public void setJvmTotalMemory(String jvmTotalMemory) {
		this.jvmTotalMemory = jvmTotalMemory;
	}
	public String getJvmFreeMemory() {
		return jvmFreeMemory;
	}
	public void setJvmFreeMemory(String jvmFreeMemory) {
		this.jvmFreeMemory = jvmFreeMemory;
	}
	public String getDatabaseType() {
		return databaseType;
	}
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
	public String getDatabaseVersion() {
		return databaseVersion;
	}
	public void setDatabaseVersion(String databaseVersion) {
		this.databaseVersion = databaseVersion;
	}
	public String getDatabaseDriver() {
		return databaseDriver;
	}
	public void setDatabaseDriver(String databaseDriver) {
		this.databaseDriver = databaseDriver;
	}
	public String getDriverVersion() {
		return driverVersion;
	}
	public void setDriverVersion(String driverVersion) {
		this.driverVersion = driverVersion;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getCpuWaitRate() {
		return cpuWaitRate;
	}
	public void setCpuWaitRate(String cpuWaitRate) {
		this.cpuWaitRate = cpuWaitRate;
	}
	public String getCpuUsageRate() {
		return cpuUsageRate;
	}
	public void setCpuUsageRate(String cpuUsageRate) {
		this.cpuUsageRate = cpuUsageRate;
	}
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}
	public String getUserDomain() {
		return userDomain;
	}
	public void setUserDomain(String userDomain) {
		this.userDomain = userDomain;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

    
    
}
