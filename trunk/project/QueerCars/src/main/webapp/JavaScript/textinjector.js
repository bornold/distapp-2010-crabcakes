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
    $("#carsText").load("html_res/carsText.ihtml", loadCarInfo);
});
function loadCarInfo(){
    $("#carsInfo").load("InformationServlet", {
        action: "getDetailedCarTable"
    });
}