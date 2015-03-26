package com.fixit.model.card.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphData {

	private List<String> labels = new ArrayList<String>();
	private List<Integer> datas = new ArrayList<Integer>();
	private List<String> colours = new ArrayList<String>();

	public List<String> getColours() {
		return colours;
	}

	public void setColours(List<String> colours) {
		this.colours = colours;
	}

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
