var cateCode;		//카테고리 코드
var index;			//부모 팝업 호출 index 위치

/* 화면 로드 시 */
$(document).ready(function() {
	var req = new request();
	cateCode = req.getParameter("cateCode");
	index = req.getParameter("index");
	init();
});

// 조회 테이블 초기화
function init() {
	$("#mkr_list").html($(""));
	$("#brnd_list").html($(""));
}

// 제조사 검색
function getMkrList() {
	var mkrText = document.getElementById("mkrText");
	var keyword = mkrText.value.trim();

	if (keyword == "") {
		alert("제조사 검색값을 입력해 주세요.")
		mkrText.focus();
		return;
	}

	init();
	getMkrListView(keyword);	// 제조사 조회
}

// 브랜드 검색
function getBrndList() {
	var brndText = document.getElementById("brndText");
	var keyword = brndText.value.trim();

	if (keyword == "") {
		alert("전시용 속성 검색값을 입력해 주세요.")
		brndText.focus();
		return;
	}

	init();
	getBrndListView(keyword);	// 브랜드 조회
}

// 제조사 조회
function getMkrListView(keyword) {

	var isSetList = false;	// 제조사 클릭시 브랜드 변경 false

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/popup/brndMkr/mkrList",
		contentType: 'application/json',
		data: { "keyword": keyword },
		dataType: "json",
		//dataType: "jsonp",
		success: function(json) {

			var html = [];
			var mkrList = json;

			if (mkrList != "") {
				for (var i = 0; i < mkrList.length; i++) {
					var list = new Array();

					// 속성원 정보 배열에 담는다.(id:속성원명)
					for (var j = 0; j < mkrList[i].brands.length; j++) {
						var array = new Object();
						array.id = mkrList[i].brands[j].id.brandId;
						array.name = mkrList[i].brands[j].brandNm;
						list.push(array);
					}

					var jsonData = JSON.stringify(list);
					html.push("<tr style='display:table; width:100%;'>");
					html.push("<td style='width:51%' onclick='setBrndView(" + jsonData + "," + mkrList[i].makerId + "," + "\"" + mkrList[i].makerNm + "\"," + isSetList + ")'>" + mkrList[i].makerId + "<br>");	// 제조사ID
					html.push("<td style='width:50%' onclick='setBrndView(" + jsonData + "," + mkrList[i].makerId + "," + "\"" + mkrList[i].makerNm + "\"," + isSetList + ")'>" + mkrList[i].makerNm + "<br>");	// 제조사명
					html.push("</tr>");
				}

				if (html == "") {
					setListNull("mkr_list", 2);
				} else {
					$("#mkr_list").html($(html.join('')));
				}
			} else {
				setListNull("mkr_list", 2);
			}
		},
		error: function(request, status, error) {
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
		}
	});
}

// 브랜드 내역 view 생성
function setBrndView(json, mkrId, mkrNm, isSetList) {

	var html = [];
	setMkr(mkrId, mkrNm);	// 속성ID, 속성명 담아둠

	for (i in json) {
		html.push("<tr style='display:table; width:100%;'>");
		if (i == 0) {
			html.push("<td style='width:12%'><input type='radio' name='brndRadio' id='brndRadio'" + i + "' onclick='setBrnd(" + json[i].id + ",\"" + json[i].name + "\"," + "\"\",\"\"," + isSetList + ")' checked='checked'></td>");
			setBrnd(json[i].id, json[i].name, "", "", isSetList);	//속성원id, 속성원명 담아둠
		} else {
			html.push("<td style='width:12%'><input type='radio' name='brndRadio' id='brndRadio'" + i + "' onclick='setBrnd(" + json[i].id + ",\"" + json[i].name + "\"," + "\"\",\"\"," + isSetList + ")'></td>");
		}

		html.push("<td style='width:42%'>" + json[i].id + "<br>");	//속성원ID
		html.push("<td style='width:46%'>" + json[i].name + "<br>");	//속성원명
		html.push("</tr>");
	}

	if (html == "") {
		setListNull("brnd_list", 3);
	} else {
		$("#brnd_list").html($(html.join('')));
	}
}

