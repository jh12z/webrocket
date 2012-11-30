package webrocket.handlers.servlet;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

/**
 * Handler for {@link ServletOutputStream} parameters.
 */
public final class ServletOutputStreamHandler implements IParamHandler {

	public static final IParamHandler instance = new ServletOutputStreamHandler();

	@Override
	public Object handle(HttpContext context) throws IOException {
		return context.getResponse().getOutputStream();
	}
}
