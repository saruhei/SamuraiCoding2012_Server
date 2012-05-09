package org.samuraicoding2012.javaee.utility;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpDataUpload
{
	private static HttpDataUpload self;
	
	public Response send(String strUrl, String filePath){
		String boundary = "boundary";
		StringBuilder builder = new StringBuilder();
		try{
			File file = new File(filePath);
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type","multipart/form-data; boundary="+boundary);
			builder.append("--");
			builder.append(boundary);
			builder.append("\r\n");
			OutputStream op = conn.getOutputStream();
			op.write(builder.toString().getBytes());
			op.write("Content-Disposition: form-data;".getBytes());
			op.write("name=\"upfile\";".getBytes());
			op.write(("filename=\""+filePath+"\"\r\n").getBytes());
			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			
		}catch(MalformedURLException ex){
			ex.printStackTrace();
		}catch(IOException ie){
			ie.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static synchronized HttpDataUpload getInstanse(){
		if(self==null){
			self = new HttpDataUpload();
		}
		return self;
	}
}
