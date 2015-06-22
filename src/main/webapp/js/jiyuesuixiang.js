// JavaScript Document
$(function(){
	$("#jyInfo img").tap(function(){
		$("#jyInfo").hide();
	});
	$(".zan").live("tap",function(){
		if($(this).hasClass("on")){
			$(this).children("span").addClass("on")
			var html = $(this).children("span").html();
			var num = parseInt(html.substr(1))+1;
			$(this).children("span").html("已赞"+num);
			$(this).removeClass("on");
		}
		else{
			$(this).children("span").removeClass("on");
			var html = $(this).children("span").html();
			var num = parseInt(html.substr(2))-1;
			$(this).children("span").html("赞"+num);
			$(this).addClass("on");
		}
	});
});
window.onload = function() {
	$("#titleImg").css("height",$("#banner_img img").height());
};
window.onresize = function(){
	$("#titleImg").css("height",$("#banner_img img").height());
}
$(document).ready(function() {
	var bannr=$("#banner");
	var id=$("#banner_img");
	var list=$("#banner_list");
	Rui.banner(id,list,bannr); 
});
var Rui=new Object();
//图片切换封装事件
Rui.banner=function(id,list,bannr){
	//加上导航
	Rui.index=id.find('li').length;//当前总数
	Rui.total=0;//当图片
	Rui.arrive=0;//到当图片怎么
	Rui.ba_t=true;
	//输出html
	for(var i=0;i<Rui.index;i++){
		if(i==Rui.total){
			list.append('<li class="on" index="'+i+'"></li>');
		}else{
			list.append('<li index="'+i+'"></li>');
		}
	}

	//定时事件
	var t=setInterval(function(){
		$('#banner_right').click();
	},3000);
	
	//停止事件
	bannr.mouseover(function(){
		clearInterval(t);
		//console.log('停止');
	}).mouseout(function(){
		t=setInterval(function(){
		$('#banner_right').click();
		///console.log('开始');
		},3000);
	});
	
	//左滑动事件
	$('#banner_left').live('click',function(){
		
		Rui.arrive=Rui.total-1;
		if(Rui.arrive < 0){
			Rui.arrive=Rui.index-1;
		}
		yindong(Rui.total,Rui.arrive,-1);
	});	
	//右滑事件
	$('#banner_right').live('click',function(){
		Rui.arrive=(Rui.total*1)+1;
		if(Rui.arrive > Rui.index-1){
			Rui.arrive=0;
		}
		yindong(Rui.total,Rui.arrive,1);
	})
	
	function yindong(total,arrive,off){
			//阻止多次点击
			if(!Rui.ba_t){
				return;
			}
			Rui.ba_t=false;
			off=off*100;
			onn=-1*off;
			$("#banner_list li").removeClass("on");
			$("#banner_list li").eq(arrive).addClass("on");//改变下标
			id.find('li').eq(arrive).addClass("on").css({"left":off+"%"});
			id.find('li').eq(arrive).addClass("on").animate({"left":"0"},500);
			id.find('li').eq(total).addClass("on").animate({"left":onn+"%"},500,function(){
				$(this).removeClass('on').css({"left":"0"});
				Rui.ba_t=true;
			});
			Rui.total=arrive;
	}
}
