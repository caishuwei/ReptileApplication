<%@page isELIgnored="false" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<%--<h2>${url}</h2>--%>
<div id="videoContainer">

</div>
<script type="text/javascript">
    var videoContainer = document.getElementById("videoContainer");
    var iframe = document.createElement("iframe");
    iframe.src = "${url}";
    iframe.width = "100%";
    iframe.height = "100%"
    videoContainer.appendChild(iframe);
</script>
</body>
</html>
