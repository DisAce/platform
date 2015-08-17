<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>"/>
<c:set var="cdnPath" value="${pageContext.request.contextPath}/" scope="application" />
<c:set var="sysPath" value="${pageContext.request.contextPath}/" scope="application" />
<c:set var="user" value="${sessionScope.CURRENT_USER}" scope="application" />
<c:set var="fields" value="${applicationScope.fields}" scope="application" />
