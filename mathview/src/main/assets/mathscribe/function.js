function setText(text) {
    $("#text").html(text);
    var script = document.createElement('script');
    script.src = 'jqmath-etc-0.4.6.min.js';
    document.head.appendChild(script);
}

function setTextSize(fontSize) {
    $("#text").css("font-size", fontSize + "px");
}

function setTextColor(colorCode) {
    $("#text").css("color", colorCode);
}