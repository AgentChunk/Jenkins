"use strict";

function addForm(rf){
    var row = document.createElement("tr");

    var elements = [
        rf.eventName,
        rf.eventType,
        new Date(rf.eventDate),
        rf.eventLocation,
        rf.cost,
        rf.gradeId,
        rf.formDate,
        rf.grade,
        rf.supApproval,
        rf.dHeadApproval,
        rf.bcoApproval,
        rf.approvedAmount,
        rf.reason
    ];

    for(var i=0;i<elements.length;i++){
        var cell = document.createElement("td");
        var node = document.createTextNode(elements[i]);
        cell.appendChild(node);
        row.appendChild(cell);
    }
    var table = document.getElementById("applied");
    table.appendChild(row);
}

function addFormToApprove(rf){
    var row = document.createElement("tr");

    var elements = [
        rf.requester,
        rf.eventName,
        rf.eventType,
        new Date(rf.eventDate),
        rf.eventLocation,
        rf.cost,
        rf.gradeId,
        rf.formDate,
        rf.grade,
        rf.supApproval,
        rf.dHeadApproval,
        rf.bcoApproval,
        rf.approvedAmount,
        rf.reason
    ];
    //add checkbox before other items
    var boxcell = document.createElement("td");
    var box = document.createElement("input");
    box.setAttribute("type","checkbox");
    box.setAttribute("name",rf.requester);
    boxcell.appendChild(box);
    row.appendChild(boxcell);

    for(var i=0;i<elements.length;i++){
        var cell = document.createElement("td");
        var node = document.createTextNode(elements[i]);
        cell.appendChild(node);
        row.appendChild(cell);
    }
    var table = document.getElementById("toApproveTable");
    table.appendChild(row);

}

function createToApproveTable(rfList){
    var header = document.createElement("h1");
    header.innerHTML="Applications to Approve or Deny";
    document.getElementById("toApprove").appendChild(header);
    var table = document.createElement("table");
    table.setAttribute("id","toApproveTable");
    var row = document.createElement("tr");
    
    var columnHeader = [
        "&nbsp;",
        "Employee Name",
        "Event Name",
        "Event Type",
        "Event Date",
        "Event Location",
        "Event Cost",
        "Grade Format",
        "Form Date",
        "Grade",
        "Supervisor Approval",
        "Department Head Approval",
        "Benefits Coordinator Approval",
        "Approved Amount",
        "Work Related Justification"
    ]
   
    for(var i=0;i<columnHeader.length;i++){
        var th = document.createElement("th");
        th.innerHTML=columnHeader[i];
        row.appendChild(th);
    }
    document.getElementById("toApprove").appendChild(table);
    table.appendChild(row);

    for(var i=0;i<rfList.length;i++){
        addFormToApprove(rfList[i]);
    }
    
    var input = document.createElement("input");
    input.setAttribute("class","btn btn-primary");
    input.setAttribute("type","submit");
    input.setAttribute("value","Approve or Deny");
    document.getElementById("toApprove").appendChild(input);
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

window.onload = function(){
    var nameElement = document.getElementById("employeeName");
    
    
    //Step 1.
    var xhr = new XMLHttpRequest();

    //Step 2. function to handle ready state change of the response
    xhr.onreadystatechange = function(){
        if(xhr.readyState===4 && xhr.status ===200){
            var rfList = JSON.parse(xhr.responseText);
            nameElement.innerHTML=rfList[0];
            for(var i=0;i<rfList[1].length;i++){
                addForm(rfList[1][i]);
            }
            if(rfList[2].length>0){
                createToApproveTable(rfList[2]);
            }
        }
        else{
            console.log(xhr.readyState);
            if(xhr.readyState ===4){
                console.log(xhr.status);
            }
        }
    }
    xhr.open("GET", "RetrieveInfo" ,true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send();
}