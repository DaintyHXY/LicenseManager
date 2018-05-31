<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>

<!-- <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"> -->

<!-- Bootstrap -->
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/bootstrap-datetimepicker.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/index.css">
    
   <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="${pageContext.request.contextPath}/webapp/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webapp/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/webapp/js/bootstrap-datetimepicker.js"></script>
<script src="${pageContext.request.contextPath}/webapp/js/index.js"></script>
    
<script type="text/javascript">

     //目前还没做注册的弹出
     //5.29号更新：注册已完成
  
     function login(){
    	 
    	 
    	 var name = $("#name").val();
    	 var pwd = $("#pwd").val();
    	 var user = {
    			 "name" : name,
    	         "pwd"  : pwd
    	 };
    	     
    	 $.ajax({
    		 
    	     async:true,
    		 type:"POST",
    		 dataType:"json",
    		 contentType : "application/json",
    		 url:"${pageContext.request.contextPath}/toLogin",
    		 data: JSON.stringify(user) ,
    		 success:function (result){
    			 //console.log(result);
    			 //alert(result);
    			 if(result.msg == "用户不存在")
    				 alert("用户不存在");
    			 else if (result.msg == "未知")
    				 alert("未知")
    		     else {
    		    	 //alert(result.msg+"登录成功！")
    		    	 window.location.href="${pageContext.request.contextPath}/main"
    		    	 }
    		 },
    		 
    	 
    	 error:function(XMLHttpRequest, textStatus, errorThrown){
    		 alert(XMLHttpRequest.readyState);
    		 }
    	 });
     }
     
  
</script>



</head>
<body id="body">

     
   
    

    <!-- 注册框 -->
<div class="modal fade" id="signModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="signModalLabel">用户注册</h4>
      </div>
      <div class="modal-body">
        <div class="row"><div class="col-md-3 ">用户名：</div><div class="col-md-6"><input id="signName" type="text" class="form-controller" placeholder="用户名"></div></div>
        <div class="row"><div class="col-md-3 ">密码：</div><div class="col-md-6"><input id="signPwd" type="password" class="form-controller" placeholder="密码"></div></div>

        <div class="row"><div class="col-md-3">生日：</div><div class="col-md-6"><input id="datetimepicker" value="2018-05-26"></div></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="signBtn" type="button" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>

  <!-- 巨幕 -->
 <div class="site-wrapper">
    <div class="site-wrapper-inner">
    <div  class="jumbotron" id="backt">
    <!-- <h1>Hello, world!</h1>
         <p>...</p>
         <p><a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a></p>
    </div> -->
    <div id="topText" class="col-md-offset-2">
    <h1>VR改变世界!</h1>
    <h3>VR内容许可证管理系统</h3>
    <h3>VR License Management</h3>
    <h3>现在就开始你的VR之旅！</h3>
    </div>

    <div id="background" class="container container-fluid" >
        
        <!-- <div id="bacTop">
             <h3><p class="text-center" >VR内容许可证管理系统</p></h3>
             <h3><p class="text-center" >VR License Management</p></h3>
        </div> -->
        
        
             <div id="right-block" class="col-md-offset-7">
             <div id="adminPut" class="row ">  
                       <div class="col-md-3 col-md-offset-1 col-xs-4 "><p class="text-center">用户名</p></div>                                       
                       <div class="col-md-8 col-xs-8"><input type="text" id="name" name="name" tabindex="1" class="form-control" placeholder="用户名"></div>                  
             </div>
             
             <div id="pwdPut" class="row">
                       <div class="col-md-3 col-md-offset-1 col-xs-4"><p class="text-center">密码</p></div>                                                           
                       <div class="col-md-8 col-xs-8"><input type="password" id="pwd" name="pwd" tabindex="2" class="form-control" placeholder="用户名"></div>
             </div>                          
             
             <div id="adButton" class="row center-block ">                  
                    <div id="adBut">
                       <div class="col-md-4 col-xs-6 col-md-offset-4"><button name="action" id="submit" type="button" value="login" tabindex="3" class="btn btn-lg btn-default btn-block" onclick="login()">login</button></div>
                       <div class="col-md-4 col-xs-6"><button name="action" id="sign" type="button" value="sign" tabindex="4" class="btn btn-lg btn-default btn-block" data-toggle="modal" data-target="#signModal">register</button></div>
                    </div>
             </div>
             </div>
             
            
        
        
        
        
        
        
    </div>
  </div>
  </div>
</div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script> -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
  </body>
</html>