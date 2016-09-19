<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>top</title>
<style>

body { margin: 0; }

.zeta-menu-bar {
position: fixed;
  background: gray;
  display: inline-block;
  width: 100%;
}
.zeta-menu { margin: 0; padding: 0; }
.zeta-menu li {
  float: left;
  list-style:none;
  position: relative;
}
.zeta-menu li:hover { background: white; }
.zeta-menu li:hover>a { color: gray; }
.zeta-menu a {
  color: white;
  display: block;
  padding: 10px 20px;
  text-decoration: none;
}
.zeta-menu ul {
  background: #eee;
  border: 1px solid silver;
  display: none;
  padding: 0;
  position: absolute;
  left: 0;
  top: 100%;
  width: 180px;
}
.zeta-menu ul li { float: none; }
.zeta-menu ul li:hover { background: #ddd; }
.zeta-menu ul li:hover a { color: black; }
.zeta-menu ul a { color: black; }
.zeta-menu ul ul { left: 100%; top: 0; }
.zeta-menu ul ul li {float:left; margin-right:10px;}
</style>

<script src="//code.jquery.com/jquery.min.js"></script>
<script>
$(function(){
  $(".zeta-menu li").hover(function(){
    $('ul:first',this).show();
  }, function(){
    $('ul:first',this).hide();
  });
  $(".zeta-menu>li:has(ul)>a").each( function() {
    $(this).html( $(this).html()+' &or;' );
  });
  $(".zeta-menu ul li:has(ul)")
    .find("a:first")
    .append("<p style='float:right;margin:-3px'>&#9656;</p>");
});
</script>
</head>
<body>
   <div class='zeta-menu-bar'>
  <ul class="zeta-menu">
    <li><a href="#">게시판</a>
      <ul>
        <li><a href="#">공지사항</a></li>
        <li><a href="#">자유게시판</a></li>
      </ul>
    </li>
    <li><a href="#">공부방</a>
   	  <ul>
        <li><a href="#">모집글 올리기</a></li>
        <li><a href="#">모집글 검색</a></li>
      </ul>
    </li>

    <li><a href="#">핫이슈</a></li> 
    <li><a href="#">사이트맵</a></li> 
  </ul>
</div>
</body>
</html>