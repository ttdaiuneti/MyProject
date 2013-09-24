package com.example.Entity;

import com.google.gson.annotations.SerializedName;

public class ENews {
	@SerializedName("news_id")
	private int id;
	@SerializedName("news_title")
	private String title;
	@SerializedName("news_date")
	private String date;
	@SerializedName("image")
	private String resourceImg;
	@SerializedName("news_content")
	private String content;
	/*
	public ENews(String title, String content, String resourceImg){
		this.setTitle(title);
		this.setContent(content);
		this.setResourceImg(resourceImg);
	}
	*/

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResourceImg() {
		return resourceImg;
	}

	public void setResourceImg(String resourceImg) {
		this.resourceImg = resourceImg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
}
