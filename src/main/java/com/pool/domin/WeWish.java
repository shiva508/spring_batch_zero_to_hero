package com.pool.domin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class WeWish implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long weWishId;
	private String nameOfWishe;
	private String nameOfWisher;
	private String heroMessage;
	private String subHeroMessage;
	private Date wishCreatedDate;
	private Date wishScheduledDate;
	private String emailOfWisher;
	private String phoneNumber;
	@Lob
	private String heroImage;
	@Lob
	private String subHeroImage;

	public WeWish() {

	}

	public Long getWeWishId() {
		return weWishId;
	}

	public void setWeWishId(Long weWishId) {
		this.weWishId = weWishId;
	}

	public String getNameOfWishe() {
		return nameOfWishe;
	}

	public void setNameOfWishe(String nameOfWishe) {
		this.nameOfWishe = nameOfWishe;
	}

	public String getNameOfWisher() {
		return nameOfWisher;
	}

	public void setNameOfWisher(String nameOfWisher) {
		this.nameOfWisher = nameOfWisher;
	}

	public String getHeroMessage() {
		return heroMessage;
	}

	public void setHeroMessage(String heroMessage) {
		this.heroMessage = heroMessage;
	}

	public String getSubHeroMessage() {
		return subHeroMessage;
	}

	public void setSubHeroMessage(String subHeroMessage) {
		this.subHeroMessage = subHeroMessage;
	}

	public Date getWishCreatedDate() {
		return wishCreatedDate;
	}

	public void setWishCreatedDate(Date wishCreatedDate) {
		this.wishCreatedDate = wishCreatedDate;
	}

	public Date getWishScheduledDate() {
		return wishScheduledDate;
	}

	public void setWishScheduledDate(Date wishScheduledDate) {
		this.wishScheduledDate = wishScheduledDate;
	}

	public String getEmailOfWisher() {
		return emailOfWisher;
	}

	public void setEmailOfWisher(String emailOfWisher) {
		this.emailOfWisher = emailOfWisher;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHeroImage() {
		return heroImage;
	}

	public void setHeroImage(String heroImage) {
		this.heroImage = heroImage;
	}

	public String getSubHeroImage() {
		return subHeroImage;
	}

	public void setSubHeroImage(String subHeroImage) {
		this.subHeroImage = subHeroImage;
	}

}
