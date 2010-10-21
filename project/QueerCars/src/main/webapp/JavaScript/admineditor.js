/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#adminSubmitButton").click(saveButtonClicked);
});
$(document).ready(loadAdministrators);

function saveButtonClicked(){
    adminId = $("#adminId").val();
    adminName = $("#adminName").val();
    $("#adminlistarea").load("AdminServlet", {
        action: "saveAdministrator",
        adminId: adminId,
        adminName: adminName
    },refreshTableButtonListeners);
    adminId = $("#adminId").val("");
    adminName = $("#adminName").val("");
}

function loadAdministrators(){ //Gets and loads the list of admins.
    $("#adminlistarea").load("AdminServlet", {
        action: "getAdministratorTable"
    },refreshTableButtonListeners)
    
}

function refreshTableButtonListeners(){ //Sets button listeners after loading.
    $(".editButton").click(function() {
        editId = $(this).parent().parent().find("td:eq(0)").html();
        editName = $(this).parent().parent().find("td:eq(1)").html();
        $("#adminId").val(editId);
        $("#adminName").val(editName);
    });
    $(".removeButton").click(removeButtonFunction);
}

function removeButtonFunction(){
    adminId = this.id;
    $("#adminlistarea").load("AdminServlet", {
        action: "removeAdministrator",
        adminId: adminId,
        adminName: ""
    },refreshTableButtonListeners);
}