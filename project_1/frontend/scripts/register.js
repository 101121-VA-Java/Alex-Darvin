function register() {
    // resetting error div
    document.getElementById("error-div").innerHTML = "";

    //retrieving user credentials
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;

    let newEmployee = { firstName, lastName, username, password, email };

    //add other stuff

    let xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/users");
    // xhr.open("POST", `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            let authToken = xhr.getResponseHeader("Authorization");

            /*
                    storing authtoken in the session storage to be retrieved in different views
                      - an item of key "token" and value authToken (Authorization token passed back from Javalin) is stored in the sessionStorage
                   */
            sessionStorage.setItem("token", authToken);

            // navigate to a different view (ie: homepage)
            // window.location.href="../htmlFiles/login.html";
            let x = sessionStorage.getItem("token");
            window.location.href = "Employee/employee_dash.html";
            console.log("You signed in as an employee!!!");
        } else if (xhr.readyState === 4) {
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML = "Unable to register.";
        }
    };
    //used for login
    // xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    let requestBody = JSON.stringify(newEmployee);

    xhr.send(requestBody);
}