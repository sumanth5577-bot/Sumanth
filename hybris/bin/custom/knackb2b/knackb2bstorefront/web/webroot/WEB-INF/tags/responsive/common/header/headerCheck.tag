<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/responsive/common/header"%>
<%@ attribute name="layout" required="false" rtexprvalue="true"%>

<header>
		<c:choose>	
			<c:when test="${layout eq 'knack1' }">
				<header:header1 hideHeaderLinks="${hideHeaderLinks}" />
			</c:when>		
			<%-- <c:when test="${layout eq 'knack2' }">
				<header:header2 hideHeaderLinks="${hideHeaderLinks}" />
			</c:when>
			<c:when test="${layout eq 'knack3' }">
				<header:header3 hideHeaderLinks="${hideHeaderLinks}" />
			</c:when>		
			<c:when test="${layout eq 'knack4' }">
				<header:header4 hideHeaderLinks="${hideHeaderLinks}" />
			</c:when> --%>
			<c:when test="${layout eq 'knack5' }">
				<header:header5 hideHeaderLinks="${hideHeaderLinks}" />
			</c:when>
			<c:otherwise>
				<header:header hideHeaderLinks="${hideHeaderLinks}" />
			</c:otherwise>
		</c:choose>

</header>