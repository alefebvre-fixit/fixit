package com.fixit.model.card.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphData {

	private List<String> labels = new ArrayList<String>();
	private List<Integer> datas = new ArrayList<Integer>();
	
	public List<String> getLabels() {
		return labels;
	}
	
	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
	
	public List<Integer> getDatas() {
		return datas;
	}
	
	public void setDatas(List<Integer> datas) {
		this.datas = datas;
	}
	
	
}
