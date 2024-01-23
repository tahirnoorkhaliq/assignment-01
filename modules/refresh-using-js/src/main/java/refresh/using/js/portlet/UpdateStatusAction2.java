package refresh.using.js.portlet;


import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;

import refresh.using.js.constants.RefreshUsingJsPortletKeys;

@Component(
		immediate = true,
		property = {
	        "javax.portlet.name=" + RefreshUsingJsPortletKeys.REFRESHUSINGJS,
	        "mvc.command.name=/test/osgi/action"
	    },
	    service = MVCActionCommand.class
	)

public class UpdateStatusAction2 extends BaseMVCActionCommand{

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		System.out.println("Hellooo");
		String redirect = ParamUtil.getString(actionRequest, "redirect");
		sendRedirect(actionRequest, actionResponse, redirect);
		
	}


	



}
