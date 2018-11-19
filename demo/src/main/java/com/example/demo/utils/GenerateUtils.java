package com.example.demo.utils;

import java.io.File;
import java.lang.reflect.Field;

import org.aspectj.weaver.World;

import com.example.demo.entity.User;

public class GenerateUtils {
	private static final Class clazz=User.class;
	private static final String POJO="User";
	
	private static final String path=System.getProperty("user.dir");
	
	private static final String POJOTEMPLATE="${POJO}";
	
	private static final String RESPOSITORYTEMPLATE="${POJO}Respository.txt";
	private static final String SERVICETEMPLATE="${POJO}Service.txt";
	private static final String SERVICEIMPLTEMPLATE="${POJO}ServiceImpl.txt";
	
	private static final String RESPOSITORYPATH=path+"\\src\\main\\java\\"+POJO+"Respository.java";
	private static final String SERVICEPATH=path+"\\src\\main\\java\\"+POJO+"Service.java";
	private static final String SERVICEIMPLPATH=path+"\\src\\main\\java\\"+POJO+"ServiceImpl.java";
	
	public static void main(String[] args) {
//		generateRespository();
//		generateService();
		generateServicImpl();
		
	}
	
	public static String generateRespository(){
		return genBaseCode(RESPOSITORYTEMPLATE,RESPOSITORYPATH);
	}
	
	public static String generateService(){
		return genBaseCode(SERVICETEMPLATE,SERVICEPATH);
	}
	
	public static String generateServicImpl(){
		return genBaseCode(SERVICEIMPLTEMPLATE,SERVICEIMPLPATH);
	}
	
	public static String genBaseCode(String template,String codePath) {
		String genCode="";
		try {;
			template=path+"\\src\\main\\resources\\"+template;
			File file=new File(template);
			String content = FileUtils.readToString(file, "UTF-8");
			genCode = StrUtils.replace(content, POJOTEMPLATE, POJO);
			genCode = StrUtils.replace(genCode, "${PRO}", new StringBuilder().append(Character.toLowerCase(POJO.charAt(0))).append(POJO.substring(1)).toString());
			genCode = StrUtils.replace(genCode, "${GETBYID}", hql(clazz,true));
			genCode = StrUtils.replace(genCode, "${GETBYALL}", hql(clazz,false));
			FileUtils.writeFile(codePath, genCode.getBytes());
		}catch(Exception e) {
			e.getStackTrace();
		}
		return genCode;
		
	}
	
	public static String hql(Class clazz,Boolean where) {
		Field[] fields=User.class.getDeclaredFields();
		String pre=clazz.getSimpleName().substring(0, 1).toLowerCase();
		StringBuffer sb=new StringBuffer();
		sb.append("select new ");
		sb.append(clazz.getName());
		sb.append("Dto(");
		for(Field f:fields) {
			sb.append(pre);
			sb.append(".");
			sb.append(f.getName());
			sb.append(",");
			System.out.println(f.getName());
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") ");
		sb.append("from ");
		sb.append(clazz.getSimpleName());
		sb.append(" ");
		sb.append(pre);
		if(where=true) {
			sb.append(" where ");
			sb.append(pre);
			sb.append(".id");
		}
		return sb.toString();
	}

}
