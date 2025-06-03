document.addEventListener('DOMContentLoaded', () => {
	// Toggle navigation
	const navToggle = document.querySelector('.nav-toggle');
	const navList = document.querySelector('.nav-list');
	navToggle.addEventListener('click', () => {
		navList.classList.toggle('active');
	});

	// Portfolio filter
	const filterButtons = document.querySelectorAll('.filter-btn');
	const galleryItems = document.querySelectorAll('.gallery-item');
	filterButtons.forEach(btn => {
		btn.addEventListener('click', () => {
			const category = btn.getAttribute('data-category');
			galleryItems.forEach(item => {
				item.style.display = (category === 'all' || item.dataset.category === category) ? 'block' : 'none';
			});
		});
	});

	// Booking form submission
	const bookingForm = document.getElementById('booking-form');
	if (bookingForm) {
		bookingForm.addEventListener('submit', e => {
			e.preventDefault();
			const formData = new FormData(bookingForm);
			const data = Object.fromEntries(formData.entries());

			fetch('/api/bookings', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(data),
			})
				.then(response => {
					if (response.ok) {
						alert('Richiesta di prenotazione inviata con successo!');
						bookingForm.reset();
					} else {
						alert("Errore nell'invio della richiesta. Riprova più tardi.");
					}
				})
				.catch(error => {
					console.error('Error:', error);
					alert("Errore nell'invio della richiesta. Riprova più tardi.");
				});
		});
	}

	// Contact form submission
	const contactForm = document.getElementById('contact-form');
	if (contactForm) {
		contactForm.addEventListener('submit', e => {
			e.preventDefault();
			const formData = new FormData(contactForm);
			const data = Object.fromEntries(formData.entries());

			fetch('/api/contact', {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json',
				},
				body: JSON.stringify(data),
			})
				.then(response => {
					if (response.ok) {
						alert('Messaggio inviato con successo!');
						contactForm.reset();
					} else {
						alert("Errore nell'invio del messaggio. Riprova più tardi.");
					}
				})
				.catch(error => {
					console.error('Error:', error);
					alert("Errore nell'invio del messaggio. Riprova più tardi.");
				});
		});
	}
});
