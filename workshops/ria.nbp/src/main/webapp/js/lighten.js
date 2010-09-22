window.onload = function() {
    lighten("topleft");
}

/*
 * Takes an id to an element and brightens its colour a notch...
 */
function lighten(lid) {
    le = document.getElementById(lid);
    le.addEventListener("mouseover", doLighten, false)
    le.addEventListener("mouseout", undoLighten, false)
}

function doLighten(){
    lcs = document.defaultView.getComputedStyle(le,null);
    lcolor = lcs.getPropertyValue('background-color');

    lfoo = RGB(lcolor);
    lfoo.addRGB(10, 10, 10);

    le.style.backgroundColor = lfoo.getHex();
}
function undoLighten(){
    lcs = document.defaultView.getComputedStyle(le,null);
    lcolor = lcs.getPropertyValue('background-color');

    lfoo = RGB(lcolor);
    lfoo.subRGB(10, 10, 10);

    le.style.backgroundColor = lfoo.getHex();
}