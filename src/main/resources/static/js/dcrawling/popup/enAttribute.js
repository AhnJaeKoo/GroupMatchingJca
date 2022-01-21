var cateCode;		//카테고리 코드
var parentLocale;	//부모 팝업 호출 위치
var index;			//부모 팝업 호출 index 위치

/* 화면 로드 시 */
$(document).ready(function() {
	var req = new request();
	cateCode = req.getParameter("cateCode");
	parentLocale = req.getParameter("parentLocale");
	index = req.getParameter("index");
	init();
});

// 조회 테이블 초기화
function init() {
	$("#enAtt_list").html($(""));
	$("#enAttEl_list").html($(""));
}

// 전시용 속성 검색
function getEnAttList() {
	var enAtt = document.getElementById("enAtt");
	var keyword = enAtt.value.trim();
	if (keyword == "") {
		alert("전시용 속성 검색값을 입력해 주세요.")
		enAtt.focus();
		return;
	}

	init();
	getEnAttListView(keyword);	// 전시용 속성 조회
}

function getEnAttElList() {
	var enAttEl = document.getElementById("enAttEl");
	var keyword = enAttEl.value.trim();
	if (keyword == "") {
		alert("속성원 검색값을 입력해 주세요.")
		enAttEl.focus();
		return;
	}

	init();
	getEnAttElListView(keyword);	// 속성원 조회
}


// 전시용 속성 조회
function getEnAttListView(keyword) {

	var isSetAttList = false;	// 속성원 클릭시 전시용 속성 변경 false

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/popup/enAttribute/enAttList",
		contentType: 'application/json',
		data: {
			"cateCode": cateCode,
			"keyword" : keyword
		},
		dataType: "json",
		//dataType: "jsonp",
		success: function(json) {

			var html = [];
			var enAttList = json;

			if (enAttList != "") {
				for (var i = 0; i < enAttList.length; i++) {
					var list = new Array();

					// 속성원 정보 배열에 담는다.(id:속성원명)
					for (var j = 0; j < enAttList[i].attEls.length; j++) {
						var array = new Object();
						array.id=enAttList[i].attEls[j].id.attributeElementId;
						array.name=enAttList[i].attEls[j].attributeElement;
						list.push(array);
					}

					var jsonData = JSON.stringify(list);

					// 속성ID별 복수의 카테고리가 있으므로 카테고리 개수만큼 나타낸다.
					for (var j = 0; j < enAttList[i].cateAtts.length; j++) {
						html.push("<tr style='display:table; width:100%;'>");
						html.push("<td style='width:20%' onclick='setEnAttElView(" + jsonData + "," + enAttList[i].attributeId + ",\"" + enAttList[i].galleryAttributeNm + "\",\"" + enAttList[i].useClassCode + "\"," + isSetAttList + ")'>" + enAttList[i].attributeId + "<br>");			//ID
						html.push("<td style='width:21%' onclick='setEnAttElView(" + jsonData + "," + enAttList[i].attributeId + ",\"" + enAttList[i].galleryAttributeNm + "\",\"" + enAttList[i].useClassCode + "\"," + isSetAttList + ")'>" + enAttList[i].cateAtts[j].id.category + "<br>");	//카테고리 코드
						html.push("<td style='width:31%' onclick='setEnAttElView(" + jsonData + "," + enAttList[i].attributeId + ",\"" + enAttList[i].galleryAttributeNm + "\",\"" + enAttList[i].useClassCode + "\"," + isSetAttList + ")'>" + enAttList[i].manageAttributeNm + "<br>");	//관리용 속성명
						html.push("<td style='width:100%' onclick='setEnAttElView(" + jsonData + "," + enAttList[i].attributeId + ",\"" + enAttList[i].galleryAttributeNm + "\",\"" + enAttList[i].useClassCode + "\"," + isSetAttList + ")'>" + enAttList[i].galleryAttributeNm + "<br>");	//전시용 속성명
						html.push("</tr>");
					}
				}

				if (html == "") {
					setListNull("enAtt_list", 4);
				} else {
					$("#enAtt_list").html($(html.join('')));
				}
			} else {
				setListNull("enAtt_list", 4);
			}
		},
		error: function(request, status, error) {
			copyToClipboard(request.responseText);
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
		}
	});
}

