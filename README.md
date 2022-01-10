# PEP-query

Function to check if a person is contained in PEP list

Arguments are firstname, lastname and birthdate (with no delimiters). Depending on implementation it should return boolean, but prints it for now.

The hashmap is queried in constant time. A test comparing it to a list is included. A different solution could be a binary tree, but it is slightly slower (log time).

A test is also included to assert if all persons on the PEP list are contained. It prints all lines not in the map, and if all people are contained only invalid lines will show.
