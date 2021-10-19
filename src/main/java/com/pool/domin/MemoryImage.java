package com.pool.domin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MemoryImage {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long memoryImageId;
	private String memoryImageName;
	private String memoryImageBase64;
	private Integer imageCategory;

	public MemoryImage() {

	}

	public Long getMemoryImageId() {
		return memoryImageId;
	}

	public void setMemoryImageId(Long memoryImageId) {
		this.memoryImageId = memoryImageId;
	}

	public String getMemoryImageName() {
		return memoryImageName;
	}

	public void setMemoryImageName(String memoryImageName) {
		this.memoryImageName = memoryImageName;
	}

	public String getMemoryImageBase64() {
		return memoryImageBase64;
	}

	public void setMemoryImageBase64(String memoryImageBase64) {
		this.memoryImageBase64 = memoryImageBase64;
	}

	public Integer getImageCategory() {
		return imageCategory;
	}

	public void setImageCategory(Integer imageCategory) {
		this.imageCategory = imageCategory;
	}

	@Override
	public String toString() {
		return "MemoryImage [memoryImageId=" + memoryImageId + ", memoryImageName=" + memoryImageName
				+ ", memoryImageBase64=" + memoryImageBase64 + ", imageCategory=" + imageCategory + "]";
	}

}
