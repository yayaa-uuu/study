package cn.tnar.msf.cm4.msg.dto.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 拦截器详细使用
 * https://www.baeldung.com/spring-mvc-handlerinterceptor
 * <p>
 * 拦截器和过滤器区别
 * https://www.baeldung.com/spring-mvc-handlerinterceptor-vs-filter
 * <p>
 * 拦截器过滤器拦截顺序
 * https://www.baeldung.com/wp-content/uploads/2021/05/filters_vs_interceptors.jpg
 * <p>
 * 过滤器在请求到达DispatcherServlet之前拦截请求，使其成为粗粒度任务的理想选择，例如：
 * 验证
 * 日志记录和审计
 * 图像和数据压缩
 * 我们想要从 Spring MVC 中解耦的任何功能
 * <p>
 * 另一方面，HandlerIntercepor拦截DispatcherServlet和我们的Controller之间的请求。
 * 这是在 Spring MVC 框架内完成的，提供对Handler和ModelAndView对象的访问。这减少了重复并允许更细粒度的功能，例如：
 * 处理横切关注点，例如应用程序日志记录
 * 详细的授权检查
 * 操作 Spring 上下文或模型
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    /**
     * 在调用目标处理程序之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle");
        return true;
    }

    /**
     * 在目标处理程序之后但在DispatcherServlet呈现视图之前执行
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        log.info("postHandle");
    }

    /**
     * 请求处理和视图渲染完成后的回调
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("afterCompletion");
    }

}