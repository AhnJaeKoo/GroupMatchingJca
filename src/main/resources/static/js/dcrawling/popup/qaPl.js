var qaId;
var userId;
var plCntId;

$(document).ready(function() {
	var req = new request();
	userId = sessionStorage.getItem("userId");
	qaId = req.getParameter("qaId");
	plCntId = req.getParameter("plCntId");
	$("#pl_cnt").html("크롤링 상품 수 : " + getList(qaId));
});

function getList(qaId) {

	var result = 0;

	loadingWithMask();	// 로딩 처리

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/popup/qaPl/list/" + qaId,
		contentType: "application/json",
		dataType: "json",
		//dataType: "jsonp",
		async: false,
		success: function(json) {
			var html = [];
			if (json != "") {
				result = json.length;

				for (var i = 0; i < json.length; i++) {
					html.push("<tr>");
					if (json[i].useYn == "Y") {
						html.push("<td style='text-align:center'><input type='checkbox' title='Y' checked='true' name=check id='check'" + i + " value='" + json[i].plNo + "'></td>");
					} else {
						html.push("<td style='text-align:center'><input type='checkbox' title='N' name=check id='check'" + i + " value='" + json[i].plNo + "'></td>");
					}
					html.push("<td>" + json[i].plNo + '</td>');			//PLNO
					html.push("<td>" + json[i].plModelno + '</td>');	//매칭 모델번호
					html.push("<td>" + json[i].plGoodsnm + '</td>');	//상품명
					html.push("<td>" + json[i].plSrvflag + '</td>');	//상품 단종코드
					html.push("</tr>");
				}

				if (html == "") {
					setListNull();
				} else {
					$("#tbody1").html($(html.join('')));
				}
			} else {
				setListNull();
			}
			closeLoadingWithMask();
		},
		error: function(request, status, error) {
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
			copyToClipboard(request.responseText);
			closeLoadingWithMask();
		}
	});

	return result;
}

function setListNull() {
	var tbodyObj = $("#tbody1");
	var html = "";

	html += "<tr>";
	html += "	<td colspan=\"5\">데이터가 없습니다.</td>";
	html += "</tr>";

	tbodyObj.html(html);
}

function checkAll(element) {
	if (element.checked) {
		$("input:checkbox[name=check]").each(function() {
			this.checked = true;
		});
	} else {
		$("input:checkbox[name=check]").each(function() {
			this.checked = false;
		});
	}
}

// 저장
function save() {
	var checkQaPls = new Array();
	loadingWithMask();	// 로딩 처리

	// 전체 체크 순회
	$("input:checkbox[name=check]").each(function() {
		var useYn = this.checked == true ? "Y" : "N"
		if (this.title != useYn) {	// 초기값과 달라진것만 처리
			var map = {};
			map.useYn = useYn;
			map.plNo = this.value;
			checkQaPls.push(map);
		}
	});

	if (checkQaPls.length > 0) {
		var jsonData = "{ \"qaPls\": " + JSON.stringify(checkQaPls) + ", \"qaId\":" + JSON.stringify(qaId) + ", \"userId\":" + JSON.stringify(userId) + "}";

		jQuery.ajax({
			type: "PUT",
			url: getContextPath() + "/dcrawling/popup/qaPl/list",
			contentType: "application/json",
			data: jsonData,
			dataType: "json",
			async: false,
			success: function(result) {
				alert(result + "건 저장 완료");
				opener.document.getElementById(plCntId).innerHTML = "선택 상품 수 : " + getCheckCnt();
				closeLoadingWithMask();
			},
			error: function(request, status, error) {
				closeLoadingWithMask();
				alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
				console.log(request.responseText);
			}
		});

		close();	//창 닫기
	} else {
		alert("변경된 사항이 없습니다.");
		closeLoadingWithMask();
	}
}

function getCheckCnt() {
	result = 0;

	$("input:checkbox[name=check]").each(function() {
		if (this.checked) {
			result++;
		}
	});

	return result;
}