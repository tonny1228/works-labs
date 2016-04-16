<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/tpl" prefix="t"%>
<t:fragment name="_nav">请假管理演示</t:fragment><t:fragment name="_title">待办列表</t:fragment>
<t:fragment name="_ctlbnt">
	<a onclick="selAll('.chk',this)" class="button-main button-icon-selall">全选</a>
	<a onclick="f('${pageContext.request.contextPath}/catalog/add.action')" class="button-main button-icon-add">添加</a>
	<a onclick="delSelected('.chk','#delete')" class="button-main button-icon-del">删除</a>
</t:fragment>
<t:tpl menuId="__catalog">
		<s:form name="delForm" action="delete">
		<table class="table-list">
			<tr class="table-list-head">
				<td width="40">选择</td>
				<td>请假人</td>
				<td>类型</td>
				<td>小时</td>
				<td>开始时间</td>
				<td>结束时间</td>
			</tr>
		<s:iterator value="#request.list">
			<tr class="table-list-data">
				<td><s:checkbox name="id" fieldValue="%{id}" cssClass="chk"/></td>
				<td><s:a href="view.action?id=%{data.id}&taskId=%{id}"><s:property value="subject" escape="false"/></s:a></td>
				<td><s:property value="data.type" escape="false"/></td>
				<td><s:property value="data.hours" escape="false"/></td>
				<td><s:date name="data.startTime" format="yyyy-MM-dd HH:mm"/></td>
				<td><s:date name="data.endTime" format="yyyy-MM-dd HH:mm"/></td>
			</tr>
		</s:iterator>
		
		</table>
		<div align="center"><script type="text/javascript">setpage(${page},<s:property value="#request.list.total"/>,${pagesize});</script></div>
		</s:form>
</t:tpl>

