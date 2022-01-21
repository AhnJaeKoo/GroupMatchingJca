<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/util/cate.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/attribute.js"></script>
<script src="${pageContext.request.contextPath}/js/dcrawling/util/gmJcaUtil.js"></script>
</head>
<body>
	<%@ include file="include/navigation_bar.jsp"%>

	<div class="container1" style="margin-top: 30px">
		<div class="row">
			<div class="col-sm-12">
				<h2>속성관리</h2>
				<div style="margin-top: 7px;" id="listDiv1">
					<table class="tblBody2" id="attInfo" style="table-layout: fixed">
						<thead>
							<tr>
								<th style="width: 180px"><b>카테고리</b></th>
								<td style="width: 200px">
									<div style="display: inline-block; padding-top: 0px; margin-left: 5px">
										<select class="sel" name="cate1" id="cate1"	onChange="setCate('1')" style="width: 190px">
											<option value="">대카테고리선택</option>
										</select>
									</div>
								</td>
								<td style="width: 200px">
									<div style="display: inline-block; padding-top: 0px; margin-left: 5px">
										<select class="sel" name="cate2" id="cate2" onChange="setCate('2')" style="width: 190px">
											<option value="">중카테고리선택</option>
										</select>
									</div>
								</td>
								<td style="width: 200px">
									<div style="display: inline-block; padding-top: 0px; margin-left: 5px">
										<select class="sel" name="cate3" id="cate3" style="width: 190px">
											<option value="">소카테고리선택</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<th><b>D사 속성명</b></th>
								<td><input type="text" name="dnAttNm" id="dnAttNm" style="width: 590px; margin-left: 5px"></td>
							</tr>
							<tr>
								<th><b>에누리 속성명(전시용)</b></th>
								<td><input type="text" name="enAttNm" readonly id="enAttNm" onClick="enAttPop('attInfo', 0)" title="" style="width: 590px; margin-left: 5px"></td>
							</tr>
							<tr>
								<th><b>에누리 속성원명</b></th>
								<td><input type="text" name="enAttElNm" readonly id="enAttElNm" onClick="enAttPop('attInfo', 0)" title="" style="width: 590px; margin-left: 5px"></td>
							</tr>
						</thead>
						<tbody id="">
						</tbody>
						<tfoot>
							<tr>
								<td style='text-align: center; height: 5px;' colspan=11>
									<div id="pageList" style='display: inline; text-align: center;'></div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>

				<%-- 상단 정보 버튼  --%>
				<div style="text-align: left;">
					<div class=divBtnLeftBlue style="width: 100px" onclick="getQaList();" alt="조회" title="조회" id="att_list">조회</div>
					<div class=divBtnLeftBlue style="width: 100px" onclick="clearInfo()" alt="초기화" title="초기화" id="enAtt_clear">초기화</div>
				</div>

				<%-- 하단 리스트 버튼  --%>
				<div style="text-align: right;">
					<!-- <div class=divBtnLeftBlue style="width: 100px" onclick="AddRow()" alt="추가" title="추가" id="att_del">추가</div> -->
					<div class=divBtnLeftBlue style="width: 100px" onclick="delDnAtt()" alt="삭제" title="삭제" id="att_del">삭제</div>
					<div class=divBtnLeftBlue style="width: 100px" onclick="saveDnAtt()" alt="저장" title="저장" id="att_save">저장</div>
				</div>

				<%-- 조회 리스트 - Start --%>
				<div style="margin-top: 7px;" id="listDiv1">
					<table class="tblBody2" id="attList" style="table-layout: fixed">
						<thead>
							<tr>
								<th rowspan="2" scope="rowgroup" style="visibility:hidden"><b>등록/변경 type</b></th>
								<th rowspan="2" scope="rowgroup" style="width: 3%"><input type="checkbox"id="checkAll" onClick="checkAll(this)"></th>
								<th rowspan="2" scope="rowgroup" style="width: 5%"><b>카테고리</b></th>
								<th rowspan="2" scope="rowgroup" style="width: 10%"><b>카테고리명</b></th>
								<th rowspan="2" scope="rowgroup" style="width: 17%"><b>D사속성</b></th>
								<th colspan="5" scope="colgroup" style="width: 65%"><b>에누리</b></th>
							</tr>
							<tr>
								<th scope="col"><b>속성ID</b></th>
								<th scope="col"><b>전시용 속성명</b></th>
								<th scope="col"><b>속성원ID</b></th>
								<th scope="col"><b>속성원명</b></th>
								<th scope="col"><b>속성원 범위값</b></th>
							</tr>
						</thead>
						<tbody id="tbody1">
						</tbody>
						<tfoot>
							<tr>
								<td style='text-align: center; height: 40px;' colspan=11>
									<div id="pageList" style='display: inline; text-align: center;'></div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
				<%-- 조회 리스트 - End --%>
			</div>
		</div>
	</div>
</body>
</html>