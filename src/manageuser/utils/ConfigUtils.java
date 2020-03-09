/**
 * Coppyright(C) [2019] Luvina software company
 *ConfigUtils.java [Aug 6, 2019]
 */
package manageuser.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
@SuppressWarnings("unchecked")
public class ConfigUtils {
	
	static private Map<String, String> data = new HashMap<String, String>();
    
	static {
		Properties prop = new Properties();
		try {
			prop.load(
					new InputStreamReader(DatabaseProperties.class.getResourceAsStream("/config.properties"), "UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration<String> en = (Enumeration<String>) prop.propertyNames();
		while (en.hasMoreElements()) {
			String key = (String) en.nextElement();
			data.put(key, prop.getProperty(key));
		}
	}
	/**
	 * Hàm đọc từ trong map
	 * @param key: key cần đọc
	 * @return giá trị của key
	 */
	public static String getData(String key) {
		String value = "";
		if (data.containsKey(key)) {
			value = data.get(key);
		}
		return value;
	}
}
