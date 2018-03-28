

function addFormat(format){
    var option = document.createElement("option");
    option.innerHTML = format.format;
    document.getElementById("sel1").appendChild(option);
    
}

window.onload = function(){
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function(){
        if(xhr.readyState===4 && xhr.status ===200){
            var formatList = JSON.parse(xhr.responseText);
            for(var i=0;i<formatList.length;i++){
                addFormat(formatList[i]);
            }
        }
        else{
            console.log(xhr.readyState);
            if(xhr.readyState ===4){
                console.log(xhr.status);
            }
        }
    }

    xhr.open("GET", "Request" ,true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send();

}