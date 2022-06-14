//몇페이지인지 표시하는 함수
getPageNum();
//게시글 통계 함수
getBoardStatistics();

//페이징 구현
function getBoardList(pageNum, pageSize) {
	location.href = "/board?pageNum=" + pageNum + "&pageSize=" + pageSize;
}
//몇페이지인지 표시하는 함수
function getPageNum() {
	var pageNum = $('#nowPageNum').val();
	$('#pageNum' + pageNum).css('backgroundColor', '#287bff');
	$('#pageNum' + pageNum).css('color', '#fff');
}

// 게시물 클릭시 조회수 카운트
function setBoardViews(boardId) { //게시판 조회수 증가 함수
	$.ajax({
		url: '/api/v1/board/views/boardId/' + boardId,
		type: 'PATCH', // 해당게시글 클릭하면 조회수 증가하며 업데이트가 되어야함.
		dataType: 'json', //서버결과를 json으로 응답받겠다.
		success: function(response) {
			if (response > 0) {
				// 추후 로직 예정(에러페이지로 이동하는 로직)
			}
		}, error: function(request, statis, error) {
			console.log(error)
		}
	}) // ajax end
}

//게시글 통계 함수
function getBoardStatistics() {
	$.ajax({
		url: '/api/v1/board/statistics',
		type: 'GET',
		dataType: 'json', //서버결과를 json으로 응답받겠다.
		success: function(response) {
			// input의 value가 아니라 div의 값이라 text()를 붙여준다.
			// text() or html() : input을 제외한 태그의 값을 컨트롤할 때 사용.
			// val() : input을 컨트롤할 때 사용.
			$('#boardCnt').text(response.boardCnt);
			$('#studentsCnt').text(response.studentsCnt);
			$('#writerCnt').text(response.writerCnt);
			$('#viewsCnt').text(response.viewsCnt);
		}, error: function(request, statis, error) {
			console.log(error)

		}
	})
}



// 특정 함수 상세 보기
function getBoard(boardId) {
	//boardId : 게시판 번호
	//boardId html에 hidden 하기
	//1. 화면 none -> block
	$('.update-popup').css('display', 'block');
	//AJAX 작성
	//1. AJAX를 이용해서 특정 게시물 조회
	$.ajax({
		url: '/api/v1/board/boardId/' + boardId,
		type: 'GET',
		dataType: 'json', //서버결과를 json으로 응답받겠다.
		success: function(response) {
			//3. input에 데이터 set해주기!
			$('#upt-title').val(response.title);
			$('#upt-content').val(response.content);
			$('#boardIdHidden').val(boardId);
			//<input id="boardIdHidden" type="hidden" value="">
			// .val(boardId) : val값에 boardId을 대입해주었기 때문에 input의 value값이 계속 바뀐다.

			// 특정 게시글 클릭하면 조회수 카운트 증가
			setBoardViews(boardId)
		}, error: function(request, statis, error) {
			console.log(error)
		}
	});
} //end

// 게시글 작성 함수
$('#contentSubmit').click(function() {
	if (confirm('게시글을 작성하시겠습니까?')) {
		var studentsName = $('#studentsName').val();
		var title = $('#title').val();
		var content = $('#content').val();
		var count = 0;

		//studentsId ==> Session에서 가져오기
		var studentsId = $('#studentsId').val();

		if (title == '') {
			alert('제목을 작성해주세요')
			$('#title').focus();
			return false;
		}
		if (content == '') {
			alert('내용을 작성해주세요')
			$('#content').focus();
			return false;
		}

		var jsonData = {
			studentsId: studentsId,
			title: title,
			content: content
		}
		$.ajax({
			url: '/api/v1/board',
			type: 'POST',
			contentType: 'application/json',//서버에 json타입으로 보낼 예정(요청).
			dataType: 'json', //서버결과를 json으로 응답받겠다.
			data: JSON.stringify(jsonData),
			success: function(response) {
				if (response > 0) {
					$('.write-popup').css('display', 'none'); //작성화면 감추기
					$('#title').val('');// 내가 작성한글 등록하면 지우기
					$('#content').val('');// 내가 작성한글 등록하면 지우기

					var nowPageNum = $('#nowPageNum').val();
					location.href = "/board?pageNum=1&pageSize=+5";
				}
			}, error: function(request, statis, error) {
				console.log(error)
			}
		}) // ajax end

	}
})
//contentSubmit click end

