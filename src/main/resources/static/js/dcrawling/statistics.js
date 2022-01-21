var userId = "";
var cateInfos;

/* 화면 로드 시 */
$(document).ready(function() {
	userId = sessionStorage.getItem("userId");
	initDatePicker("datepicker");
	setCate('0');
});

// 달력
function initDatePicker(domobjid) {
	var datepickerSettings = {
		changeMonth: true,
		changeYear: true,
		dayNames: ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일'],
		dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
		monthNamesShort: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'],
		monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		dateFormat: 'yy-mm-dd',
		showMonthAfterYear: true
	};

	$.datepicker.setDefaults($.datepicker.regional['ko']);

	$('[name=datepicker]').datepicker({
		showButtonPanel: true
	}).on("change", function() {
		var reg = /\d{4}\-\d{2}\-\d{2}/;
		var reg2 = /\d{8}/;
		if (reg2.exec($(this).val())) {
			var str = $(this).val();
			str = str.substr(0, 4) + '-' + str.substr(4, 2) + '-' + str.substr(-2);
			$(this).datepicker("setDate", str);
		}
		if (!(reg.exec($(this).val()))) {
			$(this).val('');
		}
	});
	$('#sDate').on("change", function() {
		var date = $(this).datepicker("getDate");
		date.setDate(date.getDate() - 365);
		//$('#searchSDate2').datepicker("setDate", date);
	});
	$('#eDate').on("change", function() {
		var date = $(this).datepicker("getDate");
		if (date) {
			date.setDate(date.getDate() - 365);
		}
		//$('#searchEDate2').datepicker("setDate", date);
	});
	$(function() {
		$('#' + domobjid).datepicker(datepickerSettings);


	});
}

function setListNull() {
	var tbodyObj = $("#tbody1");
	var html = "";

	html += "<tr>";
	html += "	<td colspan=\"14\">데이터가 없습니다.</td>";
	html += "</tr>";

	tbodyObj.html(html);
}

// QA 테이블 조회
function showList() {
	var cateCode = getSelectBoxCateCode(4);
	var sDate = document.getElementById("sDate").value;
	var eDate = document.getElementById("eDate").value;

	loadingWithMask();	// 로딩 처리

	if (!cateInfos) {	// 카테고리 정보 없으면 가져온다.
		getCateInfo();
	}

	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/statistics",
		contentType: "application/json",
		data: {
			"cateCode": cateCode,
			"sDate": sDate,
			"eDate": eDate
		},
		dataType: "json",
		//dataType: "jsonp",
		async: false,
		success: function(json) {
			var html = [];
			var date = "";

			if (sDate == eDate) {
				date = sDate;
			}

			var totColcQaCnt = 0;		//총 누적 모델수 합계
			var totColcQaMtcCnt = 0;	//총 누적 상품수 합계
			var totUprcsQaCnt = 0;		//잔여 모델수 합계
			var totUprcsQaMtcCnt = 0;	//잔여 상품수 합계
			var totAddModelCnt = 0;		//신규생성 모델수 합계
			var totAddModelMtcCnt = 0;	//신규생성 모델의 매칭 상품수 합계
			var totUpdModelCnt = 0;		//정보갱신 모델수 합계
			var totUpdModelMtcCnt = 0;	//정보 갱신 모델의 매칭 상품수 합계


			if (json) {
				for (var i = 0; i < json.length; i++) {

					html.push('<tr>');
					html.push('<td>' + date + '</td>');	//날짜
					html.push('<td>' + json[i].cateCd + '</td>');	//카테고리코드
					html.push('<td>' + cateInfos[json[i].cateCd] + '</td>');	//카테고리명
					html.push('<td>' + json[i].colcQaCnt + '</td>');	//총 누적 모델수
					html.push('<td>' + json[i].colcQaMtcCnt + '</td>');	//총 누적 상품수
					html.push('<td>' + json[i].uprcsQaCnt + '</td>');	//잔여 모델수
					html.push('<td>' + json[i].uprcsQaMtcCnt + '</td>');	//잔여 상품수
					html.push('<td>' + json[i].addModelCnt + '</td>');	//신규생성 모델수
					html.push('<td>' + json[i].addModelMtcCnt + '</td>');	//신규생성 모델의 매칭 상품수
					html.push('<td>' + json[i].updModelCnt + '</td>');	//정보갱신 모델수
					html.push('<td>' + json[i].updModelMtcCnt + '</td>');	//정보 갱신 모델의 매칭 상품수

					html.push('</tr>');

					// 합계계산
					totColcQaCnt += json[i].colcQaCnt;
					totColcQaMtcCnt += json[i].colcQaMtcCnt;
					totUprcsQaCnt += json[i].uprcsQaCnt;
					totUprcsQaMtcCnt += json[i].uprcsQaMtcCnt;
					totAddModelCnt += json[i].addModelCnt;
					totAddModelMtcCnt += json[i].addModelMtcCnt;
					totUpdModelCnt += json[i].updModelCnt;
					totUpdModelMtcCnt += json[i].updModelMtcCnt;
				}

				if (html == "") {
					setListNull();
				} else {
					// 합계부분
					html.push('<tr>');
					html.push('<td colspan="3" scope="colgroup" align="center"><span style="font-weight:bold;font-size:1.2em">합계</span></td>');
					html.push('<td><span style="font-weight:bold;font-size:1.2em">' + totColcQaCnt + '</span></td>');
					html.push('<td><span style="font-weight:bold;font-size:1.2em">' + totColcQaMtcCnt + '</span></td>');
					html.push('<td><span style="font-weight:bold;font-size:1.2em">' + totUprcsQaCnt + '</span></td>');
					html.push('<td><span style="font-weight:bold;font-size:1.2em">' + totUprcsQaMtcCnt + '</span></td>');
					html.push('<td><span style="font-weight:bold;font-size:1.2em">' + totAddModelCnt + '</span></td>');
					html.push('<td><span style="font-weight:bold;font-size:1.2em">' + totAddModelMtcCnt + '</span></td>');
					html.push('<td><span style="font-weight:bold;font-size:1.2em">' + totUpdModelCnt + '</span></td>');
					html.push('<td><span style="font-weight:bold;font-size:1.2em">' + totUpdModelMtcCnt + '</span></td>');
					html.push('</tr>');
					$("#tbody1").html($(html.join('')));
				}
			} else {
				setListNull();
			}
			closeLoadingWithMask();
		},
		error: function(request, status, error) {
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
			returnData = "error";
			closeLoadingWithMask();
		}
	});
}

// 운영중인 카테고리 전부 가져옴
function getCateInfo() {
	jQuery.ajax({
		type: "GET",
		url: getContextPath() + "/dcrawling/common/cate",
		dataType: "json",
		//dataType: "jsonp",
		async: false,
		success: function(json) {
			if (json) {
				/*for (var key in json) {
					console.log(key + ":" + json[key]);
				}*/
				cateInfos = json;
			}
		},
		error: function(request, status, error) {
			alert("code = " + status + "\r\n" + "message = " + request.responseText + "\r\n" + "error = " + error); // 실패 시 처리
			returnData = "error";
		}
	});
}