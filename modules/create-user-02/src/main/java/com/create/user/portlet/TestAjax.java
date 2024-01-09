package com.create.user.portlet;

import com.create.user.constants.CreateUser02PortletKeys;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCResourceCommand;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
	        "javax.portlet.name=" + CreateUser02PortletKeys.CREATEUSER02,
	        "mvc.command.name=/test/osgi/resourceUrljquery"
	    },
	    service = MVCResourceCommand.class
	)
public class TestAjax implements MVCResourceCommand{

	@Override
	public boolean serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws PortletException {
		try {
		System.out.println("TestAjax");
		resourceResponse.setContentType("application/json");
		System.out.println("Parameters: "+resourceRequest.getParameter("backgroundTaskDisplayId"));
		JSONObject jsonObject= JSONFactoryUtil.createJSONObject();
		jsonObject.put("backgroundTaskId", resourceRequest.getParameter("backgroundTaskDisplayId"));
		System.out.println(jsonObject.toJSONString());
		PrintWriter out = resourceResponse.getWriter();
			out.println(jsonObject.toJSONString());
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

}
