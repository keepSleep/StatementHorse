package com.member.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

//@WebServlet("/account/IdentityServlet")
public class IdentityServlet extends HttpServlet {

	public static final char[] CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	public static Random random = new Random();

	public static String getRandomString() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 1; i < 5; i++) {
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}

	public static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}
	public static Color getReverseColor(Color c){
		return new Color(255-c.getRed(),255-c.getGreen(),255-c.getBlue());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		String randomString = getRandomString();
		request.getSession(true).setAttribute("randomString", randomString);
		int width = 100;
		int height = 30;

		Color color = getRandomColor();
		Color reverse = getReverseColor(color);
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		g.setColor(color);
		g.fillRect(0, 0, width, height);
		g.setColor(reverse);
		g.drawString(randomString, 18, 20);
		for (int i = 0, n = random.nextInt(10); i < n; i++) {
			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		ServletOutputStream out = response.getOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(bi);
		out.flush();

	}

}
