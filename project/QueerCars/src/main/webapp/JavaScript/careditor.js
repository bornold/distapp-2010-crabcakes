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
    newModelId = $("#newModelId").val();
    modelEmission = $("#modelEmission").val();
    modelFuelConsumption = $("#modelFuelConsumption").val();
    $("#carlistarea").load("AdminServlet", {
        action: "saveCar",
        carId: carId,
        carModel: carModel,
        newModelId: newModelId,
        modelEmission: modelEmission,
        modelFuelConsumption: modelFuelConsumption
    },refreshTableButtonListeners);
    $("#carId").val("");
    $("#newModelId").val("");
    $("#modelEmission").val("");
    $("#modelFuelConsumption").val("");
}

function loadCars(){
    $("#carModel").load("AdminServlet", {
        action: "getModels"
    },setNewModelListener);
    $("#carlistarea").load("AdminServlet", {
        action: "getCarTable"
    },refreshTableButtonListeners);
}

function refreshTableButtonListeners(){
    $(".editButton").click(function() {
        editId = $(this).parent().parent().find("td:eq(0)").html();
        editModel = $(this).parent().parent().find("td:eq(1)").html();
        $("#carId").val(editId);
        $("#carModel").val(editModel);
        $("#newModelId").val("");
        $("#modelEmission").val("");
        $("#modelFuelConsumption").val("");
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

function setNewModelListener(){
    $("#newModelSelection").click(function(){
        $("#newModelInfo").css("display", "block");
    });
    $(".existingModel").click(function(){
        $("#newModelInfo").css("display", "none");
        $("#newModelId").val("");
        $("#modelEmission").val("");
        $("#modelFuelConsumption").val("");
    });
}