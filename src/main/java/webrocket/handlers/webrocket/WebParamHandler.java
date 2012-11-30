package webrocket.handlers.webrocket;

import webrocket.HttpContext;
import webrocket.annotations.WebParam;
import webrocket.handlers.IParamHandler;

/**
 * Handler for {@link WebParam} annotated parameters.
 */
public final class WebParamHandler implements IParamHandler {

	/**
	 * Parameter name.
	 */
	private String name;

	/**
	 * Flag that indicates if the parameter is required.
	 */
	private boolean required;

	public WebParamHandler(String name, boolean required) {
		this.name = name;
		this.required = required;
	}

	@Override
	public Object handle(HttpContext context) throws Exception {
		String value = context.getRequest().getParameter(name);
		if (required && (value == null || value.isEmpty())) {
			throw new RuntimeException("Parameter '" + name + "' is required.");
		}
		return value;
	}
}
