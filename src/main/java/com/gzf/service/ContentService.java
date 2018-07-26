package com.gzf.service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gzf.service.common.BaseService;
import com.gzf.persist.pojo.Content;

/**
 *	Created by admin on Wed Apr 25 11:49:18 CST 2018
 *	content 
 */
@RestController
@RequestMapping("/content")
public interface ContentService extends BaseService<Content>{

}

