$(function(){
	$('#signModal').ready(function(){
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

$(function(){
	$("div").on("click","#signBtn",function(){
		var name = $("#signName").val();
		var pwd = $("#signPwd").val();
		var user = {
				"signName" : name,
				"signPwd"  : pwd,
		};
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"./register",
			data:user,
			success: function(result){
				if(result.msg == "success"){
					alert("注册成功！");
					window.location.href="./main";
				}
				else alert("注册失败！");
			},
			error:function(result){
				alert("error");
			}
		});
		return false;
	});
});