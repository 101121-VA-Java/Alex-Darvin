// retrieving token from session storage if it exists
let token = sessionStorage.getItem("token");

// if no token is present, redirect to the login page
if (!token) {
   window.location.href = "views/login.html";
}

// targets logout button
document.getElementById('logout-button').addEventListener('click', logout);

function logout() {
   sessionStorage.clear();
   window.location.href = "login.html";
}

function redirect() {
   let authToken = sessionStorage.getItem("token");
   if (authToken.split(":")[1] === "1") {
      window.location.href = "employee_dash.html";
   } else {
      window.location.href = "manager_dash.html";
   }
}