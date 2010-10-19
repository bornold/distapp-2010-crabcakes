/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#clubText").load("html_res/clubText.ihtml");
});
$(document).ready(function(){
    $("#serviceText").load("html_res/serviceText.ihtml");
});
$(document).ready(function(){
    $("#termsText").load("html_res/termsText.ihtml");
});
$(document).ready(function(){
    $("#carsTable").load("html_res/carsText.ihtml", loadCarInfo);
});
$(document).ready(function(){
    $("#newsFeed").load("InformationServlet", {action: "getNewsFeed"});
});

function loadCarInfo(){
    $("#carsTable").load("InformationServlet", {
        action: "getDetailedCarTable"
    },refreshTableButtonListeners);
}

function refreshTableButtonListeners(){
    $(".showButton").click(function() {
        model = this.id;
        $("#carInfo").load("html_res/modelText_" + model + ".ihtml", scrollBack);
    });
    $(".rentButton").click(function(){
        alert("nothing here yet.");
    });
}
function scrollBack()
{
    window.scrollTo(0,document.body.scrollHeight);
}
