function startTimer(){
    setInterval(displayNextImage, 3000);
}

function displayNextImage(){
    x = (x === images.length - 1) ? 0 : x+1;
    document.getElementById("img").src = "#{resource['" + images[x] +"']}" ;
}

var images = [], x=-1;
images[0] = "resources/images/search.png.xhtml";
images[1] = "images/back.png";


