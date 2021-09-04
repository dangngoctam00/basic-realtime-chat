import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {User} from "../../chat-model/user";
import Message from "../../chat-model/message";
import {ChatService} from "../chat.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.scss']
})
export class FriendsComponent implements OnInit, OnChanges {
  @Input() public friends: User[];
  chosenUser: string = '';
  @Output() public onChosenUser = new EventEmitter<string>();

  constructor(private chatService: ChatService) { }

  ngOnInit(): void { }

  ngOnChanges(changes: SimpleChanges) {
    if (this.chosenUser === '' && this.friends) {
      this.chosenUser = this.friends[0].username;
    }
  }

  chooseUser(event) {
    this.chosenUser = event.target.textContent;
    this.onChosenUser.emit(this.chosenUser);
  }

}
