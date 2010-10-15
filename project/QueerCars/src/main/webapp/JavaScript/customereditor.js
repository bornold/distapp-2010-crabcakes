/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#customerSubmitButton").click(saveButtonClicked);
});
$(document).ready(loadCustomers);

function saveButtonClicked(){
    customerId = $("#customerId").val();
    customerName = $("#customerName").val();
    $("#customerlistarea").load("AdminServlet", {
        action: "saveCustomer",
        customerId: customerId,
        customerName: customerName
    },refreshTableButtonListeners);
    customerId = $("#customerId").val("");
    customerName = $("#customerName").val("");
}

function loadCustomers(){
    $("#customerlistarea").load("AdminServlet", {
        action: "getCustomerTable"
    },refreshTableButtonListeners)
    
}

function refreshTableButtonListeners(){
    $(".editButton").click(function() {
        editId = $(this).parent().parent().find("td:eq(0)").html();
        editName = $(this).parent().parent().find("td:eq(1)").html();
        $("#customerId").val(editId);
        $("#customerName").val(editName);
    });
    $(".removeButton").click(removeButtonFunction);
}

function removeButtonFunction(){
    customerId = this.id;
    $("#customerlistarea").load("AdminServlet", {
        action: "removeCustomer",
        customerId: customerId,
        customerName: ""
    },refreshTableButtonListeners);
}