<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>D크롤링</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/paging.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/portal.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.22/css/jquery.dataTables.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/dcrawling/util/cate.js"></script>
	<script src="${pageContext.request.contextPath}/js/dcrawling/qaAdmin.js"></script>
	<script src="${pageContext.request.contextPath}/js/dcrawling/util/gmJcaUtil.js"></script>
</head>
<body>

<%@ include file="include/navigation_bar.jsp" %>

<div class="container1" style="margin-top:30px; ">
	<div class="row">
		<div class="col-sm-12">
	      	<h2>QA / 반영</h2>
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
				<div style="display:inline-block;padding-left:20px;">
					<label id="sort">정렬순서:</label>
					<select class="sel" name="sortType" id="sortType">
						<option value="1">인기순</option>
						<option value="2">등록일순</option>
					</select>
				</div>
				<div class=divBtnLeftBlue onclick="setQaList(1);" alt="조회" title="조회" id="show_list">조회</div>
			</div>
			<br>
			<div style="display:inline-block;padding-top:10px;">
	      		<label for="nonAttList"><input type="checkbox" id="nonAttList">속성 미등록 리스트</label>
				<label for="nonBrndMkrList"><input type="checkbox" id="nonBrndMkrList">브랜드/제조사 미등록 리스트</label>
	      	</div>
	      	<br>
			<div style="display:inline-block;padding-top:10px;">
				<label id="totModelCnt">총 모델수:</label><br>
				<label id="totPlCnt">총 매칭상품수:</label>
			</div>
			<!-- <div style="display:inline-block;padding-top:10px; padding-left:250px;"> -->
			<div style="text-align: right;">
				<div class=divBtnLeftBlue style="width: 100px" onclick="qaModelDel();" alt="삭제" title="삭제" id="qaModelDel">삭제</div>
				<div class=divBtnLeftBlue style="width: 100px" onclick="qaModelSave();" alt="저장" title="저장" id="qaModelSave">저장</div>
				<div class=divBtnLeftBlue style="width: 200px" onclick="qaModelSubmit();" alt="사이트반영" title="사이트반영" id="qaModelSubmit">사이트반영</div>
			</div>
			<%-- 목록 - Start --%>
	        <div style="margin-top:7px;" id="listDiv1">
				<table class="tblBody2" id="send_list_table" style="table-layout: fixed">
					<thead>
						<tr>
							<th style="width: 30px"><input type="checkbox" id="checkAll" onClick="checkAll(this);"></th>
							<th style="display:none"><b>qaId</b></th>
							<th style="width: 90px"><b>카테고리</b></th>
							<th style="width: 60px"><b>D사순위</b></th>
							<th style="width: 90px"><b>등록일</b></th>
							<th style="width: 70px"><b>그룹번호</b></th>
							<th style="width: 240px"><b>가상모델명</b></th>
							<th style="width: 100px"><b>조건명</b></th>
							<th style="width: 120px"><b>제조사</b></th>
							<th style="width: 120px"><b>브랜드</b></th>
							<th style="width: 120px"><b>연결된속성</b></th>
							<th style="width: 180px"><b>D사속성</b></th>
							<th style="width: 50px"><b>용량</b></th>
							<th style="width: 50px"><b>환산</b></th>
							<th style="width: 50px"><b>수량</b></th>
							<th style="width: 50px"><b>단위</b></th>
							<th style="width: 100%"><b>크롤링으로 찾은 에누리 모델</b></th>
						</tr>
					</thead>
					<tbody id="tbody1">	</tbody>
					<tfoot>
						<tr>
						    <td style="text-align:center;" colspan=17>
						    	<div id="pageList" style="display:inline;"></div>
						    </td>
						</tr>
					</tfoot>
				</table>

	        </div>
	        <%-- 목록 - End --%>
			<!-- <button type="button" class="btn btn-primary" id = "writeBtn">글쓰기</button> -->
		</div>
	</div>
</div>
</body>
</html>