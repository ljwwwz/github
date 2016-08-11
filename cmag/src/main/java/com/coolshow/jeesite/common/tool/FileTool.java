package com.coolshow.jeesite.common.tool;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 文件操作工具类
 * ClassName: FileTool.java
 * @author LJW
 * @date 上午10:23:41
 */
public final class FileTool {

	private FileTool(){}
	
	/**
	  * @Description: 保存文件到本地
	  * @param  @param file
	  * @param  @param request
	  * @param  @throws Exception 
	  * @return void  
	  * @throws
	  * @author LJW
	  * @date 2016年6月2日
	 */
	static public void saveFile(MultipartFile file,File targetFile) throws Exception{
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			// 保存
			file.transferTo(targetFile);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	  * @Description: 用io流的形式从服务器上下载文件
	  * @param  @param path
	  * @param  @param response 
	  * @return void  
	 * @throws IOException 
	  * @throws
	  * @author LJW
	  * @date 2016年6月12日
	 */
	static public void download(String path, HttpServletResponse response) throws IOException {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition","attachment;filename="+ new String(filename.getBytes("utf-8"), "ISO-8859-1"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            //ex.printStackTrace();
            throw ex;
        }
    }
}
