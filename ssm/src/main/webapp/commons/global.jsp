<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

    request.setAttribute("path", path);
    request.setAttribute("basePath", basePath);
    request.setAttribute("domain", "http://www.fcs.com");
    request.setAttribute("sys_name", "翼城县涉农惠民资金管理平台");
    request.setAttribute("company", "山西快云软件开发有限公司");
%>