


function dochange(){
    alert('clicked buttom')
}

function myConfirmFunction(){
    var text;
    var r = confirm("Press a button!\nEither OK or Cancel.");
    if (r == true) {
        alert('You pressed OK!');
    }
    else {
        alert('Are you sure you want to cancel?');
    }
}

function changecolor(){
    var idd1 = document.getElementById('d1');
    var idd2 = document.getElementById('d2');
    idd1.className = "blueback";
    idd2.className = "yellowback";
}

function changeText(){
    var idd1 = document.getElementById('d1');
    var idd2 = document.getElementById('d2');
    idd1.innerHTML = "Good afternoon";
    idd2.innerHTML = "Good night";
}

function doLime(){
    var idd3 = document.getElementById('d3');
    idd3.style.backgroundColor = "lime";
}

function doYellow(){
    var idd3 = document.getElementById("d3");
    var ctext = idd3.getContext("2d");
    ctext.fillStyle = "yellow";
    ctext.fillRect(10,10,40,40);  
    ctext.fillRect(60,10,40,40);  
    // ctext.fillRect(x,y,40,40); 
    ctext.fillStyle = "black";
    ctext.font = "30px Arial"
    ctext.fillText("Hello", 10, 80)
}

function doPink(){
    var id = document.getElementById('d4');
    id.style.backgroundColor = "pink";
    var ctext = id.getContext("2d");
    ctext.fillStyle = "yellow";
    ctext.fillRect(10,10,60,60);
    ctext.fillRect(80,10,60,60);  
    ctext.fillStyle = "black";
    ctext.font = "20px Arial"
    ctext.fillText("Hello", 15, 45);
}

function doBlue(){
    var id = document.getElementById('d5');
    id.style.backgroundColor = "blue";
    var ctext = id.getContext("2d");

}

function clearContext(){
    var id = document.getElementById('d4');
    var ctext = id.getContext("2d");
    id.style.backgroundColor = "orange";
    ctext.clearRect(0,0,id.width,id.height);
}