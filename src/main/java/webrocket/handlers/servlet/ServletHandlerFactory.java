package webrocket.handlers.servlet;

import webrocket.handlers.IParamHandler;
import webrocket.handlers.IParamHandlerFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Locale;

/**
 * Creates param handlers for the following parameter types:
 * <ul>
 * <li>{@link HttpServletRequest}</li>
 * <li>{@link HttpServletResponse}</li>
 * <li>{@link HttpSession}</li>
 * <li>{@link Locale}</li>
 * <li>{@link ServletInputStream}</li>
 * <li>{@link ServletOutputStream}</li>
 * <li>{@link PrintWriter}</li>
 * <li>{@link Principal}</li>
 * </ul>
 */
public final class ServletHandlerFactory implements IParamHandlerFactory {

	@Override
	public IParamHandler createParamHandler(Method method, int paramIndex) {
		// Handles the result
		if (paramIndex == -1) {
			return null;
		}

		Class<?>[] parameterTypes = method.getParameterTypes();
		Class parameterType = parameterTypes[paramIndex];

		if (parameterType.isAssignableFrom(HttpServletRequest.class)) {
			return HttpServletRequestHandler.instance;

		} else if (parameterType.isAssignableFrom(HttpServletResponse.class)) {
			return HttpServletResponseHandler.instance;

		} else if (parameterType.isAssignableFrom(HttpSession.class)) {
			return HttpSessionHandler.instance;

		} else if (parameterType.equals(Locale.class)) {
			return LocaleHandler.instance;

		} else if (parameterType.isAssignableFrom(ServletInputStream.class)) {
			return ServletInputStreamHandler.instance;

		} else if (parameterType.isAssignableFrom(ServletOutputStream.class)) {
			return ServletOutputStreamHandler.instance;

		} else if (parameterType.isAssignableFrom(PrintWriter.class)) {
			return PrintWriterHandler.instance;

		} else if (parameterType.equals(Principal.class)) {
			return PrincipalHandler.instance;

		} else {
			return null;
		}
	}
}
