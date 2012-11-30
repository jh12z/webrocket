package webrocket.handlers;

import webrocket.HttpContext;

/**
 * TODO - Document it.
 */
public class UnknownParamHandler implements IParamHandler {

	public static final IParamHandler instance = new UnknownParamHandler();

	public Object handle(HttpContext context) throws Exception {
		return null;
	}
}
