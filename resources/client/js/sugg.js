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

    let submission = '<div style="text-align:center;">'
        if (document.getElementById("sugg").value.trim() === '') {
          alert("Please provide a question.");
          return;
        }

        if (document.getElementById("ans").value.trim() === '') {
          alert("Please provide an answer.");
          return;
        }

        let sugg = document.getElementById("sugg");
        console.log(sugg);
        let ans = document.getElementById("ans");
        console.log(ans);
        let formData = new FormData(sugg, ans);

        fetch('/suggest/input', {method: 'post', body: formData}).then(response => response.json()).then(responseData => {
            if (responseData.hasOwnProperty('error')) {
                alert(responseData.error);
            } else {
                document.getElementById("sugg").style.display = 'none';
                document.getElementById("ans").style.display = 'none';
            }
        });
    document.getElementById("mid").innerHTML = submission;
}

