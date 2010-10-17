/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#carSubmitButton").click(saveButtonClicked);
});
$(document).ready(loadCars);

function saveButtonClicked(){
    carId = $("#carId").val();
    carModel = $("#carModel").val();
    $("#carlistarea").load("AdminServlet", {
        action: "saveCar",
        carId: carId,
        carModel: carModel
    },refreshTableButtonListeners);
    carId = $("#carId").val("");
    carName = $("#carName").val("");
}

function loadCars(){
    $("#carlistarea").load("AdminServlet", {
        action: "getCarTable"
    },refreshTableButtonListeners)

}

function refreshTableButtonListeners(){
    $(".editButton").click(function() {
        editId = $(this).parent().parent().find("td:eq(0)").html();
        editModel = $(this).parent().parent().find("td:eq(1)").html();
        $("#carId").val(editId);
        $("#carModel").val(editModel);
    });
    $(".removeButton").click(removeButtonFunction);
}

function removeButtonFunction(){
    carId = this.id;
    $("#carlistarea").load("AdminServlet", {
        action: "removeCar",
        carId: carId,
        carModel: ""
    },refreshTableButtonListeners);
}