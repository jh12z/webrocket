package webrocket.handlers.webrocket;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

/**
 * Handler for {@link HttpContext} parameters.
 */
public final class HttpContextHandler implements IParamHandler {

	public static final IParamHandler instance = new HttpContextHandler();

	@Override
	public Object handle(HttpContext context) {
		return context;
	}
}
