/*常量*/
var actionSuccess = "ok";

/*提示信息*/
var msgTip = "提示";
var msgErr = "错误";
var msgOperSuccess = "操作成功";
var msgOperFailed = "操作失败";
var msgOperUpdateNull = "请选择要修改的项";
var msgOperViewNull = "请选择要查看的项";
var msgOperSettingNull = "请选择要设置的项";
var msgOperUpdateNotOne = "每次只能修改一条记录";
var msgOperViewNotOne = "每次只能查看一条记录";
var msgOperSettingNotOne = "每次只能设置一项";
var msgOperSelectNull = "请选择要操作的数据";
var msgOperConfirmDel = "确定删除";

/*Cookie*/
var cookieUserName = "cookieUserName";
var cookieUserPwd = "cookieUserPwd";
var cookieAutoLogin = "cookieAutoLogin";

/**********公共方法********************/

//解决IE不支持console.log写法的问题
var console = console || {
    log: function () {
        return false;
    }
};

//得到当前日期yyyy-MM-dd 格式
function getToday() {
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日

    var clock = year + "-";

    if (month < 10)
        clock += "0";

    clock += month + "-";

    if (day < 10)
        clock += "0";

    clock += day;
    return clock;
}


//得当当月第一天 yyyy-MM-dd 
function getMonthFistDay() {
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    var firstDay = year + "-" + month + "-" + "01";
    return firstDay;
}

//获取当前最后一天日期
function getMonthLastDate() {
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    if (month < 10) {
        month = "0" + month;
    }
    myDate = new Date(year, month, 0);
    var lastDay = year + "-" + month + "-" + myDate.getDate();
    return lastDay;
}

//获取月份天数
function getMonthLastDay(year, month) {
    var day = new Date(year, month, 0);
    return day.getDate();
}

//得到当前日期时间字符串 yyyyMMddHHmmss 格式
function getTodayTimeStr() {
    var now = new Date();
    var year = now.getFullYear();       //年
    var month = now.getMonth() + 1;     //月
    var day = now.getDate();            //日
    var clock = year;
    if (month < 10)
        clock += "0";

    clock += month;
    if (day < 10)
        clock += "0";
    clock += day;
    var hh = now.getHours();
    if (hh < 10) {
        clock += "0";
    }
    clock += hh;
    var mm = now.getMinutes();
    if (mm < 10) {
        clock += "0";
    }
    clock += mm;
    var ss = now.getSeconds();
    if (ss < 10) {
        clock += "0";
    }
    clock += ss;
    return clock;
}
//验证是否为时间日期


//格式化 Json日期
function jsonDateFormat(cellval) {
    if (!cellval) return;
    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    return date.getFullYear() + "-" + month + "-" + currentDate;
}

//格式化 Json时间
function jsonTimeFormat(cellval) {
    if (!cellval) return;
    var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var minute = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var second = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    return date.getFullYear() + "-" + month + "-" + day + " " + hour + ":" + minute;
}

/** 
 * 格式化金额（分-->元），小数点后保留两位 
 * @param value 
 */
function formatYuanAmount(value) {
    var number = Number(value);
    if (isNaN(number)) {
        return '';
    } else {
        return (number / 100).toFixed(2);
    }
}

/** 
 * 格式化金额（元），小数点后保留两位 
 * @param value 
 */
function formatAmount(value) {
    var number = Number(value);
    if (isNaN(number)) {
        return '';
    } else {
        return (number).toFixed(2);
    }
}

//万元
function toWanYuan(yuan) {
    if (yuan > 0) {
        return (yuan / 10000) + "万元";
    }
    else {
        return '';
    }
}

//获取url参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}

//获取backurl然后返回操作
function doBack() {
    var url = getQueryString("backUrl");
    location.href = url;
}

//ztree展开或折叠
function expandNode(flag) {
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    treeObj.expandAll(flag);
}

//得到状态显示标题
function getStatusTitle(status){
    var title = '';
    switch (status)
    {
        case 0:
            title = "待处理";
            break;
        case 1:
            title = "已打开";
            break;
        case 2:
            title = "已完成";
            break;
        case 3:
            title = "已退回";
            break;
        case 4:
            title = "他人已处理";
            break;
        case 5:
            title = "他人已退回";
            break;
        default:
            title = "其它";
            break;
    }
    return title;
}