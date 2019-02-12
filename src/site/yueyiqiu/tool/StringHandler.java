package site.yueyiqiu.tool;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHandler {
	
	public static String cookieEncode(String str){
		String change = null;
		
		try {
			change=URLEncoder.encode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		return change;
	}
	
	public static String cookieDecode(String str){
		String change = null;
		
		try {
			change=URLDecoder.decode(str, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return change;
	}
	
	public static int[] changeToIntArray(String[] strarray){
		
		int[] nums=null;
		if(strarray!=null){
			nums=new int[strarray.length];
			for(int i=0;i<strarray.length;i++){
				nums[i]=Integer.parseInt(strarray[i]);
			}
		}
		
		return nums;
	}
	
	public static int strToint(String str){
		
		int ptr = 0;
		try {
			if(str!=null&&!str.equals("")){
				ptr=Integer.parseInt(str);
			}
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
			ptr=-1;
		}
		
		return ptr;
		
	}
	
	public static String arrayToString(String[] array){
		
		String str="";
		if(array!=null&&array.length>0){
			for(int i=0;i<array.length;i++){
				System.out.println(array[i]);
				str+=array[i]+",";
			}
		}
		
		str=str.substring(0, str.length()-1);
		
		return str;
	}
	
	
	public static String timeTostr(Date date){
		
		String datestr = null;
		if(date!=null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			datestr=sdf.format(date);
		}
		return datestr;
	}
	
}
