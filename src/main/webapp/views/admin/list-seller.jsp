<%@ page language="java" contentType="text/html; charset=UTF-8"
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
					<label for="sellerid">SellerID: </label> <input type="text"
						readonly="readonly" class="form-control" id="sellerid"
						name="sellerid" value="${seller.sellerid}">
				</div>
				<div class="form-group">
					<label for="sellername">Seller Name: </label> <input type="text"
						class="form-control" id="sellername" name="sellername"
						value="${seller.sellername}">
				</div>


			</div>
			<div class="col-sm-6">
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
					<c:if test="${seller.images==null}">
						<c:url value="/templates/images/avatar.png" var="imgURL"></c:url>
					</c:if>
					<c:if test="${seller.images!=null}">
						<c:url value="/image?fname=users/${seller.images}" var="imgURL"></c:url>
					</c:if>
					<img alt="images" src="${imgURL}" width="80px" id="images"
						class="rounded-circle" /> <input type="file" class="form-control"
						onchange="chooseFile(this)" name="images" value="${seller.images}" />
				</div>

				<label for="status">Status: </label>
				<div class="form-check form-check-inline">
					<input type="radio" class="form-check-input" id="statuson"
						name="status" value="1" ${seller.status ==1 ? 'checked':''}>
					<label class="form-check-label">HOAT DONG</label> <input
						type="radio" class="form-check-input" id="statusoff" name="status"
						value="0" ${seller.status ==1 ? 'checked':'' }> <label
						class="form-check-label">KHOA</label>
				</div>

				<br />
				<button class="btn btn-success"
					formaction='<c:url value="/admin/seller/create"/>'>
					Create <i class="fa fa-plus" />
				</button>
				<button class="btn btn-warning"
					formaction='<c:url value="/admin/seller/update"/>'>
					Update <i class="fa fa-edit" />
				</button>
				<button class="btn btn-danger"
					formaction='<c:url value="/admin/seller/delete"/>'>
					Delete <i class="fa fa-trash" />
				</button>
				<button class="btn btn-primary"
					formaction='<c:url value="/admin/seller/reset"/>'>
					Reset <i class="fa fa-undo" />
				</button>
			</div>
		</div>
	</form>
	<br />

	<div class="row">
		<div class="col-sm-12">
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Seller Id</th>
						<th scope="col">Images</th>
						<th scope="col">Seller Name</th>

						<th scope="col">Status</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${sellerList}" varStatus="stt">
						<tr>
							<th scope="row">${i.sellerid}</th>
							<td><c:url value="/image?fname=seller/${i.images}"
									var="imgURL"></c:url> <img width="80px" src="${imgURL}"
								class="rounded-circle"></td>
							<td>${i.sellername}</td>
							<td>${i.status == 1 ? "Hoạt động" : "Khóa"}</td>
							<td><a class="mr-5"
								href='<c:url value="/admin/seller/edit?sellerid=${i.sellerid}"/>'>
									Edit </a> <a
								href='<c:url value="/admin/seller/delete?sellerid=${i.sellerid}"/>'>
									Delete </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>

