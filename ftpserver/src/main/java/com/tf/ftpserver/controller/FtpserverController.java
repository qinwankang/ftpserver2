/** 
 * <pre>项目名称:ftpserver 
 * 文件名称:FtpserverController.java 
 * 包名:com.tf.ftpserver.controller 
 * 创建日期:2018年3月21日下午8:24:28 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.tf.ftpserver.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tf.ftpserver.model.Photo;
import com.tf.ftpserver.service.FtpserverService;
import com.tf.ftpserver.util.FTPUtil;
import com.tf.ftpserver.util.OssUtil;

/** 
 * <pre>项目名称：ftpserver    
 * 类名称：FtpserverController    
 * 类描述：    
 * 创建人：小飞飞  
 * 创建时间：2018年3月21日 下午8:24:28    
 * 修改人：小飞飞
 * 修改时间：2018年3月21日 下午8:24:28    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("ftpserver")
public class FtpserverController {
	@Autowired
	private FtpserverService ftpserverService;
	
/*	@RequestMapping("uploadmsg")
	@ResponseBody
	public boolean uploadmsg(HttpServletRequest request) throws Exception{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			
			// 获取上传的文件 
			MultipartFile multiFile = multipartRequest.getFile("photo");
			
			//转为流
			InputStream inputStream = multiFile.getInputStream();
			
			//获取上传的全名
			String originalFilename = multiFile.getOriginalFilename();
			
			//截取后4位
			String substring = originalFilename.substring(originalFilename.length()-4,originalFilename.length());
			
			//生成当前时分秒
		    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		    String format = simpleDateFormat.format(new Date());
		    
		    //生成6位随机数
		    Random random = new Random(); 
		    int i = random.nextInt(100000);
		    
		    //拼接新的名称
		    StringBuffer jp = new StringBuffer();
		    jp.append(format);
		    jp.append(i);
		    jp.append(substring);
		    
		    System.out.println(jp.toString());
		    
		   //调用工具类传入相应的值<br>　　　　　　　　　　　　　　　　//FTP地址    端口  帐号    密码   FTP中路径  文件名称       文件流
		    boolean b = FTPUtil.uploadFile(inputStream,jp.toString(),"/");
		    return b;
			
	}*/
	
	@RequestMapping("uploadmsg")
	@ResponseBody
	public boolean uploadmsg() throws FileNotFoundException{
		//调用工具类传入相应的值<br>　　
		//InputStream is = new FileInputStream("C:\\Users\\lenovo\\Desktop\\11111.gif");
		InputStream is = new FileInputStream("C:\\Users\\Administrator\\Desktop\\111.jpg");
		boolean b = FTPUtil.uploadFile(is, "222.jpg", "hello");
		//FTP地址    端口  帐号    密码   FTP中路径  文件名称       文件流
	    return b;
	}
	
	
	@RequestMapping("uploadOSS")
	@ResponseBody
	public boolean uploadOSS() throws FileNotFoundException{
		InputStream is = new FileInputStream("C:\\Users\\Administrator\\Desktop\\mysql主从同步.txt");
		boolean b = OssUtil.uploadFile("text", "222.txt", is);
		return b;
	}
	
	public void deleteFile(){
		File f = new File("path");
		FTPUtil.deleteDirAndFile(f);
	}
	

}
