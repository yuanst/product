package com.product.common.util;

 

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;


/**
 * JSON帮助工具包
 * 〈功能详细描述〉
 *

 */
public class JsonUtils {
	public static String toJson(Object obj) {
		String s = castToJson(obj);
		if (s != null) {
			return s;
		} else {
			return toJson(getAttributes(obj));
		}
	}
	/** 
	  * @Title: format 
	  * @Description: 该方法给集合转变成的JSON串中的各个属性添加变量名 
	  * @param @param varName 要添加的变量名
	  * @param @param list 要添加的集合
	  * @param @param excepts 哪些字段不需要添加变量名
	  * @param @return
	  * @param @throws Exception
	  * @return String
	  * @throws 
	  */
	public static String format(String varName, List list,String excepts) throws Exception{
		StringBuffer result = new StringBuffer("[");
		for(Object obj:list){
			Field[] fields = obj.getClass().getDeclaredFields();
			Field.setAccessible(fields, true);
			result.append("{");
			for(Field att:fields){
				Object o = att.get(obj);
				String value = toJson(o);
				if(value!=null && value.indexOf("{")==-1 ){
					boolean flag = false;
					if(null!=excepts){
						for(String ex:excepts.split(";")){
							if(att.getName().equals(ex)){
								flag = true;
								break;
							}
						}
					}
					if(!flag){
						result.append("\""+varName+"."+att.getName()+"\":");
					}else{
						result.append("\""+att.getName()+"\":");
					}
					result.append(value);
				}else{
					result.append("\""+att.getName()+"\":");
//					JSONArray json = JSONArray.fromObject(o);
//					String str = json.toString();
					if(value.indexOf("[")==0){
						value=value.substring(1, value.length()-1);
					}
					result.append(value);
				}
				result.append(",");
			}
			result.deleteCharAt(result.toString().length()-1);
			result.append("},");
		}
		result.deleteCharAt(result.toString().length()-1).append("]");
		return result.toString();
	}
	public static String toJson(Map<String, Object> map) {
		String result = "";
		for (String name : map.keySet()) {
			Object value = map.get(name);
			String s = castToJson(value);
			if (s != null) {
				result += "\"" + name + "\":" + s + ",";
			} else if (value instanceof List<?>) {
				String v = toJson((List<?>) value);
				result += "\"" + name + "\":" + v + ",";
			} else if (value instanceof Object[]) {
				String v = toJson((Object[]) value);
				result += "\"" + name + "\":" + v + ",";
			} else if (value instanceof Map<?, ?>) {
				Map<String, Object> attr = castMap((Map<?, ?>) value);
				attr = removeListAttr(attr);
				result += "\"" + name + "\":" + toJson(attr) + ",";
			} else if (value.getClass().getName().startsWith("java") == false) {
				Map<String, Object> attr = getAttributes(value);
				attr = removeListAttr(attr);
				result += "\"" + name + "\":" + toJson(attr) + ",";
			} else {
				result += "\"" + name + "\":" + "\"" + value.toString() + "\",";
			}
		}
		if (result.length() == 0) {
			return "{}";
		} else {
			return "{" + result.substring(0, result.length() - 1) + "}";
		}
	}

	public static String toJson(Object[] aa) {
		if (aa.length == 0) {
			return "[]";
		} else {
			String result = "";
			for (Object obj : aa) {
				String s = castToJson(obj);
				if (s != null) {
					result += s + ",";
				} else if (obj instanceof Map<?, ?>) {
					Map<String, Object> map = castMap((Map<?, ?>) obj);
					map = removeListAttr(map);
					result += toJson(map) + ",";
				} else {
					Map<String, Object> attr = getAttributes(obj);
					attr = removeListAttr(attr);
					result += toJson(attr) + ",";
				}
			}
			return "[" + result.substring(0, result.length() - 1) + "]";
		}
	}

	public static String toJson(List<?> ll) {
		return toJson(ll.toArray());
	}

