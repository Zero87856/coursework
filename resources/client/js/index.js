function pageLoad() {

    let now = new Date();

    let myHTML = '<div style="text-align:left;">'
        + '<h1>Zero</h1>'
        + '</div>'
        + '</div>';

    let myHTML2 = '<div style="text-align:right;">'
        +'<body>'
        +'Generated at ' + now.toLocaleTimeString();


    document.getElementById("testDiv").innerHTML = myHTML;

}

