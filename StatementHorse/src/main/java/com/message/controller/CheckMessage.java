package com.message.controller;

import com.financialstatements.model.FinancialStatementsHibernateDAO;

public class CheckMessage implements Runnable{
	
	private String jobName="";
	
	CheckMessage(String name){
		this.jobName=name;
	}
	
	@Override
	public void run() {
		FinancialStatementsHibernateDAO FinancialStatements = new FinancialStatementsHibernateDAO();
		System.out.println(jobName);
		
	}

}
