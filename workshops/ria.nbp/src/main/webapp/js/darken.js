window.onload = function() {
    darken("content");
}

/*
 * Takes an id to an element and brightens its colour a notch...
 */
function darken(did) {
    de = document.getElementById(did);
    de.addEventListener("mouseover", doDarken, false)
    de.addEventListener("mouseout", undoDarken, false)
}

function doDarken(){
    dcs = document.defaultView.getComputedStyle(de,null);
    dcolor = dcs.getPropertyValue('background-color');

    dfoo = RGB(dcolor);
    dfoo.subRGB(10, 10, 10);

    de.style.backgroundColor = dfoo.getHex();
}
function undoDarken(){
    dcs = document.defaultView.getComputedStyle(de,null);
    dcolor = dcs.getPropertyValue('background-color');

    dfoo = RGB(dcolor);
    dfoo.addRGB(10, 10, 10);

    de.style.backgroundColor = dfoo.getHex();
}