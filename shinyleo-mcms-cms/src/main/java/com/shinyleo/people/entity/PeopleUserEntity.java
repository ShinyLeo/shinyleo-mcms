package com.shinyleo.people.entity;

import java.util.Date;

/**
 *
 * Created by shinyleo on 17/7/20.
 */
public class PeopleUserEntity extends PeopleEntity {

	/**
	 * 用户地址
	 */
	private String peopleUserAddress;

	/**
	 * 用户所属应用ID
	 */
	private int peopleUserAppId;

	/**
	 * 用户生日
	 */
	private Date peopleUserBirthday;

	/**
	 * 用户身份证号码
	 */
	private String peopleUserCard;

	/**
	 * 用户头像
	 */
	private String peopleUserIcon;

	/**
	 * 用户昵称
	 */
	private String peopleUserNickName;

	/**
	 * 关联用户编号
	 */
	private int peopleUserPeopleId;

	/**
	 * 用户的真实名称
	 */
	private String peopleUserRealName;

	/**
	 * 省
	 */
	private int peopleUserProvince;
	/**
	 * 城市
	 */
	private int peopleUserCity;
	/**
	 * 区
	 */
	private int peopleUserDistrict;
	/**
	 * 街道
	 */
	private int peopleUserStreet;

	/**
	 * 用户性别</br>
	 * 0.未知</br>
	 * 1.男</br>
	 * 2.女</br>
	 */
	private int peopleUserSex = 1;

	/**
	 * 获取peopleUserAddress
	 * 
	 * @return peopleUserAddress
	 */
	public String getPeopleUserAddress() {
		return peopleUserAddress;
	}

	/**
	 * 获取peopleUserAppId
	 * 
	 * @return peopleUserAppId
	 */
	public int getPeopleUserAppId() {
		return peopleUserAppId;
	}

	/**
	 * 获取peopleUserBirthday
	 * 
	 * @return peopleUserBirthday
	 */
	public Date getPeopleUserBirthday() {
		return peopleUserBirthday;
	}

	/**
	 * 获取用户身份证号码
	 * 
	 * @return
	 */
	public String getPeopleUserCard() {
		return peopleUserCard;
	}

	/**
	 * 获取peopleUserIcon
	 * 
	 * @return peopleUserIcon
	 */
	public String getPeopleUserIcon() {
		return peopleUserIcon;
	}

	/**
	 * 获取peopleUserNickName
	 * 
	 * @return peopleUserNickName
	 */
	public String getPeopleUserNickName() {
		return peopleUserNickName;
	}

	/**
	 * 设置xu
	 * 
	 * @return
	 */
	public int getPeopleUserPeopleId() {
		return peopleUserPeopleId;
	}

	/**
	 * 获取peopleUserRealName
	 * 
	 * @return peopleUserRealName
	 */
	public String getPeopleUserRealName() {
		return peopleUserRealName;
	}

	/**
	 * 获取peopleUserSex
	 * 
	 * @return peopleUserSex
	 */
	public int getPeopleUserSex() {
		return peopleUserSex;
	}

	/**
	 * 设置peopleUserAddress
	 * 
	 * @param peopleUserAddress
	 */
	public void setPeopleUserAddress(String peopleUserAddress) {
		this.peopleUserAddress = peopleUserAddress;
	}

	/**
	 * 设置peopleUserAppId
	 * 
	 * @param peopleUserAppId
	 */
	public void setPeopleUserAppId(int peopleUserAppId) {
		this.peopleUserAppId = peopleUserAppId;
	}

	/**
	 * 设置peopleUserBirthday
	 * 
	 * @param peopleUserBirthday
	 */
	public void setPeopleUserBirthday(Date peopleUserBirthday) {
		this.peopleUserBirthday = peopleUserBirthday;
	}

	/**
	 * 设置用户身份证号码
	 * 
	 * @param peopleUserCard
	 */
	public void setPeopleUserCard(String peopleUserCard) {
		this.peopleUserCard = peopleUserCard;
	}

	/**
	 * 设置peopleUserIcon
	 * 
	 * @param peopleUserIcon
	 */
	public void setPeopleUserIcon(String peopleUserIcon) {
		this.peopleUserIcon = peopleUserIcon;
	}

	/**
	 * 设置peopleUserNickName
	 * 
	 * @param peopleUserNickName
	 */
	public void setPeopleUserNickName(String peopleUserNickName) {
		this.peopleUserNickName = peopleUserNickName;
	}

	public void setPeopleUserPeopleId(int peopleUserPeopleId) {
		this.peopleUserPeopleId = peopleUserPeopleId;
	}

	/**
	 * 设置peopleUserRealName
	 * 
	 * @param peopleUserRealName
	 */
	public void setPeopleUserRealName(String peopleUserRealName) {
		this.peopleUserRealName = peopleUserRealName;
	}

	/**
	 * 设置peopleUserSex
	 * 
	 * @param peopleUserSex
	 */
	public void setPeopleUserSex(int peopleUserSex) {
		if (peopleUserSex % 2 == 0) {
			this.peopleUserSex = 2;
		} else {
			this.peopleUserSex = 1;
		}

	}

	public int getPeopleUserProvince() {
		return peopleUserProvince;
	}

	public void setPeopleUserProvince(int peopleUserProvince) {
		this.peopleUserProvince = peopleUserProvince;
	}

	public int getPeopleUserCity() {
		return peopleUserCity;
	}

	public void setPeopleUserCity(int peopleUserCity) {
		this.peopleUserCity = peopleUserCity;
	}

	public int getPeopleUserDistrict() {
		return peopleUserDistrict;
	}

	public void setPeopleUserDistrict(int peopleUserDistrict) {
		this.peopleUserDistrict = peopleUserDistrict;
	}

	public int getPeopleUserStreet() {
		return peopleUserStreet;
	}

	public void setPeopleUserStreet(int peopleUserStreet) {
		this.peopleUserStreet = peopleUserStreet;
	}

	
}