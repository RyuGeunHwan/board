<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
    <div class="container">
        <form th:action="@{/logout}" method="post"> 
        <button class="btn btn-md btn-danger btn-block" name="registration"type="Submit">Logout</button> 
        </form>
        <div class="panel-group" style="margin-top:40px">
            <div class="panel panel-primary">
                <div class="panel-heading"> <span th:utext="${name}"></span> </div>
                <p class="admin-message-text text-center" th:utext="${auth}"></p>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="/resources/static/js/index.js"> 
</script>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
></script>
</html>

