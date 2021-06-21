import { Injectable } from '@angular/core';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  webSocket!: any;

  constructor() { }

  public createObservableSocket(url: string): Observable<string> {
    console.log(`WebSocket: connection to ${url}`);
    this.webSocket = new WebSocket(url);

    return new Observable((res: any) => {
      this.webSocket.onmessage = (message: { data: any; }) => res.next(message.data);
      this.webSocket.onerror = (message: any) => res.error(message);
      this.webSocket.onclose = (message: any) => res.complete();
    });
  }

  public sendMessage(message: string) {
      this.webSocket.send(message);
  }

  public close() {
      this.webSocket.close();
  }
}
