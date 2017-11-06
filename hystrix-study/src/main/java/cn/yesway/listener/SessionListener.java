package cn.yesway.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

	public void attributeAdded(HttpSessionBindingEvent se) {
		logger.debug("attributeAdded:{sessionID:" + se.getSession().getId() + ",name:" + se.getName() + ",value:" + se.getValue().toString() + "}");
	}

	public void attributeRemoved(HttpSessionBindingEvent se) {
		logger.debug("attributeRemoved:{sessionID:" + se.getSession().getId() + ",name:" + se.getName() + ",value:" + se.getValue().toString() + "}");
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		String newValue = se.getSession().getAttribute(se.getName()).toString();
		logger.debug("attributeReplaced:{sessionID:" + se.getSession().getId() + ",name:" + se.getName() + ",value:" + newValue + "}");
	}

	public void sessionCreated(HttpSessionEvent se) {
		logger.debug("sessionCreated:{sessionID:" + se.getSession().getId() + "}");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		logger.debug("sessionDestroyed:{sessionID:" + se.getSession().getId() + "}");
	}

}
