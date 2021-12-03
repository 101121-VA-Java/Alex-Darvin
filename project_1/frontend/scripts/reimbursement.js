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

    let xhr = new XMLHttpRequest();

    xhr.open("POST", "http://localhost:8080/reimbursements");
    // xhr.open("POST", `http://localhost:8080/users/${sessionStorage.token.split(":")[0]}`);

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            document.getElementById("error-div").innerHTML =
                "You have succesfully posted a claim please wait for approval!";
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

function getAllPendingByEmployee() {
    console.log(sessionStorage.token);
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

function getAllRequestsFromAuthor() {
    clearAll();
    let xhr = new XMLHttpRequest();
    xhr.open(
        "GET",
        `http://localhost:8080/reimbursements/status/0?author_id=${sessionStorage.token.split(":")[0]}`
    );
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            let reims = xhr.response;
            reims = JSON.parse(reims);
            printEmployeeReims(reims);
        } else if (xhr.readyState === 4) {
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML = "Unable to find Reimbursements.";
        }
    };
    xhr.send();
}

function getAllPending() {
    clearAll();
    let xhr = new XMLHttpRequest();
    xhr.open("GET", `http://localhost:8080/reimbursements/status/1`);

    xhr.setRequestHeader("Authorization", `Bearer ${sessionStorage.token}`);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
            let reims = xhr.response;
            reims = JSON.parse(reims);
            printReims(reims);
        } else if (xhr.readyState === 4) {
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML = "Unable to find Reimbursements.";
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
            printResolved(reims);
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
            printResolved(reims);
        } else if (xhr.readyState === 4) {
            // provide user with feedback of failure to login
            document.getElementById("error-div").innerHTML =
                "Unable to find Reimbursements.";
        }
    };
    xhr.send();
}

function getAllPendingByStatusAndAuthor() {
    let xhr = new XMLHttpRequest();
    xhr.open(
        "GET",
        `http://localhost:8080/reimbursements/status/1?author_id= ${sessionStorage.token.split(":")[0]}`
    );
}

function printEmployeeReims(reims) {
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
        tr.setAttribute("id",row.reimId);
        tr.setAttribute("onclick","javascript:selectRemib('"+row.reimId+"','"+row.reimAmount+"','"+row.submit+"','"+row.descrip+"','"+row.author.id+"','"+row.status.status+"','"+row.type.type+"')");
        tableBody.appendChild(tr);
    });
}

function printReims(reims) {
    reims.forEach((row) => {
        const tr = document.createElement("tr");
        const td1 = document.createElement("td");
        const td2 = document.createElement("td");
        const td3 = document.createElement("td");
        // const td4 = document.createElement("td");
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
        // td4.textContent = row.resolve;
        // tr.appendChild(td4);
        td5.textContent = row.descrip;
        tr.appendChild(td5);
        td6.textContent = row.author.username;
        tr.appendChild(td6);
        td7.textContent = row.resolver.username;
        tr.appendChild(td7);
        td8.textContent = row.type.type;
        tr.appendChild(td8);
        td9.textContent = row.status.status;
        tr.appendChild(td9);
        tr.setAttribute("id",row.reimId);
        tr.setAttribute("onclick","javascript:selectRemib('"+row.reimId+"','"+row.reimAmount+"','"+row.submit+"','"+row.descrip+"','"+row.author.id+"','"+row.status.status+"','"+row.type.type+"')");
        tableBody.appendChild(tr);
    });
}

function printResolved(reims) {
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
        td4.textContent = timeStampFix(row.resolve);
        tr.appendChild(td4);
        td5.textContent = row.descrip;
        tr.appendChild(td5);
        td6.textContent = row.author.username;
        tr.appendChild(td6);
        td7.textContent = row.resolver.username;
        tr.appendChild(td7);
        td8.textContent = row.type.type;
        tr.appendChild(td8);
        td9.textContent = row.status.status;
        tr.appendChild(td9);
        tr.setAttribute("id",row.reimId);
        tr.setAttribute("onclick","javascript:selectRemib('"+row.reimId+"','"+row.reimAmount+"','"+row.submit+"','"+row.descrip+"','"+row.author.id+"','"+row.status.status+"','"+row.type.type+"')");
        tableBody.appendChild(tr);
    });
}

function printPending(reims) {
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

function selectRemib(id,amount,submitted,description,author,status,type){
       document.getElementById("amount").value=amount; 
       document.getElementById("submitted").value=submitted;
       document.getElementById("description").value=description;
       document.getElementById("author").value=author;
       document.getElementById("status").value=status;
       document.getElementById("type").value=type;
       document.getElementById("reimId").value=id;
       document.getElementById(id).style.backgroundColor="blue";
}

function updateRequests(vstatus) {
    document.getElementById("error-div").innerHTML = "";
    let xhr = new XMLHttpRequest();
    let reimId = document.getElementById("reimId").value;
    let reimAmount = document.getElementById("amount").value;
    let submit = document.getElementById("submitted").value;
    let descrip = document.getElementById("description").value;
    let author = document.getElementById("author").value;
    let status = vstatus;
    if (status === "Approved") {
      status = 2;
    } else {
      status = 3;
    }
    let type = document.getElementById("type").value;
    let vtype=document.getElementById("type").value;
    if (type === "lodging") {
      type = 1;
    } else if (type === "travel") {
      type = 2;
    } else if (type === "food") {
      type = 3;
    } else {
      type = 4;
    }
    let resolve=Date.now();
    let updatedReim = {
      //amount,
      submit,
      resolve,
      reimAmount, 
      descrip,
      author:{id:author},
      resolver:{id:author},
      status:{id:status,status:vstatus},
      type: {id: type, type: vtype} 
    };
    console.log('updatedReim'+updatedReim);
    /*
    ps.setDouble(1, rs.getReimAmount());
			ps.setTimestamp(2, rs.getSubmit());
			ps.setTimestamp(3, rs.getResolve());
			ps.setString(4, rs.getDescrip());
			ps.setInt(5, rs.getAuthor().getId());
			ps.setInt(6, rs.getResolver().getId());
			ps.setInt(7, rs.getStatus().getId());
			ps.setInt(8, rs.getType().getId());
			ps.setInt(9, rs.getReimId());
    */
    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4 && xhr.status >= 200 && xhr.status < 300) {
        document.getElementById("error-div").innerHTML = "Update successfull.";
      } else {
        document.getElementById("error-div").innerHTML = "Update failed.";
      }
    };
    console.log(sessionStorage);
    xhr.open("PUT", `http://localhost:8080/reimbursements/`+reimId);
    xhr.setRequestHeader("Authorization", sessionStorage.token);
    xhr.send(JSON.stringify(updatedReim));
}

function filterEmployeeRequests() {
    // addEventListener('onclick') clear table, only show requests from a single author

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

function logout() {
    sessionStorage.clear();
    window.location.href = "../login.html";
}