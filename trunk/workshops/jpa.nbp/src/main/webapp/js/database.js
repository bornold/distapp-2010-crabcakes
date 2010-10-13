/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){$("#saveButton").click(saveButtonFunction);});
$(document).ready(function(){$("#editButton").click(editButtonFunction);});
$(document).ready(function(){$("#deleteButton").click(deleteButtonFunction);});

function saveButtonFunction(){
    prodName = $("#productName").val();
    prodCategory = $("#productCategory").val();
    prodPrice = $("#productPrice").val();
    prodId = $("#productId").val();
    xmlhttp = new XMLHttpRequest();
}

function editButtonFunction(){
    
}

function deleteButtonFunction(){
    
}