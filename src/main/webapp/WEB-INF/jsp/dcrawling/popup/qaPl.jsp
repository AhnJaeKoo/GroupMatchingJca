<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>크롤링 상품리스트</title>
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
<script src="${pageContext.request.contextPath}/js/dcrawling/popup/qaPl.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/util/gmJcaUtil.js"></script>
</head>
<body>
	<div style="margin-top: 30px; margin-left: 10px; width: 98%; ">
		<%-- left --%>
		<div class="left" style="width: 100%; float: left;">
			<h2>크롤링 상품 리스트</h2>
			<div style="padding-top:20px; width: 100%">
				<td><B><label id="pl_cnt">크롤링 상품 수 : </label></td>
				<div class=divBtnLeftBlue onclick="save()" alt="저장" title="저장" id="btnMkrFind" style="width: 40px; float: right">저장</div>
		    </div>
			<%-- 목록 - Start --%>
	        <div style="margin-top:20px;" id="listDiv1">
				<table class="tblBody2" id="qa_pl_header" style="border-collapse:collapse; width:100%">
					<thead>
						<tr>
							<th style="width: 5%"><input type="checkbox" id="checkAll" onClick="checkAll(this)"></th>
							<th style="width: 15%"><b>PLNO</b></th>
							<th style="width: 15%"><b>매칭 모델번호</b></th>
							<th style="width: 50%"><b>상품명</b></th>
							<th style="width: 15%"><b>상품 단종코드</b></th>
						</tr>
					</thead>
					<!-- <tbody id="tbody1" style="overflow-y:scroll; overflow-x:hidden; float:left; width:100%; height:90%"</tbody> -->
					<tbody id="tbody1">	</tbody>
				</table>
	        </div>
	        <%-- 목록 - End --%>
		</div>
		<%-- left end --%>
	</div>
</body>
</html>