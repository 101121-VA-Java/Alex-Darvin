function getUserProfile() {
    document.getElementById("error-div").innerHTML = "";
    let xhr = new XMLHttpRequest();
    xhr.open(
        "GET",
        `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`
    );
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            let user = xhr.response;
            user = JSON.parse(user);
            document.getElementById("firstName").innerHTML = user.firstName;
            document.getElementById("lastName").innerHTML = user.lastName;
            document.getElementById("username").innerHTML = user.username;
            // document.getElementById("password").innerHTML = user.password;
            document.getElementById("email").innerHTML = user.email;
            document.getElementById("role").innerHTML = user.role.role;
        } else if (xhr.readyState === 4) {
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML =
                "Unable to find User Profile.";
        }
    };
    xhr.send();
}

function updateUserProfile() {
    document.getElementById("error-div").innerHTML = "";
    let xhr = new XMLHttpRequest();
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let role = document.getElementById("role").value;
    if (role === "Finance Manager(FM)") {
        role = 2;
    } else {
        role = 1;
    }
    let updatedEmployee = {
        firstName,
        lastName,
        username,
        password,
        email,
        role,
    };
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            let x = sessionStorage.token;
            if (role === 2) {
                sessionStorage.setItem("token", x.split(":")[0] + ":" + "2");
            } else if (role === 1) {
                sessionStorage.setItem("token", x.split(":")[0] + ":" + "1");
            }
            document.getElementById("error-div").innerHTML = "Update successfull.";
        } else {
            document.getElementById("error-div").innerHTML = "Update failed.";
        }
    };
    xhr.open(
        "PUT",
        `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`
    );
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.send(JSON.stringify(updatedEmployee));
}

function getAllUsers() {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/users`);
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
        let users = xhr.response;
        users = JSON.parse(users);
        printUsers(users);
      } else if (xhr.readyState === 4) {
        // provide user with feedback of failure to login
        document.getElementById("error-div").innerHTML =
          "Unable to find User Profile.";
      }
    };
    xhr.send();
}

function printUsers(users) {
    while (tableBody.firstChild) {
      tableBody.removeChild(tableBody.firstChild);
    }
    users.forEach((row) => {
      const tr = document.createElement("tr");
      // tr.textContent = row.id;
      const td1 = document.createElement("td");
      const td2 = document.createElement("td");
      const td3 = document.createElement("td");
      const td4 = document.createElement("td");
      const td5 = document.createElement("td");
      td1.textContent = row.id;
      tr.appendChild(td1);
      td2.textContent = row.firstName + " " + row.lastName;
      tr.appendChild(td2);
      td3.textContent = row.username;
      tr.appendChild(td3);
      td4.textContent = row.email;
      tr.appendChild(td4);
      td5.textContent = row.role.role;
      tr.appendChild(td5);
      tableBody.appendChild(tr);
    });
  }

function logout() {
    sessionStorage.clear();
    window.location.href = "../login.html";
}

function redirect() {
    let authToken = sessionStorage.getItem("token");
    if (authToken.split(":")[1] === "1") {
      window.location.href = "../employee_dash.html";
    } else {
      window.location.href = "../Manager/manager_dash.html";
    }
}