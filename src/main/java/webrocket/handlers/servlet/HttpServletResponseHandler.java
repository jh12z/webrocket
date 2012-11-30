package webrocket.handlers.servlet;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

import javax.servlet.http.HttpServletResponse;

/**
 * Handler for {@link HttpServletResponse} parameters.
 */
public final class HttpServletResponseHandler implements IParamHandler {

	public static final IParamHandler instance = new HttpServletResponseHandler();

	@Override
	public Object handle(HttpContext context) {
		return context.getResponse();
	}
}
