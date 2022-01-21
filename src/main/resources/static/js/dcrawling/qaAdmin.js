var userId = "";

/* 화면 로드 시 */
$(document).ready(function() {
	//loadingWithMask();	// 로딩처리
	var req = new request();
	userId = req.getParameter("USER_ID");

	ieCheck();

	if (userId) {	// userId가 있으면 세션스토리지에 넣는다.
		sessionStorage.setItem("userId", userId);
	} else {
		// 기존 세션스토리지에도 없고 파라미터의 userId도 없으면 jca에서 다시 접근하게 한다.
		if (!sessionStorage.getItem("userId")) {
			alert("비정상적인 접근입니다.\r\nJCA로 접근하여 주시길 바랍니다.");
			redirectJca();
		} else {
			userId = sessionStorage.getItem("userId");
		}
	}

	setCate('0');
	//setTimeout("closeLoadingWithMask()", 1000);	// 1초간만 로딩 후 종료
});

function redirectJca() {
	window.location.href = "http://jca.enuri.com/";
}

function ieCheck() {
	var agent = navigator.userAgent.toLowerCase();

	if ((navigator.appName == 'Netscape' && agent.indexOf('trident') != -1) || (agent.indexOf("msie") != -1)) {    //익스플로러인지 체크
    	alert("익스플로러에서는 사용이 불가합니다.");
		redirectJca();
 	}
}

function setListNull() {
	var tbodyObj = $("#tbody1");
	var html = "";

	html += "<tr>";
	html += "	<td colspan=\"17\">데이터가 없습니다.</td>";
	html += "</tr>";

	tbodyObj.html(html);
}

