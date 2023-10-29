# Online Library Management System #

### Team ###
* Chevvanthi E
* Swaminathan G M
* Homeminitha M D

### Project Requirements ###
* User Roles: 
	- **Buyer:** Users who can search, browse, and purchase books. 
	- **Seller:** Users who can manage book inventory, upload new books, and track sales.
	- **Admin:** Users with administrative privileges to add or remove sellers from the system. 
* Book Management: 
	- Sellers should be able to upload PDF versions of books to the 
system. 
	- Buyers should be able to download purchased books in PDF format.
	- Sellers should have the ability to manage their book inventory. 
* Payment System: 
	- Implement a mock-up payment system for buyers to purchase 
books. 
	- Keep track of transactions and orders. 
* Book Search and Browse: 
	- Buyers should be able to search for books based on various search criteria, including title, author, genre, etc. 
	- Buyers should be able to browse books by different categories.

### Project ###
* Developed in Java Spring Boot with HTML and JS for front-end integration.
* DB - MySql

* Sign up the User as Admin/Buyer/Seller
* Login as an Admin User
	- Admin can see the seller details.
 	- Can verify/remove/add the seller.
	- Can see the Book Inventory

* Login as an Seller
	- Seller can upload the book
 	- Can see the Book Inventory of the books he/she uploaded.
	- Can update the price of the books

* Login as a Buyer
	- Buyer can buy any number of books at a time
 	- Books will be added to the cart and can be paid via three modes of payments
	- File will be downloaded once the payment succeeds 

### Design Patterns Used ###
Module  | Design Pattern
------------- | -------------
Book Search by different criteria  | Strategy + Factory Pattern
Processing the order  | Chain of Responsibility
Notify seller and admin if any order is placed | Observer Pattern

### Run Project ###
Right-click on Project -> Run as -> Java Application -> Select "OnlineLibraryApplication"

