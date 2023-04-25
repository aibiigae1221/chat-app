const ChatRoomList = ({chatRoomList, accessChatRoom}) => {

    
    return (
        <div>
            <h1>방 목록</h1>
            <ul>
                {chatRoomList.length > 0 && chatRoomList.map(room => 
                    <li key={room.roomId}>
                        <button type="button" onClick={() => accessChatRoom(room.roomId)}>
                            {room.roomName}
                        </button>
                    </li>    
                )}
            </ul>
        </div>
    );
};

export default ChatRoomList;