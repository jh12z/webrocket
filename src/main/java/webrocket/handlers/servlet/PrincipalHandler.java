package webrocket.handlers.servlet;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

import java.io.IOException;
import java.security.Principal;

/**
 * Handler for {@link Principal} parameters.
 */
public final class PrincipalHandler implements IParamHandler {

	public static final IParamHandler instance = new PrincipalHandler();

	@Override
	public Object handle(HttpContext context) throws IOException {
		return context.getRequest().getUserPrincipal();
	}
}
