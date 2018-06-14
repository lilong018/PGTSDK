package com.pgt.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @program: pgtsdk
 * @description:
 * @author: LL
 * @create: 2018-06-11 14:04
 **/
public class MyInterceptor implements HandlerInterceptor {
    //    Logger logger = LoggerFactory.getLogger(MyInterceptor.class);
    private static final String X_API_KEY = "X_API_KEY20180611";
    private static final String localhost = "127.0.0.1";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("请求的url :" + request.getRequestURI());
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String ip = request.getLocalAddr();
        if (ip.equals(localhost)) {
            HttpSession session = request.getSession(true);
            Enumeration<?> enum1 = request.getHeaderNames();

            if (request.getHeader("X-API-KEY") == null) {
                try {
                    writer = response.getWriter();
                    String error = "X-API-KEY不存在";
                    writer.print(error);
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null)
                        writer.close();
                }
            } else if (!request.getHeader("X-API-KEY").equals(X_API_KEY)) {
                System.out.println(request.getHeader("X-API-KEY"));
                try {
                    writer = response.getWriter();
                    String error = "X-API-KEY不匹配";
                    writer.print(error);
                    return false;
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null)
                        writer.close();
                }
            }
        }else{

            try {
                writer = response.getWriter();
                String error = "该接口只能本地访问";
                writer.print(error);
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub

    }

}