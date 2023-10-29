document.addEventListener('DOMContentLoaded', function() {
	const addSellerForm = document.getElementById('addseller-form');

	const sellerDetailsSection = document.getElementById('sellerDetails');
	const addSellerSection = document.getElementById('addSeller');
	const bookInventorySection = document.getElementById('bookInventory');

	addSellerForm.addEventListener('submit', function(e) {
		e.preventDefault();
		const email = document.getElementById('seller-email').value;
		const password = document.getElementById('seller-password').value;
		addSeller(email, password);
	});




	// Function to show a specific section and hide the others
	function showSection(sectionId) {
		sellerDetailsSection.style.display = 'none';
		addSellerSection.style.display = 'none';
		bookInventorySection.style.display = 'none';
		document.getElementById(sectionId).style.display = 'block';
	}

	// Add event listeners to the navigation links
	document.querySelectorAll('.topnav a').forEach(function(link) {
		link.addEventListener('click', function(e) {
			e.preventDefault(); // Prevent the default link behavior
			showSection(link.getAttribute('href').substring(1)); // Extract the section ID and show it
		});
	});

	// Initially, show the default section
	showSection('sellerDetails');

});

const popup = document.querySelector(".popup");
const popupContent = document.querySelector(".popup .content");
const closePopupButton = document.querySelector(".popup .close")

function showPopup(message) {
	popupContent.innerText = message;
	popup.style.visibility = "visible";

}

// Close the popup
closePopupButton.addEventListener("click", function() {
	popup.style.visibility = "hidden";
	reloadPage();
});

function reloadPage() {
	window.location.reload();
}

// Function to get Seller details
function getSellerDetails() {
	fetch("/api/admin/sellerDetails")
		.then((response) => {
			if (!response.ok) {
				throw new Error("Network response was not ok");
			}
			return response.json();
		})
		.then((data) => {
			console.log(data);
			const sellerTable = document.getElementById("sellerTable");
			const tbody = sellerTable.querySelector("tbody");

			// Clear existing table rows
			tbody.innerHTML = "";

			// Populate the table with data
			data.forEach((user) => {
				const row = document.createElement("tr");
				row.innerHTML = `
                    <td>${user.sellerName}</td>
                    <td>${user.verificationStatus}</td>
                    <td>
                    	<button
                    		type="button" 
                    		id="myBtn" 
                    		onclick="updateVerificationStatus(${user.sellerId})"
                    		${user.verificationStatus == "VERIFIED"
						? "disabled"
						: ""
					}>Verify</button>
					</td>
					<td>
                    	<button
                    		type="button" 
                    		id="seller" 
                    		onclick="removeSeller(${user.sellerId})"
                    	>Remove</button>
					</td>
                `;
				tbody.appendChild(row);
			});
		})
		.catch((error) => {
			console.error("Error fetching user details:", error);
		});
}

// Update Verification Status
function updateVerificationStatus(sellerId) {
	const request = {
		sellerId: sellerId,
		verifiedBy: localStorage.getItem("username")
	};

	fetch("api/admin/updateVerificationStatus", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(request),
	})
		.then((response) => {
			if (!response.ok) {
				showPopup("Verification failed. Please try again");
				throw new Error("Verification failed. Please try again");
			} else {
				showPopup("Seller verified");
				getSellerDetails();
			}

			return response.text();
		})
		.catch((error) => {
			// Handle error
			console.error("Error:", error);
		});
}

// Remove Seller
function removeSeller(sellerId) {
	const request = {
		sellerId: sellerId
	};

	fetch("api/admin/removeSeller", {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(request),
	})
		.then((response) => {
			if (!response.ok) {
				showPopup("Failed to remove Seller");
				throw new Error("Unable to remove Seller");
			} else {
				showPopup("Seller removed successfully");
				getSellerDetails();
			}

			return response.text();
		})
		.catch((error) => {
			// Handle error
			console.error("Error:", error);
		});
}

// Add Seller
function addSeller(userName, password) {
	const user = {
		userName: userName,
		password: password,
		userRole: "seller"
	};

	const seller = {
		verifiedBy: localStorage.getItem("username"),
		addedByAdmin: true
	}

	const data = {
		user,
		seller,
	};
	fetch("/api/admin/addSeller", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	})
		.then(response => {
			if (!response.ok) {
				showPopup("Failed to add Seller");
				throw new Error('Failed to add Seller');
			} else {
				showPopup("Seller Added successfully");
				document.getElementById("addSeller-form").reset();
				getSellerDetails();
			}

			return response.text();
		})
		.catch(error => {
			console.error('Failed to add seller:', error);
		});
}

//Get book inventory
function getAllBooks() {
	fetch("/library/getBooks")
		.then((response) => {
			if (!response.ok) {
				throw new Error("Unable to fetch book details");
			}
			return response.json();
		})
		.then((data) => {
			console.log(data);
			displayBooks(data);
		})
		.catch(error => {
			console.error('Error:', error);
		});
}

function displayBooks(bookArray) {
	const bookTable = document.getElementById("bookTable");
	const tbody = bookTable.querySelector("tbody");

	// Clear existing table rows
	tbody.innerHTML = "";

	// Populate the table with data
	bookArray.forEach((book) => {
		const row = document.createElement("tr");
		row.innerHTML = `
        <td>${book.bookname}</td>
        <td>${book.author}</td>
        <td>${book.genre}</td>
        <td>${book.seller}</td>
        <td>${book.price}</td>
    `;
		tbody.appendChild(row);
	});
}

//Active Navbar
document.addEventListener("DOMContentLoaded", function() {
	const navbarLinks = document.querySelectorAll(".topnav a");

	navbarLinks.forEach(link => {
		link.addEventListener("click", function(event) {
			// Remove the 'active' class from all links
			navbarLinks.forEach(link => link.classList.remove("active"));

			// Add the 'active' class to the clicked link
			this.classList.add("active");
		});
	});
});

function initializePage() {
	getSellerDetails();
	getAllBooks();
}

document.addEventListener("DOMContentLoaded", initializePage);
