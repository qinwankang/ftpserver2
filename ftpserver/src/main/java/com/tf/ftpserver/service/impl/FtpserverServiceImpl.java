/** 
 * <pre>项目名称:ftpserver 
 * 文件名称:FtpserverServiceImpl.java 
 * 包名:com.tf.ftpserver.service.impl 
 * 创建日期:2018年3月21日下午8:25:40 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.tf.ftpserver.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.ftpserver.dao.FtpserverDao;
import com.tf.ftpserver.service.FtpserverService;

/** 
 * <pre>项目名称：ftpserver    
 * 类名称：FtpserverServiceImpl    
 * 类描述：    
 * 创建人：小飞飞  
 * 创建时间：2018年3月21日 下午8:25:40    
 * 修改人：小飞飞
 * 修改时间：2018年3月21日 下午8:25:40    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class FtpserverServiceImpl implements FtpserverService {
	@Autowired
	private FtpserverDao ftpserverDao;
}
