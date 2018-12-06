package br.com.tdc.workshopapis.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiFilter implements Filter {

	public static final String AUTHORIZATION = "authorization";
	public static final String X_API_KEY = "x-api-key";

	public static final String VALID_API_KEY = "tdc";
	public static final String VALID_BASIC_AUTH = "dGRjOnRkYw=="; // b64("tdc:tdc")

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (isValid(req)) {
			chain.doFilter(request, response);
		} else {
			cancel(res);
		}
	}

	private boolean isValid(HttpServletRequest request) {
		String apiKeyHeader = request.getHeader(ApiFilter.X_API_KEY);
		String authorizationHeader = request.getHeader("Authorization");

		if (apiKeyHeader != null && !"".equals(apiKeyHeader)) {
			return ApiFilter.VALID_API_KEY.equals(apiKeyHeader);
		} else if (authorizationHeader != null && !"".equals(authorizationHeader)) {
			return ApiFilter.VALID_BASIC_AUTH.equals(authorizationHeader.replace("Basic ", ""));
		}

		return false;
	}

	private void cancel(HttpServletResponse response) throws IOException {
		response.reset();
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The authorization is invalid");
	}
}
