package com.gzf.persist.pojo;

import java.io.Serializable;
import com.gzf.persist.pojo.common.BaseEntity;
import com.lxy.persistence.mybatis.definition.annotation.Column;
import com.lxy.persistence.mybatis.definition.annotation.Table;

/**
 *	Created by admin on Wed Apr 25 11:49:18 CST 2018
 *	
 */
@Table("advice")
public class Advice extends BaseEntity implements Serializable{

	/**
	 *	
	 */
	@Column(type = Column.Type.ID)
	private String adviceId;

	/**
	 *	反馈人姓名
	 */
	@Column
	private String name;

	/**
	 *	反馈人电话
	 */
	@Column
	private String phone;

	/**
	 *	反馈内容
	 */
	@Column
	private String content;

	/**
	 *	处理状态 0-未处理 1-处理
	 */
	@Column
	private String status;

	/**
	 *	处理备注
	 */
	@Column
	private String remark;

	public void setAdviceId(String adviceId){
		this.adviceId=adviceId;
	}

	public String getAdviceId(){
		return adviceId;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setPhone(String phone){
		this.phone=phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return status;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

}

