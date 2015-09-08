package cn.com.bsfit.frms.policy.utils;

import cn.com.bsfit.frms.policy.pojo.rams.ChecklistRisks;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射的工具类
 */
public class ReflectUtil {

	/**
	 * 将POJO对象转成Map
	 */
	public static Map<String, Object> pojoToMap(Object obj) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		try {
			Class<?> c = obj.getClass();
			Method m[] = c.getDeclaredMethods();
			for (Method aM : m) {
				String methodName = aM.getName();
				if (methodName.indexOf("get") == 0) {
					hashMap.put((char) (methodName.charAt(3) + 32) + methodName.substring(4), aM.invoke(obj));
				}
			}
		} catch (Throwable e) {
			System.err.println(e);
		}
		return hashMap;
	}

	public static void main(String[] args) {

		ChecklistRisks checklistRisks = new ChecklistRisks();
		checklistRisks.setId(119L);
		checklistRisks.setChecklistId(111L);
		checklistRisks.setRuleName("规则名");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			// 转换一百万次才2秒，效率完全可以接受
			ReflectUtil.pojoToMap(checklistRisks);
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start + "毫秒");
//		Map<String, Object> personMap = ReflectUtil.pojoToMap(checklistRisks);
//		for (Map.Entry<String, Object> entry : personMap.entrySet()) {
//			System.out.println("方法名：" + entry.getKey() + " ,值: " + entry.getValue());
//		}
	}
}
