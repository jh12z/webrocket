package webrocket.handlers.servlet;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * Handler for {@link ServletInputStream} parameters.
 */
public final class ServletInputStreamHandler implements IParamHandler {

	public static final IParamHandler instance = new ServletInputStreamHandler();

	@Override
	public Object handle(HttpContext context) throws IOException {
		return context.getRequest().getInputStream();
	}
}
