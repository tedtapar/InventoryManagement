package com.example.barclays.inventory_management.utilities;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.example.barclays.inventory_management.model.Report;

/** 
 * @author Ted Tapar
 * ReportHelper helps to generate appropriate value for profit and also serves to
 * store all generated reports 
 */
public class ReportHelper {
	private List<Report> reports= new ArrayList<>();
	
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	/**
	 * @return
	 * getSum gets the sum of so far found reports and helps to generate
	 * the profit from previous report
	 */
	public BigDecimal getSum(){
		BigDecimal sum=new BigDecimal(0);
		for(Report r: reports){
			sum=sum.add(r.getProfit()) ;
		}
		return sum;
	}
}
