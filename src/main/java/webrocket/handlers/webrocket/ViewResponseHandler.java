package webrocket.handlers.webrocket;

import webrocket.HttpContext;
import webrocket.View;
import webrocket.handlers.IParamHandler;
import webrocket.handlers.gson.GsonHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public final class ViewResponseHandler extends GsonHandler {

	public static final IParamHandler instance = new ViewResponseHandler();

	@Override
	public Object handle(HttpContext context) throws Exception {
		HttpServletRequest request = context.getRequest();
		View view = (View) context.getResult();

		if (view == null) {
			return null;
		}

		Map<String, Object> attributes = view.getAttributes();
		if (!attributes.isEmpty()) {
			for (String name : attributes.keySet()) {
				request.setAttribute(name, attributes.get(name));
			}
		}

		RequestDispatcher dispatcher = context.getServletContext().getRequestDispatcher(view.getName());
		if (dispatcher != null) {
			dispatcher.forward(request, context.getResponse());
		}

		return null;
	}
}
