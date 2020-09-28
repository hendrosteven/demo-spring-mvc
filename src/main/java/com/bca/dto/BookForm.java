package com.bca.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class BookForm {
	
	@NotEmpty(message="Code is required")
	@Size(min = 3, max = 5, message="Code length must be 3 to 5 characters")
	@Pattern(regexp = "BT[0-9]+", message = "Code must be start with BT")
	private String code;
	
	@NotEmpty(message = "Title is required")
	private String title;
	
	@NotEmpty(message = "Description is required")
	private String description;
	
	private double price;
	
	@NotEmpty(message = "Image is required")
	private String imagePath;
	
	private Long categoryId;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