//게시글 수정
$('#contentUpdate').click(function() {
	//1. 게시판 번호 확인
	var boardId = $('#boardIdHidden').val(); //hidden에 숨겨둔 boardId 가져오기.
	//2. json 생성
	var title = $('#upt-title').val();
	var content = $('#upt-content').val();
	var jsonData = {
		title: title,
		content: content
	}

	//3. AJAX를 이용해서 게시글 수정
	if (confirm('게시글을 수정하시겠습니까?')) {
		$.ajax({
			url: '/api/v1/board/boardId/' + boardId, //boarRestController를 이용한 AJAX
			type: 'PATCH',
			contentType: 'application/json',//서버에 json타입으로 보낼 예정(요청).
			dataType: 'json', //서버결과를 json으로 응답받겠다.
			data: JSON.stringify(jsonData),
			success: function(response) {
				if (response > 0) {
					alert('수정 완료!')
					$('.update-popup').css('display', 'none');//게시물 상세 화면 감추기
					var pageNum = $('#nowPageNum').val();
					location.href = "/board?pageNum=" + pageNum + "&pageSize=5"
				}
			}, error: function(request, statis, error) {
				console.log(error)
			}
		}) // ajax end
	}
})


//게시글 삭제
$('#contentDelete').click(function() {
	// 특정게시글 상세조회 메소드에 있는 boardId를 이용함.
	var boardId = $('#boardIdHidden').val();
	var nowPageNum = $('#nowPageNum').val();
	if (confirm('해당 게시물을 삭제 하시겠습니까?')) {
		$.ajax({
			url: '/api/v1/board/boardId/' + boardId,
			type: 'DELETE',
			dataType: 'json', //서버결과를 json으로 응답받겠다.
			success: function(response) {
				if (response > 0) {
					$('.update-popup').css('display', 'none');//게시물 상세 화면 감추기
					alert(boardId + '번 게시물이 삭제 되었습니다.')
					location.href = "/board?pageNum=" + nowPageNum + "&pageSize=5";
				}
			}, error: function(request, statis, error) {
				console.log(error)
			}
		}) // ajax end
	}
})

// 작성자 검색 함수
$('#searchBar').keyup(function(key) {// 키보드를 뗄 때 무엇을 눌렀다가 뗐는데 파라미터 key에 들어감.
	//엔터를 누를떄 hello word를 출력하고 싶음.
	var nowPageNum = $('#nowPageNum').val();
	var pageSize = 5;
	// keycode = 13 은 enter를 의미
	if (key.keyCode == 13) {
		//1. input값을 가져옴.
		var search = $('#searchBar').val().trim(); //input에 작성한 작성글을 가져옴
		if (search != '') {
			$('#keyword').val(search); //hidden input 태그에 내가 검색한 키워드(search value)를 keyword value에 set함.
		}
		//2. AJAX 호출
		$.ajax({
			url: '/api/v1/board/search?writer=' + search + '&pageNum=' + nowPageNum + '&pageSize=' + pageSize,
			type: 'GET',
			dataType: 'json', //서버결과를 json으로 응답받겠다.
			success: function(response) {
				if (response != null) {
					location.href = '/board/search?writer=' + search + '&pageNum=' + nowPageNum + '&pageSize=' + pageSize
				}
			}, error: function(request, statis, error) {
				console.log(error)
			}
		}) // ajax end
	}
})