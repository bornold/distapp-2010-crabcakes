# Introduction #

QueerCars makes renting cars awesome!


# Details #

## Functionality ##
  * User registration
  * Admin interface
  * Multiple pickup locations
  * Webservice for rental terminals
  * Kittens
  * Alternate transportation solution (i.e. Västtrafik)
  * Pink fluffy stuff
  * Absolutely **no** Hitlers

### Relations ###
As per [ER-diagram.dia](http://distapp-2010-crabcakes.googlecode.com/svn/trunk/project/documents/ER-diagram.dia)
```
Users(_id,name)
Cars(_id)
Administrators(_id,name)
Rentals(_car,user)
    car -> Cars.id
    user -> Users.id
```