/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#saveButton").click(saveButtonFunction);
});

$(document).ready(loadTable());

function saveButtonFunction(){

    prodName = $("#productName").val();
    prodCategory = $("#productCategory").val();
    prodPrice = $("#productPrice").val();
    prodId = $("#productId").val();
    if(prodId == null || prodId == "")
    {
        $.post("DatabaseServlet", {
            action: "saveNew",
            productName: prodName,
            productCategory: prodCategory,
            productPrice: prodPrice
        },function(data){refreshTable(data);});
    }
    else
    {
        $.post("DatabaseServlet", {
            action: "update",
            productName: prodName,
            productCategory: prodCategory,
            productPrice: prodPrice,
            productId: prodId
        },function(data){refreshTable(data);});
    }
}

function removeButtonFunction(){
    $.post("DatabaseServlet", {action: "remove", productId: this.id}, function(data){refreshTable(data);});
}

function loadTable(){
    $.post("DatabaseServlet", {action: "getAll"},function(data){refreshTable(data);});
}

function refreshTable(data){
    $("#tableContents").empty();
    $("#tableContents").append(data);
    $(".editButton").click(function() {
        editId = $(this).parent().parent().find("td:eq(0)").html();
        editName = $(this).parent().parent().find("td:eq(1)").html();
        editCategory = $(this).parent().parent().find("td:eq(2)").html();
        editPrice = $(this).parent().parent().find("td:eq(3)").html();
        $("#productId").val(editId);
        $("#productName").val(editName);
        $("#productCategory").val(editCategory);
        $("#productPrice").val(editPrice);
    });
    $(".deleteButton").click(removeButtonFunction);
}