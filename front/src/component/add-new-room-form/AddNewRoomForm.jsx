import {useState} from "react";

const AddNewRoomForm = ({jwt, loadChatRoomList}) => {


    const [newRoomName, setNewRoomName] = useState("");

    const handleChange = e => {
        setNewRoomName(e.target.value);
    };

    const handleSubmit = e => {
        e.preventDefault();

        const body = new URLSearchParams({
            roomName: newRoomName
        });

        const options = {
            method: "post",
            mode: "cors",
            body: body,
            headers:{
                "Authorization": `Bearer ${jwt}`
            }
        };

        fetch("http://localhost:8080/chat/create-room", options)
            .then(response => response.json())
            .then(json => {
                if(json.status === "success"){
                    setNewRoomName("");
                    loadChatRoomList();
                }else{
                    alert("방 만들기 실패! 이유는 몰?루");
                }
            });
    };

    if(jwt === null){
        return <></>;
    }
    
    return (
        <div>
            <h1>채팅방 만들기</h1>
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="방 이름" value={newRoomName} onChange={handleChange}/>
                <button>방 생성</button>
            </form>
        </div>
    );
};

export default AddNewRoomForm;