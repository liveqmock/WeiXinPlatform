package net.shangtech.weixin.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.weixin.site.entity.SiteInfo;
import net.shangtech.weixin.site.entity.SiteTemplate;
import net.shangtech.weixin.site.service.SiteInfoService;
import net.shangtech.weixin.site.service.SiteTemplateService;
import net.shangtech.weixin.sys.entity.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微官网相关操作
 * @author songxh
 * @createtime 2014-6-26下午10:59:31
 */
@Controller
@RequestMapping("/manage/application/site")
public class SiteController extends BaseController {
	@Autowired private SiteTemplateService templateService;
	@Autowired private SiteInfoService siteInfoService;
	/**
	 * 设置微官网首页模板页面
	 * @author songxh
	 * @createtime 2014-6-26下午10:59:59
	 * @return
	 */
	@RequestMapping("/template")
	public String template(){
		//查询系统中可用的模模板供用户选择
		List<SiteTemplate> list = templateService.find();
		request.setAttribute("list", list);
		//查询用户当前采用的模板以及网站相关的属性值,如果没有就默认为第一个
		
		return "user/application/site/template";
	}
	
	@RequestMapping("/info/list")
	public String siteInfoList(){
		List<SiteInfo> list = siteInfoService.find("where sysUserId=?", getUser().getId());
		request.setAttribute("list", list);
		return "user/application/site/info-list";
	}
	@RequestMapping("/info/form")
	public String siteInfoForm(){
		SiteInfo info = new SiteInfo();
		Integer id = getId();
		if(id != null){
			info = siteInfoService.find(id);
		}
		request.setAttribute("info", info);
		return "user/application/site/info-form";
	}
	
	@RequestMapping("/info/save")
	public String siteInfoSave(HttpServletRequest request, HttpServletResponse response, SiteInfo info){
		this.response = response;
		SysUser user = getUser();
		info.setSysUserId(user.getId());
		siteInfoService.save(info);
		return success();
	}
	
	@RequestMapping("/info/delete")
	public String siteInfoDel(HttpServletResponse response){
		this.response = response;
		Integer id = getId();
		siteInfoService.delete(id);
		return success();
	}
}
