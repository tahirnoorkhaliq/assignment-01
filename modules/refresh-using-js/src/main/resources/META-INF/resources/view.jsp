<%@page import="com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder"%>
<%@page import="com.liferay.product.navigation.control.menu.constants.ProductNavigationControlMenuCategoryKeys"%>
<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="refreshusingjs.caption"/></b>
</p>
<portlet:renderURL var="redirectURL">
	<portlet:param name="mvcRenderCommandName" value="/test/osgi/render" />
	<portlet:param name="tabs1" value="index-actions" />
</portlet:renderURL>

<aui:form
	action='<%=
		PortletURLBuilder.createRenderURL(
			renderResponse
		).setMVCRenderCommandName(
			"/test/osgi/render"
		).buildString()
	%>'
	method="post"
	name="fm"
>
<div id='<%= liferayPortletResponse.getNamespace() + "adminSearchAdminIndexActionsPanel" %>'>
	
<div class="index-action-wrapper" >
${randomNum}
</div>
<aui:button cssClass="save-server-button"  value="execute" />
</div>
</aui:form>

<aui:script use="liferay-admin-custom">
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
			'<portlet:actionURL name="/test/osgi/action"><portlet:param name="redirect" value="<%= redirectURL %>" /></portlet:actionURL>',
	});
</aui:script>