	/**
	 * 取得对象的属性
	 * 
	 * @param obj
	 * @return 对象属性表
	 */
	public static Map<String, Object> getAttributes(Object obj) {
		Class<?> c = obj.getClass();
		try {
			Method method = c.getMethod("isProxy");
			Boolean result = (Boolean) method.invoke(obj);
			if (result == true) {
				c = c.getSuperclass();
			}
		} catch (Exception e) {
		}
		Map<String, Object> attr = new HashMap<String, Object>();

		// 取得所有公共字段
		for (Field f : c.getFields()) {
			try {
				Object value = f.get(obj);
				attr.put(f.getName(), value);
			} catch (Exception e) {
			}
		}

		// 取得所有本类方法
		for (Method m : c.getDeclaredMethods()) {
			String mname = m.getName();
			if (mname.equals("getClass")) {
				continue;
			} else if (mname.startsWith("get")) {
				String pname = mname.substring(3);
				if (pname.length() == 1) {
					pname = pname.toLowerCase();
				} else {
					pname = pname.substring(0, 1).toLowerCase()
							+ pname.substring(1);
				}

				try {
					Object value = m.invoke(obj);
					attr.put(pname, value);
				} catch (Exception e) {
				}
			} else if (mname.startsWith("is")) {
				String pname = mname.substring(2);
				if (pname.length() == 1) {
					pname = pname.toLowerCase();
				} else {
					pname = pname.substring(0, 1).toLowerCase()
							+ pname.substring(1);
				}

				try {
					Object value = m.invoke(obj);
					attr.put(pname, value);
				} catch (Exception e) {
				}
			}
		}
		return attr;
	}

