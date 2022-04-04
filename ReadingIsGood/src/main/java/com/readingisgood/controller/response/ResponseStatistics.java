package com.readingisgood.controller.response;

import java.io.Serializable;
import java.util.List;

public class ResponseStatistics implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2929376623519897605L;
	private List<ResponseStatistic> statistics;

	public ResponseStatistics(List<ResponseStatistic> statistics) {
		super();
		this.statistics = statistics;
	}

	public List<ResponseStatistic> getStatistics() {
		return statistics;
	}

	public void setStatistics(List<ResponseStatistic> statistics) {
		this.statistics = statistics;
	}
}
