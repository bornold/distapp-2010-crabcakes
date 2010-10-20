/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(loadRentals);

function loadRentals(){
    $("#rentallistarea").load("AdminServlet", {action: "getActiveRentalsList"})
}

