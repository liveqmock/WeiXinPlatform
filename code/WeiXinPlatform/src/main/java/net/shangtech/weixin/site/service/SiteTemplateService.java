package net.shangtech.weixin.site.service;

import java.util.List;

import net.shangtech.ssh.core.base.BaseDao;
import net.shangtech.ssh.core.base.BaseService;
import net.shangtech.weixin.site.dao.SiteTemplateDao;
import net.shangtech.weixin.site.entity.SiteTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SiteTemplateService extends BaseService<SiteTemplate> {
	@Autowired private SiteTemplateDao dao;
	
	public List<SiteTemplate> findByType(int type){
		return dao.find("where type=?", type);
	}
	
	@Override
	protected BaseDao<SiteTemplate> dao() {
		return dao;
	}

}
