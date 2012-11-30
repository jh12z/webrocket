package webrocket.handlers.servlet;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Handler for {@link HttpServletRequest} parameters.
 */
public final class HttpServletRequestHandler implements IParamHandler {

	public static final IParamHandler instance = new HttpServletRequestHandler();

	@Override
	public Object handle(HttpContext context) {
		return context.getRequest();
	}
}
