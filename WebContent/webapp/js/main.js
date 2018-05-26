//已申请列表
$(function (){
	
	$('#hasApply').ready(function (){
		
		$.ajax({
			type:"post",
			dataType:"json",
			url:"./license/checkApplyingLicense",
			data:{},
			success:function (data){
				//var list = data.result;
				for(i=0;i<data.length;i++){
					var Li = '<li class="list-group-item"><a  class="applyInfo" type="button" value="'+data[i].applicationId+'" >'+data[i].license.licenseName+'</a></li>';
					$('#hasApplyGroup').append(Li);
				}
			},
			error:function (data){
				alert("error");
			}
		});
		
	});
	
});


//可申请列表
$(function (){
	
	$('#canApply').ready(function (){
		
		$.ajax({
			type:"post",
			dataType:"json",
			url:"./license/checkAllLicense",
			data:{},
			success:function (data){
				//var list = data.result;
				for(i=0;i<data.length;i++){
					var Li = '<li class="list-group-item"><a  class="canApplyInfo" type="button" value="'+data[i].licenseId+'">'+data[i].licenseName+'</a></li>';
					$('#canApplyGroup').append(Li);
				}
			},
			error:function (data){
				alert("error");
			}
		});
		
	});
	
});

//申请历史列表
$(function (){
	
	$('#applyHistory').ready(function (){
		
		$.ajax({
			type:"post",
			dataType:"json",
			url:"./license/checkHistoryLicense",
			data:{},
			success:function (data){
				//var list = data.result;
				
				for(i=0;i<data.length;i++){
					
					var date = changeDate(data[i]);
				    
					var Li = '<li class="list-group-item"><a class="historyInfo" type="button" value="'+data[i].recordId+'">'+date+'--'+data[i].license.licenseName+'</a></li>';
					$('#applyHistoryGroup').append(Li);
				}
			},
			error:function (data){
				alert("error");
			}
		});
		
	});
	
});

//已申请列表信息
$(function (){
	
	$("ul").on("click",".applyInfo",function (){
		
		var apply = $(this).attr("value");
		var applyId = {
				"applyId" : apply
		};
		
		$.ajax({
			type:"post",
			dataType:"json",
			url:"./license/checkApplyInfo",
			data:applyId,
			success:function (data){
				//var list = data.result;
	                $(".infoBox").remove();
	                if(data.isSuccessApply)
	                	var msg="已完成";
	                else if(data.isStopApply)
	                	var msg="已停止";
	                    else var msg="进行中";
					var Li = '<div class="infoBox">'+
						     '<li  class="list-group-item"><div><h4>申请使用天数:<h4></div><div>'+data.wantTime+'</div></li>'+
					         '<li  class="list-group-item"><div><h4>申请提交天数:<h4></div><div>'+data.applyTime+'</div></li>'+
					         '<li  class="list-group-item"><div><h4>申请情况:<h4></div><div>'+msg+'</div></li>'+
					         '<li  class="list-group-item"><div><h4>许可证名称:<h4></div><div>'+data.license.licenseName+'</div></li>'+
					         '<li  class="list-group-item"><div><h4>许可证使用时长:<h4></div><div>'+data.license.useLimitTime+'天</div></li>'
					         
					         +'</div>';
					
					$('#infoContent').append(Li);
				
			},
			error:function (data){
				alert("error");
			}
		});
		
	});
	
});

//可申请证书信息
$(function (){
	
	$("ul").on("click",".canApplyInfo",function (){
		
		var license = $(this).attr("value");
		var licenseId = {
				"licenseId" : license
		};
		
		$.ajax({
			type:"post",
			dataType:"json",
			url:"./license/checkCanApplyInfo",
			data:licenseId,
			success:function (data){
				//var list = data.result;
	                $(".infoBox").remove();
	                
					var Li = '<div class="infoBox">'+
		
					         '<li  class="list-group-item"><div><h4>许可证ID:<h4></div><div id="licenseId" value="'+license+'">'+license+'</div></li>'+
					         '<li  class="list-group-item"><div><h4>许可证名称:<h4></div><div>'+data.licenseName+'</div></li>'+
					         '<li  class="list-group-item"><div><h4>许可证使用时长:<h4></div><div>'+data.useLimitTime+'天</div></li>'+
					         '<li  class="list-group-item"><button name="apply" type="submit" class="btn btn-lg btn-primary btn-block" data-toggle="modal" data-target="#myModal">申请</button></li>'
					         
					         +'</div>';
					
					$('#infoContent').append(Li);
				
			},
			error:function (data){
				alert("error");
			}
		});
		
	});
	
});

//申请历史信息
$(function (){
	
	$("ul").on("click",".historyInfo",function (){
		
		var record = $(this).attr("value");
		var recordId = {
				"recordId" : record
		};
		
		$.ajax({
			type:"post",
			dataType:"json",
			url:"./license/checkHistoryApplyInfo",
			data:recordId,
			success:function (data){
				//var list = data.result;
	                $(".infoBox").remove();
	                
	                var stDate = new Date(data.startTime);
	                var year = stDate.getFullYear();
	                var month = stDate.getMonth()+1;
	                var day = stDate.getDate();
	                
					var Li = '<div class="infoBox">'+
					
						     '<li  class="list-group-item"><div><h4>开始使用时间:<h4></div><div>'+year+'-'+month+'-'+day+'</div></li>'+
					         '<li  class="list-group-item"><div><h4>许可证名称:<h4></div><div>'+data.license.licenseName+'</div></li>'+
					         '<li  class="list-group-item"><div><h4>许可证使用时长:<h4></div><div>'+data.license.useLimitTime+'天</div></li>'+
					         '<li  class="list-group-item"><div><h4>使用者:<h4></div><div>'+data.user.name+'</div></li>'
					         
					         +'</div>';
					
					$('#infoContent').append(Li);
				
			},
			error:function (data){
				alert("error");
			}
		});
		
	});
	
});

$(function(){
	$('#myModal').ready(function (){
		
		$('#datetimepicker').datetimepicker({
			format:'yyyy-mm-dd',
			startView: 2,
			minView: 2,
			maxView: 3,
			todayBtn: true,
			todayHighlight: true,
			initialDate: new Date()
		});
		
	});
});

//提交申请
$(function(){
	$("div").on("click","#applyBtn",function(){
		
		var wantTime = $("#wantTime").val();
		var startTime = $("#datetimepicker").data("datetimepicker").getDate();
		var userId = $("#userInfo").attr("value");
		var licenseId = $("#licenseId").attr("value");
		var application = {
				"wantTime"  : wantTime,
				"startTime" : startTime,
				"license_id" : licenseId,
				"user_id"    : userId,
		};
		
		$.ajax({
			
			type:"POST",
			dataType:"json",
			//contentType:"application/json",
			url:"./license/applyLicense",
			data: application ,
			success: function(result){
				if(result.msg == "success")
					alert("申请成功！");
				else alert("申请失败！");
			},
			error:function(application){
				alert("error");
			}
			
		});
		return false;
		
	});
});

function changeDate(data){
	var stDate = new Date(data.startTime);
    var year = stDate.getFullYear();
    var month = stDate.getMonth()+1;
    var day = stDate.getDate();
    return ""+year+"-"+month+"-"+day;
}



