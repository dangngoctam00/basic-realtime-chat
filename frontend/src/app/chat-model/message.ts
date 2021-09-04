export default class Message {
  content: string;
  sender: string;
  recipient: string;
  time?: Date;

  constructor(content: string, sender: string, recipient: string, time?: Date) {
    this.content = content;
    this.sender = sender;
    this.recipient = recipient;
    this.time = time;
  }
}
