$(document).ready(function(){
    $("#content").hover(doDarken, undoDarken);
});

function doDarken(){
    foo = RGB($("#content").css("backgroundColor"));
    foo.subRGB(10, 10, 10);
    $("#content").css("backgroundColor",foo.getHex());
}
function undoDarken(){
    foo = RGB($("#content").css("backgroundColor"));
    foo.addRGB(10, 10, 10);
    $("#content").css("backgroundColor",foo.getHex());
}