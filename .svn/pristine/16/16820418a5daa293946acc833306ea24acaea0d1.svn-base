package com.gzf.persist.pojo;

import java.io.Serializable;
import com.gzf.persist.pojo.common.BaseEntity;
import com.lxy.persistence.mybatis.definition.annotation.Column;
import com.lxy.persistence.mybatis.definition.annotation.Table;

/**
 *	Created by admin on Wed Apr 25 11:49:18 CST 2018
 *	
 */
@Table("user")
public class User extends BaseEntity implements Serializable{

	/**
	 *	
	 */
	@Column(type = Column.Type.ID)
	private String userId;

	/**
	 *	账号
	 */
	@Column
	private String userName;

	/**
	 *	密码
	 */
	@Column
	private String password;

	/**
	 *	是否是原始账号 0-不是 1-是
	 */
	@Column
	private String origin;

	/**
	 *	姓名
	 */
	@Column
	private String name;

	/**
	 *	备注
	 */
	@Column
	private String remark;

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUserName(String userName){
		this.userName=userName;
	}

	public String getUserName(){
		return userName;
	}

	public void setPassword(String password){
		this.password=password;
	}

	public String getPassword(){
		return password;
	}

	public void setOrigin(String origin){
		this.origin=origin;
	}

	public String getOrigin(){
		return origin;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

}

