<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>menubutton控件使用</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link id="easyuiTheme" rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
</head>
<body>
	<!-- 提供 a标签 class属性 easyui-menubutton ， 设置menu属性  -->
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#menu'">基本功能</a>
	<!-- 提供div 下拉菜单-->
	<div id="menu">
		<div data-options="iconCls:'icon-ok'">菜单一</div>
		<div>菜单二</div>
		<div class="menu-sep"></div>
		<div>菜单三</div>
	</div>
	
	<a href="javascript:void(0);">管理员功能</a>
</body>
</html>