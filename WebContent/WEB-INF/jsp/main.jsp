<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/main.css">

</head>
<body class="container-fluid">


<script src="${pageContext.request.contextPath}/webapp/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webapp/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/webapp/js/bootstrap-datetimepicker.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/webapp/js/main.js"></script>

<!-- 申请框 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="applyModalLabel">申请许可证</h4>
      </div>
      <div class="modal-body">
        <div class="row"><div class="col-md-3">申请人：</div><div id="userInfo" value="${user.id}" class="col-md-6">${user.name}</div></div>
        <div class="row"><div class="col-md-3">申请使用时长：</div><div class="col-md-6"><input id="wantTime" type="text" class="form-controller" placeholder="天数"></div></div>
        <br>
        <div class="row"><div class="col-md-3">开始使用时间：</div><div class="col-md-6"><input id="datetimepicker" value="2018-05-26"></div></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button id="applyBtn" type="button" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>

<!-- 删除申请框 -->
     

<div class="modal fade " id="deletModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
       <h4 class="text-center">确定要删除吗？</h4>
       <div class="row" >
       <div class="col-md-6 "><button  type="button" class="btn btn-default btn-block" data-dismiss="modal">否</button></div>
       <div class="col-md-6"><button id="toDeletApply"  type="button" class="btn btn-primary btn-block">是</button></div>
       </div>
    </div>
  </div>
</div>

<!-- 导航条 -->
<nav class="navbar navbar-default" id="daoHang">
    
   <!-- Brand -->
   <div class="container-fluid">
        <div class="navbar-header" >
             <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false" >
                    <span class="sr-only" >Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
             </button>
             <a class="navbar-brand" href="#">Brand</a>
        </div>
    
    <!-- Link -->
   <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1" >
       <ul class="nav navbar-nav">
          <li class="active" ><a href="#">Link<span class="sr-only">(current)</span></a></li>
          <li><a href="#">Link</a></li>
          <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown<span class="caret"></span></a>
              <ul class="dropdown-menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Action</a></li>
                  <li role="separator" class="divider"></li>
                  <li><a href="#">Action</a></li>
                  <li role="separator" class="divider"></li>
                  <li><a href="#">Action</a></li>
              </ul>
          </li>
       </ul>
       <form class="navbar-form navbar-left">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
            
       </form>
       <div style="display:block" ><a href="#" >${user.id}</a></div>
       
   </div>
       
   
   </div>
   
   
   

</nav>

<div id="mid" class="row">
     <div class="col-md-2">
          <div class="panel panel-default">
               <div class="panel-heading">
                    
                    <h3 class="panle-title "><span class="glyphicon glyphicon-user" aria-hidden="true"></span>个人资料</h3>
               </div>
               <div class="panel-body">
                    <div class="row">
                         <div class="col-md-6 text-left"><h4>欢迎你！</h4></div>
                         <div class="col-md-6"><h4>${user.name}</h4></div>
                    </div>
                    <div class="row">
                         <div class="text-center"><h4>你从${user.useDate}起使用小v！</h4></div>
                         
                    </div>
                    
                        <button type="button" class="btn btn-default btn-lg btn-block">
                             <span class="glyphicon glyphicon-pencil col-md-6" aria-hidden="true">修改个人资料</span>
                              
                         </button>
                         
                         <button type="button" class="btn btn-default btn-lg btn-block">
                             <span class="glyphicon glyphicon-pencil col-md-6" aria-hidden="true">登出</span> 
                             
                         </button>
                           
                         
                    
               </div>
          </div> 
      </div>
      <div class="col-md-4">
          <div class="panel panel-default">
               <div class="panel-heading">
                    <h3 class="panle-title">申请事项</h3>
               </div>
               <div class="panel-body panel-group" id="accordion" role="tablist" aria-multiselectable="true">
               
               <!-- 标签框 -->
               <!-- <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"> -->
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a id="hasApply"  role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          已申请
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      
         <div id="hasApplyGroup" class="list-group">
             <!-- <li class="list-group-item">license1</li>
             <li class="list-group-item">license1</li>
             <li class="list-group-item">license1</li>
             <li class="list-group-item">license1</li> -->
         </div>
         
      
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a id="canApply" class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          可申请
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      
           
           <div id="canApplyGroup" class="list-group">
             <!-- <li class="list-group-item">license1</li>
             <li class="list-group-item">license1</li>
             <li class="list-group-item">license1</li>
             <li class="list-group-item">license1</li> -->
         </div>
        
      
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a id="applyHistory" class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          使用记录
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      
        
        <div id="applyHistoryGroup" class="list-group">
             <!-- <li class="list-group-item">license1</li>
             <li class="list-group-item">license1</li>
             <li class="list-group-item">license1</li>
             <li class="list-group-item">license1</li> -->
         </div>
         
      
    </div>
  </div>
<!-- </div> -->
               
               </div>
          </div> 
      </div>
       <div class="col-md-6">
          <div class="panel panel-default">
               <div class="panel-heading">
                    <h3 class="panle-title">信息栏</h3>
               </div>
               <div class="panel-body" id="infoContent"></div>
          </div> 
      </div>
</div>

</body>
</html>