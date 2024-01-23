package refresh.using.js.portlet;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.constants.MVCRenderConstants;

import java.util.Random;
import java.util.UUID;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.ws.rs.core.Request;

import org.osgi.service.component.annotations.Component;

import refresh.using.js.constants.RefreshUsingJsPortletKeys;

@Component(
		immediate = true,
		property = {
	        "javax.portlet.name=" + RefreshUsingJsPortletKeys.REFRESHUSINGJS,
	        "mvc.command.name=/test/osgi/render"
	    },
	    service = MVCRenderCommand.class
	)

public class UpdateStatusAction implements MVCRenderCommand{

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		UUID uuid = UUID.randomUUID();
		renderRequest.setAttribute("randomNum", uuid);
		return "/view.jsp";
	}





}
