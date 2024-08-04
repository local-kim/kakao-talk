import axios from 'axios';
import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import * as StompJs from "@stomp/stompjs";

const RoomList = () => {
  const navigate = useNavigate();

  const [roomList, setRoomList] = useState([]);
  
  const userId = 1;
  const inviteeId = 2;

  const createRoom = () => {
    const data = {
      inviterId: userId,    // TODO: 로그인한 사용자로 바꾸기
      inviteeId: inviteeId  // TODO: 선택하는 걸로 바꾸기
    };

    axios.post('/api/room', data)
    .then(res => {
      // move to page /room/{roomId}
      // navigate(`/room/${res.data.roodId}`);
    }).catch(err => console.log(err));
  }

  useEffect(() => {
    // 웹소켓 설정
    const stompClient = new StompJs.Client({
      brokerURL: 'ws://localhost:8080/chat',
      connectHeaders: {},
      debug: function(str) {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000
    });

    // 웹소켓 연결 시
    stompClient.onConnect = (frame) => {
      // 채팅방 목록 구독
      stompClient.subscribe(`/sub/room/${inviteeId}`, (frame) => {
        const receivedRoom = JSON.parse(frame.body);
        setRoomList((prevRooms) => [...prevRooms, receivedRoom]);
      });
    };

    // 웹소켓 서버 오류 시
    stompClient.onStompError = (frame) => {
      console.error('*** Broker reported error: ' + frame.headers['message']);
      console.error('*** Additional details: ' + frame.body);
    };

    stompClient.activate();

    // TODO: 채팅방 목록 받아오기

    // 컴포넌트 언마운트 시 연결 해제
    return () => stompClient.deactivate();
  }, []);

  return (
    <div>
      <button onClick={createRoom}>채팅방 생성</button>
      {
        roomList.map((room, index) => (
          <div>{index + 1}, {room.id}, {room.type}</div>
        ))
      }
    </div>
  );
};

export default RoomList;