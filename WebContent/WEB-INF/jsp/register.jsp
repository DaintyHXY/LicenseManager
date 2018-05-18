<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/register.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/bootstrap.min.css">

</head>
<body class="container-fluid">
    <div>
       <div>
          <h3><p class="text-center">注册</p></h3>
          <h3><p class="text-center">register</p></h3>
       </div>
       
       <div class="row">
       <form action="register" method="post">
             <div class="center-block text-center">
                
                <input type="text" id="name" name="name" placeholder="用户名" class="form-control">
             </div>
             <div class="center-block text-center">
           
                <input type="password" id="pwd" name="pwd" placeholder="密码" class="form-control">
              </div>
              <div class="center-block text-center">
                <button type="submit" class="btn btn-lg btn-primary btn-block">register</button>
               </div>
             
       </form>
       </div>
       
    </div>



</body>
</html>