<%--
 * action-1.jsp
 *
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<security:authorize access="isAuthenticated()">
	<p>
		<spring:message code="messenger.mailbox" />
	</p>

	<display:table name="messages" id="row"
		requestURI="messenger/mailbox.do" pagesize="5" class="displaytag">


		<display:column
			property="<spring:url value="messenger/move_message.do?id={id}">
	<spring:param name="id" value="${id}" />
	</spring:url>"
			titleKey="messenger.move">
			<spring:param name="id" value="${id}" />
		</display:column>
		
		<display:column property="${sender}" titleKey="messenger.sender" />
		
		<display:column property="${subject}" titleKey="messenger.subject" />
		
		
		<display:column property="${sendDate}" titleKey="messenger.sendDate" />

	</display:table>

	<div>
		<a href="messenger/mailbox/send_messenger.do"> <spring:message
				code="messenger.create" /></a>
	</div>

</security:authorize>