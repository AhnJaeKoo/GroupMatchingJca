<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<%
LocalDateTime now = LocalDateTime.now();
DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
String today = now.format(dateTimeFormatter);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>D크롤링</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="http://imgenuri.enuri.gscdn.com/images/ui/accordion/jquery-ui.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/paging.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/portal.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script src="${pageContext.request.contextPath}/js/dcrawling/statistics.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/util/cate.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/util/gmJcaUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/util/datepicker/jquery.ui.datepicker-ko.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/util/datepicker/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/util/datepicker/jquery.placeholder.min.js"></script>

</head>
<body>

<%@ include file="include/navigation_bar.jsp" %>

<div class="container1" style="margin-top:30px; ">
	<div class="row">
		<div class="col-sm-12">
	      	<h2>통계</h2>
	      	<div style="display:inline-block;padding-top:10px;">
		        <select class="sel" name="cate1" id="cate1" onChange="setCate('1')">
					<option value="">대카테고리선택</option>
				</select>
	        	<select class="sel" name="cate2" id="cate2" onChange="setCate('2')">
					<option value="">중카테고리선택</option>
				</select>
				<select class="sel" name="cate3" id="cate3" onChange="setCate('3')">
					<option value="">소카테고리선택</option>
				</select>
				<select class="sel" name="cate4" id="cate4">
					<option value="">미카테고리선택</option>
				</select>
				<div style="display:inline-block;padding-left:50px;">
					<span style="display:inline-block; width:40px;"><b>· 기간</b></span>
					<input type="text" name="datepicker" size="" value="<%=today %>" id="sDate" class="datepicker" style="width:150px;height:30px;">
					 ~
					 <input type="text" name="datepicker" size="" value="<%=today %>"  id="eDate" class="datepicker" style="width:150px;height:30px;">
				</div>
				<div class=divBtnLeftBlue onclick="showList()" alt="조회" title="조회" id="show_list">조회</div>
			</div>

			<%-- 목록 - Start --%>
	        <div style="margin-top:7px;" id="listDiv1">
				<table class="tblBody2" id="send_list_table" style="table-layout: fixed">
					<thead>
						<tr>
							<th rowspan="2" scope="rowgroup" style="width: 90px"><b>날짜</b></th>
							<th rowspan="2" scope="rowgroup" style="width: 90px"><b>카테고리코드</b></th>
							<th rowspan="2" scope="rowgroup" style="width: 100px"><b>카테고리명</b></th>
							<th colspan="4" scope="colgroup" style="width: 50%"><b>가생성</b></th>
							<th colspan="4" scope="colgroup" style="width: 50%"><b>처리</b></th>
						</tr>
						<tr>
							<th scope="col"><b>총 누적 모델수</b></th>
							<th scope="col"><b>총 누적 상품수</b></th>
							<th scope="col"><b>잔여 모델수</b></th>
							<th scope="col"><b>잔여 상품수</b></th>

							<th scope="col"><b>신규생성 모델수</b></th>
							<th scope="col"><b>신규생성 모델의 매칭 상품수</b></th>
							<th scope="col"><b>정보갱신 모델수</b></th>
							<th scope="col"><b>정보갱신 모델의 매칭 상품수</b></th>
						</tr>
					</thead>
					<tbody id="tbody1">	</tbody>
					<tfoot>
						<tr>
						    <td style="text-align:center;" colspan=14>
						    	<div id="pageList" style="display:inline;"></div>
						    </td>
						</tr>
					</tfoot>
				</table>
	        </div>
	        <%-- 목록 - End --%>
		</div>
	</div>
</div>
</body>
</html>