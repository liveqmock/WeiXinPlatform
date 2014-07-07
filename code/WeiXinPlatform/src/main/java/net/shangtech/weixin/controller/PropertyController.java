package net.shangtech.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.weixin.property.entity.HouseInfo;
import net.shangtech.weixin.property.entity.ProjectImage;
import net.shangtech.weixin.property.entity.ProjectType;
import net.shangtech.weixin.property.entity.SubProject;
import net.shangtech.weixin.property.service.ProjectService;
import net.shangtech.weixin.sys.entity.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 房产相关手机页面
 * @author songxh
 * @createtime 2014-7-5下午05:38:45
 */
@Controller(value = "weixinPropertyController")
@RequestMapping("/weixin/application/property")
public class PropertyController extends BaseController {
	@Autowired private ProjectService projectService;
	/**
	 * 显示所有楼盘及分类
	 * @author songxh
	 * @createtime 2014-7-5下午05:40:53
	 * @return
	 */
	@RequestMapping("/projects")
	public String projectsByType(){
		SysUser user = getUser();
		List<ProjectType> typeList = projectService.findProjectTypesByUser(user.getId());
		request.setAttribute("typeList", typeList);
		Map<Integer, List<SubProject>> map = new HashMap<Integer, List<SubProject>>();
		for(ProjectType type : typeList){
			List<SubProject> list = projectService.findByProjectType(type.getId());
			map.put(type.getId(), list);
		}
		request.setAttribute("map", map);
		return "weixin/property/projects";
	}
	
	@RequestMapping("/project")
	public String project(){
		Integer id = getId();
		SubProject project = projectService.find(id);
		request.setAttribute("project", project);
		List<ProjectImage> images = projectService.findImagesByProject(project.getId());
		request.setAttribute("images", images);
		return "weixin/property/project";
	}
	
	@RequestMapping("/project/description")
	public String projectDescription(){
		Integer id = getId();
		SubProject project = projectService.find(id);
		request.setAttribute("project", project);
		return "weixin/property/project-description";
	}
	
	@RequestMapping("/project/peripheral")
	public String projectPeripheral(){
		Integer id = getId();
		SubProject project = projectService.find(id);
		request.setAttribute("project", project);
		return "weixin/property/project-peripheral";
	}
	
	@RequestMapping("/project/traffic")
	public String projectTraffic(){
		Integer id = getId();
		SubProject project = projectService.find(id);
		request.setAttribute("project", project);
		return "weixin/property/project-traffic";
	}
	
	@RequestMapping("/project/houses")
	public String projectHouses(){
		Integer id = getId();
		List<HouseInfo> list = projectService.findHousesByProject(id);
		request.setAttribute("list", list);
		return "weixin/property/project-houses";
	}
}