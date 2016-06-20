$(function () {
    initSys();
    initNav();
    tabClose();
    tabCloseEven();
})

function initSys() {
    //ajax全局
    $(document).ajaxError(function (event, jqxhr, settings, thrownError) {
        switch (jqxhr.status) {
            case (500):
                console.log(jqxhr.responseText);
                alert("服务器系统内部错误");
                break;
            case (401):
                alert("未登录");
                break;
            case (403):
                alert("无权限执行此操作");
                break;
            case (408):
                alert("请求超时");
                break;
            case (422): //自定义错误
                alert("操作失败：" + jqxhr.responseText);
                break;
            default:
                alert("未知错误");
        }
    });

    $(document).ajaxStart(function () {
        $("#loading", window.top.document).show();
        //console.log("start...");
    });

    $(document).ajaxStop(function () {
        $("#loading", window.top.document).hide();
        //console.log("end...");
    });
}

function initNav() {
    $('.easyui-accordion li a').click(function () {
        var tabTitle = $(this).children('.nav').text();

        var url = $(this).attr("rel");
        var menuid = $(this).attr("ref");
        var icon = $(this).children('span').first().attr('class');
        addTab(tabTitle, url, icon);
        $('.easyui-accordion li div').removeClass("selected");
        $(this).parent().addClass("selected");
    }).hover(function () {
        $(this).parent().addClass("hover");
    }, function () {
        $(this).parent().removeClass("hover");
    });
}

function addTab(subtitle, url, icon) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title: subtitle,
            content: createFrame(url),
            //href: url,
            closable: true,
            icon: icon,
            loadingMessage: '正在加载中......'

        });
    } else {
        $('#tabs').tabs('select', subtitle);
    }
    tabClose();
}

//增加Tab，并触发onLoad函数
//function addTab(subtitle, url, icon, onloadFun) {
//    if (!$('#tabs').tabs('exists', subtitle)) {
//        $('#tabs').tabs('add', {
//            title: subtitle,
//            content: createFrame(url),
//            //href: url,
//            closable: true,
//            icon: icon,
//            loadingMessage: '正在加载中......',
//            onLoad: onloadFun
//        });
//    } else {
//        $('#tabs').tabs('select', subtitle);
//    }
//    tabClose();
//}

function createFrame(url) {
    var s = '<iframe scrolling="no" frameborder="0"  src="' + url + '" style="width:100%;height:99.5%;"></iframe>';
    return s;
}

function tabClose() {
    /*双击关闭TAB选项卡*/
    $(".tabs-inner").dblclick(function () {
        var subtitle = $(this).children(".tabs-closable").text();
        $('#tabs').tabs('close', subtitle);
    });
    /*为选项卡绑定右键*/
    $(".tabs-inner").bind("contextmenu", function (e) {
        $('#mm').menu('show', {
            left: e.pageX,
            top: e.pageY
        });

        var subtitle = $(this).children(".tabs-closable").text();
        $('#mm').data("currtab", subtitle);
        $('#tabs').tabs('select', subtitle);
        return false;
    });
}
//绑定右键菜单事件
function tabCloseEven() {
    //关闭当前
    $('#mm-tabclose').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('#tabs').tabs('close', currtab_title);
    })
    //全部关闭
    $('#mm-tabcloseall').click(function () {
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            $('#tabs').tabs('close', t);
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function () {
        var currtab_title = $('#mm').data("currtab");
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            if (t != currtab_title)
                $('#tabs').tabs('close', t);
        });
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length == 0) {
            //msgShow('系统提示','后边没有啦~~','error');
            //  alert('后边没有啦~~');
            return false;
        }
        nextall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });

    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function () {
        var prevall = $('.tabs-selected').prevAll();
        if (prevall.length == 0) {
            // alert('到头了，前边没有啦~~');
            return false;
        }
        prevall.each(function (i, n) {
            var t = $('a:eq(0) span', $(n)).text();
            $('#tabs').tabs('close', t);
        });
        return false;
    });

    //退出
    $("#mm-exit").click(function () {
        $('#mm').menu('hide');
    })
}

function closeTab() {
    var index = $('#tabs').tabs('getTabIndex', $('#tabs').tabs('getSelected'));
    $('#tabs').tabs('close', index);
}

function closeAllTab() {
    $('.tabs-inner span').each(function (i, n) {
        var t = $(n).text();
        if (t != '') {
            $('#tabs').tabs('close', t);
        }
    });
}

