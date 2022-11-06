if(nessunaChat == true) {
	mostraMessaggioSuPc();
}

function mostraMessaggioSuPc() {
	const deviceWidth = window.screen.width;
	let tagPreHomemex = document.getElementById('homemex');
	
	if(deviceWidth > 500){
		newTagP = document.createElement('p');
		newTagP.classList.add('homemex');
		resultsDiv = tagPreHomemex.parentNode;
		children = tagPreHomemex.childNodes;
	
		Array.prototype.forEach.call(children, function (elem) {
			newTagP.appendChild(elem);
		});
		
	resultsDiv.replaceChild(newTagP, tagPreHomemex);
	}
}