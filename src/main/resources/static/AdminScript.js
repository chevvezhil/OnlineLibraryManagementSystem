// Base URL for the API
const baseUrl = 'http://localhost:8080/api/user';

// Function to get Seller details
function getSellerDetails() {
	fetch("/api/admin/sellerDetails")
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
             data.forEach((user) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                	<td>${user.sellerId}</td>
                    <td>${user.sellerName}</td>
                    <td>${user.verificationStatus}</td>
                    <td>
                    	<button
                    		type="button" 
                    		id="myBtn" 
                    		onclick="updateVerificationStatus(${user.sellerId}, '${user.sellerName}', '${user.verificationStatus}')"
                    		${user.verificationStatus == 'VERIFIED' ? 'disabled' : ''}>Verify</button>
					</td>
                `;
                tbody.appendChild(row);
            	});
        })
        .catch(error => {
            console.error("Error fetching user details:", error);
        });
}

// Update Verification Status
function updateVerificationStatus(sellerId, sellerName, verificationStatus) {
	const request = {
		sellerId: sellerId,
		sellerName: sellerName
	}
	
	fetch("api/admin/updateVerificationStatus", {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(request)
	})
	.then(response => {
		if (!response.ok) {
			throw new Error ('Verification failed. Please try again');
		}
		location.reload();
		return response.text();
	})
	.catch(error => {
			// Handle error
			console.error("Error:", error);
	});
}

document.addEventListener("DOMContentLoaded", getSellerDetails);