package com.aibiigae1221.study.data.dto;

import java.io.Serializable;
import java.util.UUID;

public class ChatRoom implements Serializable{

	private static final long serialVersionUID = 1L;

	private UUID roomId;
	
	private String roomName;
	
	public ChatRoom() {}

	public UUID getRoomId() {
		return roomId;
	}

	public void setRoomId(UUID roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	
	
}
