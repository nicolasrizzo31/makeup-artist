import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common'; // For *ngIf, etc.
import { environment } from '../../environments/environment'; // Adjust path as needed

@Component({
  selector: 'app-prenotazioni',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule], // Add ReactiveFormsModule and CommonModule
  templateUrl: './prenotazioni.component.html',
  // styleUrls: ['./prenotazioni.component.css'] // if you add styles
})
export class PrenotazioniComponent implements OnInit {
  bookingForm!: FormGroup; // Definite assignment assertion
  submissionMessage: string = '';

  constructor(private fb: FormBuilder, private http: HttpClient) {}

  ngOnInit(): void {
    this.bookingForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      date: ['', Validators.required],
      service: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.bookingForm.valid) {
      console.log('Form Data:', this.bookingForm.value);
      const backendUrl = `${environment.apiBaseUrl}/bookings`; // Use environment variable
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

      this.http.post(backendUrl, this.bookingForm.value, { headers: headers, responseType: 'text' })
        .subscribe({
          next: (response) => {
            this.submissionMessage = 'Booking request submitted successfully! Response: ' + response;
            this.bookingForm.reset();
          },
          error: (error) => {
            console.error('Error submitting booking:', error);
            this.submissionMessage = 'Failed to submit booking. Error: ' + (error.error?.message || error.message);
          }
        });
    } else {
      this.submissionMessage = 'Please fill out all required fields correctly.';
      Object.values(this.bookingForm.controls).forEach(control => {
        control.markAsTouched();
      });
    }
  }
}
