<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:set var="layout" value="knack5" />

<template:page pageTitle="${pageTitle}" layout="${layout}">
	
	<cms:pageSlot position="Section1" var="feature">
		<cms:component component="${feature}" element="div" class=""/>
	</cms:pageSlot>
	
</template:page>
