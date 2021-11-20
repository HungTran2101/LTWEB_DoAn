package common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class MyLib {
	public static int checkCookies(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies == null) {
			return -1;
		}
		else {
			for(Cookie ck : cookies) {
				if(ck.getName().equals("userID")) {
					return Integer.parseInt(ck.getValue());
				}
			}
			return -1;
		}
	}
}
