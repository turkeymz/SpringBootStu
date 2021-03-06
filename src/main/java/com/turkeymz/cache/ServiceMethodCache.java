package com.turkeymz.cache;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jdom.input.SAXBuilder;
import org.jdom.Document;
import org.jdom.Element;
/**
 * 

 * @author: cmxu

 * @time:2017年3月27日 下午3:37:36

 * @version 1.0 APP服务缓存类
 */

public class ServiceMethodCache {
	private static Log log = LogFactory.getLog(ServiceMethodCache.class);
	public static HashMap<String, ServiceMethodEntity> map = new HashMap<String, ServiceMethodEntity>();
	
	public HashMap<String, ServiceMethodEntity> getMap() {
		return map;
	}

	public void setMap(HashMap<String, ServiceMethodEntity> map) {
		this.map = map;
	}

	public boolean load()  {
		log.info("[CACHE] ServiceMethodCache bulid Begin .................");
        SAXBuilder b = new SAXBuilder();
        Document xDocument = null;
        ServiceMethodEntity m = null;
        String path = this.getClass().getClassLoader().getResource("ServiceMethod.xml").getPath();
        log.info("load serviceMethod.xml path={"+path+"}");

        try {
            xDocument = b.build(path);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        Element xService = xDocument.getRootElement();
        for (Iterator itr = xService.getChildren("method").iterator(); itr.hasNext();){
            Element xMethod = (Element) itr.next();
            m = new ServiceMethodEntity();
            m.setClassName(xMethod.getAttributeValue("bean"));
            m.setMethodName(xMethod.getAttributeValue("method"));
            m.setType(xMethod.getAttributeValue("type"));
            map.put(xMethod.getAttributeValue("type"), m);
        }
		log.info("[CACHE] ServiceMethodCache bulid End .................");
		return true;
	}
	
	public static void main(String[] args) {
		ServiceMethodCache s = new ServiceMethodCache();
		log.info(s.getClass().getClassLoader().getResource("ServiceMethod.xml").getPath()+"+++++++++++");
        String path = s.getClass().getClassLoader().getResource("ServiceMethod.xml").getPath();
       System.out.println(path);
	}

}
