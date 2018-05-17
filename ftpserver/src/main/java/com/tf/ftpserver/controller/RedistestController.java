/** 
 * <pre>项目名称:ftpserver 
 * 文件名称:RedistestController.java 
 * 包名:com.tf.ftpserver.controller 
 * 创建日期:2018年3月23日下午2:54:28 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.tf.ftpserver.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tf.ftpserver.model.TStu;
import com.tf.ftpserver.model.VIP;
import com.tf.ftpserver.service.RedistestService;

/** 
 * <pre>项目名称：ftpserver    
 * 类名称：RedistestController    
 * 类描述：    
 * 创建人：小飞飞  
 * 创建时间：2018年3月23日 下午2:54:28    
 * 修改人：小飞飞
 * 修改时间：2018年3月23日 下午2:54:28    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("redisTest")
public class RedistestController {
	@Autowired
	private RedistestService RedistestService;
	
	@RequestMapping("queryStuList")
	@ResponseBody
	public List<TStu> queryStuList(){
		List<TStu> stuList = RedistestService.queryStuList();
		return stuList;
	}
	
	@RequestMapping("queryEcharsTest")
	@ResponseBody
	public Map<String,Object> queryEcharsTest(VIP vip){
 		return RedistestService.queryEcharsTest(vip);
	}
}
