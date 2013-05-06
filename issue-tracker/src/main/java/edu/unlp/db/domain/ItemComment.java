package edu.unlp.db.domain;

import java.util.Date;

public class ItemComment implements Comparable<ItemComment> {
	private long oid;
	private int version;
	private String comment;
	private User user;
	private Date date;
	
	//item al que pertenece el comentario
	private Item item;

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (oid ^ (oid >>> 32));
		result = prime * result + version;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		ItemComment other;
		try{
			other = (ItemComment) obj;
		}catch(ClassCastException e){
			return false;
		}
		if (oid != other.oid)
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	public int compareTo(ItemComment o) {
		long val = this.getOid() - o.getOid() ;
		if(val==0)
			val = this.getVersion() - o.getVersion();
		return (int)val;
	}	
}
