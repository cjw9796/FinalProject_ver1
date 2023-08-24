package com.kh.myproject.api.example;

import java.util.Date;

public class News {
	private String title;
	private String originallink;
	private String link;
	private String description;
	private Date pubDate; // "EEE, d MMM yyyy HH:mm:ss Z"

	public News() {
		super();
	}

	public News(String title, String originallink, String link, String description, Date pubDate) {
		super();
		this.title = title;
		this.originallink = originallink;
		this.link = link;
		this.description = description;
		this.pubDate = pubDate;
	}

	@Override
	public String toString() {
		return "News [title=" + title + ", originallink=" + originallink + ", link=" + link + ", description="
				+ description + ", pubDate=" + pubDate + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginallink() {
		return originallink;
	}

	public void setOriginallink(String originallink) {
		this.originallink = originallink;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
}
