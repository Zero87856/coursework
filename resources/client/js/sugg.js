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
    document.getElementById("Top").innerHTML = myHTML;



}
function submit(){
    event.preventDefault();

    if (document.getElementById("content").value.trim() === '') {
        alert("Please provide a question.");
        return;
    }

    if (document.getElementById("ans").value.trim() === '') {
        alert("Please provide an answer.");
        return;
    }
    const id = document.getElementById("content").value;
    const form = document.getElementById("ans");
    const formData = new FormData(form);



    fetch('/suggest/input', {method: 'post', body: formData}
    ).then(response => response.json()
    ).then(responseData => {

        if (responseData.hasOwnProperty('error')) {
            alert(responseData.error);
        } else {
            document.getElementById("content").style.display = 'none';
            document.getElementById("ans").style.display = 'none';
            pageLoad();
        }
    });


}

