<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>ASChecker | Check</title>
    <jsp:include page="layout/head.jsp"/>

    <%--Includes all JavaScript file   --%>
    <jsp:include page="layout/scripts.jsp"/>

    <script type="text/javascript">
        var contextPath = '<c:out value="${pageContext.request.contextPath}"/>';
        $(document).ready(function() {
            getRemotePieDataDrawChart("#pie-chart",contextPath + '/chartPie');
            getRemoteColumnDataDrawChart("#column-chart",contextPath + '/chartColumn');
        });
    </script>

</head>

<body>
<!-- PRE LOADER -->
<jsp:include page="layout/preloader.jsp"/>

<!-- END PRE LOADER -->

<!-- =========================
    START ABOUT US SECTION
============================== -->
<jsp:include page="layout/header_checker.jsp"/>

<!-- END HEADER SECTION -->

<jsp:include page="layout/charts.jsp"/>

<!-- =========================
     START  ACCESSIBILITY ERRORS  AREA
============================== -->
<jsp:include page="layout/error_accessibility.jsp"/>
<!-- END ACCESSIBILITY ERRORS -->

<!-- =========================
     START SECURITY ERRORS  AREA
============================== -->
<jsp:include page="layout/error_security.jsp"/>
<!-- END SECURITY ERRORS -->



<jsp:include page="layout/subcribe.jsp"/>



</body>

</html>