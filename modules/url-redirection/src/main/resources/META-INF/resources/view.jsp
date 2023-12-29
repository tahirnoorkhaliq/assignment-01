<%@ include file="/init.jsp" %>
<portlet:renderURL var="editEntryURL">
    <portlet:param name="mvcPath" value="/redirect.jsp" />   
</portlet:renderURL>

<p>
	<b><liferay-ui:message key="urlredirection.caption"/></b>
	<a href="${editEntryURL }">click</a>
</p>
