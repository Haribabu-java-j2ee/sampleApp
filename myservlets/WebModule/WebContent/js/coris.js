function replaceExtendedAsciiChars(text) {
	var goodQuotes = text.replace(/[\x84\x93\x94]/g, '"')
					.replace(/[\u2018\u2019\u201A]/g, "'") // smart single quotes and apostrophe
					.replace(/[\u201C\u201D]/g, '"') // smart double quotes
					.replace(/\u2026/g, "..."); // ellipsis
	return goodQuotes;
}


