package com.aibiigae1221.study.service;

import java.util.List;

import com.aibiigae1221.study.data.dto.ChatRoom;

public interface ChatService {

	List<ChatRoom> getRoomList();

	void createChatRoom(String roomName);

}
