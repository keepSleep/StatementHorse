package com.member.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.member.model.CheckMailService;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.member.model.PasswordEncorder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@Controller
public class MemberController {
	private MemberService memberService = new MemberService();
	private CheckMailService checkMailService = new CheckMailService();
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

	public static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

	@GetMapping("/login")
	public String loginPage() {
		// modelmap.addAttribute("isHidden", true);
		return "login/login";
	}

	@GetMapping("/register")
	public String registerPage() {
		return "login/register";
	}

	@GetMapping("/forgetpw")
	public String forgetpwPage() {
		return "login/forgetpw";
	}

	@GetMapping("/changepw")
	public String changePage() {
		return "login/changepw";
	}

	// 驗證信箱
	@GetMapping("/registerchecker")
	public String registerChecker(@RequestParam(name = "confirmation_token", required = true) String token,
			ModelMap modelmap) {
		String memberEmail = new String(Base64.getDecoder().decode(token));
		MemberVO mem = memberService.findMember(memberEmail);
		if ((new Date().getTime() - mem.getCreatetime().getTime()) > 6 * 60 * 60 * 1000) {
			return "login/registermailagain";
		}
		mem.setMemberCheck(1);
		memberService.insertMember(mem);
		modelmap.addAttribute("title", "成功加入財報馬 ");
		modelmap.addAttribute("content", "感謝申請會員");
		modelmap.addAttribute("url", "message/Message.jsp");
		return "login/registermailok";
	}

