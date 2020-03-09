/**
 * Coppyright(C) [2019] Luvina software company
 *DatabaseProperties.java [Aug 5, 2019]
 */
package manageuser.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author QuyetThang
 *
 */
@SuppressWarnings("unchecked")
public class DatabaseProperties {
    static private Map<String, String> data = new HashMap<String, String>();

    static {
        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(DatabaseProperties.class.getResourceAsStream("/database.properties"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration<String> en  = (Enumeration<String>)prop.propertyNames();
        while (en.hasMoreElements()) {
            String key = (String)en.nextElement();
            data.put(key, prop.getProperty(key));
        }
    }
    /**
	 * Hàm đọc từ trong map
	 * 
	 * @param key:
	 *            key cần đọc
	 * 
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
