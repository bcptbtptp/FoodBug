package com.ideabobo.model;

/**
 * Good entity. @author MyEclipse Persistence Tools
 */

public class Good implements java.io.Serializable {

	// Fields

	private Integer id;
	private String gname;
	private String price;
	private String jifen;
	private String note;
	private String type;
	private String img;
	private String count;
	private String typeid;
	private Integer xiaoliang;
	private String shop;
	private String sid;
	private String ownid;
	private String username;
	private Integer uid;
	private String yongliao;
	private String zuofa;
	private String btype;
	private Integer zan;
	private String address;
	private String fenlei;
	private String fenlei2;
	
	
	
	

	// Constructors

	public String getFenlei() {
		return fenlei;
	}

	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}



	public String getFenlei2() {
		return fenlei2;
	}

	public void setFenlei2(String fenlei2) {
		this.fenlei2 = fenlei2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getZan() {
		return zan;
	}

	public void setZan(Integer zan) {
		this.zan = zan;
	}

	public String getBtype() {
		return btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getYongliao() {
		return yongliao;
	}

	public void setYongliao(String yongliao) {
		this.yongliao = yongliao;
	}

	public String getZuofa() {
		return zuofa;
	}

	public void setZuofa(String zuofa) {
		this.zuofa = zuofa;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getOwnid() {
		return ownid;
	}

	public void setOwnid(String ownid) {
		this.ownid = ownid;
	}

	/** default constructor */
	public Good() {
	}

	/** full constructor */
	public Good(String gname, String price, String jifen, String note,
			String type, String img, String count, String typeid,
			Integer xiaoliang) {
		this.gname = gname;
		this.price = price;
		this.jifen = jifen;
		this.note = note;
		this.type = type;
		this.img = img;
		this.count = count;
		this.typeid = typeid;
		this.xiaoliang = xiaoliang;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getJifen() {
		return this.jifen;
	}

	public void setJifen(String jifen) {
		this.jifen = jifen;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTypeid() {
		return this.typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public Integer getXiaoliang() {
		return this.xiaoliang;
	}

	public void setXiaoliang(Integer xiaoliang) {
		this.xiaoliang = xiaoliang;
	}

}