	// 數字驗證圖片產生
	@GetMapping("/identityServlet")
	public void identityServlet(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

	// login
	@PostMapping("/login")
	public String login(@RequestParam(name = "email", required = true) String memberEmail,
			@RequestParam(name = "password", required = true) String password, HttpServletRequest request,
			ModelMap modelmap) {
		modelmap.addAttribute("email", memberEmail);
		// ErrorMsg
		if (memberEmail == null || memberEmail.trim().length() == 0) {
			modelmap.addAttribute("errormsg", "帳號欄必須輸入");
			return "login/login";
		}
		if (password == null || password.trim().length() == 0) {
			modelmap.addAttribute("errormsg", "密碼欄必須輸入");
			return "login/login";
		}

		MemberVO memberVO = memberService.findMember(memberEmail);
		if (memberVO != null) {
			if(memberVO.getMemberCheck()==0){
					modelmap.addAttribute("erroemsg", "信箱尚未驗證");
					return "login/login";
			}
			if (memberVO.getMemberPassword().equals(PasswordEncorder.encrypt(password)))
				request.getSession().setAttribute("user", memberVO);
			else {
				modelmap.addAttribute("errormsg", "密碼錯誤");
				return "login/login";
			}
		} else {
			modelmap.addAttribute("errormsg", "該帳號不存在");
			return "login/login";
		}
		return "redirect:/message/Message.jsp";
	}

	// login
	@PostMapping("/googlelogin")
	public String googlelogin(@RequestParam(name = "email", required = true) String memberEmail,
			HttpServletRequest request) {
		MemberVO memberVO = memberService.findMember(memberEmail);
		if (memberVO == null) {
			memberVO = new MemberVO();
			String memberId = memberEmail.split("@")[0];
			memberVO.setMemberId(memberId);
			memberVO.setMemberEmail(memberEmail);
			memberVO.setMemberPassword(PasswordEncorder.encrypt(""));
			memberVO.setMemberCheck(1);
			memberVO.setCreatetime(new Date());
			memberService.insertMember(memberVO);
			memberVO = memberService.findMember(memberEmail);
		}
		request.getSession().setAttribute("user", memberVO);
		return "redirect:/message/Message.jsp";
	}

	@PostMapping("/fblogin")
	public String fblogin(@RequestParam(name = "email", required = true) String memberEmail,
			HttpServletRequest request) {
		MemberVO memberVO = memberService.findMember(memberEmail);
		if (memberVO == null) {
			memberVO = new MemberVO();
			String memberId = memberEmail.split("@")[0];
			memberVO.setMemberId(memberId);
			memberVO.setMemberEmail(memberEmail);
			memberVO.setMemberPassword(PasswordEncorder.encrypt(""));
			memberVO.setMemberCheck(1);
			memberVO.setCreatetime(new Date());
			memberService.insertMember(memberVO);
			memberVO = memberService.findMember(memberEmail);
		}
		request.getSession().setAttribute("user", memberVO);
		return "redirect:/message/Message.jsp";
	}

	// 註冊
	@PostMapping("/register")
	public String register(@RequestParam(name = "email", required = true) String memberEmail,
			@RequestParam(name = "password", required = true) String memberPassword,
			@RequestParam(name = "password2", required = true) String memberPassword2,
			@RequestParam(name = "checkword", required = true) String checkword, @SessionAttribute String randomString,
			HttpServletRequest request, ModelMap modelmap) {
		modelmap.addAttribute("email", memberEmail);
		if (!checkword.equals(randomString)) {
			modelmap.addAttribute("errormsg", "驗證碼錯誤");
			return "login/register";
		}
		if (memberEmail == null || memberEmail.trim().length() == 0) {
			modelmap.addAttribute("errormsg", "帳號欄不可空白");
			return "login/register";

		}
		if (memberPassword == null || memberPassword.trim().length() == 0) {
			modelmap.addAttribute("errormsg", "密碼欄不可空白");
			return "login/register";
		}
		if (memberPassword2 == null || memberPassword2.trim().length() == 0) {
			modelmap.addAttribute("errormsg", "密碼確認欄為不可空白");
			return "login/register";
		}
		if (memberPassword != null && memberPassword2 != null && memberPassword.trim().length() > 0
				&& memberPassword2.trim().length() > 0) {
			if (!memberPassword.trim().equals(memberPassword2.trim())) {
				modelmap.addAttribute("errormsg", "密碼必須一致");
				return "login/register";
			} else if (memberPassword.length() < 6) {
				modelmap.addAttribute("errormsg", "密碼必須大於六個字");
				return "login/register";
			} else if (!memberPassword.matches("^.*[0-9]+.*$") || !memberPassword.matches("^.*[a-zA-Z]+.*$")) {
				modelmap.addAttribute("errormsg", "密碼必須包含英文與數字");
				return "login/register";
			}
		}
		if (memberService.findMember(memberEmail) != null) {
			modelmap.addAttribute("errormsg", "該帳號已存在，請更換新帳號");
			return "login/register";
		}
		MemberVO mem = new MemberVO();
		String memberId = memberEmail.split("@")[0];
		mem.setMemberId(memberId);
		mem.setMemberEmail(memberEmail);
		mem.setMemberPassword(PasswordEncorder.encrypt(memberPassword));
		mem.setMemberCheck(0);
		mem.setCreatetime(new Date());
		memberService.insertMember(mem);

		checkMailService.sendCheckMail(memberEmail);
		return "login/registermail";
	}

	// 更改密碼mail
	@PostMapping("/changepwmail")
	public String changepwmail(@RequestParam(name = "email", required = true) String memberEmail) {
		checkMailService.sendChangepw(memberEmail);
		return "login/forgetpwcheck";
	}

	// 更改密碼
	@PostMapping("/changepw")
	public String changepw(@RequestParam(name = "confirmation_token", required = true) String token,
			@RequestParam(name = "password", required = true) String memberPassword,
			@RequestParam(name = "password2", required = true) String memberPassword2, ModelMap modelmap) {
		String memberEmail = new String(Base64.getDecoder().decode(token));

		String path = "account/changepw?confirmation_token=" + token;

		if (memberPassword == null || memberPassword.trim().length() == 0) {
			modelmap.addAttribute("errormsg", "密碼欄不可空白");
			return path;
		}
		if (memberPassword2 == null || memberPassword2.trim().length() == 0) {
			modelmap.addAttribute("errormsg", "密碼確認欄為不可空白");
			return path;
		}
		if (memberPassword != null && memberPassword2 != null && memberPassword.trim().length() > 0
				&& memberPassword2.trim().length() > 0) {
			if (!memberPassword.trim().equals(memberPassword2.trim())) {
				modelmap.addAttribute("errormsg", "密碼必須一致");
				return path;
			} else if (memberPassword.length() < 6) {
				modelmap.addAttribute("errormsg", "密碼必須大於六個字");
				return path;
			} else if (!memberPassword.matches("^.*[0-9]+.*$") || !memberPassword.matches("^.*[a-zA-Z]+.*$")) {
				modelmap.addAttribute("errormsg", "密碼必須包含英文與數字");
				return path;
			}
		}

		MemberVO mem = memberService.findMember(memberEmail);
		if ((new Date().getTime() - mem.getCreatetime().getTime()) > 6 * 60 * 60 * 1000) {
			return "login/registermailagain";
		}
		mem.setMemberPassword(PasswordEncorder.encrypt(memberPassword));
		memberService.insertMember(mem);
		modelmap.addAttribute("title", "修改密碼成功 ");
		modelmap.addAttribute("content", "歡迎使用財報馬");
		modelmap.addAttribute("url","account/login");
		return "login/registermailok";
	}
	//logout
	@GetMapping("/logout")
	public String logoutPage(ModelMap modelmap,HttpServletRequest request) {
		request.getSession().removeAttribute("user");;
		modelmap.addAttribute("title", "登出成功 ");
		modelmap.addAttribute("content", "歡迎再次使用財報馬");
		modelmap.addAttribute("url","account/login");
		return "login/registermailok";
	}
}