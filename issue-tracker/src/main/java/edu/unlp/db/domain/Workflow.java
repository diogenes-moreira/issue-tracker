package edu.unlp.db.domain;

import java.util.Collection;
import java.util.Map;

public class Workflow implements Comparable<Workflow>{
	private long oid;
	private int version;
	private String name;
	private Map<ItemState,WorkflowItem> validSecuenceStates;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<ItemState, WorkflowItem> getValidSecuenceStates() {
		return validSecuenceStates;
	}
	public void setValidSecuenceStates(
			Map<ItemState, WorkflowItem> validSecuenceStates) {
		this.validSecuenceStates = validSecuenceStates;
	}
	public void addTransition(ItemState itemStateFrom, ItemState itemStateTo) {
		Map<ItemState, WorkflowItem> states = getValidSecuenceStates();
		WorkflowItem wi = states.get(itemStateFrom);
		if(wi == null){
			wi = new WorkflowItem();
			wi.setFromState(itemStateFrom);
		}
		wi.addToState(itemStateTo);
	}
	public void replaceValidSecuenceStatesWith(
			Map<ItemState, WorkflowItem> validSecuenceStates) {
		this.validSecuenceStates.clear();
		this.validSecuenceStates.putAll(validSecuenceStates);
	}
	public void setInicialStateToItem(ItemState state, Item item) {
		boolean valid = true;
		for(WorkflowItem workflowItem: validSecuenceStates.values()){
			if(workflowItem.getToStates().contains(state)){
				valid = false;
			}
		}
		if(valid){
			item.setState(state);
		}else{
			throw new RuntimeException("No es un estado inicial");
		}
	}
	public void changeStateToItem(ItemState state, Item item) {
		Collection<ItemState> validStates = getValidSecuenceStates().get(item.getState()).getToStates();
		if(validStates.contains(state)){
			item.setState(state);
		}else{
			throw new RuntimeException("No es un transicion de estado valida");
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Workflow other;
		try{
			other = (Workflow) obj;
		}catch(ClassCastException e){
			return false;
		}
		if (oid != other.oid)
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
	public int compareTo(Workflow o) {
		long val = this.oid - o.oid ;
		if(val==0)
			val = this.version - o.version;
		return (int)val;
	}
}
