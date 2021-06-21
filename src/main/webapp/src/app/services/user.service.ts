import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

    constructor() { }

    private hashCode!: number;
    private username!: string;

    public setHashCode(hashCode: number): void {
        this.hashCode = hashCode;
    }

    public setUsername(username: string): void {
        this.username = username;
    }

    public getHashCode(): number {
        return this.hashCode;
    }

    public getUsername(): string {
        return this.username;
    }
}
