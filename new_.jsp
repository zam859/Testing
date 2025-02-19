<%-- 
    Document   : amlprocessingparameter
    Created on : May 19, 2015, 3:02:21 PM
    Author     : TridayaPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<script type="text/javascript">
    $(function(){        
        initModule("module_master_amlprocessingparameter","","");        
        $("#btnSaveMasterAmlProcessingParameter").click(function(){            
            
            sendUpdateForm({
                formId:"module_master_amlprocessingparameter_form",
                urlForm : "module/modifyMasterAmlProcessingParameter.action",
                beforeSubmitFn: function(){
                },
                afterSuccess : function(data){
                    $('#MasterAmlProcessingParameterContainer').jtable('load');
                },
                moduleId:"module_master_amlprocessingparameter"
            });
        })      
        function displayName(name) {
  const nameElement = document.getElementById('name-display');
  nameElement.textContent = `Showing results for "${name}"`;
}
    })    
</script>
<ul class="breadcrumb">
    <li>You are here</li>
    <li class="divider"></li>
    <li>Real Time Alert Configuration</li>
    <li class="divider"></li>
</ul>

<div class="innerLR">                    
    <div class="widget widget-heading-simple widget-body-white">

        <div class="widget-head">
            <h4 class="heading"></h4>
        </div>

        <div class="widget-body">

            <div class="row-fluid">
                <div class="widget widget-heading-simple widget-body-white" id="module_master_amlprocessingparameter">
                    <div class="widget-body padding-none">
                        <div class="row-fluid row-merge">
                            <div class="span6">
                                <div class="innerAll">
                                    <div class="row-fluid">
                                        <div class="span12">
                                            <form action="module/navigateRefreshMasterAmlProcessingParameter" method="post" id="module_master_amlprocessingparameter_search">
                                                <input type="hidden" name="pagerMethod" id="pagerMethod" />
                                                <div class="row-fluid">
                                                    <input type="text" placeholder="" class="span4" id="amlprocessingparameterNameSearch" name="amlprocessingparameterNameSearch" style="float: left"  />
                                                    <span class="btn btn-primary span2" style="float: left" id="btnSearchMasterAmlProcessingParameter" >Search</span> 

                                                </div>                
                                                <div class="row-fluid" id="tableContainer">                                        
                                                    <%@include file="../datatable/masteramlprocessingparameter_datatable.jsp" %>	                    
                                                </div>
                                            </form>
                                        </div>
                                    </div>

                                </div>
                            </div>
                            <div class="span6">
                                <div class="innerAll">
                                    <div class="row-fluid">
                                        <div class="span12">
                                            <form id="module_master_amlprocessingparameter_form" name="module_master_amlprocessingparameter_form" method="post" action="module/modifyMasterAmlProcessingParameter"  >
                                                <input type="hidden" name="amlprocessingparameterCode" id="amlprocessingparameterCode" />
                                                <div class="row-fluid">
                                                    <span class="span3">Process Name</span>
                                                    <input class="span6" type="text" name="processNameTxt" id="processNameTxt"  />
                                                    <input type="hidden" name="methode" id="methode" value="modify" />
                                                </div>
                                                <div class="row-fluid">
                                                    <span class="span3">Execute</span>
                                                    <select name="txtExecute" id="txtExecute">
                                                        <option value="0">No</option>
                                                        <option value="2">Yes</option>
                                                    </select>                                                    
                                                </div>
                                                <div id="paramContainer">
                                                    <div class="row-fluid">
                                                        <span class="span3">Param</span>
                                                        <input class="span6" type="text" name="txtParam1" id="txtParam1"  />
                                                    </div>
                                                </div>
                                                
                                                <div class="row-fluid">
                                                    <span class="btn btn-primary span2" id="btnSaveMasterAmlProcessingParameter" >Save</span>                                
                                                </div>
                                            </form>
                                        </div>
                                    </div>

                                </div>
                            </div>	
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>