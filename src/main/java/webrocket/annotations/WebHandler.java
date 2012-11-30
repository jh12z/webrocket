package webrocket.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for mapping web requests onto specific handler classes and/or handler methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface WebHandler {

	/**
	 * Request path that this handler accepts.
	 */
	String path() default "";

	/**
	 * HTTP request method that this handler accepts.
	 */
	String method() default "get";

	/**
	 * Parameters of the mapped request.
	 */
	String[] params() default {};

	/**
	 * Headers of the mapped request.
	 */
	String[] headers() default {};

	/**
	 * Consumable media types of the mapped request.
	 * Eg. text/plain, application/*
	 */
	String[] consumes() default {};

	/**
	 * Producible media types of the mapped request.
	 * Eg. text/plain, application/*
	 */
	String[] produces() default {};
}
