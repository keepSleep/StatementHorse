package com.message.controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class Startfinding extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
//		ScheduledExecutorService start = Executors.newScheduledThreadPool(2);
//		start.scheduleAtFixedRate(new CheckMessage("job1"), 1, 1, TimeUnit.SECONDS);
//		start.scheduleAtFixedRate(new CheckMessage("job2"), 1, 1, TimeUnit.SECONDS);
	}

}
