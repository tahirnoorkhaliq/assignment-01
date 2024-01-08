<%@page import="com.liferay.portal.kernel.backgroundtask.display.BackgroundTaskDisplay"%>
<%@ include file="/init.jsp" %>

<p>
	<b><liferay-ui:message key="createuser02.caption"/></b>
</p>
<portlet:actionURL name="addEntry" var="addEntryURL1" />
<portlet:resourceURL var="testAjaxResourceUrl" id="/test/osgi/resourceUrl"/>
<p>
	
	<a href="${addEntryURL1 }">Create Users</a>
</p>
<%
    BackgroundTaskDisplay backgroundTaskDisplay =
        (BackgroundTaskDisplay)request.getAttribute(
            "backgroundTaskDisplays");

  //  if (backgroundTaskDisplay != null) {
       
%>
           <c:if test="${not empty backgroundTaskDisplay }">
            <div>
                <p>Task Name: <%= backgroundTaskDisplay.getPercentage() %></p>
                <p class="status">Status: <%= backgroundTaskDisplay.getStatus() %></p>
                <!-- Add other relevant task information as needed -->
            </div>
            </c:if>
<%
     //   }
    
%>
<p class="up-date"></p>
<button onclick="ajaxCall()">resourceURL in Ajax</button>
<script type="text/javascript">
    function ajaxCall() {
        AUI().use('aui-io-request', function (A) {
            A.io.request('${testAjaxResourceUrl}', {
                method: 'post',
                data: {
                    <portlet:namespace/>backgroundTaskDisplayId: '10',
                },
                on: {
                    success: function () {
                        alert(this.get('responseData'));
                        $('.status').html(this.get('responseData'));
                        $('.up-date').html(this.get('responseData'));
                    }

                }
            });
        });
    }
</script>
