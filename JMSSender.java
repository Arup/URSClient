import javax.jms.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import javax.naming.*;

public class JMSSender implements Runnable {
	private static Connection connection;
	private static Session session;
	private Topic UnivRequest;
	private MessageConsumer consumer;
	private MessageProducer MP;
	private Topic replyTopic;

	String message = "";
	static String response = null;

	static InputStreamReader converter = new InputStreamReader(System.in);
	static BufferedReader in = new BufferedReader(converter);

	Thread sender = new Thread(this);

	public String sendRequest(String message) throws JMSException {
		TextMessage TM1 = session.createTextMessage(message);
		replyTopic = session.createTemporaryTopic();
		consumer = session.createConsumer(replyTopic);

		TM1.setJMSReplyTo(replyTopic);
		MP.send(TM1);
		TextMessage Reply = (TextMessage) consumer.receive();
		MP.close();
		return Reply.getText();
	}

	private JMSSender(String message) {
		try {
			Properties properties = new Properties();
			properties.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.jnp.interfaces.NamingContextFactory");
			properties.put(Context.URL_PKG_PREFIXES, "org.jnp.interfaces");
			properties.put(Context.PROVIDER_URL, "localhost");

			InitialContext jndi = new InitialContext(properties);
			ConnectionFactory conFactory = (ConnectionFactory) jndi
					.lookup("XAConnectionFactory");
			connection = conFactory.createConnection();

			this.message = message;
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			UnivRequest = (Topic) jndi.lookup("Univ_Request");

			MP = session.createProducer(UnivRequest);
			connection.start();
			sender.start();

		} catch (NamingException NE) {
			System.out.println("Naming Exception: " + NE);
		} catch (JMSException JMSE) {
			System.out.println("JMS Exception: " + JMSE);
		}
	}

	@Override
	public void run() {
	}

	public static String send(String message) {
		JMSSender jmssender = new JMSSender(message);
		try {
			response = jmssender.sendRequest(message);
			connection.close();
			session.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return response;
	}
}
