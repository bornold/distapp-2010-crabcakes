/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#newsSubmitButton").click(saveButtonClicked);
});
$(document).ready(loadNewsItems);

function saveButtonClicked(){
    newsId = $("#newsId").val();
    newsHeadline = $("#newsHeadline").val();
    newsContent = $("#newsContent").val();
    $("#newslistarea").load("AdminServlet", {
        action: "saveNewsItem",
        newsId: newsId,
        newsHeadline: newsHeadline,
        newsContent: newsContent
    },refreshTableButtonListeners);
    newsId = $("#newsId").val("");
    newsHeadline = $("#newsHeadline").val("");
    newsContent = $("#newsContent").val("");
}

function loadNewsItems(){
    $("#newslistarea").load("AdminServlet", {
        action: "getNewsItems"
    },refreshTableButtonListeners)

}

function refreshTableButtonListeners(){
    $(".editButton").click(function() {
        editId = this.id;
        editHeadline = $(this).parent().find("h3:eq(0)").html();
        editContent = $(this).parent().find("p:eq(0)").html();
        $("#newsId").val(editId);
        $("#newsHeadline").val(editHeadline);
        $("#newsContent").val(editContent);
    });
    $(".removeButton").click(removeButtonFunction);
}

function removeButtonFunction(){
    newsId = this.id;
    $("#newslistarea").load("AdminServlet", {
        action: "removeNewsItem",
        newsId: newsId
    },refreshTableButtonListeners);
}/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


