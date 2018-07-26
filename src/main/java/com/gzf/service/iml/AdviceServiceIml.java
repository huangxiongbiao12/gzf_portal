package com.gzf.service.iml;

import com.gzf.service.AdviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.gzf.persist.pojo.Advice;
import com.gzf.persist.mapper.AdviceMapper;
import com.gzf.service.common.iml.BaseServiceIml;
import com.gzf.persist.mapper.common.BaseMapper;

/**
 *	Created by admin on Wed Apr 25 11:49:18 CST 2018
 *	advice 
 */
@Service
@Transactional
public class AdviceServiceIml extends BaseServiceIml<Advice> implements AdviceService {

	@Autowired
	AdviceMapper adviceMapper;

	@Override
	protected BaseMapper getMapper() {
		return adviceMapper;
	}
}