/**
*更换皮肤
**/
function changeTheme(themeName) {/* 更换主题 */
    var $easyuiTheme = $('#easyuiTheme');
    var url = $easyuiTheme.attr('href');
    var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
    $easyuiTheme.attr('href', href);

    var $iframe = $('iframe');
    if ($iframe.length > 0) {
        for (var i = 0; i < $iframe.length; i++) {
            var ifr = $iframe[i];
            $(ifr).contents().find('#easyuiTheme').attr('href', href);
        }
    }

    $.cookie('easyuiThemeName', themeName, { expires: 7 });
};

if ($.cookie('easyuiThemeName')) {
    changeTheme($.cookie('easyuiThemeName'));
}

function logoff() {
    $.messager.confirm('提示！', '确定退出系统？', function (r) {
        if (r) {
            try {
                $.ajax({
                    url: "/baseaccount/logoff",
                    type: "post",
                    success: function (resp) {
                        window.location.href = "/baseaccount/login";
                    }
                })
            }
            catch (e)
            { }
        }
    })
}

// window.onbeforeunload() {
//    if (event.clientX > document.body.clientWidth && event.clientY < 0 || event.altKey) {
//        window.event.returnValue = "确定要退出本页吗？";
//    }
//}

function getUserResource() {
    para = {};
    para.action = "GetUserResource";
    para.timespan = new Date().getTime();
    $.ajax({
        url: "handler/Sys_User.ashx",
        data: para,
        type: "POST",
        dataType: "text",
        success: function (result) {
            userResource = result;
        }
    });
}

//动态隐藏按钮
function removePageButton(dg, pageId) {
    var itemText = "";
    var bar = $("#" + dg).datagrid('options').toolbar;
    for (var i = 0; i < bar.length; i++) {
        if (bar[i] == "-") {
            continue;
        }
        else {
            itemText += bar[i].text + ",";
        }
    }
    var objResource = eval(userResource);
    for (var i = 0; i < objResource.length; i++) {
        if (objResource[i]["ModuleId"] == pageId) {
            var rNM = objResource[i].ResourceName;
            itemText = itemText.replace(rNM + ",", ""); //去除已经有的资源
        }
    }
    var items = itemText.split(",");

    for (var j = 0; j < items.length; j++) {
        if (items[j] != '') {
            $("#" + dg).datagrid("removeToolbarItem", items[j]); //根据btn的text删除
        }
    }
}

//动态增加页面按钮
//[{"text":"xxx"},"-",{"text":"xxxsss","iconCls":"icon-ok"}]
function addPageButton(dg, pageId) {
    var objResource = eval(userResource);
    var strBar = "["
    for (var i = 0; i < objResource.length; i++) {
        if (objResource[i]["ModuleId"] == pageId) {
            var text = objResource[i].ResourceName;
            var iconCls = objResource[i].ResourceIcon;
            var handler = "Add";
            strBar += "{ \"text\":\"" + text + "\",\"iconCls\":\"" + iconCls + "\",\"handler\":" + handler + " },'-',";
        }
    }
    if (strBar.length > 4) {
        strBar = strBar.substring(0, strBar.length - 5);
        strBar += "]";
        strBar = eval(strBar);
        $("#" + dg).datagrid("addToolbarItem", strBar)
    }
}

//根据按钮文章隐藏按钮,多个的话用逗号分隔
//使用方式：removePageButtonByText("dgListRole", "添加,删除");
function removePageButtonByText(dg, btnTexts) {
    btnTexts = btnTexts + ',';
    var arrBtn = btnTexts.split(",");
    for (var i = 0; i < arrBtn.length; i++) {
        if (arrBtn[i] != '') {
            $("#" + dg).datagrid("removeToolbarItem", arrBtn[i]);
        }
    }
}


//====================公共业务方法=======================
//显示客户经营
function CustomerProcess(customerId) {
    $("<div/>").window({
        href: "crm/CustomerProcess.htm",
        title: "客户经营",
        maximized: true, //窗口默认最大化
        modal: true,
        minimizable: false,
        iconCls: "icon-reload",
        onClose: function () {
            $(this).dialog('destroy');
        },
        onLoad: function () { getCustomerDetail(customerId); CustomerProcessTabFunction(customerId); }
    });
}