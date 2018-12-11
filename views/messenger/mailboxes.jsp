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

	<display:table name="mailboxes" id="row"
		requestURI="messenger/mailboxes.do" pagesize="5" class="displaytag">

		<display:column property="name" titleKey="messenger.mailbox_name">
			<a href="messenger/mailbox.do?mailboxId={row.id}"></a>
		</display:column>


	</display:table>

	<input type="button" name="create"
		value="<spring:message code="messenger.create_mailbox" />" />

</security:authorize>