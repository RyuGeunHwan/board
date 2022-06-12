<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/resources/static/css/board.css">
</head>
<body>
    <div class="container">
        <!-- 글 작성 팝업 -->
        <div class="write-popup">
            <div class="editor">
                <div class="input-box">
                    <label for="studentsName">작성자 : </label>
                    <input id="studentsName" type="text" placeholder="본인 이름" readonly>
                </div>
                <div class="input-box">
                    <label for="title">제목 : </label>
                    <input id="title" type="text" placeholder="제목을 입력하세요...">
                </div>
                <div class="input-box">
                    <textarea id="content" rows="10" cols="65" placeholder="내용을 간단히 적어주세요..."></textarea>
                </div>
                <div class="btn-area">
                    <a href="#" class="btn-cancel">취소</a>
                    <a id="contentSubmit" href="#" class="btn-success">등록</a>
                </div>
            </div>
        </div>
        <!-- 글 작성 수정 -->
        <div class="update-popup">
            <div class="editor">
                <div class="close">
                    <a href="#" class="btn-close">닫기</a>
                </div>
                <div class="input-box">
                    <label for="title">제목 : </label>
                    <input id="upt-title" type="text" placeholder="제목을 입력하세요...">
                </div>
                <div class="input-box">
                    <textarea id="upt-content" rows="10" cols="65" placeholder="내용을 간단히 적어주세요..."></textarea>
                </div>
                <div class="btn-area">
                    <input id="boardIdHidden" type="hidden">
                    <a id="contentUpdate" href="#" class="btn-update">수정</a>
                    <a id="contentDelete" href="#" class="btn-delete">삭제</a>
                </div>
            </div>
        </div>
        <div class="navigation">
            <ul>
                <li>
                    <a href="#">
                        <span class="icon"><ion-icon name="logo-apple"></ion-icon></span>
                        <span class="title">DW Board</span>                
                    </a>
                </li>
                <li>
                    <a href="/board?pageNum=1&pageSize=5">
                        <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
                        <span class="title" >Dashboard</span>                
                    </a>
                </li>
                <li>
                    <a href="/students?pageNum=1&pageSize=5">
                        <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
                        <span class="title" >Students</span>                
                    </a>
                </li>
                <li>
                    <a href="/logs?pageNum=1&pageSize=50">
                        <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
                        <span class="title" >logs</span>                
                    </a>
                </li>
                <li>
                    <a href="/login">
                        <span class="icon"><ion-icon name="log-out-outline"></ion-icon></span>
                        <span class="title" >Sign Out</span>                
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- main -->
    <div class="main">
        <div class="topbar">
            <div class="toggle">
                <!-- toggle은 나중에 만들기 -->
                <ion-icon name="menu-outline"></ion-icon>
            </div>
            <!-- search -->
            <div class="search">
                <label>
                    <input id="searchBar" type="text" placeholder="작성자를 검색하세요...">
                    <!-- 유의 사항 : 새로고침 하기 전까지 value에 값이 들어가 있음. -->
                    <input id="keyword" type="hidden" value="null">
                </label>
            </div>
            <div>
                <a href="#" class="logout">Logout</a>
            </div>
        </div>
         <!-- cards -->
         <div class="cardBox">
             <div class="card">
                 <div>
                    <div id="studentsCnt" class="numbers">1,400</div>
                    <div class="cardName">학생 수</div>
                 </div>
                 <div class="iconBx">
                    <ion-icon name="school-outline"></ion-icon>
                 </div>
             </div>
             <div class="card">
                <div>
                   <div id="boardCnt" class="numbers">500</div>
                   <div class="cardName">게시글 수</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="book-outline"></ion-icon>
                </div>
            </div>
            <div class="card">
                <div>
                   <div id="writerCnt" class="numbers">300</div>
                   <div class="cardName">작성자 수</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="code-slash-outline"></ion-icon>
                </div>
            </div>
            <div class="card">
                <div>
                   <div id="viewsCnt" class="numbers">2,800</div>
                   <div class="cardName">총 조회 수</div>
                </div>
                <div class="iconBx">
                    <ion-icon name="eye-outline"></ion-icon>
                </div>
            </div>
         </div>
         <!-- table -->
         <div class="details">
             <div class="recentOrders">
                 <div class="cardHeader">
                     <h2>Board List</h2>
                     <a href="#" class="btn">글 작성</a>
                 </div>
                 <table>
                     <thead>
                         <tr>
                            <th>게시판 번호</th>
                            <th>작성자</th>
                            <th>제목</th>
                            <th>수정 날짜</th>
                            <th>작성 날짜</th>
                            <th>조회 수</th>
                         </tr>
                     </thead>
                     <tbody id="boardData">
                     <c:choose>
                     <c:when test="${fn:length(pageHelper.list) > 0}">
                     <c:forEach items="${pageHelper.list}" var="item">
                        <tr onclick="getBoard(${item.boardId})">
	                        <td>${item.boardId}</td>
	                        <td>${item.studentsName}</td>
	                        <td>${item.title}</td>
	                        <td>${item.updateAt}</td>
	                        <td>${item.createAt}</td>
	                        <c:if test="${item.cnt<10}">
	                        <td><span class="row">${item.cnt}</span></td>
	                        </c:if>
	                        <c:if test="${item.cnt>=10 && item.cnt<20}">
	                        <td><span class=middle>${item.cnt}</span></td>
	                        </c:if>
	                        <c:if test="${item.cnt>30}">
	                        <td><span class="high">${item.cnt}</span></td>
	                        </c:if>
                        </tr>
                        </c:forEach>
                     </c:when>
                     <c:otherwise>'<tr><td colspan=6 style="text-align:center;">게시글이 없습니다.</td></tr>'</c:otherwise>
                     </c:choose>
                         <!-- <tr>
                             <td>1</td>
                             <td>현상원</td>
                             <td>점심시간이 너무 짧아요!</td>
                             <td>2022-05-19</td>
                             <td>2022-05-18</td>
                             <td><span class="high">8320</span></td>
                         </tr>
                         <tr>
                            <td>2</td>
                            <td>현상원</td>
                            <td>학원에 커피기계가 없어요!</td>
                            <td>2022-05-19</td>
                            <td>2022-05-18</td>
                            <td><span class="middle">1200</span></td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>현상원</td>
                            <td>너무 졸려요...</td>
                            <td>2022-05-19</td>
                            <td>2022-05-18</td>
                            <td><span class="row">5</span></td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>현상원</td>
                            <td>안녕하세요!</td>
                            <td>2022-05-19</td>
                            <td>2022-05-18</td>
                            <td><span class="row">22</span></td>
                        </tr> -->
                     </tbody>
                 </table>
                 <div class="pagination">
                    <!-- <a href="#">Previous</a>
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">Next</a> -->
                    <!-- 페이징 구현 -->
                    <c:if test="${pageHelper.hasPreviousPage}">
                    	<a onclick="getBoardList(${pageHelper.pageNum-1}, 5)" href="#">Previous</a>
                    </c:if>
                    <c:forEach begin="${pageHelper.navigateFirstPage}" end="${pageHelper.navigateLastPage}"  var="pageNum">
	                    <a id="pageNum${pageNum}" onclick="getBoardList(${pageNum}, 5)">
	                    ${pageNum}
	                    </a>
                    </c:forEach>
                    <c:if test="${pageHelper.hasNextPage}">
                     	<a onclick="getBoardList(${pageHelper.pageNum+1}, 5)" href="#">Next</a>
                    </c:if>
                 </div>
                 <input id="nowPageNum" type="hidden" value="${pageHelper.pageNum}">
             </div>
         </div>
