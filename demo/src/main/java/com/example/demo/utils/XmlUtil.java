package com.example.demo.utils;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.example.demo.User;

public class XmlUtil {
	public static void main(String[] args) {
		xmlForObject();
		
//		objectForXml();
	}

	public static void xmlForObject() {
		try {
			String xml =System.getProperty("user.dir").replaceAll("\\\\", "/")+"/src/main/resources/user.xml";
//			String xml="C:/work/user.xml";
			JAXBContext context = JAXBContext.newInstance(User.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Object object = unmarshaller.unmarshal(new File(xml));
			User user = (User) object;
			System.out.println(user.toString());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	public static void objectForXml() {
		try {

			User user = new User();
			user.setId(1);
			user.setNo("001");
			user.setName("zhansna");
			user.setAddress("shanghai");

			JAXBContext context = JAXBContext.newInstance(User.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "utf-8");
			StringWriter writer = new StringWriter();
			marshaller.marshal(user, writer);
			System.out.println(writer.toString());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
