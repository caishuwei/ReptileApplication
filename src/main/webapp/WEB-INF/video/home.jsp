<%@page isELIgnored="false" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<html>
<head>
    <title>ReptileApplication</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<h2>搜索</h2>
<input type="text" id="word" value="刀剑神域">
<div id="sitesContainer">

</div>
<script type="text/javascript">
    var sites = ${sites};
    if (sites != null) {
        console.log("js")
        var sitesContainer = document.getElementById("sitesContainer");
        for (var i = 0; i < sites.length; i++) {
            var p = document.createElement("p");
            p.innerText = sites[i].name;
            p.onclick = function (key, baseUrl) {
                return function () {
                    var word = document.getElementById("word").value;
                    window.open("${pageContext.request.contextPath}/video/search?word=" + word + "&siteKey=" + key + "&baseUrl=" + baseUrl);
                    //document.location.href = "${pageContext.request.contextPath}/video/search?word=" + word + "&siteKey=" + key + "&baseUrl=" + baseUrl;
                }
            }(sites[i].key, sites[i].baseUrl);
            sitesContainer.appendChild(p);
        }
    }
</script>
</body>
</html>
