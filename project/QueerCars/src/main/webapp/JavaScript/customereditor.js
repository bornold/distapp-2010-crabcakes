/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#customerSubmitButton").click(saveButtonClicked);
});
$(document).ready(loadCustomers);

function saveButtonClicked(){
    alert("hello.")
}

function loadCustomers(){
    $("#customerlistarea").load("AdminServlet", {action: "getCustomerTable"})
}
