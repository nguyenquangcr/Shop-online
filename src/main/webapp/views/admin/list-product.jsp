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
					<label for="ProductID">ProductID: </label> <input type="text"
						readonly="readonly" class="form-control" id="productid"
						name="productid" value="${product.productid}">
				</div>
				<div class="form-group">
					<label for="ProductCode">Product Code: </label> <input type="text"
						class="form-control" id="productcode" name="productcode"
						value="${product.productcode}">
				</div>
				<div class="form-group">
					<label for="Productname">Product Name: </label> <input type="text"
						class="form-control" id="productname" name="productname"
						value="${product.productname}">
				</div>
				<div class="form-group">
					<label for="stock">Stock: </label> <input type="text"
						class="form-control" id="stock" name="stock"
						value="${product.stock}">
				</div>
				<div class="form-group">
					<label for="Price">Price: </label> <input type="text"
						class="form-control" id="price" name="price"
						value="${product.price}">
				</div>

			</div>

			<div class="col-sm-6">

				<div class="form-group">
					<label for="Description">Description: </label>
					<textarea class="form-control" id="description" name="description"
						value="${product.description}"></textarea>
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
					<c:if test="${product.images==null}">
						<c:url value="/templates/images/avatar.png" var="imgURL"></c:url>
					</c:if>
					<c:if test="${product.images!=null}">
						<c:url value="/image?fname=products/${product.images}"
							var="imgURL"></c:url>
					</c:if>
					<img alt="images" src="${imgURL}" width="80px" id="images"
						class="rounded-circle" /> <input type="file" class="form-control"
						onchange="chooseFile(this)" name="images"
						value="${product.images}" />
				</div>

				<div class="form-group">
					<label for="category">Category: </label> <select name="categoryid">
						<c:forEach items="${categoryList}" var="item">
							<option value="${item.categoryid}"
								${product.categoryid == item.categoryid? 'selected' : "" }>${item.categoryname}</option>
						</c:forEach>

					</select>
				</div>
				<div class="form-group">
					<label for="seller">Seller: </label> <select name="sellerid">
						<c:forEach items="${sellerList}" var="item">
							<option value="${item.sellerid}"
								${product.sellerid == item.sellerid? 'selected' : "" }>${item.sellername}</option>
						</c:forEach>

					</select>
				</div>
				<label for="status">Status: </label>
				<div class="form-check form-check-inline">
					<input type="radio" class="form-check-input" id="statuson"
						name="status" value="1" ${product.status ==1 ? 'checked':''}>
					<label class="form-check-label">HOAT DONG</label> <input
						type="radio" class="form-check-input" id="statusoff" name="status"
						value="0" ${product.status ==1 ? 'checked':'' }> <label
						class="form-check-label">KHOA</label>
				</div>

				<br />
				<button class="btn btn-success"
					formaction='<c:url value="/admin/product/create"/>'>
					Create <i class="fa fa-plus" />
				</button>
				<button class="btn btn-warning"
					formaction='<c:url value="/admin/product/update"/>'>
					Update <i class="fa fa-edit" />
				</button>
				<button class="btn btn-danger"
					formaction='<c:url value="/admin/product/delete"/>'>
					Delete <i class="fa fa-trash" />
				</button>
				<button class="btn btn-primary"
					formaction='<c:url value="/admin/product/reset"/>'>
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
						<th scope="col">Product Id</th>
						<th scope="col">Product Code</th>
						<th scope="col">Images</th>
						<th scope="col">Product Name</th>
						<th scope="col">Price</th>
						<th scope="col">Description</th>
						<th scope="col">Stock</th>
						<th scope="col">Category</th>
						<th scope="col">Seller</th>
						<th scope="col">Status</th>
						<th scope="col">Amount</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${productList}" varStatus="stt">
						<tr>
							<th scope="row">${i.productid}</th>
							<td>${i.productcode}</td>
							<td><c:url value="/image?fname=products/${i.images}"
									var="imgURL"></c:url> <img width="80px" src="${imgURL}"
								class="rounded-circle"></td>
							<td>${i.productname}</td>
							<td>${i.price}</td>
							<td>${i.description}</td>
							<td>${i.stock}</td>
							<td>${i.category.categoryname}</td>
							<td>${i.seller.sellername}</td>
							<td>${i.status == 1 ? "Hoạt động" : "Khóa"}</td>
							<td>${i.amount}</td>
							<td><a class="mr-5"
								href='<c:url value="/admin/product/edit?productid=${i.productid}"/>'>
									Edit </a> <a
								href='<c:url value="/admin/product/delete?productid=${i.productid}"/>'>
									Delete </a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
</div>