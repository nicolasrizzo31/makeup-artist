import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavigationComponent } from './layout/navigation.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavigationComponent], // Add NavigationComponent here
  templateUrl: './app.component.html',
  // styleUrls: ['./app.component.css'] // if it has one
})
export class AppComponent {
  title = 'angular-frontend';
}
