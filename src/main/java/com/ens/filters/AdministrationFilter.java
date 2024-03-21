package com.ens.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.ens.beans.CookieBean;


/**
 * Servlet Filter implementation class AdministrationFilter
 */
@WebFilter("/administration/")
public class AdministrationFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AdministrationFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        CookieBean instance=new CookieBean();

        String role =instance.getCookie(req.getCookies(), "role");
        
        if(role!=null) {
        	switch (role) {
				case (String)"admin": {			
					res.sendRedirect(req.getContextPath() + "/administration/etudiants");
					return;
				}
				case (String)"user": {						
					res.sendRedirect(req.getContextPath() + "/utilisation/etudiants");
					return;
				}
				default:
					res.sendRedirect(req.getContextPath() + "/");
					return;
			}
        }
        res.sendRedirect(req.getContextPath() + "/");
		return;
        
        
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
