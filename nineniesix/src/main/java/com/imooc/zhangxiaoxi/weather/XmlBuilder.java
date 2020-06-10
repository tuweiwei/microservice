package com.imooc.zhangxiaoxi.weather;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * Xml Builder.
 * 
 * @since 1.0.0 2017年11月23日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public class XmlBuilder {

	/**
	 * 将XML转为指定的POJO
	 * @param clazz
	 * @param xmlStr
	 * @return
	 * @throws Exception
	 */
	public static Object xmlStrToOject(Class<?> clazz, String xmlStr) throws Exception {
		Object xmlObject = null;
		Reader reader = null;
		JAXBContext context = JAXBContext.newInstance(clazz);
		
		// XML 转为对象的接口
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		reader = new StringReader(xmlStr);
		xmlObject = unmarshaller.unmarshal(reader);
		
		if (null != reader) {
			reader.close();
		}
		
		return xmlObject;
	}
}

//	// 读取XML文件
//	Resource resource = new ClassPathResource("citylist.xml");
//	BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
//	StringBuffer buffer = new StringBuffer();
//	String line = "";
//
//		while ((line = br.readLine()) !=null) {
//				buffer.append(line);
//				}
//
//				br.close();
//
//				// XML转为Java对象
//				CityList cityList = (CityList)XmlBuilder.xmlStrToOject(CityList.class, buffer.toString());
//		return cityList.getCityList();
