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
        <div data-options="region:'center',border:false">
            <table id="tgrid" class="easyui-treegrid"></table>
        </div>
    </div>
</div>
<script type="text/javascript">

    var tgrid;

    $(function () {

        var url = '${basePath}resource/list.json';
        var fcols = [
            { field: 'id', title: 'id', hidden: true },
            { field: 'no', title: '资源编号', width: 100 }
        ];
        var cols = [
            { field: 'name', title: '资源名称', width: 200, align: 'left' },
            { field: 'note', title: '备注', width: 200, align: 'left' }
        ];

        tgrid = $('#tgrid').treegrid({
            url: url,
            method: 'get',
            idField: 'no',
            treeField: 'name',
            fit: true,
            singleSelect: true,
            rownumbers: true,
            frozenColumns: [fcols],
            columns: [cols],
            toolbar: [{
                id: 'btnAdd',
                text: '添加',
                iconCls: 'icon-add',
                handler: add
            }, {
                id: 'btnEdit',
                text: '修改',
                iconCls: 'icon-edit',
                handler: edit
            }, {
                id: 'btnDel',
                text: '删除',
                iconCls: 'icon-remove',
                handler: del
            }, '-', {
                id: 'btnReload',
                text: '刷新',
                iconCls: 'icon-reload',
                handler: function () {
                    $("#tgrid").treegrid("reload").treegrid("clearSelections");
                }
            }]
        });
    });

    function add() {
        var title = '添加资源';
        var url = '${basePath}resource/add_page.htm';

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
                        parent.$.modalDialog.openner_tgrid = tgrid;//因为添加成功之后，需要刷新这个tgrid，所以先预定义好
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
                parent.$.modalDialog.handler.find('#no').next().find('input').focus();
            }
        });
    }

    function edit() {
        var r = tgrid.treegrid('getChecked');
        if (r < 1) {  parent.$.messager.alert("错误", "请选择要操作的数据", 'warning'); return; }
        if (r.length > 1) {
            parent.$.messager.alert("错误", "每次只能修改一条记录", 'warning');
            tgrid.treegrid('clearSelections').treegrid('clearChecked');
            return;
        }

        var title = '修改资源';
        var url = '${basePath}resource/edit_page.htm?id='+r[0].id;
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
                        parent.$.modalDialog.openner_tgrid = tgrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
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
        var rows = tgrid.treegrid("getChecked");
        if (rows.length < 1) { parent.$.messager.alert("错误", "请选择要操作的数据", 'warning'); return; }

        var purl = '${basePath}resource/del.json';
        parent.$.messager.confirm("询问", "您是否要删除当前资源？删除当前资源会连同子资源一起删除!", function(r) {
            if(r){
                var param = {};
                param.no = rows[0].no;
                $.post(purl, param, function (resp) {
                    if (resp.success) {
                        tgrid.treegrid("reload").treegrid('clearSelections');
                    }
                    else {
                        parent.$.messager.alert('错误', resp.msg, 'warning');
                    }
                }, "json");
            }
        });
    }

</script>
</body>
</html>
