package webrocket;

/**
 * Message.
 *
 * @author Juan Hernandez
 */
public final class Message {

	/**
	 * Message severity.
	 */
	private Severity severity;

	/**
	 * Message text.
	 */
	private String text;

	/**
	 * Message key.
	 */
	private String key;

	/**
	 * Message arguments.
	 */
	private Object[] args;

	/**
	 * Message source.
	 */
	private String source;

	/**
	 * Creates a new message.
	 *
	 * @param builder The message builder.
	 */
	private Message(Builder builder) {
		this.severity = builder.severity;
		this.text = builder.text;
		this.key = builder.key;
		this.args = builder.args;
		this.source = builder.source;
	}

	public Severity getSeverity() {
		return severity;
	}

	public String getKey() {
		return key;
	}

	public Object[] getArgs() {
		return args;
	}

	public String getSource() {
		return source;
	}

	public static Builder withText(String text) {
		return new Builder().text(text);
	}

	public static enum Severity {

		info, warn, error, fatal
	}

	public static final class Builder {

		private Severity severity;

		private String text;

		private String key;

		private String[] args;

		private String source;

		private Builder() {
		}

		public Builder severity(Severity severity) {
			this.severity = severity;
			return this;
		}

		public Builder text(String text) {
			this.text = text;
			return this;
		}

		public Builder key(String key) {
			this.key = key;
			return this;
		}

		public Builder args(String... args) {
			this.args = args;
			return this;
		}

		public Builder source(String source) {
			this.source = source;
			return this;
		}

		public Message build() {
			return new Message(this);
		}
	}
}