function setBrnd(brndId, brndNm, mkrId, mkrNm, isSetList) {
	document.getElementById("hdBrndId").value = brndId;	// 브랜드id 담아둠
	document.getElementById("hdBrndNm").value = brndNm;	// 브랜드명 담아둠

	// 브랜드 클릭시 제조사 변경
	if (isSetList == true) {
		setMkr(mkrId, mkrNm);

		var html = [];
		html.push("<tr style='display:table; width:100%;'>");
		html.push("<td style='width:51%'>" + mkrId + "<br>");	//제조사ID
		html.push("<td style='width:50%'>" + mkrNm + "<br>");	//제조사명
		html.push("</tr>");

		$("#mkr_list").html($(html.join('')));
	}
}

function setMkr(attId, attNm) {
	document.getElementById("hdMkrId").value = attId;	// 제조사id 담아둠
	document.getElementById("hdMkrNm").value = attNm;	// 제조사명 담아둠
}

// 브랜드 조회
function getBrndListView(keyword) {

	var isSetList = true;	// 브랜드 클릭시 제조사 변경 true

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/popup/brndMkr/brndList",
		contentType: 'application/json',
		data: { "keyword": keyword },
		dataType: "json",
		//dataType: "jsonp",
		success: function(json) {

			var html = [];
			var mkrList = json;

			if (mkrList != "") {
				for (var i = 0; i < mkrList.length; i++) {

					var makerId = mkrList[i].makerId;
					var makerNm = mkrList[i].makerNm;

					// 브랜드정보 html 생성
					for (var j = 0; j < mkrList[i].brands.length; j++) {
						html.push("<tr style='display:table; width:100%;'>");
						if (i == 0 && j == 0) {
							html.push("<td style='width:12%'><input type='radio' name='brndRadio' id='brndRadio'" + i + "' onclick='setBrnd(" + mkrList[i].brands[j].id.brandId + ",\"" + mkrList[i].brands[j].brandNm + "\",\"" + makerId + "\",\"" + makerNm + "\"," + isSetList + ")' checked='checked'></td>");
							setBrnd(mkrList[i].brands[j].id.brandId, mkrList[i].brands[j].brandNm, makerId, makerNm, isSetList);	//브랜드ID, 브랜드명 담아둠
						} else {
							html.push("<td style='width:12%'><input type='radio' name='brndRadio' id='brndRadio'" + i + "' onclick='setBrnd(" + mkrList[i].brands[j].id.brandId + ",\"" + mkrList[i].brands[j].brandNm + "\",\"" + makerId + "\",\"" + makerNm + "\"," + isSetList + ")'></td>");
						}

						html.push("<td style='width:42%'>" + mkrList[i].brands[j].id.brandId + "<br>"); //브랜드ID
						html.push("<td style='width:46%'>" + mkrList[i].brands[j].brandNm + "<br>"); //브랜드명
						html.push("</tr>");
					}
				}

				if (html == "") {
					setListNull("brnd_list", 3);
				} else {
					$("#brnd_list").html($(html.join('')));
				}

			} else {
				setListNull("brnd_list", 3);
			}
		},
		error: function(request, status, error) {
			console.log(request.responseText);
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
		}
	});
}

// 엔터시 조회버튼 실행
function enterkey(element) {
	if (event.keyCode == 13) {
		if (element.id == "mkrText") {
			getMkrList();
		} else if (element.id == "brndText") {
			getBrndList();
		}
	}
}

function setListNull(id, colspan) {
	var tbodyObj = $("#" + id);
	var html = "";

	html += "<tr style='display:table; width:100%; height:95%;'>";
	html += "	<td colspan=\"" + colspan + "\">데이터가 없습니다.</td>";
	html += "</tr>";

	tbodyObj.html(html);
}

function setParent() {
	opener.document.getElementById("mkrNm" + index).value = document.getElementById("hdMkrNm").value;
	opener.document.getElementById("mkrId" + index).textContent = document.getElementById("hdMkrId").value;
	opener.document.getElementById("brndNm" + index).value = document.getElementById("hdBrndNm").value;
	opener.document.getElementById("brndId" + index).textContent = document.getElementById("hdBrndId").value;

	close();
}