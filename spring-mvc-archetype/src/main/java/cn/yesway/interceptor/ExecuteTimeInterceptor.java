package cn.yesway.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ExecuteTimeInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(ExecuteTimeInterceptor.class);
	private static final String START_TIME_ATTRIBUTE_NAME = ExecuteTimeInterceptor.class.getName() + ".START_TIME";
	public static final String EXECUTE_TIME_ATTRIBUTE_NAME = ExecuteTimeInterceptor.class.getName() + ".EXECUTE_TIME";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Long long1 = (Long) request.getAttribute(START_TIME_ATTRIBUTE_NAME);
		if (long1 == null) {
			Long long2 = Long.valueOf(System.currentTimeMillis());
			request.setAttribute(START_TIME_ATTRIBUTE_NAME, long2);
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		Long long1 = (Long) request.getAttribute(EXECUTE_TIME_ATTRIBUTE_NAME);
		if (long1 == null) {
			Long long2 = (Long) request.getAttribute(START_TIME_ATTRIBUTE_NAME);
			Long long3 = Long.valueOf(System.currentTimeMillis());
			long1 = Long.valueOf(long3.longValue() - long2.longValue());
			request.setAttribute(START_TIME_ATTRIBUTE_NAME, long2);
		}
		if (modelAndView != null) {
			String s = modelAndView.getViewName();
			if (!StringUtils.startsWith(s, "redirect:"))
				modelAndView.addObject(EXECUTE_TIME_ATTRIBUTE_NAME, long1);
		}
		if (logger.isDebugEnabled()) {
			logger.debug((new StringBuilder("[")).append(handler).append("] executeTime: ").append(long1).append("ms").toString());
		}
	}

}
