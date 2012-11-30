package webrocket.handlers.gson;

import com.google.gson.Gson;
import webrocket.handlers.IParamHandler;

/**
 * GSON handler.
 */
public abstract class GsonHandler implements IParamHandler {

	protected static final Gson gson = new Gson();
}
