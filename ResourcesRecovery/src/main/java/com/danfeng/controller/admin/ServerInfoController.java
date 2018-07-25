package com.danfeng.controller.admin;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.danfeng.entity.ServerInfoEntity;
import com.danfeng.entity.UserEntity;
import com.danfeng.security.MyUser;
import com.danfeng.service.impl.ServerInfoServiceImpl;
import com.danfeng.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class ServerInfoController {
	
//	private String serverIP;        //服务器IP  
//    private String serverURL;       //服务器Url  
//    private String serverType;      //服务器类型  
//    private Date serverTime;        //服务器时间  
//      
//    private String osName;          //操作系统名称  
//    private String osVersion;       //操作系统版本  
//    private String userName;        //用户名称  
//    private String userHome;        //用户主目录  
//    private String osTimeZone;      //操作系统时区  
//      
//    private String memTotal;        //物理内存总量  
//    private String memUsed;         //已使用的物理内存  
//    private String memFree;         //物理内存剩余量  
//      
//    private int cpuNum;             //cpu总数  
//    private String cpuUsageRate;    //cpu使用率
//    private String cpuWaitRate;		//CPU等待率
//    
//    
//    private String javaPath;        //java安路径  
//    private String javaVendor;      //java运行时供应商  
//    private String javaVersion;     //java版本  
//    private String javaName;        //java运行时名称  
//    private String jvmVersion;      //java虚拟机版本  
//    private String jvmTotalMemory;  //java虚拟机总内存  
//    private String jvmFreeMemory;   //java虚拟机剩余内存  

	@Autowired
	private ServerInfoServiceImpl serverInfoServiceImpl;

	
	@RequestMapping(value = "/server_info", method = RequestMethod.GET)
	public String serverInfoView(HttpServletRequest request,Model model) {
//		ServerInfoEntity serverInfoEntity=serverInfoServiceImpl.getServerInfo(request);
//		System.out.println(serverInfoEntity.getMemFree());
//		 model.addAttribute("serverIP", serverInfoEntity.getServerIP());
//		 model.addAttribute("serverURL", serverInfoEntity.getServerURL());
//		 model.addAttribute("serverPort", serverInfoEntity.getServerPort());
//		 model.addAttribute("serverType", serverInfoEntity.getServerType());
//		 model.addAttribute("serverTime", serverInfoEntity.getServerTime());
//		 model.addAttribute("osName", serverInfoEntity.getOsName());
//		 model.addAttribute("osVersion", serverInfoEntity.getOsVersion());
//		 model.addAttribute("userName", serverInfoEntity.getUserName());
//		 model.addAttribute("userHome", serverInfoEntity.getUserHome());
//		 model.addAttribute("computerName", serverInfoEntity.getComputerName());
//		 model.addAttribute("userDomain", serverInfoEntity.getUserDomain());
//		 model.addAttribute("osTimeZone", serverInfoEntity.getOsTimeZone());
//		 model.addAttribute("memTotal", serverInfoEntity.getMemTotal());
//		 model.addAttribute("memUsed", serverInfoEntity.getMemUsed());
//		 model.addAttribute("memFree", serverInfoEntity.getMemFree());
//		 model.addAttribute("cpuNum", serverInfoEntity.getCpuNum());
//		 model.addAttribute("javaPath", serverInfoEntity.getJavaPath());
//		 model.addAttribute("javaVendor", serverInfoEntity.getJavaVendor());
//		 model.addAttribute("javaVersion", serverInfoEntity.getJavaVersion());
//		 model.addAttribute("javaName", serverInfoEntity.getJavaName());
//		 model.addAttribute("jvmVersion", serverInfoEntity.getJvmVersion());
//		 model.addAttribute("jvmTotalMemory", serverInfoEntity.getJvmTotalMemory());
//		 model.addAttribute("jvmFreeMemory", serverInfoEntity.getJvmFreeMemory());
		 
		return "admin/server_info";
		
	}
	@RequestMapping(value = "/admin_login", method = RequestMethod.GET)
    public String adminLogin() {
        return "admin_login";
    }
}
