<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav"><s:text name="Parent.title"/> </t:fragment><t:fragment name="_title"><s:text name="Parent.add.title"/></t:fragment>
<t:fragment>
<script>
	addOnLoad(function(){
		
			$('#save').validate({   
				rules:{
					'parent.name' : {required:true,rangelength:[1,37]},
					'child.name' : {required:true,rangelength:[1,37]}
				},
				errorPlacement:jQueryErrorPlacement,
				success:jQuerySuccess
			});
	});
</script>
</t:fragment>
<t:fragment name="_ctlbnt"><a onclick="$('#save').submit()" class="button-main button-icon-save">保存</a></t:fragment>
<t:tpl menuId="__catalog">
	<s:form action="save" method="post">
		<table class="table-form">
   			<tr>
   				<td class="form-feild"><span class="required"><s:text name="Parent.name"/></span></td>
   				<td><s:textfield name="parent.name" /></td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required">genders</span></td>
   				<td><s:checkboxlist name="gender.id" list="#request.genders" listKey="id" listValue="name"/></td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required"><s:text name="Parent.chilren"/></span></td>
   				<td>
   				<table class="table-form">
		   			<tr>
		   				<td class="form-feild"><span class="required"><s:text name="Child.name"/></span></td>
		   				<td><s:textfield name="child.name"/></td>
		   			</tr>
		   			<tr>
		   				<td class="form-feild"><span class="required"><s:text name="Child.name"/></span></td>
		   				<td><s:textfield name="child.name"/></td>
		   			</tr>
				</table> 
   				</td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required"><s:text name="Parent.properties"/></span></td>
   				<td>
   				<table class="table-form">
		   			<tr>
		   				<td class="form-feild"><span class="required"><s:text name="Property.name"/></span></td>
		   				<td><s:textfield name="property.value"/></td>
		   			</tr>
		   			<tr>
		   				<td class="form-feild"><span class="required"><s:text name="Property.name"/></span></td>
		   				<td><s:textfield name="property.value"/></td>
		   			</tr>
				</table> 
   				</td>
   			</tr>
			<tr><td colspan="2"><s:submit value="保存"/></td></tr>
		</table> 
		
	</s:form>
</t:tpl>