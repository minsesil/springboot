<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"  rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<title>writeForm</title>
</head>
<body>

<form action="write" method="post">
	<div class="mb-3">
	  <label for="exampleFormControlInput1" class="form-label">제목</label>
	  <input name="title" class="form-control" id="exampleFormControlInput1">
	</div>
	
	<div class="mb-3">
	  <label for="exampleFormControlInput1" class="form-label">작성자</label>
	  <input name="writer" class="form-control" id="exampleFormControlInput1">
	</div>
	
	<div class="mb-3">
	  <label for="exampleFormControlInput1" class="form-label">내용</label>
	  <textarea name ="content" class="form-control" id="exampleFormControlInput1" rows="5"></textarea>
	</div>
	<br>
	<button type="submit" class="btn btn-primary">게시글 등록</button>
	<button type="button" class="btn btn-light" onclick="location.heft='list'">목록보기</button>
</form>

</body>
</html>