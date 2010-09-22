/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
    $("#fruits_dropdown").load("ihtml/dropdown_fruits.ihtml");
    $("#nuts_dropdown").load("ihtml/dropdown_nuts.ihtml");
    $("#vegetables_dropdown").load("ihtml/dropdown_vegetables.ihtml");
    $("#apple").live("click",appleClicked);
    $("#banana").live("click",bananaClicked);
    $("#cashew").live("click",cashewClicked);
    $("#pecan").live("click",pecanClicked);
    $("#lettuce").live("click",lettuceClicked);
    $("#tomato").live("click",tomatoClicked);

    $("#fruitsMenu").hover(showFruits,null);
    $("#nutsMenu").hover(showNuts,null);
    $("#vegetablesMenu").hover(showVegetables,null);

    $("div#fruits_dropdown").mouseleave(hideFruits);
    $("div#nuts_dropdown").mouseleave(hideNuts);
    $("div#vegetables_dropdown").mouseleave(hideVegetables);
});

function appleClicked(){
    $("#content").load("ihtml/apple.ihtml");
}
function bananaClicked(){
    $("#content").load("ihtml/banana.ihtml");
}
function cashewClicked(){
    $("#content").load("ihtml/cashew.ihtml");
}
function pecanClicked(){
    $("#content").load("ihtml/pecan.ihtml");
}
function lettuceClicked(){
    $("#content").load("ihtml/lettuce.ihtml");
}
function tomatoClicked(){
    $("#content").load("ihtml/tomato.ihtml");
}
function showFruits(){
    hideNuts();
    hideVegetables();
    $("div#fruits_dropdown").css("position","absolute");
    $("div#fruits_dropdown").css("top","2.5em");
    $("div#fruits_dropdown").css("left","1em");
    $("div#fruits_dropdown").css("display","block");
}
function hideFruits(){
    $("div#fruits_dropdown").css("display","none");
}
function showVegetables(){
    hideFruits();
    hideNuts();
    $("div#nuts_dropdown").css("display","none");
    $("div#fruits_dropdown").css("display","none");
    $("div#vegetables_dropdown").css("position","absolute");
    $("div#vegetables_dropdown").css("top","2.5em");
    $("div#vegetables_dropdown").css("left","8em");
    $("div#vegetables_dropdown").css("display","block");
}
function hideVegetables(){
    $("div#vegetables_dropdown").css("display","none");
}
function showNuts(){
    hideFruits();
    hideVegetables();
    $("div#vegetables_dropdown").css("display","none");
    $("div#fruits_dropdown").css("display","none");
    $("div#nuts_dropdown").css("position","absolute");
    $("div#nuts_dropdown").css("top","2.5em");
    $("div#nuts_dropdown").css("left","18em");
    $("div#nuts_dropdown").css("display","block");
}
function hideNuts(){
    $("div#nuts_dropdown").css("display","none");
}