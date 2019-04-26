<%@page isELIgnored="false" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            textHTML += videoListItems[i].detailPageKey;
            textHTML += "</p>";

            textHTML += "</li>";
        }
        textHTML += "</ul>";
        videoContainer.innerHTML = textHTML
    }
</script>
</body>
</html>
