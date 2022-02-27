function subtractQty0(a){
	var b="product-quantity"+a;
	if(document.getElementById(b).value - 1 <= 0){
		alert("Chọn số lượng >= 1");
		b="";
	}else{
		document.getElementById(b).value--;
		b="";
	}
}

function subtractQty(c){
	var d="product-quantity"+c;
	if(parseInt(document
	.getElementById(d).value) + 1 > parseInt(document
	.getElementById("stock").value))
	alert("Chọn số lượng <= số lượng tồn");
	else document.getElementById(d).value++;
}