// QA 테이블 조회
function setQaList(page) {
	var cateCode = getSelectBoxCateCode(4);
	var size = 100;

	if (cateCode.length == 0) {
		alert("카테고리를 선택해 주세요.")
		return false;
	}

	var isNonAtt = document.getElementById("nonAttList").checked;			// 속성 미등록 리스트
	var isNonBrndMkr = document.getElementById("nonBrndMkrList").checked;	// 브랜드/제조사 미등록 리스트
	var sortType = $("#sortType option:selected").val();					// 정렬순서 타입

	loadingWithMask();	// 로딩 처리

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/qa-admin",
		contentType: "application/json",
		data: {
			"cateCode": cateCode,
			"isNonAtt": isNonAtt,
			"isNonBrndMkr": isNonBrndMkr,
			"sortType": sortType,
			"size": size,
			"page": Number(page - 1),
			"sort": "dn_rnk,dn_cnd_nm"
		},
		dataType: "json",
		//dataType: "jsonp",
		success: function(json) {

			var html = [];
			var qaList = json.content;
			var totalPages = json.totalPages;
			var totModelCnt = json.totalElements;

			if (qaList != "") {
				for (var i = 0; i < qaList.length; i++) {
					var modelCheckBox = "";
					var mkrId = "";
					var brandId = "";

					if (qaList[i].models != null) {
						var modelSplit = qaList[i].models.split(",");

						// 매칭된 에누리 모델 checkbox 생성
						for (var j in modelSplit) {
							var modelInfo = modelSplit[j].split("|");
							var modelNo = modelInfo[0];
							var modelNm = modelInfo[1]
							var modelText = "(" + modelNo + ")" + modelNm;
							var matchingCheckbox = '<input type="checkbox" name="' + qaList[i].qa_id + '" value="' + modelNo + '" onClick="oneCheck(this, ' + qaList[i].qa_id + ',' + i + ')">'
							modelCheckBox += modelCheckBox == "" ? '<label>' + matchingCheckbox + modelText + '</label>' : '<br><label>' + matchingCheckbox + modelText + '</label>';
						}
					}

					if (qaList[i].mkr_id == "0" || qaList[i].mkr_id == "2880") {	// 제조사 없으면 등록필요
						mkrId = '<div style="text-align:center;color:red"><b><label id=mkrId' + i + '>등록필요</label><b/></div></td>'
					} else {
						mkrId = '<div style="text-align:center"><b><label id=mkrId' + i + '>'+ qaList[i].mkr_id + '</label><b/></div></td>'
					}

					if (qaList[i].brnd_id == "0" || qaList[i].brnd_id == "28804") {	// 브랜드 없으면 등록필요
						brandId = '<div style="text-align:center;color:red"><b><label id=brndId' + i + '>등록필요</label><b/></div></td>'
					} else {
						brandId = '<div style="text-align:center"><b><label id=brndId' + i + '>' + qaList[i].brnd_id + '</label><b/></div></td>'
					}

					html.push('<tr>');
					html.push('<td><input type="checkbox" name=check id=\'check' + i + '\' title="' + qaList[i].qa_id + '" value="' + qaList[i].qa_id + '"></td>');
					html.push('<td id=qaId' + i + ' style="display:none">' + qaList[i].qa_id + '</td>');	//qaID
					html.push('<td><input id=chgCateCd' + i + ' type="text" style="width:100%; font-size:12px" value="' + qaList[i].chg_cate_cd + '" onclick="setCheck(\'check' + i + '\')" onkeyup="setCheck(\'check' + i + '\')"><br>'); //CM이 변경하는 카테고리
					html.push('<td>' + qaList[i].dn_rnk + '</td>');	//D사순위
					html.push('<td>' + qaList[i].reg_date + '</td>');	//등록일
					html.push('<td>' + qaList[i].dn_gr_catl_id + '</td>');	//그룹번호
					html.push('<td><input id=modelNm' + i + ' type="text" style="width:100%" value="' + qaList[i].model_nm + '" onclick="setCheck(\'check' + i + '\')" onkeyup="setCheck(\'check' + i + '\')"><br>');		//가상모델명
					html.push('<input type="button" style="width:100%" onclick="qaPlListPop(\'' + qaList[i].qa_id + '\', ' + i + ')" value="PLNO 확인" /><br>');		// QA Pl 리스트 popup
					html.push('<div style="text-align: right"><b><label id=\'plCnt' + i + '\'>선택 상품 수 : '+ qaList[i].cnt + '</label></div></td>');	// 선택 상품 수
					html.push('<td><input id=dnCndNm' + i + ' type="text" style="width:100%" value="' + qaList[i].dn_cnd_nm + '" onclick="setCheck(\'check' + i + '\')" onkeyup="setCheck(\'check' + i + '\')"><br>');		//조건명
					html.push('<td><input id=mkrNm' + i + ' onclick=brndMkrPop(' + i + ') type="text" style="width:100%" value="' + qaList[i].mkr_nm + '"<br>');		//제조사
					html.push(mkrId);		//제조사id
					html.push('<td><input id=brndNm' + i + ' onclick=brndMkrPop(' + i + ') type="text" style="width:100%" value="' + qaList[i].brnd_nm + '"<br>');		//브랜드
					html.push(brandId);		//브랜드id
					html.push('<td>' + qaList[i].enr_atr + '</td>');	//연결된속성
					html.push('<td>' + qaList[i].dn_atr + '</td>');	//D사속성
					html.push('<td><input id=dnVolume' + i + ' type="text" style="width:100%" value="' + qaList[i].dn_volume + '" onclick="setCheck(\'check' + i + '\')" onkeyup="setCheck(\'check' + i + '\')"><br>');		//용량
					html.push('<td><input id=dnCpVlu' + i + ' type="text" style="width:100%" value="' + qaList[i].dn_cp_vlu + '" onclick="setCheck(\'check' + i + '\')" onkeyup="setCheck(\'check' + i + '\')"><br>');		//환산
					html.push('<td><input id=dnQnt' + i + ' type="text" style="width:100%" value="' + qaList[i].dn_qnt + '" onclick="setCheck(\'check' + i + '\')" onkeyup="setCheck(\'check' + i + '\')"><br>');		//수량
					html.push('<td><input id=dnCpUnt' + i + ' type="text" style="width:100%" value="' + qaList[i].dn_cp_unt + '" onclick="setCheck(\'check' + i + '\')" onkeyup="setCheck(\'check' + i + '\')"><br>');		//단위
					html.push('<td>' + modelCheckBox + '</td>');
					html.push('</tr>');
				}

				$('#pageList').html(createPageList(page, totalPages));
				$('#totModelCnt').text("총 모델수: " + totModelCnt);
				$('#totPlCnt').text("총 매칭상품수: " + getPlCount());

				//$('#tbody1').append($(html.join('')));
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
			returnData = "error";
			closeLoadingWithMask();
		}
	});
}

function getPlCount() {
	var result = 0;
	var cateCode = getSelectBoxCateCode(4);
	var isNonAtt = document.getElementById("nonAttList").checked;			// 속성 미등록 리스트
	var isNonBrndMkr = document.getElementById("nonBrndMkrList").checked;	// 브랜드/제조사 미등록 리스트

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/qa-admin/plCount",
		contentType: 'application/json',
		data: {
			"cateCode": cateCode,
			"isNonAtt": isNonAtt,
			"isNonBrndMkr": isNonBrndMkr
		},
		dataType: "json",
		async: false,
		success: function(count) {
			result = count;
			closeLoadingWithMask();
		},
		error: function(request, status, error) {
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
			copyToClipboard(request.responseText);
			result = -1;
			closeLoadingWithMask();
		}
	});

	return result;
}

