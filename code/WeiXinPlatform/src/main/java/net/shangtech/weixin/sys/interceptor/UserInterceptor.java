package net.shangtech.weixin.sys.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shangtech.weixin.sys.entity.SysUser;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 这个是后台管理的拦截器,判断用户是否登录
 * @author songxh
 * @createtime 2014-7-5下午09:13:24
 */
public class UserInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		SysUser user = (SysUser) request.getSession().getAttribute("user");
		if(user == null){
			user = new SysUser();
			user.setId(0);
			request.getSession().setAttribute("user", user);
		}
		return true;
	}

}