// 속성원 내역 view 생성
function setEnAttElView(json, attId, attNm, useClassCode, isSetAttList) {

	var html = [];
	setEnAtt(attId, attNm, useClassCode);	// 속성ID, 속성명 담아둠

	for (i in json) {
		html.push("<tr style='display:table; width:100%;'>");
		if (i == 0) {
			html.push("<td style='width:12%'><input type='radio' name='enAttElRadio' id='enAttElRadio'" + i + "' onclick='setEnAttEl(" + json[i].id + ",\"" + json[i].name + "\"," + "\"\",\"\",\"\",\"\",\"\"," + isSetAttList + ")' checked='checked'></td>");
			setEnAttEl(json[i].id, json[i].name, "", "", "", "", "", isSetAttList);	//속성원id, 속성원명 담아둠
		} else {
			html.push("<td style='width:12%'><input type='radio' name='enAttElRadio' id='enAttElRadio'" + i + "' onclick='setEnAttEl(" + json[i].id + ",\"" + json[i].name + "\"," + "\"\",\"\",\"\",\"\",\"\"," + isSetAttList + ")'></td>");
		}

		html.push("<td style='width:42%'>" + json[i].id + "<br>");	//속성원ID
		html.push("<td style='width:46%'>" + json[i].name + "<br>");	//속성원명
		html.push("</tr>");
	}

	if (html == "") {
		setListNull("enAttEl_list", 3);
	} else {
		$("#enAttEl_list").html($(html.join('')));
	}
}

// 클립보드에 텍스트 복사
function copyToClipboard(txt) {
	var tmp = document.createElement("textarea");
	tmp.value = txt;
	tmp.style.height = "0";
	tmp.style.overflow = "hidden";
	tmp.style.position = "fixed";
	document.body.appendChild(tmp);
	tmp.focus();
	tmp.select();
	document.execCommand("copy");
	document.body.removeChild(tmp);
}

function setEnAttEl(attElId, attElNm, attId, categorys, manageAttNm, galleyAttNm, useClassCode, isSetAttList) {
	document.getElementById("hdEnAttElId").value = attElId;	// 속성원id 담아둠
	document.getElementById("hdEnAttElNm").value = attElNm;	// 속성원명 담아둠

	// 속성원 클릭시 전시용 속성 변경
	if (isSetAttList == true) {
		setEnAtt(attId, galleyAttNm, useClassCode);

		categorys = "" + categorys;
		var arr = categorys.split(",");
		var html = [];

		for(const cateCode of arr){
			html.push("<tr style='display:table; width:100%;'>");
			html.push("<td style='width:20%'>" + attId + "<br>");					//ID
			html.push("<td style='width:21%'>" + cateCode + "<br>");			    //카테고리 코드
			html.push("<td style='width:31%'>" + manageAttNm + "<br>");				//관리용 속성명
			html.push("<td style='width:100%'>" + galleyAttNm + "<br>");			//전시용 속성명
			html.push("</tr>");
		}

		$("#enAtt_list").html($(html.join('')));
	}
}

function setEnAtt(attId, attNm, useClassCode) {
	document.getElementById("hdEnAttId").value = attId;	// 속성id 담아둠
	document.getElementById("hdEnAttNm").value = attNm;	// 속성명 담아둠
	document.getElementById("hdUseClassCode").value = useClassCode;	// 구분코드 담아둠
}

