package com.fixit.model;

public interface Votable<C extends Contribution> {
	
	public C submit(Vote vote);

}
