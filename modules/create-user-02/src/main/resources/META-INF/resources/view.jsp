<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="createuser02.caption"/></b>
</p>
<portlet:actionURL name="addEntry" var="addEntryURL1" />
<p>
	
	<a href="${addEntryURL1 }">Create Users</a>
</p>