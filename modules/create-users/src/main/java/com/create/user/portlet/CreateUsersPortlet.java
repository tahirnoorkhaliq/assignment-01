package com.create.user.portlet;

import com.create.user.constants.CreateUsersPortletKeys;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;


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
		"javax.portlet.display-name=CreateUsers",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + CreateUsersPortletKeys.CREATEUSERS,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class CreateUsersPortlet extends MVCPortlet {
	
	@Reference
	UserLocalService _UserLocalService;
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
		/*ExecutorService ex= Executors.newSingleThreadExecutor();
	    ex.submit(() -> {*/
	    	try {
				createUserUtil();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   // });
		
	}
	private void createUserUtil() throws JsonMappingException, JsonProcessingException {
		System.out.println("================createUser ===============");
		InputStream is = CreateUsersPortlet.class.getClassLoader().getResourceAsStream("users.json");
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
				
			User user = _UserLocalService.addUser(serviceContext.getUserId(), 20101, false, "test@123", "test@123",
						false, luser.getalternateName(), luser.getEmailAddress(), LocaleUtil.getDefault(),
						luser.getFirstName(), StringPool.BLANK, luser.getLastName(), 0, 0, true, 1, 1,
						1989, luser.getJobTitle(),1, new long[] {}, new long[] {}, new long[] {}, new long[] {},
						false, serviceContext);
			
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