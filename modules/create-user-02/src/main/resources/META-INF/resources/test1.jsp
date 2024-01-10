<%@ include file="/init.jsp" %>
<portlet:renderURL var="editEntryURL1">
    <portlet:param name="mvcPath" value="/test2.jsp" />   
</portlet:renderURL>

<p>
	<b><liferay-ui:message key="urlredirection.caption"/></b>
	<a href="${editEntryURL1 }">click</a>
</p>