package com.darkcoder.url.constants;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
			"javax.portlet.name=" + UrlRedirectionPortletKeys.URLREDIRECTION,
			"mvc.command.name=/portal_search_admin/view"
		},
		service = MVCRenderCommand.class
	)
public class progressBarMVCRenderCommand implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		System.out.println("render--------------");
		return "/view.jsp";
	}

}
