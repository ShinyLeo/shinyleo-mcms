
package com.shinyleo.people.entity;


/**
 *  开发平台用户 
 * Created by shinyleo on 17/7/20.
 */
public class PeopleOpenEntity extends PeopleUserEntity{
 
	private int peopleOpenPeopleId;
	
	private String peopleOpenId;
	
	private int peopleOpenPlatform;

	public int getPeopleOpenPeopleId() {
		return peopleOpenPeopleId;
	}

	public void setPeopleOpenPeopleId(int peopleOpenPeopleId) {
		this.peopleOpenPeopleId = peopleOpenPeopleId;
	}

	public String getPeopleOpenId() {
		return peopleOpenId;
	}

	public void setPeopleOpenId(String peopleOpenId) {
		this.peopleOpenId = peopleOpenId;
	}

	public int getPeopleOpenPlatform() {
		return peopleOpenPlatform;
	}

	public void setPeopleOpenPlatform(int peopleOpenPlatform) {
		this.peopleOpenPlatform = peopleOpenPlatform;
	}
	
}