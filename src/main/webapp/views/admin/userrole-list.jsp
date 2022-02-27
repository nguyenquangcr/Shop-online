<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-sm-12">
			<a href="<c:url value='/addrole'/> "><button
					class="btn btn-primary" onclick="">Add role</button></a>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-12">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Role id</th>
						<th scope="col">Role name</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${roleList}" varStatus="stt">
						<tr>
							<th scope="row">${i.roleid}</th>
							<td>${i.rolename}</td>
							<td><a class="mr-5"
								href="<c:url value="/editrole?id=${i.roleid}"/>"> Edit </a> <a
								href="<c:url value="/deleterole?id=${i.roleid}"/>"> Delete </a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>
