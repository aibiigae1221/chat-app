import {useState, useEffect} from "react";
import ChatRoomList from "./component/chat-room-list/ChatRoomList";
import AddNewRoomForm from "./component/add-new-room-form/AddNewRoomForm";
import ActivatedChatRoom from "./component/activated-chat-room/ActivatedChatRoom";

import styles from "./App.module.css";


const App = () => {

  const [jwt, setJwt] = useState(null);
  const [chatRoomList, setChatRoomList] = useState([]);
  const [activatedRoomId, setActivatedRoomId] = useState(-1);

  useEffect(() => {

    const options = {
      method:"post",
      mode:"cors",
      cache:"no-cache"
    };

    fetch("http://localhost:8080/token", options)
      .then(response => response.json())
      .then(json => setJwt(json.jwt));
  }, []);


  const loadChatRoomList = () => {
    const options = {
        method:"get",
        mode:"cors"
    };

    fetch("http://localhost:8080/chat/list-rooms", options)
        .then(response => response.json())
        .then(json => setChatRoomList(json.list));
  };
  
  const accessChatRoom = roomId => {
    setActivatedRoomId(roomId);
  };

  useEffect(() => {
    loadChatRoomList();
  }, []);

  if(jwt === null){
    return <></>;
  }


  let activatedRoomName = null;
  if(activatedRoomId !== -1){
    activatedRoomName = chatRoomList.find(room => room.roomId === activatedRoomId).roomName;
    console.log("활성화된 채팅방 이름: " + activatedRoomName);
  }
  
  


  return (
    <div className={styles.wrap}>

      <ChatRoomList jwt={jwt} chatRoomList={chatRoomList} setActivatedRoomId={setActivatedRoomId} accessChatRoom={accessChatRoom}/>
      {activatedRoomId === -1 &&
        <AddNewRoomForm jwt={jwt} loadChatRoomList={loadChatRoomList} />
      }
      {activatedRoomId !== -1 &&
        <ActivatedChatRoom jwt={jwt} chatRoomName={activatedRoomName} chatRoomId={activatedRoomId} />
      }
      
      
    </div>
  );
};

export default App;
