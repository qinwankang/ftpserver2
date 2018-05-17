/** 
 * <pre>项目名称:ftpserver 
 * 文件名称:RedistestService.java 
 * 包名:com.tf.ftpserver.service 
 * 创建日期:2018年3月23日下午2:55:37 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.tf.ftpserver.service;

import java.util.List;
import java.util.Map;

import com.tf.ftpserver.model.TStu;
import com.tf.ftpserver.model.VIP;

/** 
 * <pre>项目名称：ftpserver    
 * 类名称：RedistestService    
 * 类描述：    
 * 创建人：小飞飞  
 * 创建时间：2018年3月23日 下午2:55:37    
 * 修改人：小飞飞
 * 修改时间：2018年3月23日 下午2:55:37    
 * 修改备注：       
 * @version </pre>    
 */
public interface RedistestService {

	List<TStu> queryStuList();

	Map<String, Object> queryEcharsTest(VIP vip);

}
