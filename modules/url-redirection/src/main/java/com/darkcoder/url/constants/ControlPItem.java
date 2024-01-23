package com.darkcoder.url.constants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.service.PortletLocalService;


@Component(
	    immediate = true,
	    property = {
	        "panel.app.order:Integer=100",
	        "panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL_USERS
	    },
	   service = PanelApp.class
)
public class ControlPItem extends BasePanelApp {

	 @Override
	    public String getPortletId() {
		return "com_darkcoder_url_UrlRedirectionPortlet";
	    }
	 @Override
		public Portlet getPortlet() {
			return _portlet;
		}

		

		@Reference(
			target = "(javax.portlet.name=com_darkcoder_url_UrlRedirectionPortlet)"
		)
		private Portlet _portlet;

		
}

