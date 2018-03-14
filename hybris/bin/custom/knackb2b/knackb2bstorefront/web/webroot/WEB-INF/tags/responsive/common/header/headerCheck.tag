<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ attribute name="layout" required="false" rtexprvalue="true"%>

<header>
		<c:choose>	
			<c:when test="${layout eq 'knack1' }">
				<header:header1 hideHeaderLinks="${hideHeaderLinks}" />
				<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/knackstyleheader1.css"/>
			</c:when>		
			 <c:when test="${layout eq 'knack2' }">
				<header:header2 hideHeaderLinks="${hideHeaderLinks}" />
				<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/knackstyleheader2.css"/>
			</c:when>
			<c:when test="${layout eq 'knack3' }">
				<header:header3 hideHeaderLinks="${hideHeaderLinks}" />
				<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/knackstyleheader3.css"/>
			</c:when>		
			<c:when test="${layout eq 'knack4' }">
				<header:header4 hideHeaderLinks="${hideHeaderLinks}" />
				<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/knackstyleheader4.css"/>
			</c:when>
			<c:when test="${layout eq 'knack5' }">
				<header:header5 hideHeaderLinks="${hideHeaderLinks}" />
				<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/knackstyleheader5.css"/>
			</c:when>
			<c:otherwise>
				<header:header hideHeaderLinks="${hideHeaderLinks}" />
				<link rel="stylesheet" type="text/css" media="all" href="${themeResourcePath}/css/style.css"/>
			</c:otherwise>
		</c:choose>

</header>