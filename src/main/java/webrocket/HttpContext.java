package webrocket;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * HTTP context.
 */
public final class HttpContext {

	/**
	 * HTTP servlet.
	 */
	private HttpServlet servlet;

	/**
	 * HTTP request.
	 */
	private HttpServletRequest request;

	/**
	 * HTTP response.
	 */
	private HttpServletResponse response;

	/**
	 * Result.
	 */
	private Object result;

	/**
	 * Creates a new HTTP context.
	 *
	 * @param servlet The servlet instance.
	 * @param request The HTTP request.
	 * @param response The HTTP response.
	 */
	public HttpContext(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		this.servlet = servlet;
		this.request = request;
		this.response = response;
	}

	/**
	 * Gets the servlet context.
	 *
	 * @return The servlet context.
	 */
	public ServletContext getServletContext() {
		return servlet.getServletContext();
	}

	/**
	 * Gets the HTTP session.
	 *
	 * @return The HTTP session.
	 */
	public HttpSession getSession() {
		return request.getSession();
	}

	/**
	 * Gets the HTTP session.
	 *
	 * @param create {@code true} to create a new HTTP session in case none existed.
	 * @return The HTTP session.
	 */
	public HttpSession getSession(boolean create) {
		return request.getSession(create);
	}

	/**
	 * Gets the HTTP request.
	 *
	 * @return The HTTP request.
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * Gets the HTTP response.
	 *
	 * @return The HTTP response.
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	public PrintWriter getResponseWriter() throws IOException {
		return response.getWriter();
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
