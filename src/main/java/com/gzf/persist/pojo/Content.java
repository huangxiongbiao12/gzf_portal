package com.gzf.persist.pojo;

import java.io.Serializable;
import com.gzf.persist.pojo.common.BaseEntity;
import com.lxy.persistence.mybatis.definition.annotation.Column;
import com.lxy.persistence.mybatis.definition.annotation.Table;

/**
 *	Created by admin on Wed Apr 25 11:49:18 CST 2018
 *	
 */
@Table("content")
public class Content extends BaseEntity implements Serializable{

	/**
	 *	
	 */
	@Column(type = Column.Type.ID)
	private String contentId;

	/**
	 *	1-单位简介
		2-领导简介
		3-下载中心
		4-新闻中心
		5-办事指南
		6-交流互动
		7-政策法规
		8-信息公开
		9-友情链接
		10-联系我们
	 */
	@Column
	private String type;

	/**
	 *	内容头
	 */
	@Column
	private String title;

	/**
	 *	
	 */
	@Column
	private String desc;

	/**
	 *	具体内容
	 */
	@Column
	private String content;

	/**
	 *	
	 */
	@Column
	private String path;

	/**
	 *	
	 */
	@Column
	private String userId;

	/**
	 *	0-禁用 1-启用
	 */
	@Column
	private String status;

	/**
	 * 0-正常 1-优先封面
	 */
	@Column
	private String priority;

	public void setContentId(String contentId){
		this.contentId=contentId;
	}

	public String getContentId(){
		return contentId;
	}

	public void setType(String type){
		this.type=type;
	}

	public String getType(){
		return type;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getTitle(){
		return title;
	}

	public void setDesc(String desc){
		this.desc=desc;
	}

	public String getDesc(){
		return desc;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}

	public void setPath(String path){
		this.path=path;
	}

	public String getPath(){
		return path;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}
}

