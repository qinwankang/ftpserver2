/** 
 * <pre>项目名称:ftpserver 
 * 文件名称:RedistestServiceImpl.java 
 * 包名:com.tf.ftpserver.service.impl 
 * 创建日期:2018年3月23日下午2:56:07 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.tf.ftpserver.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tf.ftpserver.dao.RedistestDao;
import com.tf.ftpserver.model.TStu;
import com.tf.ftpserver.model.VIP;
import com.tf.ftpserver.service.RedistestService;

/** 
 * <pre>项目名称：ftpserver    
 * 类名称：RedistestServiceImpl    
 * 类描述：    
 * 创建人：小飞飞  
 * 创建时间：2018年3月23日 下午2:56:07    
 * 修改人：小飞飞
 * 修改时间：2018年3月23日 下午2:56:07    
 * 修改备注：       
 * @version </pre>    
 */
@Service
public class RedistestServiceImpl implements RedistestService {
	@Autowired
	private RedistestDao redistestDao;

	@Override
	@Cacheable(value="common",key="'id_'+#TStu.id") 
	public List<TStu> queryStuList() {
		return redistestDao.queryStuList();
	}
	
	@Override
	public Map<String, Object> queryEcharsTest(VIP vip) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String format = ft.format(new Date());
		if (vip.getVipdate() == null) {
			vip.setVipdate(format);
		} 
		String nowdate = vip.getVipdate();
		List<VIP> echarsList = redistestDao.queryEcharsTest(nowdate);
		if(echarsList != null && echarsList.size()>0){
			Integer size = echarsList.size();
			String[] Xinfo = new String[size];
			Integer[] Yinfo = new Integer[size];
			
			for (int i = 0; i < echarsList.size(); i++) {
				VIP vip1 = echarsList.get(i);
				String vipdate = vip1.getVipdate();
				Xinfo[i] = vipdate;
				
				Integer vipcount = vip1.getVipcount();
				Yinfo[i] = vipcount;
			}
			map.put("xAxis", Xinfo);
			map.put("series", Yinfo);
			
		}
		return map;
	}
}
