import { Component, OnInit } from '@angular/core';
import Message from "../chat-model/message";
import WebSocketAPI from "./WebSocketAPI";
import {Observable, of} from "rxjs";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  messages: Message[] = [];
  currentUser: string = '';
  webSocketAPI: WebSocketAPI;

  constructor() { }

  ngOnInit(): void {
    this.webSocketAPI = new WebSocketAPI(this);
  }

  handleMessageArrived(message: Message) {
    console.log('Message Received from Server :: ');
    console.log(message);
    if (message.user !== this.currentUser) {
      this.messages.unshift(message);
    }
    console.log('Messages: ');
    console.log(this.messages);
  }

  handleOnSend(message: Message) {
    message = {...message, user: this.currentUser};
    this.webSocketAPI?._send(message);
    this.messages.unshift(message)
  }

  joinRoom() {
    this.webSocketAPI?._connect();
  }
}
