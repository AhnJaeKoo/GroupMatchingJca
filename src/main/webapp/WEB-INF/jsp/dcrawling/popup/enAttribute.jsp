<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>D크롤링</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/paging.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/portal.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/popup/enAttribute.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/util/gmJcaUtil.js"></script>

</head>
<body>
	<div style="margin-top: 30px; margin-left: 10px; width: 100%; ">
		<%-- left --%>
		<div class="left" style="width: 55%; float: left;">
			<h2>전시용 속성</h2>
			<div style="padding-top:10px; width: 100%">
		        <td><input type="text" name="enAtt" id="enAtt" onkeyup="enterkey(this)" style="width: 34%;"></td>
				<div class=divBtnLeftBlue onclick="getEnAttList()" alt="조회" title="조회" id="btnEnAttFind" style="width: 40px;">조회</div>
			</div>
			<%-- 목록 - Start --%>
	        <div style="margin-top:7px;" id="listDiv1">
				<!-- <table class="tblBody2" id="att_table_header" style="table-layout: fixed">
					<thead>
						<tr>
							<th style="width: 50%"><b>ID</b></th>
							<th style="width: 50%"><b>전시용 속성명</b></th>
						</tr>
					</thead>
					<tbody id="enAtt_list" >	</tbody>
				</table> -->
				<table class="tblBody2" id="att_table_header" style="border-collapse:collapse; width:100%">
					<thead>
						<tr>
							<th style="width: 20%"><b>ID</b></th>
							<th style="width: 20%"><b>카테고리</b></th>
							<th style="width: 30%"><b>관리용 속성명</b></th>
							<th style="width: 30%"><b>전시용 속성명</b></th>
						</tr>
					</thead>
				</table>
				<table class="tblBody2" style="border-collapse:collapse; width:100%">
					<tbody id="enAtt_list" style="overflow-y:scroll; overflow-x:hidden; float:left; width:100%; height:370px"</tbody>
				</table>
	        </div>
	        <%-- 목록 - End --%>
		</div>
		<%-- left end --%>

		<%-- right --%>
		<div class="right" style="width: 40%; float: left; margin-left:30px;">
			<h2>속성원</h2>
			<div style="padding-top:10px; width: 100%">
		        <td><input type="text" name="enAttEl" id="enAttEl" onkeyup="enterkey(this)" style="width: 47%;"></td>
				<div class=divBtnLeftBlue onclick="getEnAttElList();" alt="조회" title="조회" id="btnEnAttElFind" style="width: 40px;">조회</div>
				<div class=divBtnLeftBlue onclick="setParent()" alt="입력" title="입력" id="btnEnAttAdd" style="width: 40px;">입력</div>
			</div>
			<%-- 목록 - Start --%>
			<div style="margin-top:7px;" id="listDiv2">
				<table class="tblBody2" id="attEl_table_header" style="border-collapse:collapse; width:100%">
					<thead>
						<tr>
							<th style="width: 12%"></th>
							<th style="width: 40%"><b>ID</b></th>
							<th style="width: 48%"><b>속성원명</b></th>
						</tr>
					</thead>
					<table class="tblBody2" style="border-collapse:collapse; width:100%">
						<tbody id="enAttEl_list" style="overflow-y:scroll; overflow-x:hidden; float:left; width:100%; height:370px"</tbody>
					</table>
				</table>
	        </div>
			<%-- 목록 - End --%>
		</div>
		<%-- right end --%>
	</div>

	<input type="hidden" id="hdEnAttId" name="hdEnAttId" value="">
	<input type="hidden" id="hdEnAttNm" name="hdEnAttNm" value="">
	<input type="hidden" id="hdEnAttElId" name="hdEnAttElId" value="">
	<input type="hidden" id="hdEnAttElNm" name="hdEnAttElNm" value="">
	<input type="hidden" id="hdUseClassCode" name="hdUseClassCode" value="">
</body>
</html>