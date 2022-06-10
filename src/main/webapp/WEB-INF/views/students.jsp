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
                    <a href="/board?pageNum=1&pageSize=10">
                        <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
                        <span class="title" >Dashboard</span>                
                    </a>
                </li>
                <li>
                    <a href="/students">
                        <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
                        <span class="title" >Students</span>                
                    </a>
                </li>
                <li>
                    <a href="/logs">
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
    // 학생 DB 전체조회
    getLogsList(1, 5);

    // function getAllList(){
    //     $.ajax({
    //         url : 'http://localhost:8080/api/v1/students/map',
    //         type : 'GET',
    //         dataType : 'json',
    //         success : function(response){
    //             console.log(response)

    //         var html = '';
    //         for(var i=0; i<response.length; i++){

    //             html = '<tr onclick="getStudents('+response[i].studentsId+')"><td>'+response[i].studentsId+'</td><td>'+response[i].studentsName+'</td><td>'+response[i].createAt+'</td></tr>'
    //             $('#studentsData').append(html);
    //         }
    //         },
    //         error : function(request,status,error){
    //             console.log(error)
    //         }
    //     })
    // }

    function getLogsList(pageNum, pageSize) {
      var controllerUrl = null;
      var keyword = $("#keyword").val().trim();
      if (keyword == "null") {
        controllerUrl =
          "http://localhost:8080/api/v1/students/map?pageNum=" +
          pageNum +
          "&pageSize=" +
          pageSize;
      } else {
        controllerUrl =
          "http://localhost:8080/api/v1/students/search?studentsName=" +
          keyword +
          "&pageNum=" +
          pageNum +
          "&pageSize=" +
          pageSize;
      }
      $.ajax({
        url: controllerUrl,
        type: "GET",
        dateType: "json",
        success: function (response) {
          console.log(response);
          if (response != null) {
            var html = "";
            for (var i = 0; i < response.list.length; i++) {
              html +=
                '<tr onclick="getStudents(' +
                response.list[i].studentsId +
                ')"><td>' +
                response.list[i].studentsId +
                "</td><td>" +
                response.list[i].studentsName +
                "</td><td>" +
                response.list[i].createAt +
                "</td></tr>";
            }

            //페이징 화면 구현
            var paginationHtml = "";

            // 이전페이지 버튼
            if (response.hasPreviousPage) {
              // 이전페이지(hasPreviousPage)가 true라면
              // hasPreviousPage : 이전버튼 유무(type => boolean)
              paginationHtml +=
                '<a onclick="getLogsList(' +
                (response.pageNum - 1) +
                "," +
                pageSize +
                ')" href="#">Previous</a>';
            }

            for (var i = 0; i < response.navigatepageNums.length; i++) {
              //페이지 번호 길이 만큼 for문 실행
              // navigatepageNums : 블록 개수
              paginationHtml +=
                '<a id="pageNum' +
                response.navigatepageNums[i] +
                '" onclick="getLogsList(' +
                response.navigatepageNums[i] +
                "," +
                pageSize +
                ')" href="#">' +
                response.navigatepageNums[i] +
                "</a>";
            }

            // 다음페이지 버튼
            if (response.hasNextPage) {
              // 다음페이지(hasNextPage)가 true라면
              // hasNextPage : 다음버튼 유무(type => boolean)
              paginationHtml +=
                '<a onclick="getLogsList(' +
                (response.pageNum + 1) +
                "," +
                pageSize +
                ')" href="#">Next</a>';
            }
            $(".pagination").children().remove();
            $(".pagination").append(paginationHtml);

            // 페이지 번호에 맞게 css 수정
            $("#pageNum" + pageNum).css("background-color", "#287bff");
            $("#pageNum" + pageNum).css("color", "#fff");

            $("#studentsData").children().remove();
            $("#studentsData").append(html);
          } else {
            html +=
              '<tr><td colspan=3 style="text-align:center;">게시글이 없습니다.</td></tr>';
          }
        },
        error: function (request, status, error) {
          console.log(error);
        },
      });
    }

    // 특정 학생 조회
    function getStudents(studentsId) {
      $(".update-popup").css("display", "block");
      $.ajax({
        url: "http://localhost:8080/api/v1/students/id/" + studentsId,
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
      console.log(jsonData);
      var studentsId = $("#studentsId").val();
      $.ajax({
        url: "http://localhost:8080/api/v1/students/id/" + studentsId,
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
              alert("변경 사항이 저장 되었습니다.");
              $("#studentsData").children().remove();
              getLogsList(1, 5);
            }
          }
        },
        error: function (request, statis, error) {
          console.log(error);
        },
      });
    });

    // 학생 삭제
    $("#contentDelete").click(function () {
      var studentsId = $("#studentsId").val();
      confirm("해당 학생을 삭제 하시겠습니까?");
      if (confirm) {
        $.ajax({
          url: "http://localhost:8080/api/v1/students/id/" + studentsId,
          type: "DELETE",
          dataType: "json",
          success: function (response) {
            if (response > 0) {
              $(".update-popup").css("display", "none");
              alert("ID : " + studentsId + "번이 삭제 되었습니다.");
              $(".update-popup").css("display", "none");
              $("#studentsData").children().remove();
              getLogsList(1, 5);
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
      console.log(studentsName, studentsPassword);
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
          url: "http://localhost:8080/api/v1/students",
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
              $("#studentsData").children().remove();
              getLogsList(1, 5);
            }
          },
          error: function (request, statis, error) {
            console.log(error);
          },
        });
      }
    });

    // 쿼리 스트링으로 특정 학생 정보 찾기
    $("#searchBar").keyup(function (key) {
      var pageNum = 1;
      var pageSize = 5;
      if (key.keyCode == 13) {
        var studentsName = $("#searchBar").val().trim();
        if (studentsName != "") {
          $("#keyword").val(studentsName); //hidden input 태그에 내가 검색한 키워드(search value)를 keyword value에 set함.
        }
        $.ajax({
          url:
            "http://localhost:8080/api/v1/students/search?studentsName=" +
            studentsName +
            "&pageNum=" +
            pageNum +
            "&pageSize=" +
            pageSize,
          type: "GET",
          dataType: "json",
          success: function (response) {
            console.log(response);
            if (response != null) {
              var html = "";
              for (var i = 0; i < response.list.length; i++) {
                html +=
                  '<tr onclick="getStudents(' +
                  response.list[i].studentsId +
                  ')"><td>' +
                  response.list[i].studentsId +
                  "</td><td>" +
                  response.list[i].studentsName +
                  "</td><td>" +
                  response.list[i].createAt +
                  "</td></tr>";
              }
              //페이징 화면 구현
              var paginationHtml = "";

              // 이전페이지 버튼
              if (response.hasPreviousPage) {
                // 이전페이지(hasPreviousPage)가 true라면
                // hasPreviousPage : 이전버튼 유무(type => boolean)
                paginationHtml +=
                  '<a onclick="getLogsList(' +
                  (response.pageNum - 1) +
                  "," +
                  pageSize +
                  ')" href="#">Previous</a>';
              }
              for (var i = 0; i < response.navigatepageNums.length; i++) {
                //페이지 번호 길이 만큼 for문 실행
                // navigatepageNums : 블록 개수
                paginationHtml +=
                  '<a id="pageNum' +
                  response.navigatepageNums[i] +
                  '" onclick="getLogsList(' +
                  response.navigatepageNums[i] +
                  "," +
                  pageSize +
                  ')" href="#">' +
                  response.navigatepageNums[i] +
                  "</a>";
              }

              // 다음페이지 버튼
              if (response.hasNextPage) {
                // 다음페이지(hasNextPage)가 true라면
                // hasNextPage : 다음버튼 유무(type => boolean)
                paginationHtml +=
                  '<a onclick="getLogsList(' +
                  (response.pageNum + 1) +
                  "," +
                  pageSize +
                  ')" href="#">Next</a>';
              }
              $(".pagination").children().remove();
              $(".pagination").append(paginationHtml);
              // 페이지 번호에 맞게 css 수정
              $("#pageNum" + pageNum).css("background-color", "#287bff");
              $("#pageNum" + pageNum).css("color", "#fff");
              $("#studentsData").children().remove();
              $("#studentsData").append(html);
            }
          },
          error: function (request, statis, error) {
            console.log(error);
          },
        });
      }
      // 검색 후 검색어 지우면 학생 전체리스트 보여주기
      // keyCode = 8 : backspace
      if (key.keyCode == 8) {
        if (keyword == "null") {
          $("#studentsData").children().remove();
          getLogsList(1, 5);
        }
      }
    });

    // 로그인 페이지 돌아가기
    $(".logout").click(function () {
      if (confirm("로그아웃 하시겠습니까?")) {
        location.href = "../login.html";
      }
    });
  </script>
</html>
