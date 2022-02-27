<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<div class="container">
	<div class="row">
		<div class="col">
			<c:if test="${not empty message}">
				<div class="alert alert-success">${message}</div>
			</c:if>
			<c:if test="${not empty error}">
				<div class="alert alert-danger">${error}</div>

			</c:if>
		</div>
	</div>


	<form action="#" method="post" enctype="multipart/form-data">
		<div class="row">
			<div class="col-sm-6">
				<div class="form-group">
					<label for="UserID">UserID: </label> <input type="text"
						readonly="readonly" class="form-control" id="userid" name="userid"
						value="${user.userid}">
				</div>
				<div class="form-group">
					<label for="Username">User Name: </label> <input type="text"
						class="form-control" id="username" name="username"
						value="${user.username}">
				</div>
				<div class="form-group">
					<label for="Fullname">Full Name: </label> <input type="text"
						class="form-control" id="fullname" name="fullname"
						value="${user.fullname}">
				</div>
				<div class="form-group">
					<label for="Email">Email: </label> <input type="text"
						class="form-control" id="email" name="email" value="${user.email}">
				</div>

			</div>
			<div class="col-sm-6">
				<div class="form-group">
					<label for="phone">Phone: </label> <input type="text"
						class="form-control" id="phone" name="phone" value="${user.phone}">
				</div>
				<div class="form-group">
					<label for="password">Password: </label> <input type="password"
						class="form-control" id="password" name="password"
						value="${user.password}">
				</div>
				<script>
					function chooseFile(fileInput) {
						if (fileInput.files && fileInput.files[0]) {
							var reader = new FileReader();
							reader.onload = function(e) {
								$('#images').attr('src', e.target.result);
							}
							reader.readAsDataURL(fileInput.files[0]);
						}
					}
				</script>
				<div class="form-group">
					<label for="Images">Images </label>
					<c:if test="${user.images==null}">
						<c:url value="/templates/images/avatar.png" var="imgURL"></c:url>
					</c:if>
					<c:if test="${user.images!=null}">
						<c:url value="/image?fname=users/${user.images}" var="imgURL"></c:url>
					</c:if>
					<img alt="images" src="${imgURL}" width="80px" id="images"
						class="rounded-circle" /> <input type="file" class="form-control"
						onchange="chooseFile(this)" name="images" value="${user.images}" />
				</div>

				<div class="form-group">
					<label for="roles">Roles: </label> <select name="roleid">
						<c:forEach items="${roles}" var="item">
							<option value="${item.roleid}"
								${user.roleid == item.roleid? 'selected' : "" }>${item.rolename}</option>
						</c:forEach>

					</select>
				</div>
				<label for="status">Status: </label>
				<div class="form-check form-check-inline">
					<input type="radio" class="form-check-input" id="statuson"
						name="status" value="1" ${user.status ==1 ? 'checked':''}>
					<label class="form-check-label">HOAT DONG</label> <input
						type="radio" class="form-check-input" id="statusoff" name="status"
						value="0" ${user.status ==1 ? 'checked':'' }> <label
						class="form-check-label">KHOA</label>
				</div>

				<br />
				<button class="btn btn-success"
					formaction='<c:url value="/admin/user/create"/>'>
					Create <i class="fa fa-plus" />
				</button>
				<button class="btn btn-warning"
					formaction='<c:url value="/admin/user/update"/>'>
					Update <i class="fa fa-edit" />
				</button>
				<button class="btn btn-danger"
					formaction='<c:url value="/admin/user/delete"/>'>
					Delete <i class="fa fa-trash" />
				</button>
				<button class="btn btn-primary"
					formaction='<c:url value="/admin/user/reset"/>'>
					Reset <i class="fa fa-undo" />
				</button>
			</div>
		</div>
	</form>
	<br />

	<div class="row">
		<div class="col-sm-12">
			<table class="table table-striped table-bordered table-hover" id="sample_2">
				<thead>
					<tr>
						<th scope="col">User Id</th>
						<th scope="col">Images</th>
						<th scope="col">User Name</th>
						<th scope="col">Full Name</th>
						<th scope="col">Email</th>
						<th scope="col">Role</th>
						<th scope="col">Status</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${userList}" varStatus="stt">
						<tr>
							<th scope="row">${i.userid}</th>
							<td><c:url value="/image?fname=users/${i.images}"
									var="imgURL"></c:url> <img width="80px" src="${imgURL}"
								class="rounded-circle"></td>
							<td>${i.username}</td>
							<td>${i.fullname}</td>
							<td>${i.email}</td>
							<td>${i.roles.rolename}</td>
							<td>${i.status == 1 ? "Hoạt động" : "Khóa"}</td>
							<td><a class="mr-5"
								href='<c:url value="/admin/user/edit?userid=${i.userid}"/>'>
									Edit </a> <a
								href='<c:url value="/admin/user/delete?userid=${i.userid}"/>'>
									Delete </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>
