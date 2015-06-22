// JavaScript Document
$(function(){
	$("nav li:first").addClass("first");
	$("nav li").tap(function(){
		$(this).addClass("first");
		$(this).siblings().removeClass("first");
	});
	
	$(".rem").live("tap",function(){
		var con = confirm("您确定删除此集约？");
		if(con == true){
			$(this).parents(".table").remove();
		}
	});
});