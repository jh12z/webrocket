package webrocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import webrocket.handlers.IParamHandlerFactory;
import webrocket.util.ClassUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Web dispatcher.
 */
public class WebDispatcher extends HttpServlet {

	// TODO - juan.hernandez - Associate an extractor to each method on a handler
	// TODO - juan.hernandez - Delay initialization of method handler until necessary or provide parameter for that.

	private static final Logger logger = LoggerFactory.getLogger(WebDispatcher.class);

	private WebHandlers handlers = new WebHandlers();

	@Override
	public void init() throws ServletException {
		// Configures the param handler factories
		String paramHandlerFactories = getServletConfig().getInitParameter("handlerFactories");
		if (paramHandlerFactories != null) {
			for (StringTokenizer t = new StringTokenizer(paramHandlerFactories, "\r\n\t "); t.hasMoreTokens(); ) {
				String className = t.nextToken();
				IParamHandlerFactory factory = (IParamHandlerFactory) ClassUtil.newInstance(className);
				handlers.register(factory);
			}
		}

		// Configures the controller classes
		String handlersParam = getServletConfig().getInitParameter("handlers");
		if (handlersParam != null) {
			for (StringTokenizer t = new StringTokenizer(handlersParam, "\r\n\t "); t.hasMoreTokens(); ) {
				String className = t.nextToken();
				Object controller = ClassUtil.newInstance(className);
				handlers.register(controller);
			}
		}
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieves the handler to process the request
		WebHandler handler = handlers.findHandler(request);
		if (handler == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Handler not found.");
			return;
		}

		handler.execute(this, request, response);
	}
}
