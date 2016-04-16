<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<%@ taglib uri="/apps-jbpm5" prefix="jbpm"%>
<t:fragment name="_nav">目录管理</t:fragment><t:fragment name="_title">查看目录</t:fragment>
<t:fragment name="_ctlbnt">
	<s:if test="#request.vacation.parentId!=null">
	<a onclick="f('view.action?id=${vacation.parentId}')" class="bnt back">上级</a>
	</s:if>
	<a onclick="f('list.action')"  class="bnt back">列表</a>
	<a onclick="f('auth.action?catalogId=${vacation.id}')" class="bnt edit">配置权限</a>
	<a onclick="f('edit.action?id=${vacation.id}')" class="bnt edit">修改</a>
	<a onclick="del()" class="button-main button-icon-del">删除</a>
</t:fragment>
<t:fragment>
<script>
	function del(){
		Frm.confirm('确定要删除吗？','提示',function(cfm){
			if(cfm){
				f('delete.action?id=${vacation.id}');
			}
		});
	}
</script>
<style>
<!--
.tname{width: 80px;display: inline-block;}
.tuser{width: 80px;display: inline-block;}
.tsdate{width: 120px;display: inline-block;}
.tedate{width: 120px;display: inline-block;}
.tStatus{width: 40px;display: inline-block;}
-->
</style>
</t:fragment>
<t:tpl menuId="__catalog">
		<h2>目录：</h2>
		<table class="viewtable">
   			<tr>
   				<td class="form-feild"><span class="required">编号</span></td>
   				<td>${vacation.id}</td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required">请假人</span></td>
   				<td>${vacation.name}(${vacation.username})</td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required">类型</span></td>
   				<td>${vacation.type}</td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required">请假小时</span></td>
   				<td>${vacation.hours}</td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required">开始时间</span></td>
   				<td>${vacation.startTime}</td>
   			</tr>
   			<tr>
   				<td class="form-feild"><span class="required">结束时间</span></td>
   				<td>${vacation.endTime}</td>
   			</tr>
			<tr>
				<td class="form-feild"><span class="required">审批步骤</span></td>
				<td>
					<s:iterator value="#request.steps">
					<s:if test="#parameters.taskId==null || !(''+id).equals(#parameters.taskId[0])">
					<div>${name}: ${completeOn} ${actualOwner.id}: ${Status} : ${comment }</div>
					</s:if>
					</s:iterator>
					
					
					
					<s:if test="#parameters.taskId!=null">
					<jbpm:tasks currentTaskId="${param.taskId}"/>
					</s:if>
					<s:else>
					<jbpm:tasks dataId="${param.id}"/>
					</s:else>
				</td>
			</tr>
		</table> 
		<s:form action="approve">
			<s:radio name="agree" list="#{'true':'同意','false':'不同意' }" value="'true'"/>
			<s:textfield name="taskId" value="%{#parameters.taskId[0]}"/>
			<s:textfield name="vacation.id" value="%{vacation.id}"/>
			<s:submit/>
		</s:form>
</t:tpl>