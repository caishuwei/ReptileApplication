<%@page isELIgnored="false" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<h2></h2>
<div id="recource">

</div>
<script type="text/javascript">
    var detailData = ${detailData};
    document.getElementsByTagName("h2")[0].innerText = detailData.title
    var resourceGroupList = detailData.resourceGroupList;
    var recource = document.getElementById("recource");
    var textHTML = "";
    var resourceGroup;
    for (var i = 0; i < resourceGroupList.length; i++) {
        resourceGroup = resourceGroupList[i];
        textHTML += "<div>";
        textHTML += "<p>" + resourceGroup.name + "</p>";

        textHTML += "<ul>";
        var resourceList = resourceGroup.resourceList;
        for (var j = 0; j < resourceList.length; j++) {
            textHTML += "<a href='" + "${pageContext.request.contextPath}/video/play?siteKey=" + detailData.siteKey + "&baseUrl=" + detailData.baseUrl +
                "&playUrl=" + resourceList[j].playUrl + "&data=" + resourceList[j].extraData + "'>";
            textHTML += "<li>";
            textHTML += resourceList[j].name;
            textHTML += "</li>";
            textHTML += "</a>";
        }
        textHTML += "</ul>";
        textHTML += "</div>";
    }
    recource.innerHTML = textHTML
</script>
</body>
</html>
