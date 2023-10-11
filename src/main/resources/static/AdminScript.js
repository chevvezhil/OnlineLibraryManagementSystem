// Base URL for the API
const baseUrl = 'http://localhost:8080/api/user';

// Function to get Seller details
function getSellerDetails() {
	fetch("/api/sellerDetails")
		.then(response => {
			if (!response.ok) {
	            throw new Error("Network response was not ok");
	        }
        	return response.json()})
        .then(data => {
			console.log(data);
            const sellerTable = document.getElementById("sellerTable");
            const tbody = sellerTable.querySelector("tbody");

            // Clear existing table rows
            tbody.innerHTML = '';

            // Populate the table with data
             //data.forEach(user => {
                const row = document.createElement("tr");
                row.innerHTML = `
                	<td>${data.sellerName}</td>
                    <td>${data.sellerId}</td>
                    <td>${data.isVerified}</td>
                `;
                tbody.appendChild(row);
             //});
        })
        .catch(error => {
            console.error("Error fetching user details:", error);
        });
}


document.addEventListener("DOMContentLoaded", getSellerDetails);