package webrocket.handlers.webrocket;

import webrocket.HttpContext;
import webrocket.annotations.Scope;
import webrocket.annotations.WebAttribute;
import webrocket.handlers.IParamHandler;

/**
 * Handler for {@link WebAttribute} parameters.
 */
public final class WebAttributeHandler implements IParamHandler {

	/**
	 * Attribute name.
	 */
	private String name;

	/**
	 * Flag that indicates if the attribute is required.
	 */
	private boolean required;

	/**
	 * Attribute scope.
	 */
	private Scope scope;

	public WebAttributeHandler(String name, boolean required, Scope scope) {
		this.name = name;
		this.required = required;
		this.scope = scope;
	}

	@Override
	public Object handle(HttpContext context) {
		switch (scope) {
			case application:
				return context.getServletContext().getAttribute(name);
			case session:
				return context.getSession().getAttribute(name);
			case request:
				return context.getRequest().getAttribute(name);
			default:
				return null;
		}
	}
}
