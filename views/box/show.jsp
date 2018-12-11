<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
	<h1>
		<spring:message code="box.show" />
	</h1>
	
	
	<!-- 
	<jstl:forEach var="message" items="${messages}">
		<jstl:out value="${message.title}" />
	</jstl:forEach>
	 -->
	
	<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="messages" requestURI="${requestURI}" id="row">
		
		<display:column property="moment" titleKey="message.moment" sortable="true" />
		
		<display:column property="subject" titleKey="message.subject" sortable="false" />
				
		<display:column property="body" titleKey="message.body" sortable="false" />
		
		<display:column property="sender" titleKey="message.sender" sortable="false" />
		
		<display:column property="recipient" titleKey="message.recipient" sortable="false" />

	</display:table>
	
	
	
</security:authorize>
