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

const popup = document.querySelector(".popup");
const popupContent = document.querySelector(".popup .content");
const closePopupButton = document.querySelector(".popup .close");

const loginForm = document.getElementById('login-form');
const signupForm = document.getElementById('signup-form');

// Show the popup with a message
function showPopup(message) {
	popupContent.innerText = message;
	popup.style.visibility = "visible";

}

// Close the popup
closePopupButton.addEventListener("click", function() {
	popup.style.visibility = "hidden";
});

// Base URL for the API
const baseUrl = 'http://localhost:8080/library/users';

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
	}).then(response => {
		if (!response.ok) {
			response.text() // Call the text() function to retrieve the response text
				.then(errorMessage => {
					showPopup(errorMessage);
				});
		} else {
			showPopup("Registration Successful. Please login to proceed further");
			loginForm.style.display = 'block';
			signupForm.style.display = 'none';
		}
	})

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
				response.text()
					.then(errorMessage => {
						showPopup(errorMessage);
					});
			} else {
				response.text()
					.then(data => {
						console.log("data ", data);
						if (data === 'seller') {
							window.location.href = '/seller.html';
						} else if (data === 'buyer') {
							window.location.href = '/bookInventory.html';
						} else if (data === 'admin') {
							window.location.href = '/admin-page.html';
						}
						console.log('Login successful:', data);
					})
					.catch(error => {
						console.error('Error parsing response data:', error);
					});
			}
		})
		.catch(error => {
			// Handle login error
			showPopup("Login Failed ");
			console.error('Login error:', error);
		});
}


