package webrocket;

import java.util.HashMap;
import java.util.Map;

/**
 * View.
 */
public final class View {

	/**
	 * View name.
	 */
	private String name;

	/**
	 * View attributes.
	 */
	private Map<String, Object> attributes = new HashMap<String, Object>();

	/**
	 * Creates a new view.
	 */
	public View() {
	}

	/**
	 * Creates a new view.
	 *
	 * @param name The view name.
	 */
	public View(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the view name.
	 *
	 * @return The view name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Adds an attribute to the view.
	 *
	 * @param name The attribute name.
	 * @param value The attribute value.
	 * @return This view.
	 */
	public View add(String name, Object value) {
		attributes.put(name, value);
		return this;
	}

	/**
	 * Gets all the attributes of this view.
	 *
	 * @return The attributes.
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}
}
