/*
 * Only load the Popup Script functions if the opener has a setWin function.
 */
if (window.opener && window.opener.setWin != null) {
	addLoadEvent(setOpenerWithThis);
	addUnloadEvent(setOpenerWithNull);
}
function setOpenerWithThis() {
	try {
		window.opener.setWin(window);
	} catch (e) {
	}
}
function setOpenerWithNull() {
	try {
		window.opener.setWinNull();
	} catch (e) {
	}
}
/*
 * This is JavaScript Object by prototype function 
 * Usage: e.g. divProperty = new PopupDivProperty('myDiv') (or: new PopupDivProperty(...) with any allowed
 * number of properties in order)
 */
function PopupDivProperty(id, classname, display, left, top, position, width,
		height) {

	if (id) {
		this.id = id;
	}
	if (classname) {
		this.classname = classname;
	}
	if (display) {
		this.display = display;
	}
	if (left) {
		this.left = left;
	}
	if (top) {
		this.top = top;
	}
	if (position) {
		this.position = position;
	}
	if (width) {
		this.width = width;
	}
	if (height) {
		this.height = height;
	}
}
/*
 * This defines a JavaScript object that manipulate <DIV> CARE pop-up features.
 * Usage: To open the pop-up: 
 * 		CAREPopup.createPopupDiv('My Title Label',1, new PopupDivProperty('myPopupDivId'), new PopupDivProperty('myDragDivId'),new PopupDivProperty('myContentDivId')); 
 * To fill up the content of the pop-up with Ajax response, call: 
 * 		CAREPopup.showContent('myContendDivId',response);
 * To close the pop-up, call 
 * 		CAREPopup.hidePopup(myPopupDivId). 
 * 		Attention: passing myPopupDivId to the hidePopup without quotes!!
 */
