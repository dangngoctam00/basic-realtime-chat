export default class Message {
  content: string;
  user?: string;

  constructor(content: string, user?: string) {
    this.content = content;
    this.user = user;
  }
}
