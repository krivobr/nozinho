package br.com.nozinho.web.seguranca.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Login Filter
 * 
 *
 */
public class LoginFilter implements Filter {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(LoginFilter.class);

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		throws IOException,
		ServletException {

		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse res = (HttpServletResponse) servletResponse;

		if (req.getUserPrincipal() == null) {
			req.getSession().setAttribute("from", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/index.xhtml");
		} else {
			LOG.debug("User Princiapl find me in the database is: " + req.getUserPrincipal().getName());
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
