package webrocket.handlers.gson;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

import javax.servlet.http.HttpServletResponse;

public final class JsonElementResponseHandler extends GsonHandler {

	public static final IParamHandler instance = new JsonElementResponseHandler();

	@Override
	public Object handle(HttpContext context) throws Exception {
		HttpServletResponse response = context.getResponse();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		gson.toJson(context.getResult(), response.getWriter());
		return null;
	}
}
