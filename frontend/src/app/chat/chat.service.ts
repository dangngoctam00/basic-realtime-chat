import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import Message from "../chat-model/message";

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  url = 'http://localhost:8080/api';

  constructor(private httpClient: HttpClient) { }

  getFriends(user: string): Observable<any> {
    return this.httpClient.get(this.url + `/friends/${user}`);
  }

  getMessages(sender: string, recipient: string): Observable<any> {
    return this.httpClient.get(this.url + `/messages/${sender}/${recipient}`);
  }

  sendMessage(message: Message) {
    this.httpClient.post(this.url + '/api/app/chat', message);
  }
}
