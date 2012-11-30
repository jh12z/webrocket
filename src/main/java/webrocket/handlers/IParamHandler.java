package webrocket.handlers;

import webrocket.HttpContext;

/**
 * Web param handler.
 */
public interface IParamHandler {

	public Object handle(HttpContext context) throws Exception;
}
