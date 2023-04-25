import {useState, useEffect} from "react";
import SockJS from "sockjs-client"
import Stomp from 'stompjs'


const ActivatedChatRoom = ({jwt,chatRoomName, chatRoomId}) => {

    const [newMessage, setNewMessage] = useState("");
    const [registeredStompClient, setRegisteredStompClient] = useState(null);
    const [stompSubs, setStompSubs] = useState(null);
    const [messageList, setMessageList] = useState([]);

    const handleChange = e => {
        setNewMessage(e.target.value);
    };

    const handleSubmit = e => {
        e.preventDefault();
        //alert(newMessage);

        if(newMessage.trim() === ""){
            return;
        }

        const body = newMessage;
        

        registeredStompClient.send(`/topic/${chatRoomId}`, {}, body);
    };


    useEffect(() => {

        const socket = new SockJS('http://localhost:8080/ws');
        const stompClient = Stomp.over(socket);
        
        const headers = {Authorization: `Bearer ${jwt}`};
        stompClient.heartbeat.outgoing = 60 * 1000;
        stompClient.reconnect_delay = 20 * 1000;

        stompClient.connect(headers, () => {
            // 채팅 서버와 연결 수립
            const subs = stompClient.subscribe(`/topic/${chatRoomId}`, message => {
                console.log("새로운 메시지: " + message);
                const newList = messageList.slice();
                newList.push(message.body);
                setMessageList(newList);
                //console.log(newList);
            });
            setRegisteredStompClient(stompClient);
            setStompSubs(subs);

        }, (frame) => {
            // 채팅 서버와 연결 시도 중 에러
            console.log(frame);
        });
        
        return () => {
            // 메모리 해제 작업
            if(stompSubs !== null){
                stompSubs.unsubscribe();
                setStompSubs(null);
            }
            
            if(registeredStompClient !== null){
                registeredStompClient.disconnect(() => {
                    alert("연결이 끊어졌습니다.");
                });
    
                setRegisteredStompClient(null);
            }
            

            setMessageList([]);
        };
    }, [chatRoomId]);

    return (
        <div>
            <h1>입장 - {chatRoomName} / {chatRoomId}</h1>
            <ul>
                {messageList.length > 0 && messageList.map((msg, idx) => 
                    <li key={idx}>
                        {msg}
                    </li>    
                )}
            </ul>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="메시지를 입력하세요" value={newMessage} onChange={handleChange}/>
                <button>전송</button>
            </form>
        </div>
    );
};

export default ActivatedChatRoom;