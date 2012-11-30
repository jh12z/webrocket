package webrocket.handlers;

import java.lang.reflect.Method;

/**
 * Interface to be implemented by factories to create specific param handlers.
 */
public interface IParamHandlerFactory {

	/**
	 * Creates a param handler for the given method and parameter.
	 * It can return {@code null} to indicate no handler.
	 *
	 * @param method The handler method.
	 * @param paramIndex The parameter index.
	 * @return The param handler instance.
	 */
	public IParamHandler createParamHandler(Method method, int paramIndex);
}
