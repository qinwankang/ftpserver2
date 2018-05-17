/** 
 * <pre>项目名称:ftpserver 
 * 文件名称:VIP.java 
 * 包名:com.tf.ftpserver.model 
 * 创建日期:2018年3月26日下午3:52:06 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.tf.ftpserver.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/** 
 * <pre>项目名称：ftpserver    
 * 类名称：VIP    
 * 类描述：    
 * 创建人：小飞飞  
 * 创建时间：2018年3月26日 下午3:52:06    
 * 修改人：小飞飞
 * 修改时间：2018年3月26日 下午3:52:06    
 * 修改备注：       
 * @version </pre>    
 */
public class VIP {
	private Integer id;
	private Integer vipcount;
	private String vipdate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVipcount() {
		return vipcount;
	}
	public void setVipcount(Integer vipcount) {
		this.vipcount = vipcount;
	}
	public String getVipdate() {
		return vipdate;
	}
	public void setVipdate(String vipdate) {
		this.vipdate = vipdate;
	}
	
}
