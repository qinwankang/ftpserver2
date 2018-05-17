package com.tf.ftpserver.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.apache.http.util.EntityUtils.getContentCharSet;

/**
 * httpClient 工具类
 */
public class HttpClient {
    //记录日志   class是为了标明哪个类报错了
    private static Logger logger = Logger.getLogger(HttpClient.class);

//创建一个http连接池
    private static PoolingHttpClientConnectionManager httpClientConnectionManager;

    /**
     * 初始化
     */
    private static void init(){
    	//懒加载，没这个属性才去创建
        if(httpClientConnectionManager == null){
            httpClientConnectionManager = new PoolingHttpClientConnectionManager();
            httpClientConnectionManager.setMaxTotal(50);
        }
    }

    /**
     * post请求
     * @param url   请求地址
     * @param params 请求参数
     * @return 返回json字符串
     */
    public static String post(String url,Map params){
    	//error  错误级别日志
    	//debug   调试日志
    	//info   正常执行日志
        logger.debug(String.format("请求参数 url{0},params{1}",url,params.toString()) );
        init();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(url);
            ArrayList arrayList = covertParams2NVPS(params);
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                String result = getResponseString(entity);
                logger.debug(String.format("响应信息：{0}",result));
                return result;
            }else{
                logger.error("请求状态错误，错误码"+":"+response.getStatusLine().getStatusCode());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("httpClient的Post请求异常",e);
        }
        return "";
    }

    /**
     * get请求
     * @param url 请求url
     * @param params 请求参数
     * @return 返回json字符串
     */
    public static String get(String url, Map params){
    	//error  错误级别日志
    	//debug   调试日志
    	//info   正常执行日志
        logger.debug(String.format("请求参数 url{0},params{1}",url,params.toString()) );
        init();
        //创建一个对象叫做
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
        	//get请求是通过这个urlbuilder   构建请求路径
            URIBuilder uriBuilder = new URIBuilder();
            //要请求的地址
            uriBuilder.setPath(url);
            //将进参通过下面的方法转化成ArrayList
            ArrayList<NameValuePair> arrayList = covertParams2NVPS(params);
            uriBuilder.setParameters(arrayList);
            //new一个httpGet请求   构建UIL
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            CloseableHttpResponse execute = httpClient.execute(httpGet);
            //返回的东西在Entity里
            HttpEntity entity = execute.getEntity();
            //200代表正常
            if(execute.getStatusLine().getStatusCode() == 200){
                String responseString = getResponseString(entity);

                logger.debug(String.format("响应信息：{0}",responseString));
                return responseString;
            }else {
                logger.error("请求状态错误，错误码"+":"+execute.getStatusLine().getStatusCode());
                return null;
            }
        } catch (Exception e) {
            logger.error("httpClient请求出错，异常：{}",e);
            return null;
        }
    }
    
    /**
     * 将请求的参数转成ArrayList
     * @param params
     * @return
     */
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }

    /**
     * 返回响应正文(无乱码)
     * @param
     * @return
     */
    private static String getResponseString(HttpEntity entity) throws IOException {
        StringBuilder result = new StringBuilder();//响应正文
        if (entity != null) {
            String charset = getContentCharSet(entity);
            InputStream instream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    instream,"utf-8"));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result.append(temp+"\n");
            }
        }
        return result.toString();
    }
    
    /**
     * 天气接口
     */
    /*
    public static void main(String[] args) {
    	Integer yzm=123456;
    	String yzname="18630033422";
    	HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("accountSid",ConBean.accountSid);
        params.put("templateid",ConBean.templateid);
        params.put("param",yzm);
        params.put("to",yzname);
        params.put("timestamp", TimeUtil.dateTostr(new Date(),"yyyyMMddHHmmss"));
        params.put("sig", Md5Util.getMd532(params.get("accountSid").toString()+ConBean.auth+params.get("timestamp").toString()));
        HttpClient.post(ConBean.SMS_URL, params);
	}*/
    
    

}
