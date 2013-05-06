package edu.unlp.db.domain;

import java.util.HashSet;
import java.util.Set;

public class WorkflowItem implements Comparable<WorkflowItem>{
	private long oid;
	private int version;
	private ItemState fromState;
	private Set<ItemState> toStates = new HashSet<ItemState>();

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
	
	public ItemState getFromState() {
		return fromState;
	}
	public void setFromState(ItemState fromState) {
		this.fromState = fromState;
	}
	public Set<ItemState> getToStates() {
		return toStates;
	}
	public void setToStates(Set<ItemState> toStates) {
		this.toStates = toStates;
	}
	public void addToState(ItemState itemState){
		getToStates().add(itemState);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		WorkflowItem other;
		try{
			other = (WorkflowItem) obj;
		}catch(ClassCastException e){
			return false;
		}
		if (oid != other.oid)
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
	public int compareTo(WorkflowItem o) {
		long val = this.oid - o.oid ;
		if(val==0)
			val = this.version - o.version;
		return (int)val;
	}
	
}
