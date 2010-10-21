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
    $("#newsFeed").load("CustomerServlet", {
        action: "getNewsFeed"
    });
});

function loadCarInfo(){
    $("#carsTable").load("CustomerServlet", {
        action: "getDetailedCarTable"
    },refreshTableButtonListeners);
}
function refreshTableButtonListeners(){
    $(".showButton").click(function() {
        model = this.id;
        $("#carInfo").load("html_res/modelText_" + model + ".ihtml", scrollBack);
    });
    $(".rentButton").click(function()
    {
        carId = this.id;
        $.post('CustomerServlet', {
            action: "isLoggedIn"
        },
        function(data) {
            loggedin = data.toString();
            doRental(carId,loggedin);
        })
    });
}
function scrollBack()
{
    window.scrollTo(0,document.body.scrollHeight);
}

function doRental(carId,loggedin){
    if (loggedin.length == 5) { //Is 5 = true, 6 = false, ugly hack.
        var answer=confirm('Do you really want to rent this car?');
        if (answer)
        {
            alert("You have rented " + carId + ". Have a look at Services to see where you can pick it up. \n An informative email has been sent to your registered email-address.");
            $("#carInfo").load("CustomerServlet", {
                action: "doRental",
                carId: carId
            },loadCarInfo);
        }
    }
    else {
        alert("Please login to rent a wreck")
    }
}