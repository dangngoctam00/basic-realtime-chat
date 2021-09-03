import {ChatComponent} from "./chat.component";
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import Message from "../chat-model/message";

export default class WebSocketAPI {
  webSocketEndPoint = 'http://localhost:8080/ws';
  listenedChannel = '/channel/1';
  messageBroker = '/app/chat';
  stompClient: any;
  chatComponent: ChatComponent;
  constructor(chatComponent: ChatComponent) {
    this.chatComponent = chatComponent;
  }
  _connect(user: string) {
    console.log('Initialize WebSocket Connection');
    const ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    this.stompClient.connect({}, (frame: any) => {
      this.stompClient.subscribe('/user/' + user + '/queue/messages', (sdkEvent: any) => {
        this.onMessageReceived(sdkEvent);
      });
    }, this.errorCallBack);
  }

  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected');
  }

  // on error, schedule a reconnection attempt
  errorCallBack(error: any) {
    console.log('errorCallBack -> ' + error);
    setTimeout(() => {
      // this._connect();
    }, 5000);
  }

  _send(message: Message) {
    console.log('send message');
    this.stompClient.send(this.messageBroker, {}, JSON.stringify(message));
  }

  onMessageReceived(message: any) {
    this.chatComponent.handleMessageArrived(JSON.parse(message.body));
  }
}
