<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/webapp/css/bootstrap.min.css">

</head>
<body class="container-fluid">

<script src="${pageContext.request.contextPath}/webapp/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webapp/js/bootstrap.min.js"></script>

<!-- 导航条 -->
<nav class="navbar navbar-default">
    
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
       <div><a href="#">userId: ${user.id}</a></div>
       
   </div>
       
   
   </div>
   
   
   

</nav>

<div id="mid" class="row">
     <div class="col-md-4">
          <div class="panel panel-default">
               <div class="panel-heading">
                    <h3 class="panle-title">个人资料</h3>
               </div>
               <div class="panel-body">
                    <div class="row">
                         <div class="col-md-6">姓名:</div>
                         <div class="col-md-6">${user.name}</div>
                    </div>
                    <div class="row">
                         <div class="col-md-6">年龄:</div>
                         <div class="col-md-6">${user.name}</div>
                    </div>
               </div>
          </div> 
      </div>
      <div class="col-md-4">
          <div class="panel panel-default">
               <div class="panel-heading">
                    <h3 class="panle-title">申请事项</h3>
               </div>
               <div class="panel-body">
               
               <!-- 标签框 -->
               <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          已申请
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          可申请
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body">
        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
      </div>
    </div>
  </div>
  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingThree">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
          申请历史
        </a>
      </h4>
    </div>
    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
      <div class="panel-body">
        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
      </div>
    </div>
  </div>
</div>
               
               </div>
          </div> 
      </div>
       <div class="col-md-4">
          <div class="panel panel-default">
               <div class="panel-heading">
                    <h3 class="panle-title">title</h3>
               </div>
               <div class="panel-body">content</div>
          </div> 
      </div>
</div>

</body>
</html>