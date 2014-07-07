package net.shangtech.weixin.sys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.shangtech.ssh.core.base.BaseController;
import net.shangtech.ssh.core.util.FileUtils;
import net.shangtech.weixin.sys.entity.SysUser;
import net.shangtech.weixin.sys.service.SysUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/manage/user")
public class SysUserController extends BaseController {
	@Autowired private SysUserService service;
	private static final String PATH = "user/user";
	@RequestMapping("/basic")
	public String basic(){
		SysUser user = service.find(getUser().getId());
		request.setAttribute("user", user);
		return PATH + "/basic";
	}
	
	@RequestMapping("/basic/save")
	public String basicSave(HttpServletRequest request, HttpServletResponse response, SysUser user){
		this.response = response;
		try{
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile image = multipartRequest.getFile("image_file");
			if(image != null){
				user.setSiteLogo(FileUtils.saveStreamToFile(image.getInputStream(), image.getOriginalFilename()));
			}
			user.setId(getUser().getId());
			service.saveBasic(user);
			request.getSession().setAttribute("user", user);
		}catch(IOException e){
			e.printStackTrace();
			return failed("文件保存出错");
		}
		return success();
	}
}
