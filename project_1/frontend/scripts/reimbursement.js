function newRequest() {
    // resetting error div
    document.getElementById("error-div").innerHTML = "";

    //retrieving user credentials
    let reimAmount = Number(document.getElementById("reimAmount").value);
    let descrip = document.getElementById("descrip").value;
    let type = document.getElementById("type").value;
    if (type === "Lodging") {
        type = 1;
    } else if (type === "Travel") {
        type = 2;
    } else if (type === "Food") {
        type = 3;
    } else {
        type = 4;
    }

    let newReim = { reimAmount, descrip, type: {id: type, type: ''} };

    //add other stuff

    let xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/reimbursements");
    // xhr.open("POST", `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            document.getElementById("error-div").innerHTML =
                "You have succesfully posted a claim please waight for approval!";
        } else if (xhr.readyState === 4) {
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML =
                "Unable to make a new claim.";
        }
    };
    let requestBody = JSON.stringify(newReim);
    //set this to send in the token make sure it is after the XMLHttpRequest
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.send(requestBody);
}

function clearAll() {
    tableBody.innerHTML = '';
}
function getAllPending() {
    clearAll();
    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/reimbursements/status/1`);

    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            let reims = xhr.response;
            reims = JSON.parse(reims);
            printReims(reims);
        } else if (xhr.readyState === 4) {
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML =
                "Unable to find Reimbursements.";
        }
    };
    xhr.send();
}

function getAllDenied() {
    clearAll();
    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/reimbursements/status/3`);

    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            let reims = xhr.response;
            reims = JSON.parse(reims);
            printReims(reims);
        } else if (xhr.readyState === 4) {
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML =
                "Unable to find Reimbursements.";
        }
    };
    xhr.send();
}

function getAllApproved() {
    clearAll();
    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/reimbursements/status/2`);

    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            let reims = xhr.response;
            reims = JSON.parse(reims);
            printReims(reims);
        } else if (xhr.readyState === 4) {
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML =
                "Unable to find Reimbursements.";
        }
    };
    xhr.send();
}

function getAllRequestsByStatusAndAuthorP() {
    let xhr = new XMLHttpRequest();
    xhr.open(
        "GET",
        `http://localhost:8080/reimbursements/status/1?author_id= ${sessionStorage.token.split(":")[0]}`
    );
}

function printReims(reims) {
    reims.forEach((row) => {
        const tr = document.createElement("tr");
        const td1 = document.createElement("td");
        const td2 = document.createElement("td");
        const td3 = document.createElement("td");
        const td4 = document.createElement("td");
        const td5 = document.createElement("td");
        const td6 = document.createElement("td");
        const td7 = document.createElement("td");
        const td8 = document.createElement("td");
        const td9 = document.createElement("td");

        td1.textContent = row.reimId;
        tr.appendChild(td1);
        td2.textContent = row.reimAmount;
        tr.appendChild(td2);
        td3.textContent = timeStampFix(row.submit);
        tr.appendChild(td3);
        td4.textContent = row.resolve;
        tr.appendChild(td4);
        td5.textContent = row.descrip;
        tr.appendChild(td5);
        td6.textContent = row.author.username;
        tr.appendChild(td6);
        td7.textContent = row.resolver.username;
        tr.appendChild(td7);
        td8.textContent = row.status.status;
        tr.appendChild(td8);
        td9.textContent = row.type.type;
        tr.appendChild(td9);
        tableBody.appendChild(tr);
    });
}

function printPReims(reims) {
    reims.forEach((row) => {
        const tr = document.createElement("tr");
        const td1 = document.createElement("td");
        const td2 = document.createElement("td");
        const td3 = document.createElement("td");
        const td4 = document.createElement("td");
        const td5 = document.createElement("td");
        const td6 = document.createElement("td");
        const td7 = document.createElement("td");

        td1.textContent = row.reimId;
        tr.appendChild(td1);
        td2.textContent = row.reimAmount;
        tr.appendChild(td2);
        td3.textContent = timeStampFix(row.submit);
        tr.appendChild(td3);
        td4.textContent = row.descrip;
        tr.appendChild(td4);
        td5.textContent = row.author.username;
        tr.appendChild(td5);
        td6.textContent = row.status.status;
        tr.appendChild(td6);
        td7.textContent = row.type.type;
        tr.appendChild(td7);
        $('<td>Select: <input type="checkbox" /></td>').appendTo(tr);
        tableBody.appendChild(tr);
    });
}

function timeStampFix(time) {
    if (time != null) {
      var d = new Date(time);
      var formattedDate =
        d.getMonth() + 1 + "/" + d.getDate() + "/" + d.getFullYear();
      var hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      var minutes = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      var formattedTime = hours + ":" + minutes;
      formattedDate = formattedDate + " " + formattedTime;
    }
    return formattedDate;
}