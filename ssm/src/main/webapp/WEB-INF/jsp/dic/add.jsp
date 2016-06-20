<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/commons/global.jsp"%>
<script type="text/javascript">

    $(function () {

        var purl = '${basePath}dic/add.json';
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
                    parent.$.modalDialog.openner_tgrid.treegrid('reload').treegrid('clearSelections');//之所以能在这里调用到parent.$.modalDialog.openner_tgrid这个对象，是因为resource.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                }
                else {
                    $('#dlg_btn_add').linkbutton('enable');
                    alert("操作失败：" + resp.msg);
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
                    上级项目：
                </td>
                <td colspan="3">
                    <input id="typeCode" name="typeCode" type="hidden" value="${code}"/>
                    <input id="pid" name="pid" class="easyui-combotree" data-options="url:'${basePath}dic/get_type_combotree?code=${code}',method:'get',required:true" />
                </td>
            </tr>
            <tr>
                <td>
                    项目名称：
                </td>
                <td>
                    <input id="name" name="name" type="text" class="easyui-textbox" data-options="required:true" />
                </td>
                <td>
                    项目值：
                </td>
                <td>
                    <input id="val" name="val" type="text" class="easyui-textbox" data-options="required:true" />
                </td>
            </tr>
            <tr>
                <td>
                    顺序：
                </td>
                <td>
                    <input id="seq" name="seq" type="text" class="easyui-textbox" data-options="required:true" />
                </td>
                <td>
                    状态：
                </td>
                <td>
                    <select id="status" name="status" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
                        <option value="1">正常</option>
                        <option value="0">停用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    备注：
                </td>
                <td colspan="3">
                    <input id="note" name="note" class="easyui-textbox" data-options="multiline:true" style="width:300px;height:50px">
                </td>
            </tr>
        </table>
    </form>
</div>
