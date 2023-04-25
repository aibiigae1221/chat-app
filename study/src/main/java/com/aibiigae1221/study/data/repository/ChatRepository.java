package com.aibiigae1221.study.data.repository;

import java.util.List;

import com.aibiigae1221.study.data.dto.ChatRoom;

public interface ChatRepository {

	List<ChatRoom> getRoomList();

	void createChatRoom(String roomName);
}
