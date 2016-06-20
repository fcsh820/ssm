<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title<%=basePath%></title>
    <link rel="stylesheet" href="<%=basePath%>public/css/login.css" />
</head>
<body>
<div class="cn_login">
    <div>
        <div style="position:absolute;">
            <img style="width: 210px; height: 42px; padding: 0 0 0 10%; * padding: 0 0 0 20px !important; margin-top: -8px;" src="<%=basePath%>/public/images/login/QUICKCLOUD.jpg" />
        </div>
        <div class="cn_login_top">
            <img style="width:440px;" src="<%=basePath%>/public/images/login/tit.png" />
        </div>
    </div>
    <form id="form1" name="form1" onsubmit="login(); return false;">
        <div class="cn_login_bg">
            <div class="cn_login_con">
                <div class="cn_txt">
                    <label>用　户　名： </label>
                    <input class="cn_input01" name="uname" id="uname" type="text" autocomplete="off" tabindex="1" value="admin" />
                </div>
                <div class="cn_txt">
                    <label>密　　　码： </label>
                    <input class="cn_input01" name="pwd" id="pwd" type="password" tabindex="2" value="123456" />
                </div>
                <div class="cn_authcode">
                    <a href="javascript:change_validate_code();">
                        <img id="img_validate_code" title="看不清，换一张" class="authcode_img" style="vertical-align: middle;width:65px;float:right;margin-top:6px;margin-right:5px;overflow:hidden;" src="<%=basePath%>code.htm" onfocus="$(this).blur();"/>
                    </a>
                    <label>验　证　码： </label><input class="cn_input02" type="text" name="validateCode" id="validateCode" autocomplete="off" tabindex="3" />
                </div>
                <div class="login_btn">
                    <button class="cn_button" type="submit" tabindex="4">登 录</button>
                    <button class="cn_button" type="reset" tabindex="5">重 置</button>
                </div>
                <p id="info" class="cn_info" >
                </p>
            </div>
        </div>
    </form>
    <div class="cn_login_bottom">Copyright &copy; <%= new java.text.SimpleDateFormat("yyyy").format(new Date())%> 山西快云软件开发有限公司. All Rights Reserved</div>
</div>
<script src="<%=basePath%>public/lib/jquery-1.10.2.min.js"></script>
<script src="<%=basePath%>public/lib/jsencrypt.min.js"></script>
<script type="text/javascript">

    function login() {
        var uname = $.trim($('#uname').val());
        if (!uname) {
            alert("请输入用户名！");
            $("#uname").focus();
            return;
        }
        var pwd = $.trim($('#pwd').val());
        if (!pwd) {
            alert("请输入密码！");
            $("#pwd").focus();
            return;
        }
        var code = $.trim($('#validateCode').val());
        if (!code) {
            alert("请输入验证码！");
            $("#validateCode").focus();
            return;
        }

        var ajax_url = '<%=basePath%>login.htm';
        var return_url = '<%=basePath%>index.htm';
        var encrypt = new JSEncrypt();
//        encrypt.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChDzcjw/rWgFwnxunbKp7/4e8w");
        var param = { validateCode: code };
        param.uname = uname;// encrypt.encrypt(name);
        param.pwd = pwd;//encrypt.encrypt(pwd);
        $.ajax({
            url: ajax_url,
            type: "post",
            data: param,
            dataType: "json",
            beforeSend: function () {
                $("form").find("button").attr("disabled", true);
                $("#info").html("登录中...");
            }
        })
        .done(function (resp) {
            $("#info").text("登录成功正在跳转，请稍候...");
            if (resp==1) {
                $("#info").text("登录成功正在跳转，请稍候...");
                location.href = return_url;
            } else {
                $("#info").html(resp.error);
            }
        })
        .fail(function (e) {
            $("#info").html(e.responseText);
        })
        .always(function () {
            change_validate_code();
            $("form").find("button").attr("disabled", false);
        });
    }

    function change_validate_code() {
        $("#img_validate_code").attr("src", "<%=basePath%>code.htm?r=" + Math.random());
    }

</script>
</body>
</html>
