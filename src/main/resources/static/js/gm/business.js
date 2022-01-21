function runProcess() {

	var modelNm = document.getElementById("modelNm").value;
	var cateCode = document.getElementById("cateCode").value;
	var exFlag = document.getElementById("selectBox").value;

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/gm/filterStr",
		data: { "modelNm": modelNm,
				"cateCode": cateCode,
				"exFlag": exFlag },
		dataType: "text",
		async: false,
		success: function(json) {
			if (json) {
				//document.getElementById("result").textContent = json;
				$('#result').html(json);
			}
		},
		error: function(request, status, error) {
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
		}
	});
}
