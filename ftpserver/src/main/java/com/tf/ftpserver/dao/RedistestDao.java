/** 
 * <pre>项目名称:ftpserver 
 * 文件名称:RedistestDao.java 
 * 包名:com.tf.ftpserver.dao 
 * 创建日期:2018年3月23日下午2:56:51 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.tf.ftpserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tf.ftpserver.model.TStu;
import com.tf.ftpserver.model.VIP;

/** 
 * <pre>项目名称：ftpserver    
 * 类名称：RedistestDao    
 * 类描述：    
 * 创建人：小飞飞  
 * 创建时间：2018年3月23日 下午2:56:51    
 * 修改人：小飞飞
 * 修改时间：2018年3月23日 下午2:56:51    
 * 修改备注：       
 * @version </pre>    
 */
public interface RedistestDao {

	List<TStu> queryStuList();

	List<VIP> queryEcharsTest(@Param("nowdate")String nowdate);

}
