package com.danfeng.service.impl;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Service;

import com.danfeng.entity.ServerInfoEntity;
import com.danfeng.service.ServerInfoService;
import com.danfeng.util.SigarUtils;

@Service
public class ServerInfoServiceImpl implements ServerInfoService  {

	@Override
	public ServerInfoEntity getServerInfo( HttpServletRequest request) {
		ServerInfoEntity serverInfoEntity =new ServerInfoEntity();
		
		 try {  
		        SigarUtils.initSigar();         //初始化动态库链接路径   
		        Properties props=System.getProperties();    
		        Runtime runTime = Runtime.getRuntime();  
		        InetAddress address = InetAddress.getLocalHost();  
		        
	            String ip =  InetAddress.getLocalHost().getHostAddress();
	            Map<String, String> map = System.getenv();
	            serverInfoEntity.setUserName( map.get("USERNAME"));		   // 获取用户名
	            serverInfoEntity.setComputerName(map.get("COMPUTERNAME")); // 获取计算机名
	            serverInfoEntity.setUserDomain(map.get("USERDOMAIN"));    // 获取计算机域名
	            
	            
	            
		        //服务信息  
		        InetAddress.getLocalHost().getHostAddress();  
		        serverInfoEntity.setServerIP(address.getHostAddress());  
		        serverInfoEntity.setServerURL(  
		                request.getScheme()                 //请求头  
		                +"://" + address.getHostAddress()   //服务器地址    
		                + ":"     
		                + request.getServerPort()           //端口号    
		                + request.getContextPath());        //项目名称   
		        ServletContext application = request.getSession().getServletContext();  
		        serverInfoEntity.setServerType(application.getServerInfo());  
		        serverInfoEntity.setServerPort(request.getServerPort());
		        System.out.println(request.getServerPort());
		        serverInfoEntity.setServerTime(new Date());  
		              
		        //操作系统  
		        serverInfoEntity.setOsName(props.getProperty("os.name")+"  "+props.getProperty("os.arch"));  
		        serverInfoEntity.setOsVersion(props.getProperty("os.version"));  
		        serverInfoEntity.setUserName(props.getProperty("user.name"));  
		        serverInfoEntity.setUserHome(props.getProperty("user.home"));  
		        Calendar cal = Calendar.getInstance();  
		        TimeZone timeZone = cal.getTimeZone();  
		        serverInfoEntity.setOsTimeZone(timeZone.getDisplayName());  
		              
//		        //Cpu  
		        Sigar sigar = new Sigar();  
		            CpuInfo cpuInfos[] = sigar.getCpuInfoList();  
		            serverInfoEntity.setCpuNum(cpuInfos.length);  
		            CpuPerc cpuList[] = sigar.getCpuPercList();  
		            serverInfoEntity.setCpuUsageRate(CpuPerc.format(cpuList[0].getUser()));
		            serverInfoEntity.setCpuUsageRate(CpuPerc.format(cpuList[0].getWait()));	            
//		            serverInfoEntity.setCpuList(cpuVoList);  
//		              
//		            //网卡信息  
//		            String netInfos[] = sigar.getNetInterfaceList();  
//		            List<NetInterfaceConfig> netList = new ArrayList<NetInterfaceConfig>();  
//		            for(int i=0;i<netInfos.length;i++){  
//		                NetInterfaceConfig netInfo = sigar.getNetInterfaceConfig(netInfos[i]);   
//		                if((NetFlags.LOOPBACK_ADDRESS.equals(netInfo.getAddress()))     //127.0.0.1   
//		                        || (netInfo.getFlags() == 0)                            //标识为0  
//		                        || (NetFlags.NULL_HWADDR.equals(netInfo.getHwaddr()))   //MAC地址不存在  
//		                        || (NetFlags.ANY_ADDR.equals(netInfo.getAddress()))     //0.0.0.0  
//		                        ){  
//		                    continue;  
//		                }  
//		                netList.add(netInfo);  
//		            }  
//		            serverInfoEntity.setNetNum(netList.size());  
//		            serverInfoEntity.setNetList(netList);  
		              
		            //物理内存  
		            Mem mem = sigar.getMem();    
		            serverInfoEntity.setMemTotal((mem.getTotal()/(1024*1024))+"M");  
		            serverInfoEntity.setMemUsed((mem.getUsed()/(1024*1024))+"M");  
		            serverInfoEntity.setMemFree((mem.getFree()/(1024*1024))+"M");  
		            System.out.println(serverInfoEntity.getMemFree());
		              
		        //java配置  
		        serverInfoEntity.setJavaPath(props.getProperty("java.home"));  
		        serverInfoEntity.setJavaVendor(props.getProperty("java.vendor"));  
		        serverInfoEntity.setJavaVersion(props.getProperty("java.version"));  
		        serverInfoEntity.setJavaName(props.getProperty("java.specification.name"));  
		        serverInfoEntity.setJvmVersion(props.getProperty("java.vm.version"));  
		        serverInfoEntity.setJvmTotalMemory((int)(runTime.totalMemory()/(1024*1024))+"M");  
		        serverInfoEntity.setJvmFreeMemory((int)(runTime.freeMemory()/(1024*1024))+"M");  
		              
		            //数据库信息  
//		        findDatabase(serverInfoEntity);  
//		        dto.setserverInfo(serverInfoEntity);  
		              
		    } catch (Exception e) {  
		        e.printStackTrace();  
		    }
		return serverInfoEntity;  
		}  
	}


