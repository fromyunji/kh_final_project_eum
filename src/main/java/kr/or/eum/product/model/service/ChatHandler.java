package kr.or.eum.product.model.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ChatHandler extends TextWebSocketHandler {
	
	@Autowired
	private ProductService productService;

	// 1:1채팅방 : 따로 따로 x 한 번에 관리 필요, 제일 먼저 필요한 건 채팅방 하나고, 채팅방을 리스트화 한 게 필요 이걸 리스트로 만들꺼냐, 맵으로 만들꺼냐
	// 중요한 건 내가 보낸 메세지가 어떤 채팅방에 갈 건지 구분이 필요....참여하고 있는 채팅방이 여러 채팅이 여러개 일 수 있어서 
	
	//우선순위 : 1:1 최적화.
	//이후 : 이게 여러개 됐을 때 어떻게 관리할지.
	
	//접속한 회원
	private HashMap<String, ArrayList<WebSocketSession>> sessionMap;
	private HashMap<WebSocketSession, String> sessionRoom;

//	LocalTime localTime = LocalTime.now();
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//	String now = localTime.format(formatter);

	public ChatHandler() {
		super();
		this.sessionMap = new HashMap<String, ArrayList<WebSocketSession>>();
		this.sessionRoom = new HashMap<WebSocketSession, String>();
	}
	
	//클라이언트가 웹소캣에 최초로 접속했을 때 자동으로 수행되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
//		sessionList.add(session);
		System.out.println("새 클라이언트 접속!");
		System.out.println("session : "+ session.getId());
//		session.get
	}//afterConnectionEstablished
	
	//클라이언트가 서버로 메세지를 전송하면 수행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		//System.out.println("메세지 전송한 세션 : "+session);
		System.out.println("전송 메세지 : "+message.getPayload());
		//문자열을 Json 객체로 변환해줄 객체
		JsonParser parser = new JsonParser();
		//parser를 이용해서 String > Json형태로 변화
		JsonElement element = parser.parse(message.getPayload()); //구글
		//키가 type인 값을 추출
		String type = element.getAsJsonObject().get("type").getAsString();
		//키가 msg인 값을 추출
		String msg = element.getAsJsonObject().get("msg").getAsString();
		String memberNo = element.getAsJsonObject().get("memberNo").getAsString();
		String counselNo = element.getAsJsonObject().get("counselNo").getAsString();
		String time = element.getAsJsonObject().get("time").getAsString();
		
		// 상담이 없을 때
		if(!sessionMap.containsKey(counselNo)) {
			sessionMap.put(counselNo, new ArrayList<WebSocketSession>());
		}
		
		
		// 새로 채팅방에 회원이 들어온 경우
		if(type.equals("enter")) {
			System.out.println("---------------");
			System.out.println(counselNo);
			System.out.println("---------------");
			sessionMap.get(counselNo).add(session);
			sessionRoom.put(session, counselNo);
			//int readResult = productService.updateReadCheck(counselNo, memberNo);
			//System.out.println("readResult : "+readResult);
		//채팅메세지를 입력한 경우
		}else if(type.equals("chat")) {//if문으로 이미지 있다 없다, 읽음 표시도 
			String sendMsg ="<div class='chat-content-wrap'><div class='chat left'><span class='chatId'></span>"+msg+"</div><div class='content-sub-wrap'><div class='read-check'>1<div><div class='chat-time'>"+time+"</div></div></div>";
			int result = productService.insertChat(msg, memberNo,counselNo); 
			for(WebSocketSession s : sessionMap.get(counselNo)) {
				if(!s.equals(session)) {
					TextMessage tm = new TextMessage(sendMsg);
					//나 외에 다른 사람이 있다 없다 체크
					s.sendMessage(tm);
				}//if문
			}//for문
		}//else if(type.chat)
	}//handleTextMessage
		
	//클라이언트와 연결이 끊겼을 때 자동으로 수행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		String counselNo = sessionRoom.get(session);
		ArrayList<WebSocketSession> sessionList = sessionMap.get(counselNo);
		sessionList.remove(session);
		sessionRoom.remove(session);
		
		if(sessionList.isEmpty()) {
			sessionMap.remove(counselNo);
		}
		
		String sendMsg = "<p'>부재중입니다.</p>"; //나중에 삭제
		TextMessage tm = new TextMessage(sendMsg);
		for(WebSocketSession s : sessionList) {
			s.sendMessage(tm); 
		}
	}//afterConnectionClosed
	
}//ChatHandler 