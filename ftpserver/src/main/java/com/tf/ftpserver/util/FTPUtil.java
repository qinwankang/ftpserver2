package com.tf.ftpserver.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.SocketException;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FTPUtil {

	/*public static void main(String[] args) throws FileNotFoundException {
		InputStream is = new FileInputStream("C:\\Users\\lenovo\\Desktop\\11111.gif");
		boolean nicai = uploadFile(is, "哈哈.gif", "nicaicai");
		System.out.println(nicai);
	}*/
	
	public static boolean uploadFile(InputStream is, String fileName, String path) {
		boolean storeResult = false;
		//1、实例化ftp客户端
		FTPClient f = new FTPClient();
		try {
			//2、连接服务器
			f.connect(Constant.FTP_HOST, Constant.FTP_PORT);
			//3、登录验证
			f.login(Constant.FTP_USER, Constant.FTP_PWD);
			//设置被动连接
			f.enterLocalPassiveMode();
			//4、判断是否登陆成功
			int reply = f.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				//切换到根路径
				f.changeWorkingDirectory("/");
				//判断是否存在
				//创建文件夹(返回true:服务器上没有，已被创建；返回false:服务器上有，创建失败)
				boolean d = f.makeDirectory("aa"+path);
				//切换到刚刚创建完的路径下
				f.changeWorkingDirectory("aa"+path);
				//在这个路径下保存文件
				//f.storeFile(new String("黑名单.txt".getBytes("GBK"), "ISO-8859-1"), is);
				//设置文件类型为二进制文件
				f.setFileType(FTPClient.BINARY_FILE_TYPE);
				storeResult = f.storeFile(fileName, is);
				f.logout();
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				f.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return storeResult;
	}
	
	
	public static boolean downLoadFile(String path, OutputStream os) {
		boolean boo = false;
		//1、创建ftpclient
		FTPClient ftp = new FTPClient();
		//2、连接服务器
		try {
			ftp.connect(Constant.FTP_HOST, Constant.FTP_PORT);
			//3、登录
			ftp.login(Constant.FTP_USER, Constant.FTP_PWD);
			//4、判断是否登陆成功
			int reply = ftp.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				//5、设置编码
				ftp.setControlEncoding(System.getProperty("file.encoding"));
	            //6、切换路径
				ftp.changeWorkingDirectory("/");
				// 设置文件传输类型为二进制
	            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				//7、获取某文件
	            boo = ftp.retrieveFile(path, os);
	            ftp.logout();
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ftp.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boo;
	}
	
	public static boolean downFileListFromFTP(String tempPath, List<String> pathList, List<String> fileNameList) {
		boolean boo = false;
		//1、创建ftpclient
		FTPClient ftp = new FTPClient();
		//2、连接服务器
		try {
			ftp.connect(Constant.FTP_HOST, Constant.FTP_PORT);
			//3、登录
			ftp.login(Constant.FTP_USER, Constant.FTP_PWD);
			//4、判断是否登陆成功
			int reply = ftp.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				//5、设置编码
				ftp.setControlEncoding(System.getProperty("file.encoding"));
	            //6、切换路径
				ftp.changeWorkingDirectory("/");
				// 设置文件传输类型为二进制
	            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	            
	            OutputStream os = null;
	            //循环文件路径集合
	            for (int i = 0; i < pathList.size(); i++) {
					InputStream is = ftp.retrieveFileStream(pathList.get(i));
					//判断文件是否存在
		            if (null == is || ftp.getReplyCode() == 550) {
		            	is = null;
		            	continue;
	                }
	            	os = new FileOutputStream(tempPath + fileNameList.get(i));
	            	boo = ftp.retrieveFile(pathList.get(i), os);
	            	is.close();
	            	os.close();
				}
	            ftp.logout();
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ftp.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boo;
	}
	
	public static void downLoadZipFile(String tempPath, String tempZipName, List<String> pathList, List<String> fileNameList, HttpServletResponse response) {
		File f = new File(tempPath);
        if (!f.exists()) {
        	//创建临时路径
        	f.mkdirs();
        }
        
        ZipOutputStream zos = null;
        try {
        	// 设置response内容的类型
        	response.setCharacterEncoding("utf-8");
			// 设置头部信息
			response.setHeader("Content-Type", "application/zip");
			response.setHeader("Content-disposition", "attachment;filename="
                            + UUID.randomUUID().toString() + ".zip");
        	//实例化zip输出流
        	zos = new ZipOutputStream(response.getOutputStream());
        	//调用ftp的方法，获取文件输入流的集合
        	boolean boo = downFileListFromFTP(tempPath, pathList, fileNameList);
        	if (boo) {
        		//读取临时路径下所有文件
        		File[] listFiles = f.listFiles();
        		if (null != listFiles && 0 < listFiles.length) {
        			for (int j = 0; j < listFiles.length; j++) {
        				File file = listFiles[j];
        				if (file.isFile() && !file.getName().endsWith(".zip")) {
        					InputStream is = new FileInputStream(file);
        					zos.putNextEntry(new ZipEntry(file.getName()));
        					int len;
        					byte[] buffer = new byte[1024];
        					// 读入需要下载的文件的内容，打包到zip文件
        					while ((len = is.read(buffer)) != -1) {
        						zos.write(buffer);
        					}
        					//文件放置完毕，关闭当前文件
        					zos.closeEntry();
        					is.close();
        				}
        			}
        		}
        	}
        	zos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				zos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//删除临时文件夹及其所有文件
        	deleteDirAndFile(f);
		}
	}
	

	
	/**
	 * 删除文件和路径
	 * @param f
	 */
	public static void deleteDirAndFile(File f) {
		if (f.isDirectory()) {
			File[] listFiles = f.listFiles();
			for (File file : listFiles) {
				if (f.isDirectory()) {
					deleteDirAndFile(file);
				} else {
					file.delete();
				}
			}
		}
		f.delete();
	}

}
