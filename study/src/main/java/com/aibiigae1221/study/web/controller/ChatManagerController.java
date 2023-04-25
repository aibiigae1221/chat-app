package com.aibiigae1221.study.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aibiigae1221.study.data.dto.ChatRoom;
import com.aibiigae1221.study.service.ChatService;

@RestController
public class ChatManagerController {

	
	@Autowired
	private ChatService chatService;
	
	
	@GetMapping("/chat/list-rooms")
	public Map<String, Object> getChatRoomList(){
		List<ChatRoom> list = chatService.getRoomList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		return map;
	}
	
	@PostMapping("/chat/create-room")
	public Map<String, Object> createChatRoom(@RequestParam("roomName") String roomName){
		chatService.createChatRoom(roomName);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		return map;
	}
}
