var userId;

/* 화면 로드 시 */
$(document).ready(function() {
	userId = sessionStorage.getItem("userId");
	setCate('0');
});

function setListNull() {
	var tbodyObj = $("#tbody1");
	var html = "";

	html += "<tr>";
	html += "	<td colspan=\"9\">데이터가 없습니다.</td>";
	html += "</tr>";

	tbodyObj.html(html);
}

function enAttPop(locale, index) {
	//var cateCode = getSelectBoxCateCode(3);	// select box 카테고리 가져오기
	var cateCode = document.getElementById("cateCode" + index).textContent;

	if (cateCode.length < 4 ) {
		alert("최소 중카테고리까지 선택해 주세요.")
		return false;
	}

	var width = '1200';
    var height = '600';

    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
    var left = Math.ceil((window.screen.width - width) / 2 + window.screenLeft);
    var top = Math.ceil((window.screen.height - height) / 2);

	// 팝업띄움
	window.open(getContextPath() + "/dcrawling/popup/enAttribute?cateCode=" + cateCode + "&parentLocale=" + locale + "&index=" + index, "attSearch", "width="+width+",height="+height+",top="+top+",left="+left);
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

// D사속성 테이블 조회
function getQaList() {
	var cateMap = getSelectBoxMap("cate2");
	var cateCode = getSelectBoxCateCode(3);
	var dnAttNm = document.getElementById("dnAttNm").value.trim();
	var enrAtrId = document.getElementById("enAttNm").title.trim();
	var enrAtrDtlId = document.getElementById("enAttElNm").title.trim();

	// cate3(소카테)의 내용을 cate2(중카테)의 map과 합친다.
	getSelectBoxMap("cate3").forEach((value, key) => cateMap.set(key, value));

	// 초기값 설정
	enrAtrId = enrAtrId == "" ? 0 : enrAtrId;
	enrAtrDtlId = enrAtrDtlId == "" ? 0 : enrAtrDtlId;

	if (cateCode.length < 4 ) {
		alert("최소 중카테고리까지 선택해 주세요.")
		return false;
	}

	loadingWithMask();	// 로딩 처리

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/dnAtt-admin",
		contentType: 'application/json',
		data: { "cateCode": cateCode,
				"dnAtrNm": dnAttNm,
				"enrAtrId": enrAtrId,
				"enrAtrDtlId": enrAtrDtlId },
		dataType: "json",
		//dataType: "jsonp",
		success: function(json) {
			var html = [];

			if (json) {
				for (var i = 0; i < json.length; i++) {
					var attId = json[i].attributeId;
					var galAttNm = json[i].galleryAttributeNm;
					var attElId = json[i].attributeElementId;
					var attElNm = json[i].attributeElement;

					attId = attId == 0 ? "" : attId;
					galAttNm = galAttNm == null ? "" : galAttNm;
					attElId = attElId == 0 ? "" : attElId;
					attElNm = attElNm == null ? "" : attElNm;

					html.push("<tr>");
					html.push("<td id='type" + i + "\' value='U' ></td>");	// 등록(I)/변경(U) 타입 (숨김처리)..추후 신규건 추가기능 여기서 할때를 대비해서...
					html.push("<td><input type='checkbox' name='check' id='check" + i + "\' title='" + json[i].dnAtrId + "' value='" + json[i].dnAtrId + "'></td>");	//체크박스
					html.push("<td id=cateCode" + i + ">" + json[i].cateCd + "</td>");	//카테고리 코드
					html.push("<td>" + cateMap.get(json[i].cateCd) + "</td>");	//카테고리 이름
					html.push("<td id=dnAtrNm" + i + " title =\'" + json[i].dnAtr + "\'>" + json[i].dnAtrNm + "</td>");	//D사 속성명
					/*html.push("<td>" + json[i].dnAtr + "</td>");	//D사 속성명*/
					html.push("<td>" + "<input id='attId" + i + "\' type='text' readonly style='width:100%' value=\'" + attId + "\' onclick=setCheck(" + i + ",true)></td>"); //속성ID
					html.push("<td id='attNm" + i + "\'>" + galAttNm + "</td>");	//전시용 속성명
					html.push("<td>" + "<input type='text' id='attElId" + i + "\' readonly style='width:100%' value=\'" + attElId + "\' onclick=setCheck(" + i + ",true)></td>"); //속성원ID
					html.push("<td id='attElNm" + i + "\'>" + attElNm + "</td>");	//속성원명

					if (json[i].useClassCode == "1") {	//속성 사용 구분코드 1이면 범위값 입력 불가
						html.push("<td>" + "<input id='enrAtrDtlRngVlu" + i + "\' type='text' disabled style='width:100%' value=\'" + json[i].enrAtrDtlRngVlu + "\' onclick=setCheck(" + i + ",false)></td>");	//속성원 범위값
					} else {
						html.push("<td>" + "<input id='enrAtrDtlRngVlu" + i + "\' type='text' style='width:100%' value=\'" + json[i].enrAtrDtlRngVlu + "\' onclick=setCheck(" + i + ",false)></td>");	//속성원 범위값
					}

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
			closeLoadingWithMask();
		}
	});
}

// 체크박스 자동 체크
function setCheck(i, isPopup) {
	var check = document.getElementById("check" + i);
	check.checked = true;
	if (isPopup) {
		enAttPop("attList", i);
	}
}

// 검색한 속성정보 초기화
function clearInfo() {
	document.getElementById("enAttNm").value = "";
	document.getElementById("enAttNm").title = "";
	document.getElementById("enAttElNm").value = "";
	document.getElementById("enAttElNm").title = "";
	document.getElementById("dnAttNm").value = "";
	document.getElementById("dnAttNm").title = "";
}

// 체크 D사 속성 삭제
function delDnAtt() {
	var dnAtrIds = new Array();

	if (!confirm("체크한 속성을 삭제하시겠습니까?")) {
		return;
	}

	// 전체 체크 순회
	$("input:checkbox[name=check]").each(function() {
	 	if (this.checked) {
			$(this).parents("tr").remove();	// row 삭제
			dnAtrIds.push(this.title);
		}
	});

	loadingWithMask();	// 로딩 처리

	jQuery.ajax({
		type: "DELETE",
		url: getContextPath() + "/dcrawling/dnAtt-admin/" + dnAtrIds,
		data: { "userId" : userId },
		async: false,
		success: function(result) {
			alert(result + "건 삭제 완료.");
			closeLoadingWithMask();
		},
		error: function(request, status, error) {
			closeLoadingWithMask();
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
		}
	});

	getQaList();
}

function saveDnAtt() {
	var dnAtrIds = new Array();

	if (!confirm("체크한 속성을 저장하시겠습니까?")) {
		return;
	}

	// 전체 체크 순회
	$("input:checkbox[name=check]").each(function(index, item) {
	 	if (item.checked) {
			var dnAtrId = item.title;
			var attId = document.getElementById("attId" + index).value;
			var attElId = document.getElementById("attElId" + index).value;
			var dnAtrNm = document.getElementById("dnAtrNm" + index).textContent;
			var enrAtrDtlRngVlu = document.getElementById("enrAtrDtlRngVlu" + index).value;

			if (attId == "" || attElId == "") {
				alert("D사속성명 : " + dnAtrNm + "\r\n에누리 속성ID/속성원ID가 없습니다.");
				return;
			}

			var map = {};
			map.dnAtrId = dnAtrId;
			map.attId = attId;
			map.attElId = attElId;
			map.enrAtrDtlRngVlu = enrAtrDtlRngVlu;

			dnAtrIds.push(map);
		}
	});

	var jsonData = "{ \"dnAtrIds\": " + JSON.stringify(dnAtrIds) + ", \"userId\": " + JSON.stringify(userId) + "}";

	loadingWithMask();	// 로딩 처리

	jQuery.ajax({
		type: "PUT",
		url: getContextPath() + "/dcrawling/dnAtt-admin; charset=utf-8",
		contentType: "application/json",
		data: jsonData,
		dataType: "json",
		async: false,
		success: function(result) {
			alert(result + "건 변경 완료.");
			closeLoadingWithMask();
		},
		error: function(request, status, error) {
			closeLoadingWithMask();
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
		}
	});

	getQaList();
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