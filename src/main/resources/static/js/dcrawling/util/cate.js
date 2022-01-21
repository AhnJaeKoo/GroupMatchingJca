// 카테고리 코드 가져오기
function getSelectBoxCateCode(index) {
	var result = "";

	for (var i = index; i > 0; i--) {
		var cateCode = $("#cate" + i).val();

		if (cateCode != '') {
			result = cateCode;
			break;
		}
	}

	return result;
}

// 카테고리 이름 가져오기
function getSelectBoxCateCodeNm(index) {
	var result = "";

	for (var i = index; i > 0; i--) {
		var sel = document.getElementById("cate" + i);
		var cateCode = $("#cate" + i).val();

		if (cateCode != "") {	// value가 있을때가 정상 선택이다.
			var cateNm = sel.options[sel.selectedIndex].title;
			result = cateNm;
			break;
		}
	}

	return result;
}

// 카테고리 이름 가져오기
function getSelectBoxMap(id) {
	const map = new Map();

	$("#" + id + ">option").map(function() {
		if ($(this).val() != "") {
			var arr = $(this).text().split(":");
			map.set(arr[0].trim(), arr[1].trim());
		}
	});

	return map;
}

// select box 카테고리 set
function setCate(id) {
	var cateCode = "";

	if (id > "0") {
		cateCode = $("#cate" + id).val();
	}

	$.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/common/selectBoxCate",
		data: { "cateCode": cateCode },
		success: function(result) {

			if (result.length == 0) {
				alert("카테고리 가져오기 실패");
				return false;
			}

			var arrCate = result.split(",");

			if (id == "0") {
				$("#cate1").find("option").remove().end().append("<option value=''>-대카테고리선택-</option>");
				$("#cate2").find("option").remove().end().append("<option value=''>-중카테고리선택-</option>");
				$("#cate3").find("option").remove().end().append("<option value=''>-소카테고리선택-</option>");
				$("#cate4").find("option").remove().end().append("<option value=''>-미카테고리선택-</option>");
			} else if (id == "1") {
				$("#cate2").find("option").remove().end().append("<option value=''>-중카테고리선택-</option>");
				$("#cate3").find("option").remove().end().append("<option value=''>-소카테고리선택-</option>");
				$("#cate4").find("option").remove().end().append("<option value=''>-미카테고리선택-</option>");
			} else if (id == "2") {
				$("#cate3").find("option").remove().end().append("<option value=''>-소카테고리선택-</option>");
				$("#cate4").find("option").remove().end().append("<option value=''>-미카테고리선택-</option>");
			} else if (id == "3") {
				$("#cate4").find("option").remove().end().append("<option value=''>-미카테고리선택-</option>");
			}

			// 하위 select box 초기화
			if (id > "0") {
				for (var i = id + 1; i <= 4; i++) {
					$("#cate" + i).find("option").remove().end().append("<option value=''>-전체-</option>");
				}
			}

			// select box 값 추가
			for (i = 0; i < arrCate.length; i += 2) {
				if (arrCate[i] != "") {
					$("#cate" + (Number(id) + 1)).append("<option value='" + arrCate[i] + "' title ='" + arrCate[i + 1] + "'>" + arrCate[i] + " : " + arrCate[i + 1] + "</option>");
				}
			}
		},
		error: function(request, status, error) {
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
		}
	});
}