	/**
	 * 将简单对象转换成JSON串
	 * 
	 * @param obj
	 * @return 如果是简单对象则返回JSON，如果是复杂对象则返回null
	 */
	private static String castToJson(Object obj) {
		if (obj == null) {
			return "null";
		} else if (obj instanceof Boolean) {
			return obj.toString();
		} else if (obj instanceof Integer || obj instanceof Long
				|| obj instanceof Float || obj instanceof Double
				|| obj instanceof Short || obj instanceof java.math.BigInteger
				|| obj instanceof java.math.BigDecimal) {
			return obj.toString();
		} else if (obj instanceof String) {
			String v = (String) obj;
			v = v.replaceAll("\\\\", "\\\\\\\\");
			v = v.replaceAll("\n", "\\\\n");
			v = v.replaceAll("\r", "\\\\r");
			v = v.replaceAll("\t", "\\\\t");
			v = v.replaceAll("\"", "\\\\\"");
//			v = v.replaceAll("'", "\\\\\'");
			return "\"" + v + "\"";
		} else if (obj instanceof java.sql.Date) {
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
					"yyyy-MM-dd");
			java.sql.Date v = (java.sql.Date) obj;
			String s = df.format(new java.util.Date(v.getTime()));
			return "\"" + s + "\"";
		} else if (obj instanceof java.sql.Timestamp) {
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			java.sql.Timestamp v = (java.sql.Timestamp) obj;
			String s = df.format(new java.util.Date(v.getTime()));
			return "\"" + s + "\"";
		} else if (obj instanceof java.util.Date) {
			java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
			java.util.Date v = (java.util.Date) obj;
			String s = df.format(v);
			return "\"" + s + "\"";
		} else {
			return null;
		}

	}

	public static Map<String, Object> castMap(Map<?, ?> map) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		for (Object key : map.keySet()) {
			newMap.put(key.toString(), map.get(key));
		}
		return newMap;
	}

	/**
	 * 删除属性中类型是List的属性
	 * 
	 * @param map
	 * @return
	 */
	private static Map<String, Object> removeListAttr(Map<String, Object> map) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		for (Entry<String, Object> en : map.entrySet()) {
			if (!(en.getValue() instanceof List<?>)) {
				newMap.put((String) en.getKey(), en.getValue());
			}
		}
		return newMap;
	}
    /**
     * JsonConfig cfg = new JsonConfig();
     * <br/>cfg.setRootClass(rootClass);//设置要转换的类型
     * <br/>cfg.setExcludes(new String[]{...});//...为要过滤的字段，过滤这些字段不被解析。
     * <br/>cfg.setIgnoreDefaultExcludes(true);//默认为false，即过滤默认值的key。
     * <br/>cfg.registerJsonBeanProcessor(Date.class,new JsDateJsonBeanProcessor());// 当输出时间格式时，采用和JS兼容的格式输出。
     */
    
    /**
     * 这是一个说明字段
     */
    public final static boolean HELP_JSONCONFIG = true;
    
	/**
     * 把JSON格式数据转换为Bean领域模型
     * @param <T> 领域模型的类型，返回结果会根据此类型来判断返回类型。
     * @param json JSON格式数据
     *          （如果JSON串里的属性在Bean里没有该属性对象的set方法将会报错，
     *              如果要实现过滤XXX字段转换请使用JsonConfig方法。）
     * @param beanClass 要转换的领域模型类型
     * @return 根据beanClass的类型来返回解析的领域模型对象

	
	 */
	public static <T> T jsonToBean(String json, Class<T> beanClass){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return beanClass.cast(JSONObject.toBean(jsonObject,beanClass));
	}
	
	/**
     *  把JSON格式数据转换为Bean领域模型
     * @param json JSON格式数据
     * @param jsonConfig JSON转换格式配置文件（设置方法详细见底部说明，或本类的HELP_JSONCONFIG常量DOM注释。）
     * @return 解析的领域模型对象

	
	 */
	public static Object jsonToBean(String json, JsonConfig jsonConfig){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return  jsonConfig.getRootClass().cast(JSONObject.toBean(jsonObject,jsonConfig));
	}

	/**
     * 把JSON格式数据转换为Bean领域模型集合
     * @param <T> 领域模型的类型，返回结果会根据此类型来判断返回类型。
     * @param json JSON格式数据
     *          （如果JSON串里的属性在Bean里没有该属性对象的set方法将会报错，
     *              如果要实现过滤XXX字段转换请使用JsonConfig方法。）
     * @param beanClass 要转换的领域模型类型
     * @return 根据beanClass的类型来返回解析的领域模型对象集合的泛型类型

	
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> jsonToBeanList(String json, Class<T> beanClass){
		JSONArray jsonArray = JSONArray.fromObject(json);
		Collection<T> collection = JSONArray.toCollection(jsonArray, beanClass);
		List<T> list = new ArrayList<T>();
		for (T t : collection) {
		    if(beanClass.equals(t.getClass())){
		        list.add(t);
		    }
		}
		return list;
	}

	/**
     * 把JSON格式数据转换为Bean领域模型集合
     * @param json JSON格式数据
     * @param jsonConfig JSON转换格式配置文件（设置方法详细见底部说明，或本类的HELP_JSONCONFIG常量DOM注释。）
     * @return 解析的领域模型对象集合

	
	 */
	public static Collection<?> jsonToBeanList(String json, JsonConfig jsonConfig){
		JSONArray jsonArray = JSONArray.fromObject(json);
		Collection<?> collection = JSONArray.toCollection(jsonArray, jsonConfig);
		return collection;
	}

	
	/**
     * 把一个对象转换为JSON格式数据
     * @param bean 领域模型对象
     *              （如果要过滤默认值的字段或某些字段的值请使用JsonConfig方法。）
     * @return JSON格式数据

	
	 */
	public static String beanToJson(Object bean){
		return JSONObject.fromObject(bean).toString();
	}

	
	/**
     * 把一个对象转换为JSON格式数据
     * @param bean 领域模型对象
     * @param jsonConfig JSON转换格式配置文件（设置方法详细见底部说明，或本类的HELP_JSONCONFIG常量DOM注释。）
     * @return JSON格式数据
	 */
	public static String beanToJson(Object bean,JsonConfig jsonConfig){
		return JSONObject.fromObject(bean,jsonConfig).toString();
	}
	
	/**
     * 获取JSON格式数据某个属性值
     * @param json JSON格式数据
     * @param key 要获取的属性名
     * @return 指定属性的值

	
	 */
	public static Object getJsonAttribute(String json,String key){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return jsonObject.get(key);
	}

	
	/**
     * 获取JSON格式数据某个属性值
     * @param <T> 属性值的类型，返回结果会根据此类型来判断返回类型。
     * @param json JSON格式数据
     * @param key 要获取的属性名
     * @param attributeClass 要获取的属性类型
     * @return 根据attributeClass的类型来返回该类型的数据

	
	 */
	public static <T> T getJsonAttribute(String json,String key,Class<T> attributeClass){
		JSONObject jsonObject = JSONObject.fromObject(json);
		return attributeClass.cast(jsonObject.get(key));
	}
	
	/**
	 * 将数组转化为JSON字符串
	 * 〈功能详细描述〉
	 *
	 * @param array
	 * @return

	
	 */
	public static String array2Json(Object[] array) {
		return JSONArray.fromObject(array).toString();
	}
	
	/**
	 * 将集合对象转化为JSON字符串
	 * 〈功能详细描述〉
	 *
	 * @param collection
	 * @return

	
	 */
	public static String collection2Json(Collection collection) {
		return JSONArray.fromObject(collection).toString();
	}
	

}
