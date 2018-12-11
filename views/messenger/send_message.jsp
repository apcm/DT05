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
		<spring:message code="messenger.send_message" />
	</p>

	<form:form action="messenger/send_message" modelAttribute="message">

		<form:hidden path="id" />
		<form:hidden path="version" />
		<form:hidden path="mailbox" />
		<form:hidden path="sender" />

		<form:label path="recipient">
			<spring:message code="messenger.recipient" />:
		<form:errors cssClass="error" path="recipient" />
		</form:label>
		
		<form:label path="body">
			<spring:message code="messenger.body" />:
		<form:errors cssClass="error" path="body" />
		</form:label>
		
		<form:label path="tag">
			<spring:message code="messenger.tag" />:
</form:label>

		<form:label path="priority">
			<spring:message code="messenger.priority" />:
		<form:errors cssClass="error" path="priority" />
		</form:label>
		
		<input type="submit" name="save"
			value="<spring:message code="messenger.save" />"
			onclick="javascript: relativeRedir('messenger/mailboxes/mailbox.do');" />
		
		<input type="button" name="cancel"
			value="<spring:message code="messenger.cancel" />"
			onclick="javascript: relativeRedir('messenger/mailboxes/mailbox.do');" />
	</form:form>

	
</security:authorize>
