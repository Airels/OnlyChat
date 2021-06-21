import {Component, HostListener, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {HttpRequestService} from "../services/http-request.service";
import {WebSocketService} from "../services/web-socket.service";
import {Message} from "../models/message";
import {observable, Observable} from "rxjs";
import {UserService} from "../services/user.service";
import {min} from "rxjs/operators";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

    messageSenderForm = this.formBuilder.group({
        message: ''
    });
    messages: Promise<String> | null = null;


    constructor(private formBuilder: FormBuilder, private httpService: HttpRequestService, private webSocketService: WebSocketService, private userService: UserService) {
    }

    @HostListener('window:beforeunload', ['$event'])
    beforeunloadHandler(event: any) {
        this.endChat();
    }

    ngOnInit(): void {
        let audio = new Audio();
        audio.src = "assets/notification.ogg";
        audio.load();

        this.refreshMessages();
        this.webSocketService.createObservableSocket('ws://localhost:8080/test').subscribe((res) => {
            console.log(`WebSocket: ${res}`);

            if (res === "ping") {
                this.getMessages().subscribe((username: string) => {
                    if (username != this.userService.getUsername())
                        audio.play();
                });
            }
        });
    }

    refreshMessages(): void {
        this.getMessages().subscribe((res) => {});
    }

    // Return observable of latest username message
    getMessages(): Observable<any> {
        return new Observable<string>(observer => {
            this.httpService.getMessages().subscribe((res) => {
                let messages: Message[] = res;
                let render: string = (messages.length == 0) ? "No messages currently... :(" : "";

                messages.forEach((message) => {
                    let date = new Date(message.timestamp);
                    let hours = "0" + date.getHours();
                    let minutes = "0" + date.getMinutes();
                    render += `(${hours.substr(-2)}:${minutes.substr(-2)}) ${message.author} - ${message.content}\n`;
                });

                this.messages = new Promise<String>((resolve) => {
                    resolve(render);
                });

                if (messages.length == 0) observer.next('');
                else observer.next(messages[0].author);
            });
        });
    }

    onSubmit() {
        this.messageSenderForm.disable();
        let formValue = this.messageSenderForm.value;

        this.httpService.postMessage(formValue['message']).subscribe((res) => {
            this.messageSenderForm.reset();
            this.messageSenderForm.enable();
            // @ts-ignore
            document.getElementById('message').focus();
            this.refreshMessages();
        });
    }

    endChat() {
        this.httpService.logout().subscribe((res) => {});
        this.webSocketService.close();
    }
}
