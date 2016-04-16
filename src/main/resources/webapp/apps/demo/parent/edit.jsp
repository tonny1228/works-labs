<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav"><s:text name="Parent.title"/> </t:fragment><t:fragment name="_title"><s:text name="Parent.edit.title"/></t:fragment>
<t:fragment>
<script>
	addOnLoad(function(){
		
			$('#update').validate({   
				rules:{
					'parent.id' : {required:true,rangelength:[1,37]},
					'parent.name' : {required:true,rangelength:[1,37]}
				},
				errorPlacement:jQueryErrorPlacement,
				success:jQuerySuccess
			});
	});
</script>
</t:fragment>
<t:fragment name="_ctlbnt"><a onclick="$('#save').submit()" class="button-main button-icon-save">保存</a></t:fragment>
<t:tpl menuId="__catalog">
	<s:form action="update" method="post">
		<s:textfield name="parent.id"/>
		<table class="table-form">
   			<tr>
   				<td class="form-feild"><span class="required"><s:text name="Parent.name"/></span></td>
   				<td><s:textfield name="parent.name"/></td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required"><s:text name="Parent.chilren"/></span></td>
   				<td>
   				<table class="table-form">
   					<s:iterator value="parent.children" status="sta">
		   			<tr>
		   				<td class="form-feild"><span class="required"><s:text name="Child.name"/></span></td>
		   				<td><s:textfield name="child[%{#sta.index}].name" value="%{name}"/>
		   					<s:hidden name="child[%{#sta.index}].id" value="%{id}"/>
		   					<s:hidden name="child[%{#sta.index}].parent.id" value="%{parent.id}"/></td>
		   			</tr>
		   			</s:iterator>
		   			<tr>
		   				<td class="form-feild"><span class="required"><s:text name="Child.name"/></span></td>
		   				<td><s:textfield name="child[%{#request.parent.children!=null?#request.parent.children.size():0}].name" value=""/></td>
		   			</tr>
				</table> 
   				</td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required"><s:text name="Parent.properties"/></span></td>
   				<td>
   				<table class="table-form">
   					<s:iterator value="parent.properties" status="sta">
		   			<tr>
		   				<td class="form-feild"><span class="required"><s:text name="Property.name"/></span></td>
		   				<td><s:textfield name="property[%{#sta.index}].value" value="%{value}"/>
		   					<s:hidden name="property[%{#sta.index}].id" value="%{id}"/>
		   					<s:hidden name="property[%{#sta.index}].parent.id" value="%{parent.id}"/></td>
		   			</tr>
		   			</s:iterator>
		   			<tr>
		   				<td class="form-feild"><span class="required"><s:text name="Property.name"/></span></td>
		   				<td><s:textfield name="property[%{#request.parent.properties!=null?#request.parent.properties.size():0}].value" value=""/></td>
		   			</tr>
				</table> 
   				</td>
   			</tr>
			<tr><td colspan="2"><s:submit value="保存"/></td></tr>
		</table> 
		
	</s:form>
</t:tpl>