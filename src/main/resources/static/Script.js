document.addEventListener("DOMContentLoaded", function () {
    const loginBtn = document.getElementById("login-btn");
    const signupBtn = document.getElementById("signup-btn");
    const loginForm = document.getElementById("login-form");
    const signupForm = document.getElementById("signup-form");

    loginBtn.addEventListener("click", function () {
        loginForm.style.display = "block";
        signupForm.style.display = "none";
    });

    signupBtn.addEventListener("click", function () {
        loginForm.style.display = "none";
        signupForm.style.display = "block";
    });

    // Initially, hide the signup form
    signupForm.style.display = "none";
});
