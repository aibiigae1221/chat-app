package com.aibiigae1221.study.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.aibiigae1221.study.data.dto.ChatRoom;

import jakarta.annotation.PostConstruct;

@Repository
public class ChatRepositoryImpl implements ChatRepository{
	
	private List<ChatRoom> list = null;
	
	@PostConstruct
	public void init() {
		list = new ArrayList<ChatRoom>();
	}
	
	@Override
	public List<ChatRoom> getRoomList() {
		return list;
	}

	@Override
	public void createChatRoom(String roomName) {
		ChatRoom newRoom = new ChatRoom();
		newRoom.setRoomId(UUID.randomUUID());
		newRoom.setRoomName(roomName);
		list.add(newRoom);
	}

}
