package com.kh.myproject.api.example;

public class Product {
	private String title;
	private String link;
	private String image;
	private int lprice;
	private int hprice;
	private String mallName;
	private String productId;
	private String productType;
	private String brand;
	private String maker;
	private String category1;
	private String category2;
	private String category3;
	private String category4;

	public Product() {
		super();
	}

	public Product(String title, String link, String image, int lprice, int hprice, String mallName, String productId,
			String productType, String brand, String maker, String category1, String category2, String category3,
			String category4) {
		super();
		this.title = title;
		this.link = link;
		this.image = image;
		this.lprice = lprice;
		this.hprice = hprice;
		this.mallName = mallName;
		this.productId = productId;
		this.productType = productType;
		this.brand = brand;
		this.maker = maker;
		this.category1 = category1;
		this.category2 = category2;
		this.category3 = category3;
		this.category4 = category4;
	}

	@Override
	public String toString() {
		return "Product [title=" + title + ", link=" + link + ", image=" + image + ", lprice=" + lprice + ", hprice="
				+ hprice + ", mallName=" + mallName + ", productId=" + productId + ", productType=" + productType
				+ ", brand=" + brand + ", maker=" + maker + ", category1=" + category1 + ", category2=" + category2
				+ ", category3=" + category3 + ", category4=" + category4 + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLprice() {
		return lprice;
	}

	public void setLprice(int lprice) {
		this.lprice = lprice;
	}

	public int getHprice() {
		return hprice;
	}

	public void setHprice(int hprice) {
		this.hprice = hprice;
	}

	public String getMallName() {
		return mallName;
	}

	public void setMallName(String mallName) {
		this.mallName = mallName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getCategory1() {
		return category1;
	}

	public void setCategory1(String category1) {
		this.category1 = category1;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

	public String getCategory3() {
		return category3;
	}

	public void setCategory3(String category3) {
		this.category3 = category3;
	}

	public String getCategory4() {
		return category4;
	}

	public void setCategory4(String category4) {
		this.category4 = category4;
	}

}
