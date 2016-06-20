<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/global.jsp"%>
<html>
<head>
    <title>Title</title>
    <%@ include file="/commons/head.jsp"%>
</head>
<body class="easyui-layout" data-options="border:false">
<div data-options="region:'center',border:false" style="padding: 3px 3px 0 3px;">
    <div data-options="fit:true,border:false" class="easyui-layout">
        <div data-options="region:'north',border:false" style="margin-bottom:3px;height:38px;">
            <div class="easyui-panel" style="padding:5px;margin:0;">
                <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="add();">添加</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="edit();">修改</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="del();">删除</a>
                <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick='grid.datagrid("load").datagrid("clearSelections").datagrid("clearChecked");'>刷新</a>
            </div>
        </div>
        <div data-options="region:'center',border:false">
            <table id="grid"></table>
        </div>
    </div>
</div>
<script type="text/javascript">

    var grid;

    $(function () {

        var url = '${basePath}role/get_list.json';
        var fcols = [
            { field: 'ck', checkbox: true },
            { field: 'id', title: 'id', hidden: true },
            { field: 'name', title: '角色名称', width: 200, align: 'left' }
        ];
        var cols = [
            { field: 'sort', title: '顺序', width: 100, align: 'left' },
            { field: 'note', title: '备注', width: 200, align: 'left' }
        ];

        grid = $('#grid').datagrid({
            url: url,
            idField: 'id',
            iconCls: 'icon-table',
            fit: true,
            nowrap: false,
            rownumbers: true,
            frozenColumns: [fcols],
            columns: [cols]
        });
    });

    function add() {
        var title = '添加角色';
        var url = '${basePath}role/add_page.htm';

        parent.$.modalDialog({
            href: url,
            title: title,
            height: 300,
            width: 600,
            modal: true,
            iconCls: "icon-add",
            buttons: [
                {
                    id: "dlg_btn_add",
                    text: '确定',
                    iconCls: 'icon-ok',
                    handler: function () {
                        parent.$.modalDialog.openner_grid = grid;//因为添加成功之后，需要刷新这个tgrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#frmEdit');
                        f.submit();
                    }
                },
                {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () { parent.$.modalDialog.handler.dialog('close'); }
                }
            ],
            onLoad: function () {
                parent.$.modalDialog.handler.find('#name').next().find('input').focus();
            }
        });
    }

    function edit() {
        var r = grid.datagrid('getChecked');
        if (r < 1) {  parent.$.messager.alert("错误", "请选择要操作的数据", 'warning'); return; }
        if (r.length > 1) {
            parent.$.messager.alert("错误", "每次只能修改一条记录", 'warning');
            grid.treegrid('clearSelections').datagrid('clearChecked');
            return;
        }

        var title = '修改用户';
        var url = '${basePath}role/edit_page.htm?id='+r[0].id;
        parent.$.modalDialog({
            href: url,
            title: title,
            height: 300,
            width: 600,
            modal: true,
            iconCls: "icon-edit",
            buttons: [
                {
                    id: "dlg_btn_edit",
                    text: '确定',
                    iconCls: 'icon-ok',
                    handler: function () {
                        parent.$.modalDialog.openner_grid = grid;//因为添加成功之后，需要刷新这个grid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#frmEdit');
                        f.submit();
                    }
                },
                {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () { parent.$.modalDialog.handler.dialog('close'); }
                }
            ],
            onLoad: function () {
                //$p("#frmEdit").form("load", r[0]);
                parent.$.modalDialog.handler.find('#Name').next().find('input').focus();
            }
        });
    }

    function del() {
        var rows = grid.datagrid("getChecked");
        if (rows.length < 1) { parent.$.messager.alert("错误", "请选择要操作的数据", 'warning'); return; }

        var purl = '${basePath}role/del.json';
        parent.$.messager.confirm("询问", "确认删除？", function(r) {
            if(r){
                var param = {};
                param.ids = $.map(rows, function (row) { return row.id; }).join(",");
                $.post(purl, param, function (resp) {
                    if (resp.success) {
                        grid.datagrid("reload").datagrid('clearSelections');
                    }
                    else {
                        parent.$.messager.alert('错误', resp.msg, 'warning');
                    }
                }, "json");
            }
        });
    }

    function searchData() {
        var param = $.serializeObject($('#frmSearch'));
        grid.datagrid('load', param);
    }

</script>
</body>
</html>
