import { Injectable } from '@angular/core';
import { User } from 'src/app/interfaces/user';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor() {}

  private users: User[] = [
    {
      email: 'user1@gmail.com',
      username: 'user1',
      password: 'password1',
      is_admin: true,
      is_confirmed: false,
    },
    {
      email: 'user2@gmail.com',
      username: 'user2',
      password: 'password2',
      is_admin: false,
      is_confirmed: false,
    },
    {
      email: 'user3@gmail.com',
      username: 'user3',
      password: 'password3',
      is_admin: false,
      is_confirmed: false,
    },
  ];

  login(email: string, password: string): boolean {
    const userIndex = this.users.findIndex(
      (user) => user.password === password && user.email === email
    );
    if (userIndex != -1) {
      this.users[userIndex].is_confirmed = true;
      console.log(this.users[userIndex].is_confirmed);
      return true;
    } else {
      return false;
    }
  }
}