// 속성원 조회
function getEnAttElListView(keyword) {

	var isSetAttList = true;	// 속성원 클릭시 전시용 속성 변경 true

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/popup/enAttribute/enAttElList",
		contentType: 'application/json',
		data: {
			"cateCode": cateCode,
			"keyword" : keyword
		},
		dataType: "json",
		//dataType: "jsonp",
		success: function(json) {

			var html = [];
			var enAttList = json;

			if (enAttList != "") {
				for (var i = 0; i < enAttList.length; i++) {
					var attId = enAttList[i].attributeId;
					var manageAttNm = enAttList[i].manageAttributeNm;
					var galleryAttNm = enAttList[i].galleryAttributeNm;
					var useClassCode = enAttList[i].useClassCode;
					var categorys = [];

					enAttList[i].cateAtts.forEach(function(e) {
						categorys.push(e.id.category);
					});

					// 속성원 정보 html 생성
					for (var j = 0; j < enAttList[i].attEls.length; j++) {
						html.push("<tr style='display:table; width:100%;'>");

						if (i == 0 && j == 0 ) {
							html.push("<td style='width:12%'><input type='radio' name='enAttElRadio' id='enAttElRadio'" + i + "' onclick='setEnAttEl(" + enAttList[i].attEls[j].id.attributeElementId + ", \"" + enAttList[i].attEls[j].attributeElement + "\", \"" + attId + "\", \"" + categorys + "\", \"" + manageAttNm + "\", \"" + galleryAttNm + "\", \"" + useClassCode + "\", " + isSetAttList + ")' checked='checked'></td>");
							setEnAttEl(enAttList[i].attEls[j].id.attributeElementId, enAttList[i].attEls[j].attributeElement, attId, categorys, manageAttNm, galleryAttNm, useClassCode, isSetAttList);	//속성원id, 속성원명 담아둠
						} else {
							html.push("<td style='width:12%'><input type='radio' name='enAttElRadio' id='enAttElRadio'" + i + "' onclick='setEnAttEl(" + enAttList[i].attEls[j].id.attributeElementId + ", \"" + enAttList[i].attEls[j].attributeElement + "\", \"" + attId + "\", \"" + categorys + "\", \"" + manageAttNm + "\", \"" + galleryAttNm + "\", \"" + useClassCode + "\", " + isSetAttList + ")'></td>");
						}

						html.push("<td style='width:42%'>" + enAttList[i].attEls[j].id.attributeElementId + "<br>"); //속성원ID
						html.push("<td style='width:46%'>" + enAttList[i].attEls[j].attributeElement + "<br>");	  //속성원명
						html.push("</tr>");
					}
				}

				if (html == "") {
					setListNull("enAttEl_list", 3);
				} else {
					$("#enAttEl_list").html($(html.join('')));
				}

			} else {
				setListNull("enAttEl_list", 3);
			}
		},
		error: function(request, status, error) {
			copyToClipboard(request.responseText);
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
		}
	});
}

// 엔터시 조회버튼 실행
function enterkey(element) {
	if(event.keyCode == 13) {
		if (element.id == "enAtt") {
			getEnAttList();
		} else if (element.id == "enAttEl") {
			getEnAttElList();
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
	if (parentLocale == "attInfo") {	// 상단에서 호출시
		opener.document.getElementById("enAttNm").value = document.getElementById("hdEnAttNm").value;
		opener.document.getElementById("enAttNm").title = document.getElementById("hdEnAttId").value;
		opener.document.getElementById("enAttElNm").value = document.getElementById("hdEnAttElNm").value;
		opener.document.getElementById("enAttElNm").title = document.getElementById("hdEnAttElId").value;
	} else {	//하단에서 호출시
		opener.document.getElementById("attNm" + index).innerText = document.getElementById("hdEnAttNm").value;
		opener.document.getElementById("attId" + index).value = document.getElementById("hdEnAttId").value;
		opener.document.getElementById("attElNm" + index).innerText = document.getElementById("hdEnAttElNm").value;
		opener.document.getElementById("attElId" + index).value = document.getElementById("hdEnAttElId").value;
		opener.document.getElementById("enrAtrDtlRngVlu" + index).value = "";

		if (document.getElementById("hdUseClassCode").value == "1") {	//속성의 구분코드가 1이면 속성원 범위값 입력 불가처리
			opener.document.getElementById("enrAtrDtlRngVlu" + index).disabled = true;
		} else {
			opener.document.getElementById("enrAtrDtlRngVlu" + index).disabled = false;
		}
	}

	close();
}