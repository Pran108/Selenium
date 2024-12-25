# Project Title: Selenium Automation with Java, TestNG, and Maven

## Description
This project automates various functionalities of an e-commerce website (https://www.printemps.com/fr/fr) using **Selenium WebDriver** with **Java**, **Maven**, and **TestNG**. The project includes test cases for login, registration, product search, wishlisting, adding products to the cart.  
The tests use different data sources such as **JSON**, **HashMap**, **SQL**, and **arrays**, and they are organized with **TestNG DataProvider** for **data-driven testing**.

## Table of Contents
- [Project Overview](#project-overview)
- [Technologies Used](#technologies-used)
- [Test Cases](#test-cases)
  - [1. Login Test Case](#1-login-test-case)
  - [2. Registration Test Case](#2-registration-test-case)
  - [3. Product Search and Filter Test Case](#3-product-search-and-filter-test-case)
  - [4. Wishlist Test Case](#4-wishlist-test-case)
  - [5. Cart and Checkout Test Case](#5-cart-and-checkout-test-case)

## Project Overview
This project simulates user interactions on an e-commerce website, performing the following tasks:
1. **Login:** Automated login to the website using user credentials fetched from **JSON, HashMap, and arrays**.
2. **Registration:** Automates user registration using data fetched from an **SQL database**.
3. **Search and Filters:** Automates product search. It applies various filters based on criteria like price, category, brand, etc., and applies sorting.
4. **Wishlist:** Adds products to the wishlist and displays wishlist details. Later, checks whether the products that were wishlisted are added to the page.
5. **Cart and Checkout:** Adds products to the cart and validates the products added to the cart.

## Technologies Used
- **Java:** Programming language for implementing the test cases.
- **Selenium WebDriver:** For automating web interactions in browsers.
- **Maven:** For project management, dependency management, and building.
- **TestNG:** For structuring and running the tests, along with **DataProvider** for data-driven tests.
- **MySQL:** For fetching data used in the registration test case.
- **JSON:** For providing login test data.
- **HashMap and Arrays:** For providing login test data.

## Test Cases 

### 1. Login Test Case
**Description:** This test case verifies the login functionality using various credentials.  
- **Data Source:**  
  - Data is provided using **TestNG DataProvider**.  
  - Test data is fetched from **JSON**, **HashMap**, and **arrays**.
- **Test Flow:**
  - Open the login page.
  - Enter the username and password.
  - Validate if login is successful by verifying the presence of user-specific elements (e.g., name of the user) and URL.

### 2. Registration Test Case
**Description:** This test case automates the user registration process using data extracted from an SQL database.  
- **Data Source:**  
  - Registration details are fetched from an **SQL database** (MySQL).
- **Test Flow:**
  - Open the registration page.
  - Fill in the required fields using the extracted data (e.g., username, password, email).
  - Submit the form.
  - Verify if the user is registered successfully by checking for a success message or redirection to the login page.

### 3. Product Search and Filter Test Case
**Description:** This test case tests the product search functionality and the application of various filters (e.g., price, category).  
- **Test Flow:**
  - Open the homepage.
  - Search for a product (e.g., "shirt")—it is not hardcoded.
  - Apply filters such as price range, category, brand.
  - Apply sorting based on ascending prices.
  - Verify if the products shown match the selected filters.

### 4. Wishlist Test Case
**Description:** This test case checks the functionality of adding products to the wishlist and displaying them.  
- **Test Flow:**
  - Go to the **PLP** (Product Listing Page) and add multiple products to the wishlist.
  - Navigate to the wishlist page.
  - Validate that the product is present in the wishlist and the correct details (e.g., product name, price) are displayed.

### 5. Cart and Checkout Test Case
**Description:** This test case verifies the functionality of adding products to the cart and attempting the checkout process.  
- **Test Flow:**
  - Add products to the shopping cart from the **PLP** (Product Listing Page).
  - Fetch the product names.
  - Add products to the shopping cart from the **PDP** (Product Detail Page).
  - Go to the cart page and check if the correct products are added.

