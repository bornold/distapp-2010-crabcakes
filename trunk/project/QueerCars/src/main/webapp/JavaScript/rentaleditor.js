/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(loadRentals);
$(document).ready(function(){
    $("#endRentalButton").click(endRentalButtonClicked);
});

function loadRentals(){
    $("#rentallistarea").load("AdminServlet", {action: "getActiveRentalsList"},bindButtons);
}

function bindButtons(){
    $(".endButton").click(function(){
        rentalId = this.id;
        carId = $(this).parent().parent().find("td:eq(1)").html();
        customerId = $(this).parent().parent().find("td:eq(2)").html();
        $("#rentalId").val(rentalId);
        $("#carId").val(carId);
        $("#customerId").val(customerId);
    });
}

function endRentalButtonClicked(){
    odometerValue = $("#odometerValue").val();
    if(odometerValue.length > 0 && rentalId.length > 0){
        $("#rentallistarea").load("AdminServlet", {
            action: "endRental",
            rentalId: rentalId,
            odometerValue: odometerValue
        },bindButtons);
    } else {
        alert("you must enter proper values.")
    }
}

