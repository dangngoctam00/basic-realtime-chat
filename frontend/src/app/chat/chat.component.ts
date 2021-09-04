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

  messages: Message[];
  currentUser: string = '';
  targetUser: string = '';
  friends$: Observable<User[]>;
  webSocketAPI: WebSocketAPI;

  constructor(private chatService: ChatService, private _route: ActivatedRoute) { }

  ngOnInit(): void {
    this.webSocketAPI = new WebSocketAPI(this);
    this.friends$ = this._route.paramMap.pipe(
      map(params => {
        this.currentUser = params.get('user');
        this.webSocketAPI._connect(this.currentUser);
        return this.currentUser;
      }),
      switchMap(user => this.chatService.getFriends(user))
    )
  }

  handleMessageArrived(message: Message) {
    console.log('Message Received from Server :: ', message);
    this.messages.unshift(message);
  }

  handleOnSend(content: string) {
    console.log('Send message: ' + content);
    const message: Message = {content: content, sender: this.currentUser, recipient: this.targetUser};
    this.webSocketAPI?._send(message);
    this.messages.unshift(message);
  }

  joinRoom() {
    this.webSocketAPI?._connect(this.currentUser);
  }

  onChosenUser(user: string) {
    console.log('On chosenUser in Chat component', user);
    this.targetUser = user;
    this.chatService.getMessages(this.currentUser, user).subscribe(response => this.messages = response);
  }
}
