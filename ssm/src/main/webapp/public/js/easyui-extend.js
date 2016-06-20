/*
    扩展jQuery easyui form插件的三个方法
    使用方法：
    $('form').form('serialize');
    $('form').form('getValue','a'); //获取表单中name=a 的元素值
    $('form').form('setValue',{'name':'a'}); //赋值给表单中name=a 的元素值
*/
$.extend($.fn.form.methods, {
    serialize: function (jq) {
        var arrayValue = $(jq[0]).serializeArray();
        var json = {};
        $.each(arrayValue, function () {
            var item = this;
            if (json[item["name"]]) {
                json[item["name"]] = json[item["name"]] + "," + item["value"];
            } else {
                json[item["name"]] = item["value"];
            }
        });
        return json;
    },
    getValue: function (jq, name) {
        var jsonValue = $(jq[0]).form("serialize");
        return jsonValue[name];
    },
    setValue: function (jq, name, value) {
        return jq.each(function () {
            _b(this, _29);
            var data = {};
            data[name] = value;
            $(this).form("load", data);
        });
    }
});

/** 
* 在iframe中调用，在父窗口中出提示框(herf方式不用调父窗口)
*/
$.extend({
    show_warning: function (title, msg) {
        $.messager.show({
            title: title,
            msg: msg,
            showType: 'slide',
            style: {
                right: '',
                top: document.body.scrollTop + document.documentElement.scrollTop,
                bottom: ''
            }
        });
    }
});

$.extend({
    show_alert: function (title, msg) {
        $.messager.alert(title, msg);
    }
});