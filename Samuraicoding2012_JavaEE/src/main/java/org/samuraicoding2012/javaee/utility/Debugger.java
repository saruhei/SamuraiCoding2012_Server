package org.samuraicoding2012.javaee.utility;

public class Debugger
{
	private static boolean isDebug = true;
	public static void print(String str){
		if(isDebug){
			System.out.println(str);
		}
	}
}
