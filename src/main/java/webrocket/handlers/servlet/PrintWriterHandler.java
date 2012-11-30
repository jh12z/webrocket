package webrocket.handlers.servlet;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handler for {@link PrintWriter} parameters.
 */
public final class PrintWriterHandler implements IParamHandler {

	public static final IParamHandler instance = new PrintWriterHandler();

	@Override
	public Object handle(HttpContext context) throws IOException {
		return context.getResponse().getWriter();
	}
}
