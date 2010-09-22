$(document).ready(function(){
    $("#topleft").hover(doLighten, undoLighten);
});

function doLighten(){
    foo = RGB($("#topleft").css("backgroundColor"));
    foo.addRGB(10, 10, 10);
    $("#topleft").css("backgroundColor",foo.getHex());
}
function undoLighten(){
    foo = RGB($("#topleft").css("backgroundColor"));
    foo.subRGB(10, 10, 10);
    $("#topleft").css("backgroundColor",foo.getHex());
}