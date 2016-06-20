<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/global.jsp"%>
<script type="text/javascript">

    $(function () {

        var purl = '${basePath}user/edit.json';
        $("#frmEdit").form({
            url: purl,
            onSubmit: function (param) {
                $('#dlg_btn_add').linkbutton('disable');    //点击就禁用按钮，防止连击
                var isValid = $(this).form('validate');
                if (!isValid) {
                    $('#dlg_btn_add').linkbutton('enable');
                }
                return isValid;
            },
            success: function (resp) {
                resp = $.parseJSON(resp);
                if (resp.success) {
                    parent.$.modalDialog.openner_grid.datagrid('reload').datagrid('clearSelections');//之所以能在这里调用到parent.$.modalDialog.openner_tgrid这个对象，是因为resource.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                }
                else {
                    $('#dlg_btn_add').linkbutton('enable');
                    alert("操作失败：" + resp.error);
                }
            }
        });

    });

</script>
<div style="padding: 15px; overflow: hidden">
    <form id="frmEdit" method="post">
        <table style="width: 100%;" class="tbform">
            <tr>
                <td>
                    登录名：
                </td>
                <td>
                    <input id="uname" name="uname" type="text" class="easyui-textbox easyui-validatebox txt" data-options="required:true" />
                </td>
                <td>
                    姓名：
                </td>
                <td>
                    <input id="name" name="name" type="text" class="easyui-textbox easyui-validatebox" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td>
                    单位：
                </td>
                <td>
                    <input id="orgno" name="orgno" class="easyui-combotree" data-options="url:'${basePath}organization/get_combotree',method:'get',required:true" />
                </td>
                <td>
                    角色：
                </td>
                <td>
                    <input id="roleIds" name="roleIds" class="easyui-combotree" data-options="url:'${basePath}role/get_combotree',method:'get'" multiple />
                </td>
            </tr>
            <tr>
                <td>
                    备注：
                </td>
                <td colspan="3">
                    <input id="note" name="note" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:50px;">
                </td>
            </tr>
        </table>
    </form>
</div>
