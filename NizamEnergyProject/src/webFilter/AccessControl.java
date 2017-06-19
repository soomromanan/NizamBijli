package webFilter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import bal.AccessControlBAL;

@WebFilter("/AccessControl")
public class AccessControl implements Filter {

	public AccessControl() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// place your code here
		HttpServletRequest req = ((HttpServletRequest) request);
		String url = req.getRequestURL().toString();

		if (req.getSession().getAttribute("userType") != null) {
			String userType = req.getSession().getAttribute("userType")
					.toString();
			List<String> screens = AccessControlBAL.getScreenByUserType(Integer
					.parseInt(userType));
			for (String str : screens) {
				if (url.contains(str)) {
					req.getSession().invalidate();
					RequestDispatcher rd = req
							.getRequestDispatcher("SolarHomeSystemLogin");
					rd.forward(request, response);
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
