import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {User} from "../../chat-model/user";
import Message from "../../chat-model/message";
import {ChatService} from "../chat.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss']
})
export class FriendsComponent implements OnInit {
  @Input() public friends$: Observable<User[]>;
  chosenUser: string = '';
  @Output() public onChosenUser = new EventEmitter<string>();

  constructor(private chatService: ChatService) { }

  ngOnInit(): void { }

  chooseUser(event) {
    this.chosenUser = event.target.textContent;
    this.onChosenUser.emit(this.chosenUser);
  }

}
