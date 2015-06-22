// JavaScript Document
$(function(){
	$(".rem").live("tap",function(){
		var con = confirm("您确定删除此集约？");
		if(con == true){
			$(this).parents(".remove").remove();
		}
	});
});