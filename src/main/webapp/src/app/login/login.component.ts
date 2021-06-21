import { Component, OnInit } from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {HttpRequestService} from "../services/http-request.service";
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    userConnection = this.formBuilder.group({
        username: '',
        password: ''
    });

    constructor(private formBuilder: FormBuilder, private httpService : HttpRequestService, private userService: UserService, private router: Router) { }

    ngOnInit(): void {

    }

    onSubmit(buttonType: any): void {
        this.userConnection.disable();
        let formValue = this.userConnection.value;

        if (buttonType == 'login') {
            this.httpService.login(formValue['username'], formValue['password']).subscribe(
                (res) => {
                    console.log(res);
                    this.userService.setHashCode(res);
                    this.userService.setUsername(formValue['username']);
                    this.router.navigate(['/home']);
                },
                error => {
                    console.log(error.status);
                    this.userConnection.enable();
                    if (error.status == 401)
                        alert("Wrong credentials, please try again")
                });
        } else {
            this.httpService.register(formValue['username'], formValue['password']).subscribe(
                (res) => {
                    console.log(res);
                    this.userService.setHashCode(res);
                    this.userService.setUsername(formValue['username']);
                    this.router.navigate(['/home']);
                },
                error => {
                    console.log(error.status);
                    if (error.status == 409)
                        alert("Username already exist, please try again")
                    this.userConnection.enable();
                });
        }
    }

}
