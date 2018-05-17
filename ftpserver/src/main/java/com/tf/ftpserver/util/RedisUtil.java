/** 
 * <pre>项目名称:ftpserver 
 * 文件名称:RedisUtil.java 
 * 包名:com.tf.ftpserver.util 
 * 创建日期:2018年3月22日下午8:08:00 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.tf.ftpserver.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/** 
 * <pre>项目名称：ftpserver    
 * 类名称：RedisUtil    
 * 类描述：    
 * 创建人：小飞飞  
 * 创建时间：2018年3月22日 下午8:08:00    
 * 修改人：小飞飞
 * 修改时间：2018年3月22日 下午8:08:00    
 * 修改备注：       
 * @version </pre>    
 */
public class RedisUtil {
	//Redis服务器IP
	  private static String ADDR = "192.168.141.129";
	   
	  //Redis的端口号
	  private static int PORT = 6379;
	   
	  //访问密码
	  private static String AUTH = "admin";
	   
	  //可用连接实例的最大数目，默认值为8；
	  //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	  private static int MAX_ACTIVE = 1024;
	   
	  //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	  private static int MAX_IDLE = 200;
	   
	  //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	  private static int MAX_WAIT = 10000;
	   
	  private static int TIMEOUT = 10000;
	   
	  //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	  private static boolean TEST_ON_BORROW = true;
	   
	  private static JedisPool jedisPool = null;
	   
	  /**
	   * 初始化Redis连接池
	   */
	  static {
	    try {
	      JedisPoolConfig config = new JedisPoolConfig();
	      config.setMaxTotal(MAX_ACTIVE);
	      config.setMaxIdle(MAX_IDLE);
	      config.setMaxWaitMillis(MAX_WAIT);
	      config.setTestOnBorrow(TEST_ON_BORROW);
	      jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	   
	  /**
	   * 获取Jedis实例
	   * @return
	   */
	  public synchronized static Jedis getJedis() {
	    try {
	      if (jedisPool != null) {
	        Jedis resource = jedisPool.getResource();
	        return resource;
	      } else {
	        return null;
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	  }
	   
	  /**
	   * 释放jedis资源
	   * @param jedis
	   */
	  public static void returnResource(final Jedis jedis) {
	    if (jedis != null) {
	      jedisPool.returnResource(jedis);
	    }
	  }
}
