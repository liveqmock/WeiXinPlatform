package net.shangtech.weixin.controller;

import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.weixin.site.entity.CustomPage;
import net.shangtech.weixin.site.entity.SiteInfo;
import net.shangtech.weixin.site.entity.SiteTemplate;
import net.shangtech.weixin.site.service.CustomPageService;
import net.shangtech.weixin.site.service.SiteInfoService;
import net.shangtech.weixin.site.service.SiteTemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "weixinSiteController")
@RequestMapping("/weixin/application/site")
public class SiteController extends BaseController {
	@Autowired private SiteInfoService service;
	@Autowired private CustomPageService pageService;
	@Autowired private SiteTemplateService tempService;
	@RequestMapping("/info")
	public String info(){
		Integer id = getId();
		SiteInfo info = service.find(id);
		request.setAttribute("info", info);
		return "weixin/site/info";
	}
	
	@RequestMapping("/custom/page")
	public String customPage(){
		Integer id = getId();
		CustomPage page = pageService.find(id);
		SiteTemplate temp = tempService.find(page.getTemp());
		request.setAttribute("page", page);
		request.setAttribute("temp", temp);
		return "weixin/site/custom-page";
	}
}
