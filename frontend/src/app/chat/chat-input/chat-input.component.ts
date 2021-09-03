import {Component, OnInit, Output, EventEmitter} from '@angular/core';
import Message from "../../chat-model/message";

@Component({
  selector: 'app-chat-input',
  templateUrl: './chat-input.component.html',
  styleUrls: ['./chat-input.component.scss']
})
export class ChatInputComponent implements OnInit {

  textMessage = '';

  @Output() public onSendMessage = new EventEmitter<Message>();

  constructor() {}

  ngOnInit(): void {}

  send() {
    this.onSendMessage.emit({content: this.textMessage});
    this.textMessage = '';
  }

  onKeyDown(event) {
    console.log(event.key);
    if (event.key === 'Enter') {
      this.send();
    }
  }

}
