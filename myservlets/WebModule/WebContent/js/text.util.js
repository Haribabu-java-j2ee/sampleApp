//common functions

//a function to replace/remove unicode or special characters for the supplied form element
function cleanCharacters(elem) {
	var originalValue = elem.value;
	
	var s = originalValue;
	s = s.replace(/[\x84\x93\x94]/g, '"');
	s = s.replace(/[\u2018|\u2019|\u201A]/g, '\''); // replace smart single quotes and apostrophe
	s = s.replace(/[\u201C|\u201D|\u201E]/g, '\"'); // replace smart double quotes
	s = s.replace(/\u2026/g, '...'); // replace ellipsis
	s = s.replace(/[\u2013|\u2014]/g, '-'); // replace dashes
	s = s.replace(/\u02C6/g, '^'); // replace circumflex
	s = s.replace(/\u2039/g, '<'); // replace open angle bracket
	s = s.replace(/\u203A/g, '>'); // replace close angle bracket
	s = s.replace(/[\u02DC|\u00A0]/g, ' '); // replace spaces
	s = s.replace("Â",""); // stupid special character
	s = s.replace(/[^a-zA-Z0-9\s`\-=~!@#$%^&*()_+\[\]\\{}\|;':",.\/<>?]/g, ''); // remove non standard characters
	elem.value = s;
	
	return s === originalValue;
}

//a function to remove everything but digits 0-9 for the supplied form element 
function cleanCharactersDigitsOnly(elem) {
	var originalValue = elem.value;
	
	var s = originalValue;
	s = s.replace(/[^0-9]/g, ''); // remove everything but digits
	elem.value = s;
	
	return s === originalValue;
}

//a function to remove everything but A-Z and a-z for the supplied form element 
function cleanCharactersCharsOnly(elem) {
	var originalValue = elem.value;
	
	var s = originalValue;
	s = s.replace(/[^A-Za-z]/g, ''); // remove everything but chars
	elem.value = s;
	
	return s === originalValue;
}
