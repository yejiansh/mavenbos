<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	// 自定义 easyui form 校验器 
	$.extend($.fn.validatebox.defaults.rules, {
		telephone : {
			validator : function(value, param) {
				var regexp = /^1[3|4|5|8]\d{9}$/ ;
				return regexp.test(value);
			},
			message : '手机号必须为11位数字'
		}
	});

	function doAdd() {
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}

	function doView() {
		alert("查看...");
	}

	// 作废
	function doDelete() {
		// 获取页面中 选择所有数据id
		var rows = $("#grid").datagrid('getSelections');
		// 判断是否选中 
		if(rows.length == 0){
			// 一行没选中 
			$.messager.alert("警告","作废功能至少选中一行数据","warning");
			return ;
		}
		// 选中数据， 获取这些数据id
		var array = new Array();
		for(var i=0; i<rows.length; i++){
			array.push(rows[i].id);
		}
		var ids = array.join(",");
		// 发送给服务器 
		location.href = "${pageContext.request.contextPath}/staff_delBatch.action?ids="+ids;
	}

	function doRestore() {
		alert("将取派员还原...");
	}
	//工具栏
	var toolbar = [ {
		id : 'button-view',
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, {
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	}, {
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	}, {
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	} ];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter: function(value, rowData, rowIndex){
			// value 匹配json中属性值  
			// rowData 整个一行数据json对象 
			// rowIndex 行号 
			if(value=="1"){
				return "有";
			}else{
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "1") {
				return "已作废";
			} else {
				return "正常使用";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 取派员信息表格
		$('#grid').datagrid({
			iconCls : 'icon-forward',
			fit : true,
			border : false,
			rownumbers : true,
			striped : true,
			pageList : [ 30, 50, 100 ],
			pagination : true,
			toolbar : toolbar,
			url : "${pageContext.request.contextPath}/staff_pageQuery.action",
			idField : 'id',
			columns : columns,
			onDblClickRow : doDblClickRow
		});

		// 添加取派员窗口
		$('#addStaffWindow').window({
			title : '添加取派员',
			width : 400,
			modal : true,
			shadow : true,
			closed : true,
			height : 400,
			resizable : false
		});

		// 为保存按钮，添加点击事件
		$("#save").click(function() {
			// 手动校验
			/*
			var name = $("input[name='name']").val();
			if(name.trim()==""){
				$.messager.alert("警告","取派员姓名不能为空","warning");
			}
			 */

			// 提交form 
			if ($("#staffForm").form('validate')) {
				// 当form 所有字段 都通过校验 提交form表单
				$("#staffForm").submit();
			}
		});
	});

	function doDblClickRow(rowIndex, rowData) {
		// rowIndex 行号
		// rowData 当前点击 行 数据 js 对象
		// 装载form数据 
		$("#staffForm").form('load',rowData);
		// 显示窗口
		$("#addStaffWindow").window('open');
	}
</script>
</head>
<body class="easyui-layout" style="visibility:hidden;">
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top:20px;left:200px">
		<div region="north" style="height:31px;overflow:hidden;" split="false"
			border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="javascript:void(0);"
					class="easyui-linkbutton" plain="true">保存</a>
			</div>
		</div>

		<div region="center" style="overflow:auto;padding:5px;" border="false">
			<form id="staffForm"
				action="${pageContext.request.contextPath }/staff_save.action"
				method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<tr>
						<td>姓名</td>
						<td>
							<!-- 隐藏域 id 用来区分 执行 save 还是 update -->
							<input type="hidden" name="id"/>
							<input type="text" name="name" class="easyui-validatebox"
							data-options="required:true" />
						</td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone"
							class="easyui-numberbox"
							data-options="required:true,validType:'telephone'" />
						</td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station" />
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="haspda"
							value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
