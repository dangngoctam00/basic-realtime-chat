import { Component, OnInit } from '@angular/core';
import Message from "../chat-model/message";
import WebSocketAPI from "./WebSocketAPI";
import {Observable, of} from "rxjs";
import {ChatService} from "./chat.service";
import {User} from "../chat-model/user";
import {ActivatedRoute} from "@angular/router";
import {map, switchMap} from "rxjs/operators";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  messages: Message[] = [];
  currentUser: string = '';
  friends$: Observable<User[]>;
  webSocketAPI: WebSocketAPI;

  constructor(private chatService: ChatService, private _route: ActivatedRoute) { }

  ngOnInit(): void {
    this.webSocketAPI = new WebSocketAPI(this);
    this.friends$ = this._route.paramMap.pipe(
      map(params => params.get('user')),
      switchMap(user => this.chatService.getFriends(user))
    )
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
    this.webSocketAPI?._connect(this.currentUser);
  }

  onChosenUser(user: string) {
    console.log(user);
  }
}
