package kr.or.eum.member.controller;


import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import kr.or.eum.community.model.vo.Community;
import kr.or.eum.manager.model.vo.Answer;
import kr.or.eum.manager.model.vo.Question;
import kr.or.eum.member.model.service.MemberService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.eum.member.model.vo.Member;
import kr.or.eum.product.model.vo.Payment;
import kr.or.eum.product.model.vo.Product;
import kr.or.eum.product.model.vo.ProductAndPayment;
import kr.or.eum.product.model.vo.Review;
import kr.or.eum.wishlist.model.vo.Wishlist;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//대권 로그인페이지 이동
	@RequestMapping(value="/loginFrm.do")
	public String loginFrm() {
		return "member/loginFrm";
	}
	
	//대권 로그인 실행
	//@ResponseBody
	@RequestMapping(value="/login.do")
	public String login(Member m , HttpSession session) {
		Member member = service.selectOneMember(m);
		System.out.println(member);
		String str="";
		if(member != null) {
			session.setAttribute("member", member);
			str="1";
			return "redirect:/";
		}else {
			str="0";
			return "member/loginFrmFail";
		}
		
	}
	
	//대권 로그아웃
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
	session.invalidate();
	return "redirect:/";
	}
	
	//즉시로그인
	@RequestMapping(value="/instantlogin.do")
	public String instatlogin(Member m, HttpSession session) {
		Member member = service.selectOneMember(m);
		if(member != null) {
			session.setAttribute("member", member);
		}
		return "redirect:/";
	}
	
	//대권 회원가입페이지이동
	@RequestMapping(value="/joinFrm.do")
	public String joinFrm() {
		return "member/joinFrm";
	}	
	//재민 내 정보페이지
	@RequestMapping(value="/Mypage.do")
	public String Mypage(Member m,HttpSession session) {
		
		return "mypage/Mypage";
	}
	//재민 내정보수정
	@RequestMapping(value="/updateMember.do")
	public String updateMember(Member m,HttpSession session) {
		int result = service.updateMember(m);
	
		if(result>0) {
			session.setAttribute("m", m);
		}
		System.out.println(m);
		return "redirect:/";
	}
	//재민 1:1 문의내역 확인
	@RequestMapping(value="/MyquestionList.do")
	public String questionList(Model model) {
		ArrayList<Question> list = service.selectQuestionList();
		model.addAttribute("list", list);
		
		return "mypage/MyquestionList";
	}
	
	//재민 1:1문의 상세내역
	@RequestMapping(value="/questionView.do")
	public String questionView(int qstNo, Model model) {
		Question q = service.selectOneQuestion(qstNo);
		model.addAttribute("q",q);
		
		return "mypage/questionView";
		
	}
	//재민 구매내역
	@RequestMapping(value="/Myproject.do")
	public String Myproject(Model model, HttpSession session, int memberNo) {
		ArrayList<Product> list = service.selectMyproject(memberNo);
		
		model.addAttribute("list", list);
		
		return "mypage/Myproject";
	}
	@RequestMapping(value="/Myproduct.do")
	public String Myproduct(Model model, HttpSession session, int memberNo) {
		ArrayList<ProductAndPayment> list = service.selectProductList(memberNo);
		
		model.addAttribute("list", list);
		
		return "mypage/Myproduct";
	}
	//재민 찜내역
	
	@RequestMapping(value="/Mywishlist.do")
	public String Mywishlist(Model model,int memberNo) {
		ArrayList<Wishlist> list = service.selectWishlist(memberNo);
		model.addAttribute("list", list);
		
		return "mypage/wishlist";
	}
	//재민 리뷰목록
	@RequestMapping(value="/Myreview.do")
	public String Myreview(Model model,int memberNo) {
		ArrayList<Review> list = service.selectReviewlist(memberNo);
		model.addAttribute("list", list);
		System.out.println(list);
		return "mypage/Myreview";
	}
	
	//재민 전문가 신청페이지로 이동
	@RequestMapping(value="/Expertapply.do")
	public String Expertapply() {
		return "expert/Expertapply";
	}
	
	//필요서류 확인페이지
	@RequestMapping(value="/checkdocument.do")
	public String checkdocument() {
		return "expert/checkdocument";
	}
	//재민 전문가 신청페이지2로 이동
	@RequestMapping(value="/Expertapply2.do")
	public String Expertapply2() {
		return "expert/Expertapply2";
	}
	//재민 전문가 신청페이지3로 이동
	@RequestMapping(value="/Expertapply3.do", method = RequestMethod.POST)
	public String Expertapply3(Model model, String expertClass, String expertTag, String expertQual,String expertLicense,String expertIssuer) {
		
		model.addAttribute("expertClass",expertClass);
		model.addAttribute("expertTag",expertTag);
		model.addAttribute("expertQual",expertQual);
		model.addAttribute("expertLicense",expertLicense);
		model.addAttribute("expertIssuer",expertIssuer);
		return "expert/Expertapply3";
	}
	//재민 전문가 (sysout용 확인)
	@RequestMapping(value="/Expertapply4.do",method = RequestMethod.POST)
	public String Expertapply4(Model model, String expertClass, String expertTag,String expertDate,String expertQual,String expertLicense,String expertIssuer,String expertName,String expertJob,String expertPhone,String expertEmail) {
		
		System.out.println(expertClass);
		System.out.println(expertTag);
		System.out.println(expertQual);
		System.out.println(expertLicense);
		System.out.println(expertIssuer);
		System.out.println(expertDate);
		System.out.println(expertName);
		System.out.println(expertJob);
		System.out.println(expertPhone);
		System.out.println(expertEmail);
		return null;
	}
	//재민 주문취소
	@RequestMapping(value="/DeleteMyproduct.do")
	public String DeleteMyproduct(int payNo) {
		int result = service.DeleteMyproduct(payNo);
		
		System.out.println(result);
		return "redirect:/";
	}
	@RequestMapping(value="/Myproductdetail.do")
	public String Myproductdetail(int payNo) {
	System.out.println(payNo+"payNo");
	ArrayList<Payment> list = service.Myproductdetail(payNo);
	System.out.println(list);
	return "mypage/Myproductdetail";
	}
	//대권 아이디찾기
	@RequestMapping(value="/findId.do")
	public String findId() { 
		return "member/findId";
	}
	//대권 비밀번호찾기
	@RequestMapping(value="/findPw.do")
	public String findPw() { 
		return "member/findPw";

	}
}
