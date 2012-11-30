package webrocket.handlers.servlet;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

import javax.servlet.http.HttpSession;

/**
 * Handler for {@link HttpSession} parameters.
 */
public final class HttpSessionHandler implements IParamHandler {

	public static final IParamHandler instance = new HttpSessionHandler();

	@Override
	public Object handle(HttpContext context) {
		return context.getSession();
	}
}
