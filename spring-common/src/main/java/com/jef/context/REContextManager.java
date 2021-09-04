package com.jef.context;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 上下文管理
 *
 * @author Jef
 * @date 2018/9/13 13:55
 */
public class REContextManager {
    public static final String CONTEXT_KEY = "__RECONTEXT_CONTEXT_IN_SESSION__";

    public static REContext getREContext(Map<String, Object> session) {
        if (session == null) {
            return null;
        }
        return (REContext) session.get(CONTEXT_KEY);
    }

    public static REContext getREContext(HttpSession session) {
        if (session == null) {
            return null;
        }
        return (REContext) session.getAttribute(CONTEXT_KEY);
    }

    /**
     * 获取已经存储的上下文
     *
     * @return 上下文
     */
    public static REContext getREContext() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request == null) {
            return null;
        }
        return getREContext(request.getSession());
    }

    /**
     * 登录或者退出时处理上下文
     * @param session
     * @param context
     */
    public static void setREContext(HttpSession session, REContext context) {
        if (session == null) {
            return;
        }
        if (context != null) {
            session.setAttribute(CONTEXT_KEY, context);
        } else {
            session.removeAttribute(CONTEXT_KEY);
        }
    }
}
