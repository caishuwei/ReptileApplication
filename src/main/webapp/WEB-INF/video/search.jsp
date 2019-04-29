<%@page isELIgnored="false" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        a{text-decoration:none;}
    </style>
</head>

<body>
<h2>${title}</h2>
<div id="videoContainer">

</div>
<script type="text/javascript">
    var videoListItems = ${videoListItems};
    var videoContainer = document.getElementById("videoContainer");
    if (videoListItems != null && videoContainer != null) {
        var textHTML = "<ul>";
        for (var i = 0; i < videoListItems.length; i++) {
            textHTML = textHTML + "<a href='" + "${pageContext.request.contextPath}/video/detail?siteKey=" + videoListItems[i].siteKey + "&baseUrl=" + videoListItems[i].baseUrl +
                "&detailUrl=" + videoListItems[i].detailUrl + "'>";
            textHTML += "<li>";
            textHTML += "<p>";
            textHTML += videoListItems[i].name;
            textHTML += "</p>";

            textHTML += "<img src = ";
            textHTML += videoListItems[i].coverUrl;
            textHTML += "></img>";

            textHTML += "<p>";
            textHTML += videoListItems[i].desc;
            textHTML += "</p>";

            textHTML += "<p>";
            textHTML += videoListItems[i].detailUrl;
            textHTML += "</p>";

            textHTML += "</li>";
            textHTML += "</a>";
        }
        textHTML += "</ul>";
        videoContainer.innerHTML = textHTML
    }
</script>
</body>
</html>
