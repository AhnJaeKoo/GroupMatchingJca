// url 파싱하여 request 생성
function request(){
    var requestParam = "";

	//getParameter 펑션
	this.getParameter = function(param) {
        //현재 주소를 decoding
        var url = unescape(location.href);
        //파라미터만 자르고, 다시 &그분자를 잘라서 배열에 넣는다.
        var paramArr = (url.substring(url.indexOf("?")+1,url.length)).split("&");
        for(var i = 0 ; i < paramArr.length ; i++){
           var temp = paramArr[i].split("="); //파라미터 변수명을 담음

           if(temp[0].toUpperCase() == param.toUpperCase()){
             // 변수명과 일치할 경우 데이터 삽입
             requestParam = paramArr[i].split("=")[1];
             break;
           }
        }

        return requestParam;
    }
}

function getContextPath() {
	return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}



// 로딩중 표시
function loadingWithMask() {

    //화면의 높이와 너비를 구합니다.
    var maskHeight = window.document.body.clientHeight;
    var maskWidth  = window.document.body.clientWidth;

    //화면에 출력할 마스크를 설정해줍니다.
    var mask       = "<div id='mask' style='position:absolute; z-index:9000; background-color:#000000; display:none; left:0; top:0;'></div>";
    var loadingImg = '';

    loadingImg += "<div id='loadingImg'>";
    loadingImg += " <img src='" + getContextPath() + "/css/images/spinner.gif' style='position: relative; display: block; margin: 0px auto;'/>";
    loadingImg += "</div>";

    //화면에 레이어 추가
    $('body')
        .append(mask)
        .append(loadingImg)

    //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채웁니다.
    $('#mask').css({
            'width' : maskWidth
            , 'height': maskHeight
            , 'opacity' : '0.3'
    });

    //마스크 표시
    $('#mask').show();

    //로딩중 이미지 표시
    $('#loadingImg').show();
}

// 로딩중 종료
function closeLoadingWithMask() {
    $('#mask, #loadingImg').hide();
    $('#mask, #loadingImg').empty();
}
