
<%@ include file="/init.jsp" %>


<liferay-portlet:resourceURL var="testAjaxJqueryResourceUrl" id="/test/osgi/resourceUrljquery" copyCurrentRenderParameters="<%=false %>"/>
<liferay-portlet:resourceURL id="/test/osgi/resourceUrl" var="testAjaxResourceUrl" copyCurrentRenderParameters="<%=false %>"/>


<p class="up-date"></p>
<button onclick="ajaxCall()">resourceURL in Ajax</button>
<button onclick="ajaxCallJquery()">resourceURL in Jquery Ajax</button>
<script type="text/javascript">
    function ajaxCall() {
        AUI().use('aui-io-request', function (A) {
            A.io.request('${testAjaxResourceUrl}', {
                method: 'post',
                async:false,
                cache:false,
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
 function ajaxCallJquery() {
    $.ajax({
        url:'${testAjaxJqueryResourceUrl}',
        type:"POST",
        dataType:"json",
         async:false,
         cache:false,
         data: {
             <portlet:namespace/>backgroundTaskDisplayId: '10',
         },
         success:function(data,textStatus, XMLHttpRequest){
        	 console.log(data);
        	 $('.up-date').html(data);
         },          
          error:function(data,textStatus, XMLHttpRequest){
            alert("request failed");
        }
 	});    
  }
</script>
