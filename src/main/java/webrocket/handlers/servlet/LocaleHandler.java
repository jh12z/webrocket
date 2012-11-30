package webrocket.handlers.servlet;

import webrocket.HttpContext;
import webrocket.handlers.IParamHandler;

import java.util.Locale;

/**
 * Handler for {@link Locale} parameters.
 */
public final class LocaleHandler implements IParamHandler {

	public static final IParamHandler instance = new LocaleHandler();

	@Override
	public Object handle(HttpContext context) {
		return context.getRequest().getLocale();
	}
}
