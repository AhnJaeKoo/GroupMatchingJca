<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>그룹매칭 필터 테스트 페이지</title>
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
<script src="${pageContext.request.contextPath}/js/dcrawling/util/gmJcaUtil.js"></script>
<script src="${pageContext.request.contextPath}/js/gm/business.js"></script>
</head>
<body>

<div class="container1" style="margin-top:30px; ">
	<div class="row">
		<div class="col-sm-12">
			<div style="text-align: left;">
				<div style="display:inline-block;padding-top:10px;">
			        <select class="sel" name="selectBox" id="selectBox" onChange="">
						<option value="b">이동키워드</option>
					</select>
					모델명 : <input type="text" name="modelNm" id="modelNm" style="width: 390px; margin-left: 5px">
					카테고리 : <input type="text" name="cateCode" id="cateCode" style="width: 100px; margin-left: 5px">
					<div class=divBtnLeftBlue onclick="runProcess()" alt="조회" title="조회" id="runProcess">조회 =></div>
					<label id="result"></label>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>