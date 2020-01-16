function pageLoad() {

    let now = new Date();

    let myHTML = '<div style="text-align:left;">'
        + '<h1>Suggestions</h1>'
        + '<h2>'
        + '<a href="index.html"><button>Home</button></a>'
        + '<a href="info.html"><button>Info</button></a>'
        + '<a href="quiz.html"><button>Quiz</button></a>'
        + '</h2>'
        + '</div>';




    document.getElementById("testDiv").innerHTML = myHTML;

}

