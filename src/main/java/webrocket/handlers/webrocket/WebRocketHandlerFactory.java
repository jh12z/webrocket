package webrocket.handlers.webrocket;

import webrocket.HttpContext;
import webrocket.View;
import webrocket.annotations.WebAttribute;
import webrocket.annotations.WebParam;
import webrocket.handlers.IParamHandler;
import webrocket.handlers.IParamHandlerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Creates param handlers for the following parameter types:
 * <ul>
 * <li>{@link HttpContext}</li>
 * <li>{@link WebParam} annotated parameters</li>
 * </ul>
 */
public final class WebRocketHandlerFactory implements IParamHandlerFactory {

	@Override
	public IParamHandler createParamHandler(Method method, int paramIndex) {
		// Handles the result
		if (paramIndex == -1) {
			Class returnType = method.getReturnType();
			if (returnType.equals(View.class)) {
				return ViewResponseHandler.instance;
			} else {
				return null;
			}
		}

		Class<?>[] parameterTypes = method.getParameterTypes();

		Class parameterType = parameterTypes[paramIndex];
		WebParam webParam;
		WebAttribute webAttribute;

		if (parameterType.equals(HttpContext.class)) {
			return HttpContextHandler.instance;
		} else if ((webParam = getParamAnnotation(method, paramIndex, WebParam.class)) != null) {
			return new WebParamHandler(webParam.name(), webParam.required());
		} else if ((webAttribute = getParamAnnotation(method, paramIndex, WebAttribute.class)) != null) {
			return new WebAttributeHandler(webAttribute.name(), webAttribute.required(), webAttribute.scope());
		} else {
			return null;
		}
	}

	/**
	 * Retrieves an annotation from a method parameter.
	 *
	 * @param method The method.
	 * @param paramIndex The parameter index.
	 * @param annotationType The annotation type.
	 * @param <T> The annotation type.
	 * @return The annotation or {@code null} if the annotation is not defined.
	 */
	private <T extends Annotation> T getParamAnnotation(Method method, int paramIndex, Class<T> annotationType) {
		Annotation[] annotations = method.getParameterAnnotations()[paramIndex];
		for (Annotation annotation : annotations) {
			if (annotationType.isAssignableFrom(annotation.getClass())) {
				return (T) annotation;
			}
		}
		return null;
	}
}
