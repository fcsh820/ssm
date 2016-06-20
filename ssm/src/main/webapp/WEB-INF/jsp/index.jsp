<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ include file="/commons/global.jsp"%>
<html>
<head>
    <title>${sys_name}</title>
    <%@include file="/commons/head.jsp"%>
</head>
<body class="easyui-layout">
    <div data-options="region:'north',border:false" style="height:57px;background:#B3DFDA;padding:0;">
        <div class="cs-north-bg">
            <div class="cs-north-logo"></div>
            <div class="cs-north-title">${sys_name}</div>
            <div class="cs-north-btn">
                <a href="javascript:;" class="easyui-menubutton" data-options="plain:true,menu:'#mm1'" style="">欢迎您，@user.RealName</a>
                <a href="javascript:addTab('首页', '/basehome/welcome', '');" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-home'" style="">后台首页</a>
                <a href="/home/contact" target="_blank" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-help1'" style="">联系我们</a>
                <a href="javascript:logoff();" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-exit'" style="">安全退出</a>
            </div>
            <div id="mm1" style="width:100px;">
                <div onclick ="addTab($(this).text(), '/baseuser/setprofile', '');">修改资料</div>
                <div onclick="addTab($(this).text(), '/baseuser/changepwd', '');">修改密码</div>
            </div>
        </div>
    </div>
    <div data-options="region:'west',split:true,title:'导航菜单'" style="width: 200px; height: 165px;overflow:auto;">
        <ul id="tree" class="ztree"></ul>
    </div>
    <div data-options="region:'south',border:false" style="font-family: Arial;text-align:center;
            height:18px;background: #A9FACD; padding: 2px 10px;">
        Copyright &copy; @DateTime.Now.Year ${company}. All Rights Reserved
        &nbsp;&nbsp;
        快云技术支持：sxkyrj@163.com  2992160
        &nbsp;&nbsp;
        一体化系统指导交流QQ群：282866845
    </div>
    <div data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false,
                        tools:[
                        {iconCls : 'icon-delete3',text:'关闭全部',handler:closeAllTab}
                        ]">
            <div title="首页">
                <iframe scrolling="auto" frameborder="0" src="${basePath}welcome.htm" style="width:100%;height:99.5%;"></iframe>
            </div>
        </div>
    </div>
    <div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
        <img src="${basePath }/public/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    <script type="text/javascript">

        var setting = {
            view: {
                dblClickExpand: false
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
            if (treeNode.isParent) {
                var zTree = $.fn.zTree.getZTreeObj("tree");
                zTree.expandNode(treeNode);
                return;
            }

            var title = treeNode.name;
            var url = treeNode.page;
            var icon = '';
            addTab(title, url, icon);
        }

        var zNodes = [
            { id: 1, pId: 0, name: "快云管理平台", open: true, iconOpen: "${basePath}public/images/icon/book_open.png", iconClose: "${basePath}public/images/icon/book_close.png" },
            <%--{ id: 11, pId: 1, name: "基础数据设置", open: true, iconOpen: "${basePath}public/images/icon/book_open.png", iconClose: "${basePath}public/images/icon/book_close.png" },--%>
            <%--{ id: 111, pId: 11, name: "银行设置", icon: "${basePath}public/images/icon/nav.png", page: "/bank/index" },--%>
            <%--{ id: 111, pId: 11, name: "行政区域", icon: "${basePath}public/images/icon/nav.png", page: "/basearea/index" },--%>
            <%--{ id: 111, pId: 11, name: "人员信息管理", icon: "${basePath}public/images/icon/nav.png", page: "/employee/manage" },--%>
            <%--{ id: 111, pId: 11, name: "人员信息查询", icon: "${basePath}public/images/icon/nav.png", page: "/employee/index" },--%>
            <%--{ id: 111, pId: 11, name: "资金类型管理", icon: "${basePath}public/images/icon/nav.png", page: "/zijlx/index" },--%>
            <%--{ id: 111, pId: 11, name: "资金类型分配", icon: "${basePath}public/images/icon/nav.png", page: "/zijlx/assign" },--%>
            <%--{ id: 111, pId: 11, name: "资金类型明细", icon: "${basePath}public/images/icon/nav.png", page: "/zijlxmx/index" },--%>
            <%--{ id: 111, pId: 11, name: "资金类型文件", icon: "${basePath}public/images/icon/nav.png", page: "/zijlxmx_doc/index" },--%>
            <%--{ id: 111, pId: 11, name: "资金类型互斥", icon: "${basePath}public/images/icon/nav.png", page: "/zijhc/index" },--%>
            <%--{ id: 22, pId: 1, name: "日常业务处理", open: true, iconOpen: "${basePath}public/images/icon/book_open.png", iconClose: "${basePath}public/images/icon/book_close.png" },--%>
            <%--{ id: 221, pId: 22, name: "资金下达管理", icon: "${basePath}public/images/icon/nav.png", page: "/zijxd/manage" },--%>
            <%--{ id: 221, pId: 22, name: "资金下达查询", icon: "${basePath}public/images/icon/nav.png", page: "/zijxd/index" },--%>
            <%--{ id: 222, pId: 22, name: "资金发放", icon: "${basePath}public/images/icon/nav.png", page: "/zijff/add" },--%>
            <%--{ id: 222, pId: 22, name: "发放列表", icon: "${basePath}public/images/icon/nav.png", page: "/zijff/index" },--%>
            <%--{ id: 222, pId: 22, name: "待办任务", icon: "${basePath}public/images/icon/nav.png", page: "/workflowtask/waitlist" },--%>
            <%--{ id: 222, pId: 22, name: "已办任务", icon: "${basePath}public/images/icon/nav.png", page: "/workflowtask/completedlist" },--%>
            <%--{ id: 33, pId: 1, name: "流程配置", open: true, iconOpen: "${basePath}public/images/icon/book_open.png", iconClose: "${basePath}public/images/icon/book_close.png" },--%>
            <%--{ id: 331, pId: 33, name: "流程管理", icon: "${basePath}public/images/icon/nav.png", page: "/workflowdesigner/index" },--%>
            <%--{ id: 331, pId: 33, name: "按钮管理", icon: "${basePath}public/images/icon/nav.png", page: "/zijxd/manage" },--%>
            <%--{ id: 331, pId: 33, name: "表单管理", icon: "${basePath}public/images/icon/nav.png", page: "/zijxd/manage" },--%>
            { id: 55, pId: 1, name: "系统管理", open: true, iconOpen: "${basePath}public/images/icon/book_open.png", iconClose: "${basePath}public/images/icon/book_close.png" },
            { id: 551, pId: 55, name: "系统资源", icon: "${basePath}public/images/icon/nav.png", page: "${basePath}resource/index" },
            { id: 551, pId: 55, name: "数据字典", icon: "${basePath}public/images/icon/nav.png", page: "${basePath}dic/index.htm" },
            { id: 552, pId: 55, name: "组织机构", icon: "${basePath}public/images/icon/nav.png", page: "${basePath}organization/list.htm" },
            { id: 554, pId: 55, name: "角色管理", icon: "${basePath}public/images/icon/nav.png", page: "${basePath}role/list.htm" },
            { id: 555, pId: 55, name: "角色授权", icon: "${basePath}public/images/icon/nav.png", page: "${basePath}permission/index.htm" },
            { id: 556, pId: 55, name: "用户管理", icon: "${basePath}public/images/icon/nav.png", page: "${basePath}user/list.htm" },
            { id: 556, pId: 55, name: "登录日志", icon: "${basePath}public/images/icon/nav.png", page: "/baselog/login" },
            { id: 557, pId: 55, name: "修改密码", icon: "${basePath}public/images/icon/nav.png", page: "/baseuser/changepwd" },
            { id: 558, pId: 55, name: "修改资料", icon: "${basePath}public/images/icon/nav.png", page: "/baseuser/setprofile" }
        ];

        $(function () {
            $.fn.zTree.init($("#tree"), setting, zNodes);
        });

        //顶层弹窗
        function doDialog(data) {
            $("<div id='dlg'/>").dialog(data);
        }

        //顶层弹窗扩展
        function doDialogEx(dom, opts, callback) {
            $(dom).dialog(opts);
            if (callback) {
                callback();
            }
        }

    </script>
</body>
</html>
