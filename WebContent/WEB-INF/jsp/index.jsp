<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>

<!-- <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> -->

<!-- Bootstrap -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/bootstrap.min.css">
    
   <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    



</head>
<body class="container-fluid">

<script src="${pageContext.request.contextPath}/webapp/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webapp/js/bootstrap.min.js"></script>
    <div id="background" >
        
        <div id="bacTop">
             <h3><p class="text-center" >VR内容许可证管理系统</p></h3>
             <h3><p class="text-center" >VR License Management</p></h3>
        </div>
        
        <div id="bacMid">
        <form action="LoginAndSign" method="post" >
             <div id="adminPut" class="row">
                  <div class="col-md-4"></div>
                  <div id="adInput" class="col-md-4">
                       
                       <div class="center-block"><input type="text" id="name" name="name" tabindex="1" class="form-control" placeholder="用户名"></div>
                  </div>
                  <div class="col-md-4"></div>
             </div>
             
             <div id="pwdPut" class="row">
                  <div class="col-md-4"></div>
                  <div id="psdText" class="col-md-4">
                       
                       <div class="center-block"><input type="text" id="pwd" name="pwd" tabindex="2" class="form-control" placeholder="用户名"></div>
                  </div>
             
             </div>
             
             <div id="adButton" class="row">
                  <div class="col-md-4"></div>
                  <div id="adBut" class="col-md-4">
                       <div class="col-xs-6"><p class="text-right" ><button name="action" id="submit" type="submit" value="login" tabindex="3" class="btn btn-lg btn-primary btn-block">login</button></p></div>
                       <div class="col-xs-6"><p class="text-left" ><button name="action" id="sign" type="submit" value="sign" tabindex="4" class="btn btn-lg btn-primary btn-block">register</button></p></div>
                  </div>
             </div>
          </form>   
        </div>
        
        
        
        
        <div id="bacBtm"></div>
        
        
    </div>
 

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
  </body>
</html>