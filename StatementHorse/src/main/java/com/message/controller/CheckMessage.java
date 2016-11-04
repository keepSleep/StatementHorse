package com.message.controller;

import com.financialstatements.model.FinancialStatementsDAO;

public class CheckMessage implements Runnable{
	
	private String jobName="";
	
	CheckMessage(String name){
		this.jobName=name;
	}
	
	@Override
	public void run() {
		FinancialStatementsDAO FinancialStatements = new FinancialStatementsDAO();
		System.out.println(jobName);
		
	}

}
