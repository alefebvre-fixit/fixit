package com.fixit.model;

import java.util.List;

public interface Contributable<C extends Contribution>{

	public List<C> getContributions();
	
	public abstract void setContributions(List<C> contributions);

}
