package kr.or.eum.product.model.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.eum.product.model.vo.ProductAndWishList;
import kr.or.eum.product.model.vo.ProductDetail;
import kr.or.eum.member.model.dao.MemberDao;
import kr.or.eum.member.model.vo.Expert;
import kr.or.eum.member.model.vo.ExpertAndMember;
import kr.or.eum.member.model.vo.Member;
import kr.or.eum.product.model.dao.ProductDao;
import kr.or.eum.product.model.vo.Chat;
import kr.or.eum.product.model.vo.Counsel;
import kr.or.eum.product.model.vo.Payment;
import kr.or.eum.product.model.vo.Product;
import kr.or.eum.product.model.vo.ProductAndEtc;
import kr.or.eum.product.model.vo.ProductPageData;
import kr.or.eum.product.model.vo.Review;
import kr.or.eum.product.model.vo.ReviewPageData;
import kr.or.eum.wishlist.model.vo.Wishlist;
import kr.or.eum.product.model.vo.ProReviewMember;


@Service
@Transactional
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private MemberDao memberDao;
	
	public ProductPageData selectClassList(int reqPage, String selPro) {
		int numPerPage = 12;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("selPro", selPro);

		int totalCount = productDao.selectClassCount(map);
		ArrayList<Product> list = productDao.selectClassList(map);

		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		
		
		
		
		
		String pageNavi = "<ul class='pagination'>";
		if(pageNo != 1) { 
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/ClassList.do?reqPage=1&selPro="+selPro+"'>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/ClassList.do?reqPage="+(pageNo-1)+"&selPro="+selPro+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
			
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/ClassList.do?reqPage="+pageNo+"&selPro="+selPro+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/ClassList.do?reqPage="+pageNo+"&selPro="+selPro+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/ClassList.do?reqPage="+pageNo+"&selPro="+selPro+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
			
		}
		pageNavi += "</ul>";
		ProductPageData ppd = new ProductPageData(list, pageNavi);	
					 
		return ppd;
		
	}
	
	public ProductPageData selectExpertList(int reqPage, String selPro) {
		int numPerPage = 12;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("selPro", selPro);

		int totalCount = productDao.selectExpertCount(map);
		ArrayList<Product> list = productDao.selectExpertList(map);

		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		
		
		
		
		
		String pageNavi = "<ul class='pagination'>";
		if(pageNo != 1) { 
			
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/ExpertList.do?reqPage="+(pageNo-1)+"&selPro="+selPro+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/ExpertList.do?reqPage="+pageNo+"&selPro="+selPro+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/ExpertList.do?reqPage="+pageNo+"&selPro="+selPro+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/ExpertList.do?reqPage="+pageNo+"&selPro="+selPro+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
			
		}
		pageNavi += "</ul>";
		ProductPageData ppd = new ProductPageData(list, pageNavi);	
					 
		return ppd;
		
	}
	
	public ProductPageData selectIdeamarketList(int reqPage, String selPro) {
		int numPerPage = 12;
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("selPro", selPro);

		int totalCount = productDao.selectIdeamarketCount(map);
		ArrayList<Product> list = productDao.selectIdeamarketList(map);

		int totalPage = 0;
		if(totalCount % numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		int pageNaviSize = 5;
		int pageNo = ((reqPage - 1) / pageNaviSize) * pageNaviSize + 1;
		
		
		
		
		
		String pageNavi = "<ul class='pagination'>";
		if(pageNo != 1) { 
			
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/IdeamarketList.do?reqPage="+(pageNo-1)+"&selPro="+selPro+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
		}
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/IdeamarketList.do?reqPage="+pageNo+"&selPro="+selPro+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/IdeamarketList.do?reqPage="+pageNo+"&selPro="+selPro+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/IdeamarketList.do?reqPage="+pageNo+"&selPro="+selPro+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
			
		}
		pageNavi += "</ul>";
		ProductPageData ppd = new ProductPageData(list, pageNavi);	
					 
		return ppd;
		
	}
	
	public int classWrite(Product pro) {
		// TODO Auto-generated method stub
		
		Product product=setToken(pro);
		int result = productDao.classWrite(product);
		
		return result;
	}
	
	public int expertWrite(Product pro) {
		// TODO Auto-generated method stub
		Product product=setToken(pro);
		int result = productDao.expertWrite(product);
		
		return result;
	}
	
	public int ideamarketWrite(Product pro) {
		// TODO Auto-generated method stub
		Product product=setToken(pro);
		int result = productDao.ideamarketWrite(product);
		
		return result;
	}
	
	public Product setToken(Product pro) {
		
		String productQst = pro.getProductQst();
		List<String> qstList = new ArrayList<String>();
		
		String []tokens=productQst.split("\\|");
		
		for(int i=0;i<tokens.length;i++) {
			if(tokens[i] == null || tokens[i].trim().length() < 2) {
			}else {
				if(tokens[i].indexOf(",") == 0) {
					qstList.add(tokens[i].replaceFirst(",", ""));
				}else {
					qstList.add(tokens[i].trim());
				}
			}
			
		}
		productQst = "";
		
		for(int i=0;i<qstList.size();i++){
			productQst += qstList.get(i)+"/";
		}
		
		
		String productAns = pro.getProductAns();
		List<String> ansList = new ArrayList<String>();
		String []tokens2=productAns.split("\\|");
		for(int i=0;i<tokens2.length;i++){
			if(tokens2[i] == null || tokens2[i].trim().length() < 2) {
			}else {
				if(tokens2[i].indexOf(",") == 0) {
					ansList.add(tokens2[i].replaceFirst(",", ""));
				}else {
					ansList.add(tokens2[i].trim());
				}
			}
		}
		productAns = "";
		for(int i=0;i<ansList.size();i++){
			productAns += ansList.get(i)+"/";
		}
//////////////////////////////////////////////////////////////////////////////		
		String productTag = pro.getProductTag();
		List<String> tagList = new ArrayList<String>();
		
		String []tokens3=productTag.split("\\|");
		
		for(int i=0;i<tokens3.length;i++){
			if(tokens3[i] == null || tokens3[i].trim().length() < 1) {//null 또는 공백제거후 1글자 일때
				
			}else {
				if(tokens3[i].indexOf(",") == 0) {
					tagList.add(tokens3[i].replaceFirst(",", ""));
				}else {
					tagList.add(tokens3[i].trim());
				}
			}
		}
		productTag = "";
		for(int i=0;i<tagList.size();i++){
			productTag += "#"+tagList.get(i)+"/";
		}
		
		
		pro.setProductQst(productQst);
		pro.setProductAns(productAns);
		pro.setProductTag(productTag);
		
		return pro;
	}
	
	
	//윤지
	public ProductDetail selectProductDetail(int productNo, int expertNo, Member member) {
		Product product = productDao.selectOneProduct(productNo);
		ArrayList<String> productQNA = new ArrayList<String>();
		if(product.getProductQst() != null) {
			String productQst[] = product.getProductQst().split("/");
			String productAns[] = product.getProductAns().split("/");
			for(int i = 0; i < productQst.length; i++) {
				productQNA.add("Q"+(i+1)+". "+productQst[i]);
				productQNA.add("A"+(i+1)+". "+productAns[i]);
			}
		}
		Expert expert = memberDao.selectOneExpertOnly(expertNo);
		ExpertAndMember expertM = memberDao.selectOneExpertPicture(expertNo);
		ArrayList<Review> reviewRnum = productDao.selectAllReview(productNo);
		double reviewAvrbef = productDao.selectReviewStar(productNo); //null일 때 double로 resulttype 달라해서 에러
		String reviewAvr = "";
		if(reviewAvrbef != 0) {
			reviewAvr = String.format("%.1f", reviewAvrbef);			
		}
		int reviewCount = productDao.selectReviewCount(productNo);
		int paymentCount = productDao.selectPaymentExpertNoCount(productNo);
		int cost = (product.getCost()-(product.getCost()*product.getSale()/100));
		String[] tag = product.getProductTag().split("/");
		ArrayList<ProductAndWishList> wishList = productDao.selectWishList();
		int wishCount = productDao.selectwish(productNo);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("productNo", productNo);
		if(member != null) {
			map.put("memberNo", member.getMemberNo());			
		}else {
			map.put("memberNo", 0);
		}
		int wishMemberCheck = productDao.selectWishMemberCheck(map);
		ProductDetail pd = new ProductDetail(product, productQNA, expert, expertM, reviewRnum, reviewAvr, reviewCount, paymentCount, cost, tag, wishList, wishCount, wishMemberCheck);
		return pd;
	}//selectProductDetail
	
	//윤지
	public ReviewPageData selectReviewList(int productNo,int reqPage) {
		int numPerPage = 5;
		int end = reqPage*numPerPage;
		int start = end - numPerPage + 1;
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("productNo", productNo);
		map.put("start", start);
		map.put("end", end);
		
		ArrayList<ProReviewMember> list = productDao.selectReviewList(map); 
		//전체 페이지 계산을 위한 전체 게시물 수 조회
		int totalCount = productDao.selectReviewCount(productNo);
		
		//전체페이지 수
		int totalPage = 0;
		if(totalCount%numPerPage == 0) {
			totalPage = totalCount/numPerPage;
		}else {
			totalPage = totalCount/numPerPage + 1;
		}
		//페이지 네비게이션의 길이 지정
		int pageNaviSize = 5;
		//페이지 모양 지정
		//1~5페이지 요청시 > 1 2 3 4 5
		//6~10페이지 요청시 > 6 7 8 9 10
		//페이지 네비게이션 시작번호 계산
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		
		String pageNavi = "<ul class='pagination'>";
		//이전버튼
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' data-page='"+(pageNo-1)+"'>";
			pageNavi += "<span class='meterial-icons'>chevron_left</span>";
			pageNavi += "</a></li>";
			//html '' 자바 ""
		}
		//페이지숫자
		for(int i = 0; i < pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' data-page='"+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' data-page='"+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a></li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		//다음버튼
		if(pageNo<=totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' data-page='"+(pageNo+1)+"'>";
			pageNavi += "<span class='meterial-icons'>chevron_right</span>";
			pageNavi += "</a></li>";
		}
		pageNavi += "</ul>";
		ReviewPageData rpd = new ReviewPageData(list, pageNavi);
		
		return rpd;
	}//selectReviewList
	
	//윤지
	public int insertWish(int productNo, int memberNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("productNo", productNo);
		map.put("memberNo", memberNo);
		return productDao.insertWish(map);
	}
	
	//윤지
	public int deleteWish(int productNo, int memberNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("productNo", productNo);
		map.put("memberNo", memberNo);
		return productDao.deletetWish(map);
	}

	//윤지
	public int afterWishCount(int productNo) {
		return productDao.selectwish(productNo);
	}

	//윤지
	public HashMap<String, Object> selectProductAndExpertAndPayment(int payNo) {
		Product product = productDao.selectOneProduct2(payNo);
		Expert expert = memberDao.selectOneExpertOnly2(payNo);
		ExpertAndMember expertM = memberDao.selectOneExpertPicture2(payNo);
		Payment payment = productDao.selectPaymentState(payNo);
		Counsel counsel = productDao.selectCounsel(payNo);
		int paymentState = payment.getPayState();
		int reviewUploadCheck = productDao.selectReviewUploadCheck(payNo);
		ArrayList<Chat> chatList = productDao.selectChat(payNo);
		String first = "";
		if(chatList.size() > 0) {
			for(int i = 0; i < 1; i++) {
				first = chatList.get(i).getChatDate();
			}
		}else {
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년MM월dd일");
			String formatedNow = now.format(formatter);
			first = formatedNow;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("product", product);
		map.put("expert",expert);
		map.put("expertM",expertM);
		map.put("payment",payment);
		map.put("paymentState",paymentState);
		map.put("review",reviewUploadCheck);
		map.put("counsel",counsel);
		map.put("chatList", chatList);
		map.put("first", first);
		return map;
	}

	public Expert selectExpertNo(int memberNo) {
		return productDao.selectExpertNo(memberNo);
	}
	
	public Member selectMemberNo(int memberNo) {
		return productDao.selectMemberNo(memberNo);
	}

	//윤지 채팅
	public int insertChat(String msg, String memberNo, String counselNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", msg);
		map.put("memberNo", memberNo);
		map.put("counselNo",counselNo);
		return productDao.insertChat(map);
	}


	//윤지
	public HashMap<String, Object> compareMemberNo(int payNo, HttpServletRequest request) {
		Expert expert = memberDao.selectOneExpertOnly2(payNo);
		Payment payment = productDao.selectPaymentState(payNo);
		HashMap<String, Object> compare = new HashMap<String, Object>();
		HttpSession session = request.getSession(false);
		Member member = null;
		if(session != null) {
			member = (Member)session.getAttribute("member");	
			compare.put("member", member);
			boolean expertNoCheck = false;
			boolean paymentMemberNoCheck = false;
			if(member.getMemberNo() == expert.getMemberNo()) {
				expertNoCheck = true;
			} 
			if(member.getMemberNo() == payment.getMemberNo()) {
				paymentMemberNoCheck = true;
			}			
			compare.put("expertTrue", expertNoCheck);
			compare.put("memberTrue",paymentMemberNoCheck);
		}		
		return compare;
	}

	
	//윤지 채팅
	public int updateReadCheck(String counselNo, String memberNo) {
		int realCounselNo = Integer.parseInt(counselNo);
		int realMemberNo = Integer.parseInt(memberNo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("counselNo", realCounselNo);
		map.put("memberNo", realMemberNo);
		return productDao.updateReadCheck(map);
	}
	
	//윤지
	public int updatePaymentState(int counselNo) {
		return productDao.updatePaymentState(counselNo);
	}

	//윤지
	public HashMap<String, Object> reviewFrm(int payNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Product product = productDao.selectOneProduct2(payNo);
		Expert expert = memberDao.selectOneExpertOnly2(payNo);
		ExpertAndMember expertM = memberDao.selectOneExpertPicture2(payNo);
		Payment payment = productDao.selectPaymentState(payNo);
		map.put("product", product);
		map.put("expert", expert);
		map.put("expertM", expertM);
		map.put("payment", payment);
		return map;
	}

	//윤지
	public Boolean reviewMemberCompare(int payNo, HttpServletRequest request) {
		Payment payment = productDao.selectPaymentState(payNo);
		HttpSession session = request.getSession(false);
		Member member = null;
		boolean memberCheck = false;
		if(session != null) {
			member = (Member)session.getAttribute("member");	
			if(member.getMemberNo() == payment.getMemberNo()) {
				memberCheck = true;
			}
		}
		return memberCheck;
	}

	//윤지
	public int insertReview(Review review, int payNo) {
		//여기서 중복체크..작성된 리뷰가 있으면, 인서트를 리뷰를 안 하고 다른 값 리턴
		int result = productDao.overlapCheckReview(payNo);
		if(result == 0) {
			return productDao.insertReview(review);			
		}else {
			return 0;
		}
	}

	//윤지
	public HashMap<String, Object> selectReview(int reviewNo) {
		Review review = productDao.selectReview(reviewNo);
		Product product = productDao.selectProductName(reviewNo);
		ExpertAndMember expertM = memberDao.selectOneExpert(reviewNo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("review", review);
		map.put("product", product);
		map.put("expertM", expertM);
		return map;
	}

	//윤지
	public int updateReview(Review review) {
		return productDao.updateReview(review);
	}

	//윤지
	public int deleteReview(int reviewNo) {
		return productDao.deleteReview(reviewNo);
	}

	public int updateStartTime(String startTime, int counselNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("counselNo", counselNo);
		return productDao.updateStartTime(map);
	}
	//대권 구매페이지
	public HashMap<String, Object> selectProduct(int productNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Product product = productDao.selectProductPayment(productNo);
		map.put("product", product);
		Expert expert = memberDao.selectExepertPayment(product.getExpertNo());
		map.put("expert",expert);
		return map;
	}

	public ArrayList<Product> selectPopular() {
		ArrayList<Integer> popularNo = productDao.selectPopularNo();
		
		ArrayList<Product> popularProduct = new ArrayList<Product>();
		for(int i=0;i<popularNo.size();i++) {
			popularProduct.add(productDao.selectPopularProduct(popularNo.get(i)));
		} 
		
		return popularProduct;
	}

	public int insertIdeamarket(int productNo, int marketfileNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("productNo", productNo);
		map.put("marketfileNo", marketfileNo);
		
		return productDao.insertIdeamarket(map);
	}
	//결제페이지등록
	public int paymentInsert(Payment p) {
		
		return productDao.paymentInsert(p);
	}
	//결제상담페이지등록
	public int counselInsert(Counsel c) {
		// TODO Auto-generated method stub
		return productDao.counselInsert(c);
	}
	//결제성공출력
	public HashMap<String, Object> purchaseSuccess(int memberNo, int productNo) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberNo",memberNo);
		map.put("productNo",productNo);
		HashMap<String, Object> pay = productDao.purchaseSuccess(map);
		HashMap<String, Object> pro = productDao.purchaseSuccess2(map);
		map.put("payment",pay);
		map.put("product",pro);
		//System.out.println("성공2"+map);
		return map;
	}

	public ArrayList<ProductAndEtc> selectProductList() {
		return productDao.selectProductList();
	}


}
