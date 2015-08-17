

package org.coiol.platform.common.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class WebUtils {

	public WebUtils() {
	}

	public static HashMap<String,String> getPraramsAsMap(HttpServletRequest request) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		Map<String, String[]> map = request.getParameterMap();
		Iterator<String> keyIterator = map.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = (String) keyIterator.next();
			String value = ((String[]) (String[]) map.get(key))[0];
			hashMap.put(key, value);
		}

		return hashMap;
	}
}