var CAREPopup = {
	/* provide default div property object */
	outer : new PopupDivProperty('popupDiv', 'noteAreaPopup', 'block', '400px',
			'120px', 'absolute', '500px;', '550px;'),
	dragger : new PopupDivProperty('drag-handle', '', '', '', '', '', '', ''),
	h2label : null,
	content : new PopupDivProperty('contentDiv', 'noteAreaPopupInside',
			'inline-block', '', '', '', '500px;', '500px;'),
	popupArr : [],
	popupSeq : 1,
	wrapperDiv : null,
	draggerDiv : null,
	contentDiv : null,
	/*
	 * This method is the main method to open up a pop up. 
	 * h2lbl -- Title label for the pop up 
	 * popupSeq -- levels for the pop up. Default is 1, and a pop  up on a pop up the level is 2 and so on 
	 * wrapper -- top level PopupDivProperty. It is an instance of PopupDivProperty 
	 * dragger -- dragable PopupDivProperty. It is an instance of PopupDivProperty 
	 * content -- content PopupDivProperty It is an instance of PopupDivProperty
	 */
	createPopupDiv : function(h2lbl, popupSeq, wrapper, dragger, content) {

		if (wrapper)
			this.setDivProperties(this.outer, wrapper);
		if (dragger)
			this.setDivProperties(this.dragger, dragger);
		if (content)
			this.setDivProperties(this.content, content);
		if(popupSeq)
			this.popupSeq = popupSeq;
		this.h2label = h2lbl;
		var div = this.popupDivExists()
		if (!div) {
			this.wrapperDiv = this.createDiv(this.outer);
			this.draggerDiv = this.createDiv(this.dragger);
			this.draggerDiv.appendChild(this.createH2Header(h2lbl));
			this.draggerDiv.appendChild(this.createCloseBtnSpan());
			this.wrapperDiv.appendChild(this.draggerDiv);
			this.contentDiv = this.createDiv(this.content);
			this.wrapperDiv.appendChild(this.contentDiv);
			if (this.popupArr.length > 0) {
				this.wrapperDiv.style.left = this.getNewLeftAnchorPosition(this.wrapperDiv) + 'px';
			}
			document.body.appendChild(this.wrapperDiv);

			this.initDragHandle();

			this.wrapperDiv.popupSeq = this.popupSeq;
			this.popupArr.push(this.wrapperDiv);
			this.refreshDivDisabled();
		} else {
			for ( var i = 0; i < this.popupArr.length; i++) {
				this.popupArr[i].style.zIndex = 0;
			}
			div.popupSeq = this.popupSeq;
			div.style.zIndex = 10;
			div.style.left = this.outer.left;
			if (this.popupArr.length > 0) {
				div.style.left = this.getNewLeftAnchorPosition(div) + 'px';
			}
			div.style.top = this.outer.top;
			div.style.display = 'block';
			this.refreshDivDisabled();
		}
	},

	/*
	 * Internally used
	 */
	popupDivExists : function() {
		var divId = this.outer.id;
		for ( var i = 0; i < this.popupArr.length; i++) {
			if (this.popupArr[i].id == divId) {
				return this.popupArr[i];
			}
		}
		return null;
	},

	/*
	 * Internally used
	 */
	createDiv : function(divAttr) {
		var div = document.getElementById(divAttr.id);
		if (!div)
			div = document.createElement('div');
		div.id = divAttr.id;
		if (divAttr.classname) {
			div.className = divAttr.classname;
		}
		if (divAttr.display) {
			div.style.display = divAttr.display;
		}
		if (divAttr.position) {
			div.style.position = divAttr.position;
		}
		if (divAttr.left) {
			div.style.left = divAttr.left;
		}
		if (divAttr.top) {
			div.style.top = divAttr.top;
		}
		if (divAttr.width) {
			div.style.width = divAttr.width;
		}
		if (divAttr.height) {
			div.style.height = divAttr.height;
		}
		div.onmouseover = function() {
			div.style.zIndex = 10;
		};
		div.onmouseout = function() {
			div.style.zIndex = 0;
		};
		return div;
	},

	/*
	 * Internally used
	 */
	createH2Header : function(label) {
		var h2 = document.createElement('h2');
		h2.innerHTML = label;
		return h2;
	},

	/*
	 * Internally used
	 */
	createCloseBtnSpan : function() {
		var sp = document.createElement('span');
		sp.className = 'closeBtn';
		sp.innerHTML = '<img src="images/close_btn.gif" alt="close" width="26" height="18" border="0" '
				+ 'onclick="CAREPopup.hidePopup(this,true);" />';
		return sp;
	},

	/*
	 * This method is called to hide Pop up
	 * hideDiv -- pop up to hide. it should be the pop up div object, not the string of the div ID.
	 */
	hidePopup : function(hideDiv, dragger) {
		if (dragger) {
			hideDiv.parentNode.parentNode.parentNode.style.display = 'none';
		} else {
			hideDiv.style.display = 'none';
		}
		this.refreshDivDisabled();
	},

	showPopup : function() {
		this.wrapperDiv.style.display = 'block';
	},

	/*
	 * Internally used
	 */
	initDragHandle : function() {
		if (this.draggerDiv) {
			Drag.init(this.draggerDiv, this.wrapperDiv,0,null,0,null);
		}
	},

	/*
	 * Internally used
	 */
	setDivProperties : function(divProp1, divProp2) {
		if (divProp2.id) {
			divProp1.id = divProp2.id;
		}
		if (divProp2.classname) {
			divProp1.classname = divProp2.classname;
		}
		if (divProp2.left) {
			divProp1.left = divProp2.left;
		}
		if (divProp2.top) {
			divProp1.top = divProp2.top;
		}
		if (divProp2.display) {
			divProp1.display = divProp2.display;
		}
		if (divProp2.position) {
			divProp1.position = divProp2.position;
		}
		if (divProp2.width) {
			divProp1.width = divProp2.width;
		}
		if (divProp2.height) {
			divProp1.height = divProp2.height;
		}
	},

	/*
	 * This method is called to show Ajax response in a pop up screen
	 * contentDivId -- string Content <DIV> ID 
	 * contentHtml -- Ajax call html response
	 */
	showContent : function(contentDivId, contentHtml) {
		this.contentDiv = document.getElementById(contentDivId);
		this.contentDiv.innerHTML = contentHtml;
	},

	/*
	 * Internally used
	 */
	refreshDivDisabled : function() {
		var highestSeq = 0;
		var div;
		var i;
		for (i = 0; i < this.popupArr.length; i++) {
			div = this.popupArr[i];
			if (div.popupSeq > highestSeq && div.style.display == 'block') {
				highestSeq = div.popupSeq;
			}
		}

		for (i = 0; i < this.popupArr.length; i++) {
			div = this.popupArr[i];
			if (div.popupSeq >= highestSeq) {
				div.style.pointerEvents = 'auto';
			} else {
				div.style.pointerEvents = 'none';
			}
		}

	},

	/*
	 * This method is called to show application (system or error) message on top of Pop up.
	 */
	setAllToBackGround : function() {
		for (i = 0; i < this.popupArr.length; i++) {
			var div = this.popupArr[i];
			div.style.zIndex = -1;
		}
	},

	/*
	 * Internally used
	 */
	getNewLeftAnchorPosition : function(div) {
		var divWidth = div.clientWidth;
		if(!divWidth) divWidth = 555;
		var tmpDiv;
		var oldLeft = 0;
		var left = div.style.left;
		for(var i = 0; i< this.popupArr.length; i++){
			if(this.popupArr[i].id != div.id && this.popupArr[i].style.display == 'block'){
				tmpDiv = this.popupArr[i];
			}
		}
		if(tmpDiv){
			oldLeft = parseInt(tmpDiv.style.left.substring(0, tmpDiv.style.left.indexOf('px')));
			left = document.getElementById(tmpDiv.id).clientWidth + oldLeft;
			var windowW = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
			if(left + divWidth > windowW){
				left = oldLeft - divWidth;
				if(left < 0) left = 0;
			}
		}
		return left;
	}

};