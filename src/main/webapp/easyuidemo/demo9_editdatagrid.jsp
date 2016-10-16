<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>datagrid控件使用</title>
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
<script type="text/javascript">
	$(function(){
		// 通过js代码 定义数据表格
		$("#grid").datagrid({
			// 定义表头
			columns : [[ // 二维数组，每个数组就是一行，可以定义复杂表头
				{
					title: '编号',
					field : 'id',
					width : 100,
					align : 'center',
					checkbox :true
				},
				{
					title: '名称',
					field : 'name',
					width : 200,
					align : 'center',
					// 设置editor 属性 可被编辑
					editor : {
						type : "validatebox", 
						options : {
							required : true
						}
					}
				},
				{
					title: '价格',
					field : 'price',
					width : 200,
					align: 'center'
				}
			]],
			url : 'data.json',
			pagination : true,
			rownumbers : true,
			toolbar: [ // 可以定义很多按钮
				 {
					id: "edit",
					text: "编辑",
					iconCls : 'icon-edit',
					handler : function(){
						// 使表格第一行 可以被编辑
						$("#grid").datagrid('beginEdit', 0);
					}
				 },
				 {
					id: "save",
					text: "保存",
					iconCls : 'icon-save',
					handler : function(){
						// 结束编辑 保存
						$("#grid").datagrid('endEdit', 0);
					}
				  },
				  {
						id: "cancel",
						text: "取消",
						iconCls : 'icon-cancel',
						handler : function(){
							// 结束编辑 取消
							$("#grid").datagrid('cancelEdit', 0);
						}
				},
				{
					id: "add",
					text: "新增",
					iconCls : 'icon-add',
					handler : function(){
						// 在表格中插入一个新的行
						$("#grid").datagrid('insertRow' , {
							// index 行号
							index : 0,
							// row当前行数据
							row : {
								id: 1000,
								name : "热水器",
								price : 2999
							}
						});
					}
				}
			],
			onAfterEdit : function(rowIndex, rowData, changes){
				// rowIndex 当前结束编辑行号
				// rowData 当前编辑后 行数据 
				// changes 改变的字段 
				alert("编辑结束！");
				// 发送Ajax请求， 将当前编辑rowData 发给服务器，执行保存操作，插入到数据库
			}
		});
		
	});
</script>	
</head>
<body>
	<!-- 案例三： 使用JS定义数据表格 -->
	<table id="grid"></table>
</body>
</html>