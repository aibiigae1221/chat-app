package com.aibiigae1221.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aibiigae1221.study.data.dto.ChatRoom;
import com.aibiigae1221.study.data.repository.ChatRepository;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public List<ChatRoom> getRoomList() {
		return chatRepository.getRoomList();
	}

	@Override
	public void createChatRoom(String roomName) {
		chatRepository.createChatRoom(roomName);
	}

}