// 페이지 리스트 생성
function createPageList(nowPage, totPage) {

	var prev = "";
	var next = "";
	var startPage = "";

	// 페이지 리스트의 시작 페이지 번호
	if (Number(nowPage % 5) == 0) {
		startPage = Number(nowPage - 4);
	} else {
		startPage = Number(nowPage - ((nowPage % 5) - 1));
	}

	if (startPage <= 5) {
		prev = "<li><a href='#' style='color: gray; font-size: 1em;'>PREV</a></li>";
	} else {
		prev = "<li><a href='#' style='color: black; font-size: 1em;' onclick='setQaList(" + Number(startPage - 5) + ")'>PREV</a></li>";
	}

	if (startPage + 5 > totPage) {
		next = "<li><a href='#' style='color: gray; font-size: 1em;'>NEXT</a></li>";
	} else {
		next = "<li><a href='#' style='color: black; font-size: 1em;' onclick='setQaList(" + Number(startPage + 5) + ")'>NEXT</a></li>";
	}

	var html = "<ul>";
	html += prev;

	for (var page = startPage; page <= totPage; page++) {

		if (page == nowPage) {
			html += '<li><a href="#" style="color: blue; font-size: 1em; font-weight: bold;" onclick="setQaList(' + page + ')">' + page + '</a></li>';
		} else {
			html += '<li><a href="#" style="color: black; font-size: 1em;" onclick="setQaList(' + page + ')">' + page + '</a></li>';
		}

		if (startPage + 4 == page) {	// 5개까지만 리스트에 보여준다.
			break;
		}
	}

	html += next;
	html += "</ul>";

	return html;
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

// 체크박스 자동 체크
function setCheck(id) {
	var check = document.getElementById(id);
	check.checked = true;
}

// 체크박스 최대 한개만 체크처리
function oneCheck(element, name, index) {
	var isCheck = element.checked;	// 체크박스 체크 여부

	if (isCheck) {	// 체크시 전부 체크해제 후 자신을 체크함
		checkboxes = document.getElementsByName(name);
		//checkboxes.forEach((cb) => {
		// IE11에서 안되서 아래처럼 함
		checkboxes.forEach(function forEachFunction(cb) {
			cb.checked = false;
		})

		element.checked = true;
		setCheck("check" + index);	// 자동체크
	}
}

function brndMkrPop(index) {

	var cateCode = getSelectBoxCateCode(2);	// select box 카테고리 가져오기

	if (cateCode.length == 0 ) {
		alert("카테고리를 선택해 주세요.")
		return false;
	}

	var width = '900';
    var height = '600';

    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
    var left = Math.ceil((window.screen.width - width) / 2 + window.screenLeft);
    var top = Math.ceil((window.screen.height - height) / 2);

	// 팝업띄움
	window.open(getContextPath() + "/dcrawling/popup/brndMkr?cateCode=" + cateCode + "&index=" + index, "brndMkrSearch", "width="+width+",height="+height+",top="+top+",left="+left);
	setCheck("check" + index);	// 자동체크
}

function qaModelDel() {

	var qaIds = new Array();

	if (!confirm("체크한 모델을 삭제하시겠습니까?")) {
		return;
	}

	// 전체 체크 순회
	$("input:checkbox[name=check]").each(function() {
	 	if (this.checked) {
			$(this).parents("tr").remove();	// row 삭제
			qaIds.push(this.title);
		}
	});

	loadingWithMask();	// 로딩 처리

	jQuery.ajax({
		type: "DELETE",
		url: getContextPath() + "/dcrawling/qa-admin/" + qaIds,
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

	setQaList(1);
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

function qaModelSave() {

	var qaInfos = new Array();

	if (!confirm("체크한 모델을 변경하시겠습니까?")) {
		return;
	}

	// 전체 체크 순회
	$("input:checkbox[name=check]").each(function(index, item) {
	 	if (item.checked) {
			var map = {};
			var chgCateCd = document.getElementById("chgCateCd" + index).value.trim();
			var mkrNm = document.getElementById("mkrNm" + index).value.trim();
			var brndNm = document.getElementById("brndNm" + index).value.trim();
			var mkrId = document.getElementById("mkrId" + index).textContent;
			var brndId = document.getElementById("brndId" + index).textContent;
			var dnVolume = document.getElementById("dnVolume" + index).value;
			var dnQnt = document.getElementById("dnQnt" + index).value;

			map.qaId = item.title;
			map.chgCateCd = chgCateCd;
			map.modelNm = document.getElementById("modelNm" + index).value;
			map.dnCndNm = document.getElementById("dnCndNm" + index).value;
			map.mkrNm = mkrNm;
			map.mkrId = mkrNm == "" ? "0" : mkrId == "등록필요" ? "0" : mkrId;
			map.brndNm = brndNm;
			map.brndId = brndNm == "" ? "0" : brndId == "등록필요" ? "0" : brndId;
			map.dnVolume = dnVolume == "" ? "0" : dnVolume;
			map.dnCpVlu = document.getElementById("dnCpVlu" + index).value;
			map.dnQnt = dnQnt == "" ? "0" : dnQnt;
			map.dnCpUnt = document.getElementById("dnCpUnt" + index).value;
			map.updEmpId = userId;

			if (chgCateCd.length % 2 == 1) {
				alert(chgCateCd + " 카테고리 코드를 확인해 주세요.");
				return;
			}

			qaInfos.push(map);
		}
	});

	var jsonData = "{ \"qaInfos\": " + JSON.stringify(qaInfos) + "}";

	loadingWithMask();	// 로딩 처리

	jQuery.ajax({
		type: "PUT",
		url: getContextPath() + "/dcrawling/qa-admin; charset=utf-8",
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

	setQaList(1);
}

function qaModelSubmit() {

	var checkQaIds = new Array();

	if (!confirm("체크한 모델을 신규 생성하시겠습니까?")) {
		return;
	}

	loadingWithMask();	// 로딩 처리

	// 전체 체크 순회
	$("input:checkbox[name=check]").each(function(index, item) {
	 	if (item.checked) {
			var map = {};
			var qaId = item.title;

			jQuery.ajax({
				type: "GET",
				url: getContextPath() + "/dcrawling/qa-admin/id/" + qaId,
				contentType: "application/json",
				dataType: "json",
				//dataType: "jsonp",
				async: false,
				success: function(qaInfo) {
					if (qaInfo != "") {
						map.qaId = qaId;
						map.modelNo = "";
						map.modelNm = qaInfo.modelNm;
						map.cndNm = qaInfo.dnCndNm;
						map.mkrId = qaInfo.mkrId;
						map.brndId = qaInfo.brndId;
						map.chgCateCd = qaInfo.chgCateCd;
						map.dnVolume = qaInfo.dnVolume == "" || qaInfo.dnVolume == "0.0" ? "0" : qaInfo.dnVolume;	//용량
						map.dnQnt = qaInfo.dnQnt != ""? qaInfo.dnQnt : "0";	// 수량
						map.dnCpVlu = qaInfo.dnCpVlu;
						map.dnCpUnt = qaInfo.dnCpUnt;

						$("input:checkbox[name=" + qaId + "]").each(function() {
				 			if (this.checked) {
								map.modelNo = this.value;
							}
						});
					}
				},
				error: function(request, status, error) {
					console.log("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 로그
					copyToClipboard(request.responseText);
				}
			});

			checkQaIds.push(map);
		}
	});

	var jsonData = "{ \"qaIds\": " + JSON.stringify(checkQaIds) + ", \"userId\":" + JSON.stringify(userId) + "}";

	jQuery.ajax({
		type: "POST",
		url: getContextPath() + "/dcrawling/qa-admin",
		contentType: "application/json",
		data: jsonData,
		dataType: "json",
		async: false,
		success: function(result) {
			alert(result + "건 사이트 반영 완료.");
			closeLoadingWithMask();
		},
		error: function(request, status, error) {
			closeLoadingWithMask();
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
			console.log(request.responseText);
		}
	});

	setQaList(1);
}

function qaPlListPop(qaId, index) {

	var width = '1300';
    var height = '800';

    // 팝업을 가운데 위치시키기 위해 아래와 같이 값 구하기
    var left = Math.ceil((window.screen.width - width) / 2 + window.screenLeft);
    var top = Math.ceil((window.screen.height - height) / 2);

	// 팝업띄움
	window.open(getContextPath() + "/dcrawling/popup/qaPl?qaId=" + qaId + "&plCntId=plCnt" + index, "qaPlSetting", "width="+width+",height="+height+",top="+top+",left="+left);
	setCheck("check" + index);	// 자동체크

}