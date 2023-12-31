package com.create.user.portlet;

import com.create.user.constants.CreateUser02PortletKeys;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskConstants;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskThreadLocal;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay;
import com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplayFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author tahir
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=CreateUser02",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CreateUser02PortletKeys.CREATEUSER02,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CreateUser02Portlet extends MVCPortlet {
	@Override
		public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
				throws IOException, PortletException {
		try {
            // Get a list of background tasks
            BackgroundTaskDisplay backgroundTaskDisplays =BackgroundTaskDisplayFactoryUtil.getBackgroundTaskDisplay(BackgroundTaskThreadLocal.getBackgroundTaskId());
            

            // Set the background task displays as a request attribute
            renderRequest.setAttribute(
                "backgroundTaskDisplays", backgroundTaskDisplays);
        } catch (Exception e) {
            // Handle exceptions
        }
			super.doView(renderRequest, renderResponse);
		}
	@Reference
	UserLocalService _UserLocalService;
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		System.out.println("===========Start==============");
		ExecutorService ex= Executors.newSingleThreadExecutor();
	    ex.submit(() -> {
	    	
				try {
					createUserUtil();
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    });
	    System.out.println("============Stop=============");
		
	}
	private void createUserUtil() throws IOException, InterruptedException {
		System.out.println("================createUser ===============");
		Thread.sleep(100000);
		InputStream is = CreateUser02Portlet.class.getClassLoader().getResourceAsStream("users.json");
		System.out.println(is);
		String usersJson = printInputStream(is);
		System.out.println(usersJson);
		ObjectMapper mapper = new ObjectMapper();
		ServiceContext serviceContext = new ServiceContext();
		List<LiferayUser> ppl2 = Arrays.asList(mapper.readValue(usersJson, LiferayUser[].class));
		System.out.println(ppl2);
		ppl2.stream().forEach(luser->{
			try {
				System.out.println("Userr== "+luser);
				User user =_UserLocalService.addUser(serviceContext.getUserId(), 20101, false, "test@123", "test@123",
						false, luser.getalternateName(), luser.getEmailAddress(), 0, 
						null, LocaleUtil.getDefault(),luser.getFirstName(), StringPool.BLANK, luser.getLastName(), 0, 0, true, 1, 1,
						1989, luser.getJobTitle(), new long[] {}, new long[] {}, new long[] {}, new long[] {},
						false, serviceContext);
				
				/*
				 * User user = _UserLocalService.addUser(serviceContext.getUserId(), 20101,
				 * false, "test@123", "test@123", false, luser.getalternateName(),
				 * luser.getEmailAddress(), LocaleUtil.getDefault(), luser.getFirstName(),
				 * StringPool.BLANK, luser.getLastName(), 0, 0, true, 1, 1, 1989,
				 * luser.getJobTitle(),1, new long[] {}, new long[] {}, new long[] {}, new
				 * long[] {}, false, serviceContext);
				 */
			
			System.out.println(user);
			} catch (PortalException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		});
		
		
	}
	private  String printInputStream(InputStream is) {

		try {
			String text = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
					.collect(Collectors.joining("\n"));
			System.out.println(text.length());
			return text;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}