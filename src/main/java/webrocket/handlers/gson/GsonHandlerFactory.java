package webrocket.handlers.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import webrocket.handlers.IParamHandler;
import webrocket.handlers.IParamHandlerFactory;

import java.lang.reflect.Method;

/**
 * GSON handler factory.
 */
public final class GsonHandlerFactory implements IParamHandlerFactory {

	@Override
	public IParamHandler createParamHandler(Method method, int paramIndex) {
		// Handles the result
		if (paramIndex == -1) {
			Class returnType = method.getReturnType();
			if (JsonElement.class.isAssignableFrom(returnType)) {
				return JsonElementResponseHandler.instance;
			} else {
				return null;
			}
		}

		Class<?>[] parameterTypes = method.getParameterTypes();
		Class parameterType = parameterTypes[paramIndex];

		if (parameterType.isAssignableFrom(JsonObject.class)) {
			return JsonObjectHandler.instance;

		} else {
			return null;
		}
	}
}
