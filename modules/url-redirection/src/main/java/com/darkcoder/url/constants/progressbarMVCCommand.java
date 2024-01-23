package com.darkcoder.url.constants;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.HttpComponentsUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		property = {
				"javax.portlet.name=" + UrlRedirectionPortletKeys.URLREDIRECTION,
			"mvc.command.name=/portal_search_admin/edit"
		},
		service = MVCActionCommand.class
	)
public class progressbarMVCCommand extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		System.out.println("doProcessAction--------------");
		String redirect = ParamUtil.getString(actionRequest, "redirect");

		redirect = HttpComponentsUtil.setParameter(
			redirect, actionResponse.getNamespace() + "companyIds",
			StringUtil.merge(
				ParamUtil.getLongValues(actionRequest, "companyIds")));
		redirect = HttpComponentsUtil.setParameter(
			redirect, actionResponse.getNamespace() + "executionMode",
			ParamUtil.getString(actionRequest, "executionMode"));
		redirect = HttpComponentsUtil.setParameter(
			redirect, actionResponse.getNamespace() + "scope",
			ParamUtil.getString(actionRequest, "scope"));

		sendRedirect(actionRequest, actionResponse, redirect);
		
	}

}
