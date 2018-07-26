package com.gzf.service.iml;

import com.gzf.service.ContentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.gzf.persist.pojo.Content;
import com.gzf.persist.mapper.ContentMapper;
import com.gzf.service.common.iml.BaseServiceIml;
import com.gzf.persist.mapper.common.BaseMapper;

/**
 *	Created by admin on Wed Apr 25 11:49:18 CST 2018
 *	content 
 */
@Service
@Transactional
public class ContentServiceIml extends BaseServiceIml<Content> implements ContentService {

	@Autowired
	ContentMapper contentMapper;

	@Override
	protected BaseMapper getMapper() {
		return contentMapper;
	}
}

