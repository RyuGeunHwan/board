<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Dashboard</title>
    <link rel="stylesheet" href="/resources/static/css/board.css" />
  </head>
  <body>
    <div class="container">
      <!-- 학생 정보 수정 -->
      <div class="update-popup">
        <div class="editor">
          <div class="close">
            <a href="#" class="btn-close">닫기</a>
          </div>
          <div class="input-box">
            <label for="studentsId">학생 아이디 : </label>
            <input
              id="studentsId"
              type="text"
              placeholder="아이디 입력하세요..."
            />
          </div>
          <div class="input-box">
            <label for="studentsName">학생 이름 : </label>
            <input
              id="studentsName"
              type="text"
              placeholder="변경할 이름을 입력하세요..."
            />
          </div>
          <div class="input-box">
            <label for="studentsPassword">학생 비밀번호 : </label>
            <input
              id="studentsPassword"
              type="password"
              placeholder="변경할 비밀번호를 입력하세요..."
            />
          </div>
          <div class="input-box">
            <label for="createAt">가입날짜 : </label>
            <input id="createAt" type="text" readonly />
          </div>
          <div class="btn-area">
            <input id="boardIdHidden" type="hidden" />
            <a id="contentUpdate" href="#" class="btn-update">수정</a>
            <a id="contentDelete" href="#" class="btn-delete">삭제</a>
          </div>
        </div>
      </div>
      <!-- 학생 추가 -->
      <div class="insert-popup">
        <div class="editor">
          <div class="close">
            <a href="#" class="btn-close">닫기</a>
          </div>
          <div class="input-box">
            <label for="studentsName">학생 이름 : </label>
            <input
              id="insertStudentsName"
              type="text"
              placeholder="이름을 입력하세요..."
            />
          </div>
          <div class="input-box">
            <label for="studentsPassword">학생 비밀번호 : </label>
            <input
              id="insertStudentsPassword"
              type="password"
              placeholder="비밀번호를 입력하세요..."
            />
          </div>
          <div class="btn-area">
            <input id="boardIdHidden" type="hidden" />
            <a id="contentInsert" href="#" class="btn-insert">추가</a>
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
                    <a href="/logs?pageNum=1&pageSize=5">
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
            <input
              id="searchBar"
              type="text"
              placeholder="학생이름을 검색하세요..."
            />
            <input id="keyword" type="hidden" value="null" />
          </label>
        </div>
        <div>
          <a href="#" class="logout">Logout</a>
        </div>
      </div>
      <!-- table -->
      <div class="details">
        <div class="recentOrders">
          <div class="cardHeader">
            <h2>학생 명단</h2>
            <a href="#" class="btn">학생 추가</a>
          </div>
          <table>
            <thead>
              <tr>
                <th>학생 아이디</th>
                <th>학생 이름</th>
                <th>가입 날짜</th>
              </tr>
            </thead>
            <tbody id="studentsData">
            <c:choose>	 		
	 				<c:when test="${fn:length(pageHelper.list) > 0}">
	 					<c:forEach items="${pageHelper.list}" var="item">	
		 					<tr onclick="getStudents(${item.studentsId})">
				    			<td>${item.studentsId}</td>
				    			<td>${item.studentsName}</td>
				    			<td>${item.createAt}</td>	    							    			
  							</tr>
						</c:forEach>
	 				</c:when>
	 				<c:otherwise>
	 					<tr>
	 						<td colspan="6">데이터가 없습니다.</td>
	 					</tr>
	 				</c:otherwise>
	 			</c:choose>
              <!-- <tr>
                             <td>hyunsangwon</td>
                             <td>현상원</td>
                             <td>2022-05-19</td>
                         </tr>
                         <tr>
                            <td>hyunsangwon</td>
                            <td>현상원</td>
                            <td>2022-05-19</td>
                        </tr>
                        <tr>
                            <td>hyunsangwon</td>
                            <td>현상원</td>
                            <td>2022-05-19</td>
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
             <c:if test="${pageHelper.hasPreviousPage}">
          		<a onclick="getBoardList(${pageHelper.pageNum-1},5)">Previous</a>
         	 </c:if>
          	 <c:forEach begin="${pageHelper.navigateFirstPage}" end="${pageHelper.navigateLastPage}"  var="pageNum">
          		<a id="pageNum${pageNum}"  onclick="getBoardList(${pageNum},5)">${pageNum}</a>
         	 </c:forEach>
         	 <c:if test="${pageHelper.hasNextPage}">
          		<a onclick="getBoardList(${pageHelper.pageNum+1},5)">Next</a>
         	 </c:if>
          	<input id="nowPageNum" type="hidden" value="${pageHelper.pageNum}">
          </div>
        </div>
      </div>
    </div>
  </body>
  <script
    type="module"
    src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
  ></script>
  <script
    nomodule
    src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
  ></script>
  <script
    src="https://code.jquery.com/jquery-3.6.0.min.js"
    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
    crossorigin="anonymous"
  ></script>
  <script>
    let list = document.querySelectorAll(".navigation li");
    function activeLink() {
      list.forEach((item) => {
        item.classList.remove("hovered");
      });
      this.classList.add("hovered");
    }
    list.forEach((item) => {
      item.addEventListener("mouseover", activeLink);
    });
  </script>
  <script>
    $(".btn-close").click(function () {
      // 글 수정 팝업 닫기 누르면 팝업창 닫기
      $(".update-popup").css("display", "none");
      $(".insert-popup").css("display", "none");
    });
    $(".btn").click(function () {
      $(".insert-popup").css("display", "block");
    });
  </script>
  
  <script>
	getPageNum();
  	function getPageNum(){
		var pageNum = $('#nowPageNum').val();
		  $("#pageNum" + pageNum).css("background-color", "#287bff");
     	  $("#pageNum" + pageNum).css("color", "#fff");
		}
	//페이지이동
 	function getBoardList(pageNum, pageSize){
 		location.href="/students?pageNum="+pageNum+"&pageSize="+pageSize;
 	}
	
 	 // 특정 학생 상세보기
    function getStudents(studentsId) {
      $(".update-popup").css("display", "block");
      $.ajax({
        url: "/api/v1/students/id/" + studentsId,
        data: "GET",
        dataType: "json",
        success: function (response) {
          $("#studentsId").val(response.studentsId);
          $("#studentsName").val(response.studentsName);
          $("#studentsPassword").val(response.studentsPassword);
          $("#createAt").val(response.createAt);
          console.log(response);
        },
        error: function (request, statis, error) {
          console.log(error);
        },
      });
    }
 	 
 // 학생 수정
    $("#contentUpdate").click(function () {
      var jsonData = {
        studentsName: $("#studentsName").val(),
        studentsPassword: $("#studentsPassword").val(),
      };
      var studentsId = $("#studentsId").val();
      $.ajax({
        url: "/api/v1/students/id/" + studentsId,
        type: "PATCH",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(jsonData),
        success: function (response) {
          if (response > 0) {
            confirm("학생 정보를 수정 하시겠습니까?");
            if (confirm) {
              $(".update-popup").css("display", "none");
              $("#studentsName").val("");
              $("#studentsPassword").val("");
              location.reload();
              alert("변경 사항이 저장 되었습니다.");
            }
          }
        },
        error: function (request, statis, error) {
          console.log(error);
        },
      });
    });
 	 
 
 // 학생 삭제
    $("#contentDelete").click(function(){
      var studentsId = $("#studentsId").val();
      if (confirm("해당 학생을 삭제 하시겠습니까?")) {
        $.ajax({
          url: "/api/v1/students/id/" + studentsId,
          type: "DELETE",
          dataType: "json",
          success: function (response) {
            if (response > 0) {
              $(".update-popup").css("display", "none");
              alert("ID : " + studentsId + "번이 삭제 되었습니다.");
              $(".update-popup").css("display", "none");
              location.reload();
            }
          },
          error: function (request, statis, error) {
            console.log(error);
          },
        });
      }
    });
 
 
 // 학생추가
    $(".btn-insert").click(function () {
      var studentsName = $("#insertStudentsName").val();
      var studentsPassword = $("#insertStudentsPassword").val();
      var jsonData = {
        studentsName: studentsName,
        studentsPassword: studentsPassword,
      };
      if (studentsName == "" || studentsPassword == "") {
        alert("학생 정보를 입력해주세요!");
        if (studentsName == "") {
          $("#insertStudentsName").focus();
        } else {
          $("#insertStudentsPassword").focus();
        }
        return false;
      }
      if (confirm("학생을 추가 하시겠습니까?")) {
        $.ajax({
          url: "/api/v1/students",
          type: "POST",
          contentType: "application/json", //서버에 json타입으로 보낼 예정(요청).
          dataType: "json", //서버결과를 json으로 응답받겠다.
          data: JSON.stringify(jsonData),
          success: function (response) {
            if (response > 0) {
              alert("학생이 추가 되었습니다.");
              $("#insertStudentsName").val("");
              $("#insertStudentsPassword").val("");
              $(".insert-popup").css("display", "none");
              location.reload();
            }
          },
          error: function (request, statis, error) {
            console.log(error);
          },
        });
      }
    });
 
 	// students search
    $("#searchBar").keyup(function(key) {
        var pageNum = 1;
        var pageSize = 5;
        if (key.keyCode == 13) {
          var studentsName = $("#searchBar").val().trim();
          if (studentsName != "") {
            $("#keyword").val(studentsName); //hidden input 태그에 내가 검색한 키워드(search value)를 keyword value에 set함.
          }
          $.ajax({
            url:
              "/api/v1/students/search?studentsName=" +
              studentsName +
              "&pageNum=" +
              pageNum +
              "&pageSize=" +
              pageSize,
            type: "GET",
            dataType: "json",
            success: function (response) {
              if (response != null) {
            	  location.href='/students/search?writer='+studentsName+'&pageNum='+pageNum+'&pageSize='+pageSize
            		// 페이지 이동이 된 후에 검색한 학생만 남아있는 페이지가 아닌 기존 페이지로 돌아가는 방법 찾기
              }
            },
            error: function (request, statis, error) {
              console.log(error);
            },
          })
        }
    })
  </script>
</html>
