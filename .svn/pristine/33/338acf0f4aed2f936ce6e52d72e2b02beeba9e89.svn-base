package com.gzf.service.iml;

import com.gzf.authorization.token.Token;
import com.gzf.authorization.token.TokenManager;
import com.gzf.common.BaseResultBean;
import com.gzf.config.Constants;
import com.gzf.service.UserService;
import com.gzf.util.StringUtil;
import com.lxy.crypto.Hasho;
import com.lxy.crypto.common.Text;
import com.lxy.persistence.redis.Cacher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.gzf.persist.pojo.User;
import com.gzf.persist.mapper.UserMapper;
import com.gzf.service.common.iml.BaseServiceIml;
import com.gzf.persist.mapper.common.BaseMapper;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *	Created by admin on Wed Apr 25 11:49:18 CST 2018
 *	user 
 */
@Service
@Transactional
public class UserServiceIml extends BaseServiceIml<User> implements UserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	Cacher cacher;
	@Autowired
	TokenManager tokenManager;

	@Override
	protected BaseMapper getMapper() {
		return userMapper;
	}

	@Override
	public BaseResultBean add(User user) {
		if (StringUtil.isNullOrEmpty(user.getUserName())||
				StringUtil.isNullOrEmpty(user.getPassword())) {
			return new BaseResultBean(BaseResultBean.ERROR, "请输入账户名称！");
		}
		Text hash = null;
		try {
			//将输入的密码加密
			hash = Hasho.MD5.hash(user.getPassword().getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		User userParam = new User();
		userParam.setUserName(user.getUserName());
		List userList = userMapper.query(userParam,null,null);
		if (userList.size() > 0) {
			return new BaseResultBean(BaseResultBean.ERROR, "该账户已经存在，请修改账号或直接登录！");
		}
		user.setPassword(hash.hex());
		user.setOrigin("0");
		int i = userMapper.save(user);
		if (i != 1) {
			return new BaseResultBean(BaseResultBean.ERROR, "账户添加失败");
		}
		return new BaseResultBean(BaseResultBean.NORMAL, "账户添加成功");
	}

	/**
	 * 修改用户，密码md5加密
	 * @param user
	 * @return
	 */
	@Override
	public BaseResultBean update(User user) {
		Text hash = null;
		try {
			if (!StringUtil.isNullOrEmpty(user.getPassword())) {
				//将输入的密码加密
				hash = Hasho.MD5.hash(user.getPassword().getBytes("UTF-8"));
				user.setPassword(hash.hex());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int i = userMapper.update(user);
		if (i != 1) {
			return new BaseResultBean(BaseResultBean.ERROR, "修改失败");
		}
		return new BaseResultBean(BaseResultBean.NORMAL, "修改成功");
	}

	@Override
	public BaseResultBean login(HttpServletRequest req, HttpServletResponse resp, String loginName, String password) {
		if(StringUtil.isNullOrEmpty(loginName) || StringUtil.isNullOrEmpty(password)){
			return new BaseResultBean(BaseResultBean.ERROR, "请输入账号密码!");
		}else {
			Text hash = null;
			try {
				//将输入的密码加密
				hash = Hasho.MD5.hash(password.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			User userParam = new User();
			userParam.setUserName(loginName);
			userParam.setPassword(hash.hex());
			List<User> userList = userMapper.query(userParam,null,null);
			User user = null;
			if (userList.size() > 0) {
				user = userMapper.get(userList.get(0).getUserId());
			}
			if(user != null){
				//取得角色标识符
				//根据角色标识符从角色容器中取得权限和菜单，然后创建token
				Set pset = new HashSet();
				pset.add("*");
				Token token = tokenManager.create(user.getUserId(), pset);
				resp.setHeader(Constants.ScopeKey.HEADER_TOKEN, token.toString());
				return new BaseResultBean(BaseResultBean.NORMAL, user, "登陆成功");
			}
		}
		return new BaseResultBean(BaseResultBean.ERROR, "登陆失败");
	}

	@Override
	public BaseResultBean logout(HttpServletRequest req) {
		String userId = (String)req.getAttribute(Constants.ScopeKey.REQUEST_USER_ID);
		if(StringUtils.hasLength(userId)){
			tokenManager.delete(userId);
		}
		return new BaseResultBean(BaseResultBean.NORMAL, "退出成功");
	}

}

