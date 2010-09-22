/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#unobtrusiveButton").click(goNuts);
});

function goNuts(){
    origColor = $("#content").css("backgroundColor");
    $("#content").animate({backgroundColor: '#FFFFFF'});
    $("#content").animate({backgroundColor: origColor});
}