document.addEventListener('DOMContentLoaded', function() {
	const loginForm = document.getElementById('login-form');
	const signupForm = document.getElementById('signup-form');
	const loginBtn = document.getElementById('login-btn');
	const signupBtn = document.getElementById('signup-btn');

	loginForm.addEventListener('submit', function(e) {
		e.preventDefault();
		const email = document.getElementById('login-email').value;
		const password = document.getElementById('login-password').value;
		localStorage.setItem("username", email);
		loginUser(email, password);
	});

	signupForm.addEventListener('submit', function(e) {
		e.preventDefault();
		const email = document.getElementById('signup-email').value;
		const password = document.getElementById('signup-password').value;
		const roles = document.getElementById('signup-roles').value;
		console.log("roles " + roles);
		registerUser(email, password, roles);
	});

	loginBtn.addEventListener('click', function() {
		loginForm.style.display = 'block';
		signupForm.style.display = 'none';
	});

	signupBtn.addEventListener('click', function() {
		loginForm.style.display = 'none';
		signupForm.style.display = 'block';
	});

	signupForm.style.display = "none";
	uploadSection.style.display = "none";

});



// Base URL for the API
const baseUrl = 'http://localhost:8080/api/users';

// Function to handle user registration
function registerUser(username, password, roles) {
	const userData = {
		userName: username,
		password: password,
		userRole: roles
	};

	fetch(`${baseUrl}/register`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(userData)
	})
		.then(response => {
			if (response.ok) {
				return response.json(); // Assuming the server sends a JSON response
			}
			throw new Error('Registration failed');
		})
		.then(data => {
			// Registration successful
			console.log('Registration successful:', data);
			alert("Registration Successful. Please login to proceed further");
		})
		.catch(error => {
			// Registration error
			console.error('Registration error:', error);
			// Display an error message to the user or show a popup
			alert('Registration failed. User name may already be taken.');
		});
}


// Function to handle user login
function loginUser(username, password) {
	const loginData = {
		userName: username,
		password: password
	};

	fetch(`${baseUrl}/login`, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(loginData)
	})
		.then(response => {
			if (!response.ok) {
				alert("Login Failed");
				throw new Error('Login failed');
			}
			return response.text();
		})
		.then(data => {
			console.log("data ", data);
			if (data === 'seller') {
				window.location.href = '/seller.html';
			} else if (data === 'buyer') {
				// Redirect to the book inventory page
				window.location.href = '/bookInventory.html';
			} else if (data === 'admin') {
				// Redirect to the admin page
				window.location.href = '/admin-page.html';
			}

			console.log('Login successful:', data);
		})
		.catch(error => {
			// Handle login error
			console.error('Login error:', error);
		});
}

