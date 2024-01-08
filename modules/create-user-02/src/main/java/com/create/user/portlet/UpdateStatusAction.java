package com.create.user.portlet;

import com.create.user.constants.CreateUser02PortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCResourceCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import java.io.PrintWriter;

import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
	        "javax.portlet.name=" + CreateUser02PortletKeys.CREATEUSER02,
	        "mvc.command.name=/test/osgi/resourceUrl"
	    },
	    service = MVCResourceCommand.class
	)

public class UpdateStatusAction extends BaseMVCResourceCommand{


	@Override
	protected void doServeResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws Exception {
		System.out.println("Ajax");
		resourceResponse.setContentType("text/html");
		System.out.println("Parameters: "+resourceRequest.getParameter("backgroundTaskDisplayId"));
		PrintWriter out = resourceResponse.getWriter();
		out.println(50);
		out.flush();

		
	}



}
