<%@page import="com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys"%>
<%@ include file="/init.jsp" %>
<portlet:renderURL var="editEntryURL">
    <portlet:param name="mvcPath" value="/redirect.jsp" />   
</portlet:renderURL>

<portlet:renderURL var="redirectURL">
	<portlet:param name="mvcRenderCommandName" value="/portal_search_admin/view" />
	
</portlet:renderURL>
<div class="autofit-col">
								<aui:button cssClass="save-server-button" data-cmd="reindexDictionaries" value="execute" />
							</div>
<p>
	<b><liferay-ui:message key="urlredirection.caption"/></b>
	<a href="${editEntryURL }">click</a>
</p>

<aui:script use="liferay-admin">
	new Liferay.Portlet.Admin({
		controlMenuCategoryKey:
			'<%= ProductNavigationControlMenuCategoryKeys.TOOLS %>',
		form: document.<portlet:namespace />fm,
		indexActionWrapperSelector: '.index-action-wrapper',
		indexActionsPanel:
			'<%= '#' + liferayPortletResponse.getNamespace() + "adminSearchAdminIndexActionsPanel" %>',
		namespace: '<portlet:namespace />',
		redirectUrl: '<%= redirectURL %>',
		submitButton: '.save-server-button',
		url:
			'<portlet:actionURL name="/portal_search_admin/edit"><portlet:param name="redirect" value="<%= redirectURL %>" /></portlet:actionURL>',
	});
</aui:script>