</body>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

<script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
<script>
    $('.btn').click(function(){ // 글작성 버튼 누르면 글작성 팝업창 열기
        $('.write-popup').css('display', 'block');
    });
    $('.btn-cancel').click(function(){ //글 작성 취소버튼 누르면 팝업창 닫기
        $('.write-popup').css('display', 'none');
    });
    $('.btn-close').click(function(){ // 글 수정 팝업 닫기 누르면 팝업창 닫기
    	location.reload(); //새로고침
    });
    let list = document.querySelectorAll('.navigation li');
    function activeLink(){
        list.forEach((item) => {item.classList.remove('hovered')});
        this.classList.add('hovered');
    }
    list.forEach((item) => {item.addEventListener('mouseover',activeLink)});
    function returnPageTwo(){//2페이지로 돌아가기
        location.href = '../index.html'
        console.log('hello word')
 }
</script>
<script>
	getPageNum();//페이지 번호를 알아내는 함수
	getBoardStatistics(); //통계함수 호출
	
	function goPage(pageName){
		if(pageName == 'logs'){
			location.href = "/logs";
		}
		
	if(pageName == 'students'){
		location.href = "/students";
		}
		
	if(pageName == 'dashboard'){
		location.href = "/board?pageNum=1&pageSize=5";
	}
	}
	
	//게시판 통계 함수
    function getBoardStatistics(){
    $.ajax({
    url : '/api/v1/board/statistics',
    type : 'GET',
    dataType : 'json', //서버결과를 json으로 응답받겠다.
    success: function(response){
        // input의 value가 아니라 div의 값이라 text()를 붙여준다.
        // text() or html() : input을 제외한 태그의 값을 컨트롤할 때 사용.
        // val() : input을 컨트롤할 때 사용.
        $('#boardCnt').text(response.boardCnt);
        $('#studentsCnt').text(response.studentsCnt);
        $('#writerCnt').text(response.writerCnt);
        $('#viewsCnt').text(response.viewsCnt);
    }, error : function(request, statis, error){
        console.log(error)
    
    }
})
    }

	function getPageNum(){
		var pageNum = $('#nowPageNum').val();
		$('#pageNum'+pageNum).css('backgroundColor','#287bff');
		$('#pageNum'+pageNum).css('color','#fff');
	}

	function getBoardList(pageNum, pageSize){
		location.href="/board?pageNum="+pageNum+"&pageSize="+pageSize;
	}
	
	function getBoard(boardId){ 
	    //boardId : 게시판 번호

	    //boardId html에 hidden 하기

	    //1. 화면 none -> block
	    $('.update-popup').css('display', 'block');

	    //AJAX 작성
	    //1. AJAX를 이용해서 특정 게시물 조회
	    $.ajax({
	    url : '/api/v1/board/boardId/'+boardId,
	    type : 'GET',
	    dataType : 'json', //서버결과를 json으로 응답받겠다.
	    success: function(response){
	        console.log(response)
	        //3. input에 데이터 set해주기!
	        $('#upt-title').val(response.title);
	        $('#upt-content').val(response.content);

	        $('#boardIdHidden').val(boardId); 
	        console.log($('#boardIdHidden').val())
	        //<input id="boardIdHidden" type="hidden" value="">
	        // .val(boardId) : val값에 boardId을 대입해주었기 때문에 input의 value값이 계속 바뀐다.

	        setBoardViews(boardId) // 조회수 카운트 함수
	        // 변수 html에 tr을 클릭하면 ajax를 실행(클릭한 특정게시글 상세보기 함수)하게 되면 해당 게시글 조회수 카운트 증가 함수도 같이 실행(setBoardViews메소드 실행)
	  
	    },error : function(request, statis, error){
	        console.log(error)
	    
	    }
	});
	} //end
	
	// 게시물 클릭시 조회수 카운트
	function setBoardViews(boardId){ //게시판 조회수 증감 함수
	    $.ajax({
	            url : '/api/v1/board/views/boardId/'+boardId,
	            type : 'PATCH', // 해당게시글 클릭하면 조회수 증가하며 업데이트가 되어야함.
	            dataType : 'json', //서버결과를 json으로 응답받겠다.
	            success: function(response){
	                if(response > 0){
	                	// 추후 로직 예정(에러페이지로 이동하는 로직)
	                }
	        },error : function(request, statis, error){
	            console.log(error)
	        }
	    }) // ajax end
	}
	
	// 게시글 작성 함수
    $('#contentSubmit').click(function(){
        if(confirm('게시글을 작성하시겠습니까?')){
        var studentsName = $('#studentsName').val();
        var title = $('#title').val();
        var content = $('#content').val();
        var count = 0;
        var studentsId = 9;

        if(title == ''){
            alert('제목을 작성해주세요')
            $('#title').focus();
            return false;
        }
        if(content == ''){
            alert('내용을 작성해주세요')
            $('#content').focus();
            return false;
        }

        var jsonData = {
            studentsId : studentsId,
            title : title,
            content : content
        }
        $.ajax({
            url : '/api/v1/board',
            type : 'POST',
            contentType : 'application/json',//서버에 json타입으로 보낼 예정(요청).
            dataType : 'json', //서버결과를 json으로 응답받겠다.
            data : JSON.stringify(jsonData),
            success: function(response){
            if(response>0){
             var pageNum = $('#nowPageNum').val();
              getBoardList(pageNum, 10);//게시판 DB 호출 함수
            }
          },error : function(request, statis, error){
            console.log(error)
          }
        }) // ajax end
    }
})

