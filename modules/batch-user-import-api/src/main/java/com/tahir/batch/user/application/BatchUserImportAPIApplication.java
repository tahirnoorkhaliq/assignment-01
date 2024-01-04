package com.tahir.batch.user.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.headless.batch.engine.client.dto.v1_0.ImportTask;
import com.liferay.headless.batch.engine.client.resource.v1_0.ImportTaskResource;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
 * @author Tahir Noor Khaliq
 */
@Component(property = { JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/batch-user",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=UserImport.Rest" }, service = Application.class)
public class BatchUserImportAPIApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Produces("text/plain")
	public String working() {
		return "It works!";
	}

	@Reference
	UserLocalService _UserLocalService;

	@GET
	@Path("/importusers")
	@Produces("text/plain")
	public String ImportUsers() {
		InputStream is = BatchUserImportAPIApplication.class.getClassLoader().getResourceAsStream("userloadtest.json");
		String usersJson = printInputStream(is);
		is = BatchUserImportAPIApplication.class.getClassLoader().getResourceAsStream("fieldNameMapping.json");
		String fieldNameMapping = printInputStream(is);
		System.out.println("Processing File" + usersJson + "\n ");
		ImportTaskResource.Builder builder = ImportTaskResource.builder();
		ImportTaskResource importTaskResource = builder.authentication("test@liferay.com", "test").build();//// .endpoint(host,
																											//// port,
																											//// scheme).builder.build();//

		try {
			ImportTask response = importTaskResource.postImportTask(
					String.valueOf("com.liferay.headless.admin.user.dto.v1_0.UserAccount"), null, "INSERT", null, null,
					"IMPORT_STRATEGY_ON_ERROR_CONTINUE", null, usersJson);
			System.out.println(response);
			return response.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "Error";
	}

	private static String printInputStream(InputStream is) {

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

	@GET
	@Path("/createuser")
	@Produces("text/plain")
	public String createUser() throws IOException {
		System.out.println("working createUser api");
		InputStream is = BatchUserImportAPIApplication.class.getClassLoader().getResourceAsStream("userloadtest.json");
		String usersJson = printInputStream(is);
		System.out.println(usersJson);
		ObjectMapper mapper = new ObjectMapper();
		ServiceContext serviceContext = new ServiceContext();
		List<LiferayUser> ppl2 = Arrays.asList(mapper.readValue(usersJson, LiferayUser[].class));
		System.out.println(ppl2);
		long start = System.currentTimeMillis();
		System.out.println(start);
		long total = 0;
		for (int i = 11; i <= 3004; i++) {
			LiferayUser liferayUser = ppl2.get(i);
			User s = _UserLocalService.fetchUserByScreenName(20097, liferayUser.getalternateName());
			if (s == null) {
				s = _UserLocalService.fetchUserByEmailAddress(20097, liferayUser.getEmailAddress());
				if (s != null) {
					// update email
				}
				try {
					long startAdd = System.currentTimeMillis();
					s = _UserLocalService.addUser(serviceContext.getUserId(), 20097, false, "test@123", "test@123",
							false, liferayUser.getalternateName(), liferayUser.getEmailAddress(), LocaleUtil.getDefault(),
							liferayUser.getFirstName(), StringPool.BLANK, liferayUser.getLastName(), 0, 0, true, 1, 1,
							1989, liferayUser.getJobTitle(),2, new long[] {}, new long[] {}, new long[] {}, new long[] {},
							false, serviceContext);
					long endAdd = System.currentTimeMillis();
					total = total + (endAdd - startAdd);
					System.out.println(s);
				} catch (PortalException e) {

					e.printStackTrace();
				}
			}

//43801 71320
		}
		System.out.println("total" + total);
		long totalwithusergroups = 0;
		for (int i = 3005; i <= 6010; i++) {
			LiferayUser liferayUser = ppl2.get(i);
			User s = _UserLocalService.fetchUserByScreenName(20097, liferayUser.getalternateName());
			if (s == null) {
				s = _UserLocalService.fetchUserByEmailAddress(20097, liferayUser.getEmailAddress());
				if (s != null) {
					// update email
				}
				try {
					long startAdd = System.currentTimeMillis();
					s = _UserLocalService.addUser(serviceContext.getUserId(), 20097, false, "test@123", "test@123",
							false, liferayUser.getalternateName(), liferayUser.getEmailAddress(), LocaleUtil.getDefault(),
							liferayUser.getFirstName(), StringPool.BLANK, liferayUser.getLastName(), 0, 0, true, 1, 1,
							1989, liferayUser.getJobTitle(), 2, new long[] {}, new long[] {}, new long[] {},
							new long[] { 43801, 71320 }, false, serviceContext);
					
					
					long endAdd = System.currentTimeMillis();
					totalwithusergroups = totalwithusergroups + (endAdd - startAdd);
					System.out.println(s);
				} catch (PortalException e) {

					e.printStackTrace();
				}
			}

//43801 71320
		}

		System.out.println("totalwithusergroups" + totalwithusergroups);

		return "createUser";
	}
}