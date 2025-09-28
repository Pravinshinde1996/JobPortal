package com.jobportal.backend.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiKeyFilter implements Filter {

	@Value("${app.scraper.api-key}")
	private String scraperApiKey;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getRequestURI();
		// only protect the /internal/* endpoints
		
		if (path.startsWith("/internal/")) {
			String key = req.getHeader("X-API-KEY");
			if (key == null || !key.equals(scraperApiKey)) {
				((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"Missing or invalid API key");
				return;
			}
		}
		chain.doFilter(request, response);
	}

}
