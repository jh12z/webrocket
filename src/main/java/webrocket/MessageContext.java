package webrocket;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Message context.
 */
public class MessageContext {

	/**
	 * Messages indexed by source.
	 */
	private Map<String, List<Message>> messages;

	/**
	 * Message count.
	 */
	private int messageCount;

	/**
	 * Creates an empty message context.
	 */
	public MessageContext() {
		this.messages = new LinkedHashMap<String, List<Message>>();
	}

	/**
	 * Creates a message context with the given messages.
	 *
	 * @param messageMap The initial messages.
	 */
	public MessageContext(Map<String, List<Message>> messageMap) {
		if (messageMap != null) {
			int messageCount = 0;
			for (List<Message> messages : messageMap.values()) {
				for (Message message : messages) {
					messageCount++;
				}
			}

			this.messages = messageMap;
			this.messageCount = messageCount;

		} else {
			this.messages = new LinkedHashMap<String, List<Message>>();
		}
	}

	/**
	 * Clears all the messages in this context.
	 */
	public void clear() {
		messages.clear();
		messageCount = 0;
	}

	/**
	 * Adds a message to this context.
	 *
	 * @param message The message details.
	 */
	public void addMessage(Message message) {
		String source = message.getSource();

		List<Message> messageList = messages.get(source);
		if (messageList == null) {
			messageList = new LinkedList<Message>();
			messages.put(source, messageList);
		}

		messageList.add(message);
		messageCount++;
	}

	/**
	 * Adds a message to this context.
	 *
	 * @param message The message details.
	 */
	public void addMessage(Message.Builder message) {
		addMessage(message.build());
	}

	/**
	 * Gets all the messages stored in this context indexed by source.
	 *
	 * @return A map containing all the messages.
	 */
	public Map<String, List<Message>> getMessages() {
		return messages;
	}

	/**
	 * Gets all the messages associated to a particular source.
	 *
	 * @param source The source.
	 * @return The list of messages or null if no messages are associated to the given source.
	 */
	public List<Message> getMessages(String source) {
		return messages.get(source);
	}

	/**
	 * Gets the number of messages stored in this context.
	 *
	 * @return The number of messages.
	 */
	public int getMessageCount() {
		return messageCount;
	}

	/**
	 * Gets the number of messages associated to a particular source.
	 *
	 * @param source The source.
	 * @return The number of messages associated to the given source.
	 */
	public int getMessageCount(String source) {
		List<Message> messageList = messages.get(source);
		return (messageList != null) ? messageList.size() : 0;
	}
}
