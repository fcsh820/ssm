<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/commons/global.jsp"%>
<html>
<head>
    <title>Title</title>
    <%@ include file="/commons/head.jsp"%>
</head>
<body class="easyui-layout" data-options="border:false">
    <div id="cc" class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',border:false" style="height:30px;background-color:#cccccc;padding:9px 5px 0 5px;">
            <div>
                <p style="float:left;">当前角色：<span id="c-rolename"></span></p>
                <p style="float:right;padding:0;margin-top:-7px;">
                    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="save();">保存</a>
                </p>
            </div>
        </div>
        <div data-options="region:'west',title:'所有角色',iconCls:'icon-role',split:true" style="width:450px;">
            <table id="grid"></table>
        </div>
        <div data-options="region:'center',title:'权限分配',iconCls:'icon-perm'" style="padding: 5px;">
            <ul id="tree" class="ztree"></ul>
        </div>
    </div>
    <script type="text/javascript">

        var grid,ztree,roleid;

        $(function(){
            loadRole();
        });

        //save perm
        function save() {
            if (!roleid) {
                alert("请选择一个角色！");
                return;
            }

            var zTree = $.fn.zTree.getZTreeObj("tree");
            var nodes = zTree.getCheckedNodes(true);
            var resIds = nodes.map(function (e) { return e.id; }).join(',');
            $.post("${basePath}permission/save.json", { roleId: roleid, resourceIds: resIds },
                    function (resp) {
                        if (resp.success) {
                            parent.$.messager.alert(msgTip, msgOperSuccess);
                        }
                        else {
                            alert("操作失败：" + resp.msg);
                        }
                    });
        }

        //load perm
        var setting = {
            check: {
                enable: true
            },
            view: {
                dblClickExpand: false
            },
            data: {
                key: {
                    name: "text"
                },
                simpleData: {
                    idKey: "no",
                    pIdKey: "pno",
                    enable: true
                }
            },
            callback: {
                onClick: onClick
            }
        };

        function onClick(e, treeId, treeNode) {
            if (treeNode.isParent) {
                var zTree = $.fn.zTree.getZTreeObj("tree");
                zTree.expandNode(treeNode);
                return;
            }
        }

        function loadPerm(roleId) {
            var url = '${basePath}resource/get_tree.json?_='+Math.random()+'&role_id='+roleId;
            $.get(url, function (resp) {
                $.fn.zTree.init($("#tree"), setting, resp);
            });
        }

        //load role
        function loadRole() {

            var url = '${basePath}role/get_list.json';
            var fcols = [
                { title: 'id', field: 'id', hidden: true },
                { title: '角色名称', field: 'name', width: 200 },
            ];
            var cols = [
                { title: '顺序', field: 'sort', width: 60 },
                { title: '备注', field: 'note', width: 280 }
            ];

            grid = $('#grid').datagrid({
                url: url,
                idField: 'id',
                fit: true,
                nowrap: false,
                border: false,
                rownumbers: true,
                singleSelect: true,
                frozenColumns: [fcols],
                columns: [cols],
                toolbar: [
                    {
                        id: 'btnReload',
                        text: '刷新',
                        iconCls: 'icon-reload',
                        handler: function () {
                            grid.datagrid("reload");
                        }
                    }
                ],
                onClickRow: function (index, row) {
                    roleid = row["id"];
                    loadPerm(row["id"]);
                },
                onLoadError: function () { grid.datagrid('loadData', { total: 0, rows: [] }); }
            });

        }

    </script>
</body>
</html>
