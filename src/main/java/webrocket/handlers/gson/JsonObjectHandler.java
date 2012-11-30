package webrocket.handlers.gson;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

/**
 * Handler for {@link JsonObject} parameters.
 */
public final class JsonObjectHandler extends GsonHandler {

	public static final IParamHandler instance = new JsonObjectHandler();

	@Override
	public Object handle(HttpContext context) throws Exception {
		return gson.fromJson(new JsonReader(context.getRequest().getReader()), JsonObject.class);
	}
}
