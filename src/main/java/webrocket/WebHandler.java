package webrocket;

import webrocket.handlers.IParamHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Web handler.
 */
public class WebHandler {

	/**
	 * Path.
	 */
	private String path;

	/**
	 * HTTP method.
	 */
	private String method;

	/**
	 * Request parameters.
	 */
	private NameValue[] params;

	/**
	 * Request headers.
	 */
	private NameValue[] headers;

	/**
	 * Handler instance.
	 */
	private Object handlerInstance;

	/**
	 * Handler method.
	 */
	private Method handlerMethod;

	/**
	 * Param handlers.
	 */
	private IParamHandler[] paramHandlers;

	/**
	 * Result handler.
	 */
	private IParamHandler resultHandler;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public NameValue[] getParams() {
		return params;
	}

	public void setParams(NameValue[] params) {
		this.params = params;
	}

	public NameValue[] getHeaders() {
		return headers;
	}

	public void setHeaders(NameValue[] headers) {
		this.headers = headers;
	}

	public Object getHandlerInstance() {
		return handlerInstance;
	}

	public void setHandlerInstance(Object handlerInstance) {
		this.handlerInstance = handlerInstance;
	}

	public Method getHandlerMethod() {
		return handlerMethod;
	}

	public void setHandlerMethod(Method handlerMethod) {
		this.handlerMethod = handlerMethod;
	}

	public IParamHandler[] getParamHandlers() {
		return paramHandlers;
	}

	public void setParamHandlers(IParamHandler[] paramHandlers) {
		this.paramHandlers = paramHandlers;
	}

	public IParamHandler getResultHandler() {
		return resultHandler;
	}

	public void setResultHandler(IParamHandler resultHandler) {
		this.resultHandler = resultHandler;
	}

	public void execute(HttpServlet servlet, HttpServletRequest request, HttpServletResponse response) {
		HttpContext context = new HttpContext(servlet, request, response);

		Object[] params = parseParams(context);

		try {
			Object result = handlerMethod.invoke(handlerInstance, params);
			context.setResult(result);
			resultHandler.handle(context);

		} catch (IllegalAccessException e) {
			throw new WebHandlerException("Error executing handler.", e);

		} catch (InvocationTargetException e) {
			throw new WebHandlerException("Error executing handler.", e.getTargetException());

		} catch (Exception e) {
			throw new WebHandlerException("Error executing handler.", e);
		}
	}

	private Object[] parseParams(HttpContext context) {
		Object[] params = new Object[paramHandlers.length];
		for (int i = 0; i < params.length; i++) {
			try {
				params[i] = paramHandlers[i].handle(context);
			} catch (Exception e) {
				throw new WebHandlerException("Error handling parameter at index " + i, e);
			}
		}
		return params;
	}

	public static class NameValue {

		private String name;

		private String value;

		public NameValue() {
		}

		public NameValue(String name, String value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
}
