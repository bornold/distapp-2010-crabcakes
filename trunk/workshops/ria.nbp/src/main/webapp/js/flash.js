/*
 * This function takes the id of an element and animates it's background colour from
 * black to white. Once the color is white, the animation is stopped and the original
 * colour of the element is restored.
 *
 * Several things need to be fixed before it works...
 */
function flash(id, original_colour) {
	e = document.getElementById(id);
	colour = 0;

	id = setInterval(function() {
		colour += 10;
		e.style.backgroundColor = '#' + colour.toString(16) + colour.toString(16) + colour.toString(16);

		if (colour == 250) {
			clearInterval(id);
			e.style.backgroundColor = original_colour;
		}
	}, 5);
}
