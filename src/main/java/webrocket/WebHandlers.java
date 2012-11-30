package webrocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webrocket.handlers.IParamHandler;
import webrocket.handlers.IParamHandlerFactory;
import webrocket.handlers.UnknownParamHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Web handlers.
 */
public class WebHandlers {

	private static final Logger logger = LoggerFactory.getLogger(WebHandlers.class);

	private List<IParamHandlerFactory> paramHandlerFactories = new ArrayList<IParamHandlerFactory>();

	private Map<String, List<WebHandler>> handlers = new LinkedHashMap<String, List<WebHandler>>();

	private List<WebHandler> getHandlers(String handlerKey, boolean create) {
		List<WebHandler> handlers = this.handlers.get(handlerKey);
		if (handlers == null && create) {
			handlers = new LinkedList<WebHandler>();
			this.handlers.put(handlerKey, handlers);
		}
		return handlers;
	}

	/**
	 * Finds the most suitable handler to process the request.
	 *
	 * @param request The HTTP request.
	 * @return The handler object or null if no handler is found.
	 */
	public WebHandler findHandler(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();

		String path = (pathInfo != null) ? request.getServletPath() + request.getPathInfo() : request.getServletPath();
		String method = request.getMethod();
		String handlerKey = path + "|" + method.toLowerCase();

		List<WebHandler> handlers = getHandlers(handlerKey, false);
		if (handlers != null) {
			outer:
			for (WebHandler handler : handlers) {
				// Checks if the request parameters match
				WebHandler.NameValue[] params = handler.getParams();
				if (params != null) {
					for (WebHandler.NameValue param : params) {
						if (!param.getValue().equals(request.getParameter(param.getName()))) {
							continue outer;
						}
					}
				}

				// Checks if the request headers match
				WebHandler.NameValue[] headers = handler.getHeaders();
				if (headers != null) {
					for (WebHandler.NameValue header : headers) {
						if (!header.getValue().equals(request.getHeader(header.getName()))) {
							continue outer;
						}
					}
				}

				return handler;
			}
		}

		return null;
	}

	public final void register(IParamHandlerFactory paramHandlerFactory) {
		paramHandlerFactories.add(paramHandlerFactory);
	}

	/**
	 * Registers all the handlers found in the given instance.
	 *
	 * @param handlerInstance The handler instance.
	 */
	protected final void register(Object handlerInstance) {
		Class handlerClass = handlerInstance.getClass();

		webrocket.annotations.WebHandler classHandler = (webrocket.annotations.WebHandler) handlerClass.getAnnotation(webrocket.annotations.WebHandler.class);

		// Scans all the methods looking for WebHandler annotations
		Method[] methods = handlerClass.getMethods();
		for (Method method : methods) {
			webrocket.annotations.WebHandler methodHandler = method.getAnnotation(webrocket.annotations.WebHandler.class);
			if (methodHandler != null) {
				int modifiers = method.getModifiers();
				if (!Modifier.isPublic(modifiers)) {
					throw new RuntimeException("Method has to be public");
				}

				WebHandler handler = new WebHandler();
				handler.setPath((classHandler != null) ? classHandler.path() + methodHandler.path() : methodHandler.path());
				handler.setMethod(methodHandler.method());
				handler.setHandlerInstance(handlerInstance);
				handler.setHandlerMethod(method);
				handler.setParams(toNameValues(methodHandler.params()));
				handler.setHeaders(toNameValues(methodHandler.headers()));
				handler.setParamHandlers(createParamHandlers(method));
				handler.setResultHandler(createResultHandler(method));

				register(handler);
			}
		}
	}

	public void register(WebHandler handler) {
		String path = handler.getPath();
		String method = handler.getMethod();

		String handlerKey = path + "|" + method;

		List<WebHandler> handlers = getHandlers(handlerKey, true);
		handlers.add(handler);

		logger.debug("Added handler, path = {}, method = {}, handler = {}.{}", new Object[]{path, method, handler.getHandlerInstance().getClass().getName(), handler.getHandlerMethod().getName()});
	}

	private IParamHandler[] createParamHandlers(Method method) {
		Class<?>[] paramTypes = method.getParameterTypes();
		int paramCount = paramTypes.length;

		IParamHandler[] paramHandlers = new IParamHandler[paramCount];
		for (int i = 0; i < paramCount; i++) {
			paramHandlers[i] = createParamHandler(method, i);
		}

		return paramHandlers;
	}

	private IParamHandler createResultHandler(Method method) {
		return createParamHandler(method, -1);
	}

	private IParamHandler createParamHandler(Method method, int paramIndex) {
		for (IParamHandlerFactory paramHandlerFactory : paramHandlerFactories) {
			IParamHandler paramHandler = paramHandlerFactory.createParamHandler(method, paramIndex);
			if (paramHandler != null) {
				return paramHandler;
			}
		}
		return UnknownParamHandler.instance;
	}

	private WebHandler.NameValue[] toNameValues(String[] values) {
		if (values == null || values.length == 0) {
			return null;
		}

		int length = values.length;
		WebHandler.NameValue[] entries = new WebHandler.NameValue[length];

		for (int i = 0; i < length; i++) {
			String value = values[i];
			int sep = value.indexOf("=");

			WebHandler.NameValue entry = new WebHandler.NameValue();
			entry.setName((sep != -1) ? value.substring(0, sep).trim() : value);
			entry.setValue((sep != -1) ? value.substring(sep + 1).trim() : null);

			entries[i] = entry;
		}

		return entries;
	}
}
