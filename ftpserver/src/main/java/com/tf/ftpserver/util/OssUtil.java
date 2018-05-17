package com.tf.ftpserver.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

public class OssUtil {

    private static final String ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
    private static final String ACCESSKEYID = "LTAI1RVmwTUaIi3U";
    private static final String ACCESSKEYSECRET = "WhO27bZITicpeIPBEqUUrS6dXmYSML";
    private static final String BUCKETNAME  = "xff-bucket";


    public static void main(String[] args) {
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
//        String accessKeyId = "LTAI8DLzYBn5xTOc";
//        String accessKeySecret = "8ojKYdCCGEBglbHPGuMG4capvgtvgt";
//        // 创建OSSClient实例
//        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
//        System.out.printf(client.toString());
//        // 使用访问OSS
////        client.bu
//        String content = "Hello OSS";
//        File f = new File("C:\\Users\\lenovo\\Desktop\\寒假作业-1708A班.txt");
//        // key是文件名
//        PutObjectResult myoss1aaa = client.putObject("myoss1aaa3333", "求说/求说.txt", f);
//        System.out.println(myoss1aaa.getResponse().getStatusCode());
//
//// 关闭client
//        client.shutdown();
    }

    public static boolean uploadFile(String path, String fileName, File file) {
        String endpoint = ENDPOINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
        String accessKeyId = ACCESSKEYID;
        String accessKeySecret = ACCESSKEYSECRET;
        // 创建OSSClient实例
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 使用访问OSS
        String newFileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        // key是文件名
        PutObjectResult myoss = client.putObject(BUCKETNAME, newFileName, file);
        // 关闭client
        client.shutdown();
        return true;
    }

    public static boolean uploadFile(String path, String fileName, InputStream is) {
        boolean flag = false;
        try {
            String endpoint = ENDPOINT;
            // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，
            // 请登录 https://ram.console.aliyun.com 创建
            String accessKeyId = ACCESSKEYID;
            String accessKeySecret = ACCESSKEYSECRET;
            // 创建OSSClient实例
            OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 使用访问OSS
            String newFileName = path + "/" + UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
            // key是文件名
            PutObjectResult myoss = client.putObject(BUCKETNAME, newFileName, is);
            // 关闭client
            client.shutdown();
            flag = true;
        } catch (Exception e) {

        } finally {
            if (null != is) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return flag;
        }
    }





}
