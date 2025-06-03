import { Component } from '@angular/core';
import { RouterLink } from '@angular/router'; // Ensure this is imported

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink], // Ensure RouterLink is in the imports array
  templateUrl: './home.component.html',
  // styleUrls: ['./home.component.css'] // If it has styles
})
export class HomeComponent {
  // Component logic here
}