// 2. 게시물 수정하는 함수
$('#contentUpdate').click(function(){
    //1. 게시판 번호 확인
    var boardId = $('#boardIdHidden').val(); //hidden에 숨겨둔 boardId 가져오기.
    console.log("boardId => "+boardId)
    //2. json 생성
    var title = $('#upt-title').val();
    var content = $('#upt-content').val();
    var jsonData = {
        title : title,
        content : content
    }

    //3. 게시글 업데이트
     if(confirm("게시글을 정말 수정하시겠습니까?")){
    $.ajax({
            url : '/api/v1/board/boardId/'+boardId,
            type : 'PATCH',
            contentType : 'application/json',//서버에 json타입으로 보낼 예정(요청).
            dataType : 'json', //서버결과를 json으로 응답받겠다.
            data : JSON.stringify(jsonData),
            success: function(response){
            if(response>0){
            	alert("success patch!");
            	var pageNum = $('#nowPageNum').val();
                getBoardList(pageNum, 10);//게시판 DB 호출 함수
            }
          },error : function(request, statis, error){
            console.log(error)
          }
        }) // ajax end
     }

})

//게시글 삭제
$('#contentDelete').click(function(){
    var boardId = $('#boardIdHidden').val();
    if(confirm("게시글을 정말 삭제하시겠습니까?")){
    $.ajax({
            url : '/api/v1/board/boardId/'+boardId,
            type : 'DELETE',
            dataType : 'json', //서버결과를 json으로 응답받겠다.
            success: function(response){
            if(response>0){
            	alert("success delete!");
            	var pageNum = $('#nowPageNum').val();
                getBoardList(pageNum, 10);//게시판 DB 호출 함수
            }
        },error : function(request, statis, error){
            console.log(error)
        }
    }) // ajax end
    }
})

// 작성자 검색 함수
// 1. keydown : 키보드를 누를 때
// 2. keyup : 누른 키를 뗄 때
// 3. keypress : 키보드를 계속 누르고 있을 때
$('#searchBar').keyup(function(key){// 키보드를 뗄 때 무엇을 눌렀다가 뗐는데 파라미터 key에 들어감.
    //엔터를 누를떄 hello word를 출력하고 싶음.
    var pageNum = 1;
    var pageSize = 5;
    // keycode = 13 은 enter를 의미
    if(key.keyCode == 13){
    //1. input값을 가져옴.
    var search = $('#searchBar').val().trim(); //input에 작성한 작성글을 가져옴
    if(search != ''){
        location.href='/board/search?writer='+search+'&pageNum='+pageNum+'&pageSize='+pageSize;
    }
    
}
});
	
	
</script>
</html>