# PEP-query
Function to check if a person is contained in PEP list

Arguments are firstname, lastname and birthdate (with no delimiters). Depending on implementation it should return boolean, but prints it for now.

A test is included to compare speed to standard list retrieval. Another test prints all lines not in the map, so if only invalid lines show all people are contained in the map.

The hashmap is queried in constant time. A test comparing it to a list is included. A different solution could be a binary tree, but it is slightly slower (log time). 

The included PEP list was retreived on jan 7 2022 14.14 CET from https://www.finanstilsynet.dk/tal-og-fakta/pep-liste

This PEP excel sheet must be converted to a semicolon (;) separated csv file. This guide can be consulted https://www.ablebits.com/office-addins-blog/change-excel-csv-delimiter/
