package org.samuraicoding.server.utility;

public class Response
{
	String data;
	int responseCode;
	Exception exception;
	boolean isException;
	public Response(){
		this.isException = false;
	}
	public String getData()
	{
		return data;
	}
	public void setData(String data)
	{
		this.data = data;
	}
	public int getResponseCode()
	{
		return responseCode;
	}
	public void setResponseCode(int responseCode)
	{
		this.responseCode = responseCode;
	}
	public Exception getException()
	{
		return exception;
	}
	public void setException(Exception exception)
	{
		this.exception = exception;
	}
	public boolean isException()
	{
		return isException;
	}
	public void setException(boolean isException)
	{
		this.isException = isException;
	}
	
	
}
