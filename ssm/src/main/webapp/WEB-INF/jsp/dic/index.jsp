<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/global.jsp"%>
<html>
<head>
    <title>Title</title>
    <%@ include file="/commons/head.jsp"%>
</head>
<body class="easyui-layout" data-options="border:false" style="">
    <div data-options="region:'west',title:'字典分类',iconCls:'icon-type',split:true" style="width:220px;">
        <div class="tree-tools">
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'" onclick="loadDicType();">刷新</a>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-arrow-out'" onclick="expandNode(true);">展开</a>
            <a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-arrow-in'" onclick="expandNode(false);">折叠</a>
        </div>
        <ul id="tree" class="ztree"></ul>
    </div>
    <div data-options="region:'center',border:false" style="padding: 3px 3px 0 3px;">
        <table id="grid"></table>
    </div>
    <script type="text/javascript">

        var tree, grid, code;

        //load dictype
        var setting = {
            view: {
                dblClickExpand: false,
                selectedMulti: false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onClick: onClick
            }
        };

        function onClick(e, treeId, treeNode) {
            console.log(treeNode);
            code = treeNode.tag;
            loadData(code);
        }

        function loadDicType() {
            var url = '${basePath}dic/get_typetree.json?_='+Math.random();
            $.get(url, function (resp) {
                tree = $.fn.zTree.init($("#tree"), setting, resp);
            });
        }

        $(function () {
            loadDicType();
        });

        //load dicdata
        function loadData(code) {
            var url = '${basePath}dic/get_list.json?code='+code;
            var fcols = [
                { field: 'ck', checkbox: true },
                { field: 'id', title: 'id', hidden: true },
                { field: 'name', title: '项目名称', width: 100 },
                { field: 'val', title: '项目值', width: 200, align: 'left' }
            ];
            var cols = [
                { field: 'status', title: '有效', width: 60,
                    formatter: function (value, row, index) {
                        if (row.status == 0) return '<span style="color:red;">禁用</span>';
                        if (row.status == 1) return '<span style="color:blue;">启用</span>';
                    }
                },
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
                        grid.datagrid("load").datagrid('clearSelections').datagrid('clearChecked');
                    }
                }],
                onDblClickRow: function (rowIndex, rowData) {     //双击行弹框编辑
                    grid.datagrid('clearSelections').datagrid('clearChecked').datagrid('checkRow', rowIndex);
                    view();
                },
                onLoadError: function () { grid.datagrid('loadData', { total: 0, rows: [] }); }
            });
        }

        function add() {
            var title = '添加字典';
            var url = '${basePath}dic/add_page.htm?code='+code;

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

            var title = '修改用户';
            var url = '${basePath}organization/edit_page.htm?id='+r[0].id;
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
                            parent.$.modalDialog.openner_grid = grid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
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
                    parent.$.modalDialog.handler.find('#name').next().find('input').focus();
                }
            });
        }

        function del() {
            var rows = grid.datagrid("getChecked");
            if (rows.length < 1) { parent.$.messager.alert("错误", "请选择要操作的数据", 'warning'); return; }

            var purl = '${basePath}dic/deleteByIds.json';
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

    </script>
</body>
</html>
