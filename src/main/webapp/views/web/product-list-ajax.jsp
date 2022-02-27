<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/templates/assets" var="url"></c:url>
<div class="main">
	<div class="container-fluid">
		<!-- BEGIN SIDEBAR -->
		<div class="sidebar col-md-3 col-sm-5">
			<ul class="list-group margin-bottom-25 sidebar-menu">
				<c:forEach items="${categoryList}" var="cateList">
					<li class="list-group-item clearfix"><a
						href="<c:url value="/product/list?categoryid=${cateList.categoryid}&sellerid=0"></c:url>">
							<img
							src='<c:url value="/image?fname=category/${cateList.images }"></c:url>'
							class="img-circle" width="22px" /> ${cateList.categoryname}
					</a></li>
				</c:forEach>
			</ul>

			<ul class="list-group margin-bottom-25 sidebar-menu">
				<c:forEach items="${sellerList}" var="sellerList">
					<li class="list-group-item clearfix"><a
						href="<c:url value="/product/list?sellerid=${sellerList.sellerid}&categoryid=0"></c:url>">
							<img
							src='<c:url value="/image?fname=seller/${sellerList.images}"></c:url>'
							class="img-circle" width="22px" /> ${sellerList.sellername}
					</a></li>
				</c:forEach>
			</ul>

			<div class="sidebar-filter margin-bottom-25">
				<h2>Filter</h2>
				<h3>Availability</h3>
				<div class="checkbox-list">
					<label><input type="checkbox"> Not Available (3)</label> <label><input
						type="checkbox"> In Stock (26)</label>
				</div>

				<h3>Price</h3>
				<p>
					<label for="amount">Range:</label> <input type="text" id="amount"
						style="border: 0; color: #f6931f; font-weight: bold;">
				</p>
				<div id="slider-range"></div>
			</div>

			<div class="sidebar-products clearfix">
				<h2>Bestsellers</h2>
				<div class="item">
					<a href="shop-item.html"><img
						src="../../assets/frontend/pages/img/products/k1.jpg"
						alt="Some Shoes in Animal with Cut Out"></a>
					<h3>
						<a href="shop-item.html">Some Shoes in Animal with Cut Out</a>
					</h3>
					<div class="price">$31.00</div>
				</div>
				<div class="item">
					<a href="shop-item.html"><img
						src="../../assets/frontend/pages/img/products/k4.jpg"
						alt="Some Shoes in Animal with Cut Out"></a>
					<h3>
						<a href="shop-item.html">Some Shoes in Animal with Cut Out</a>
					</h3>
					<div class="price">$23.00</div>
				</div>
				<div class="item">
					<a href="shop-item.html"><img
						src="../../assets/frontend/pages/img/products/k3.jpg"
						alt="Some Shoes in Animal with Cut Out"></a>
					<h3>
						<a href="shop-item.html">Some Shoes in Animal with Cut Out</a>
					</h3>
					<div class="price">$86.00</div>
				</div>
			</div>
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN CONTENT -->
		<div class="col-md-9 col-sm-7">
			<div class="row list-view-sorting clearfix">
				<div class="col-md-2 col-sm-2 list-view">
					<a href="#"><i class="fa fa-th-large"></i></a> <a href="#"><i
						class="fa fa-th-list"></i></a>
				</div>
				<div class="col-md-10 col-sm-10">
					<h2>&nbsp;</h2>
					<small class="shop-bg-red badge-results">${countProductAll}
						sản phẩm</small>
					<div class="pull-right">
						<label class="control-label">Show:</label> <select
							class="form-control input-sm">
							<option value="#?limit=24" selected="selected">24</option>
							<option value="#?limit=25">25</option>
							<option value="#?limit=50">50</option>
							<option value="#?limit=75">75</option>
							<option value="#?limit=100">100</option>
						</select>
					</div>
					<div class="pull-right">
						<label class="control-label">Sort&nbsp;By:</label> <select
							class="form-control input-sm">
							<option value="#?sort=p.sort_order&amp;order=ASC"
								selected="selected">Default</option>
							<option value="#?sort=pd.name&amp;order=ASC">Name (A -
								Z)</option>
							<option value="#?sort=pd.name&amp;order=DESC">Name (Z -
								A)</option>
							<option value="#?sort=p.price&amp;order=ASC">Price (Low
								&gt; High)</option>
							<option value="#?sort=p.price&amp;order=DESC">Price
								(High &gt; Low)</option>
							<option value="#?sort=rating&amp;order=DESC">Rating
								(Highest)</option>
							<option value="#?sort=rating&amp;order=ASC">Rating
								(Lowest)</option>
							<option value="#?sort=p.model&amp;order=ASC">Model (A -
								Z)</option>
							<option value="#?sort=p.model&amp;order=DESC">Model (Z -
								A)</option>
						</select>
					</div>
				</div>
			</div>
			<!-- BEGIN PRODUCT LIST -->
			<div id="content" class="row product-list">
				<c:forEach items="${productListNew}" var="proList">
					<!-- PRODUCT ITEM START -->
					<div class="product col-md-4 col-sm-6 col-xs-12">
						<div class="product-item">
							<div class="pi-img-wrapper">
								<img
									src='<c:url value="/image?fname=products/${proList.images }"></c:url>'
									class="img-responsive" alt="Berry Lace Dress">
								<div>
									<a
										href="<c:url value="/image?fname=products/${proList.images }"></c:url>"
										class="btn btn-default fancybox-button">Zoom</a> <a
										href="#product-pop-uppp${proList.productid}"
										class="btn btn-default fancybox-fast-view">View</a>
								</div>
							</div>
							<h3>
								<a
									href="<c:url value="/product/detail?productid=${proList.productid}"></c:url>">${proList.productname}</a>
							</h3>
							<div class="pi-price">${proList.price}</div>
							<a href="#" class="btn btn-default add2cart">Cho vào giỏ hàng</a>
						</div>
					</div>

					<!-- BEGIN fast view of a product -->
					<div id="product-pop-uppp${proList.productid}"
						style="display: none; width: 700px;">
						<div class="product-page product-pop-uppp${proList.productid}">
							<div class="row">
								<div class="col-md-6 col-sm-6 col-xs-3">
									<div class="product-main-image">
										<img
											src="<c:url value="/image?fname=products/${proList.images}"></c:url>"
											alt="Cool green dress with red bell" class="img-responsive">
									</div>
									<div class="product-other-images">
										<a
											href="<c:url value="/image?fname=products/${proList.images}"></c:url>"
											class="active"><img alt="Berry Lace Dress"
											src="<c:url value="/image?fname=products/${proList.images}"></c:url>"></a>
										<a
											href="<c:url value="/image?fname=products/${proList.images}"></c:url>"><img
											alt="Berry Lace Dress"
											src="<c:url value="/image?fname=products/${proList.images}"></c:url>"></a>
										<a
											href="<c:url value="/image?fname=products/${proList.images}"></c:url>"><img
											alt="Berry Lace Dress"
											src="<c:url value="/image?fname=products/${proList.images}"></c:url>"></a>
									</div>
								</div>
								<div class="col-md-6 col-sm-6 col-xs-9">
									<h1>${proList.productname}</h1>
									<div class="price-availability-block clearfix">
										<div class="price">
											<strong><span>$</span>${proList.price}</strong> <em>$<span>${proList.price *1.1}</span></em>
										</div>
										<div class="availability">
											Số lượng tồn: <strong>${proList.stock}</strong>
										</div>
									</div>
									<div class="description">
										<p>${proList.description}</p>
									</div>
									<div class="product-page-options">
										<div class="pull-left">
											<label class="control-label">Size:</label> <select
												class="form-control input-sm">
												<option>L</option>
												<option>M</option>
												<option>XL</option>
											</select>
										</div>
										<div class="pull-left">
											<label class="control-label">Color:</label> <select
												class="form-control input-sm">
												<option>Red</option>
												<option>Blue</option>
												<option>Black</option>
											</select>
										</div>
									</div>
									<div class="product-page-cart">
										<form name="f1" method="get"
											action='<c:url value="/member/cart/add"></c:url>'>
											<input type="text" name="pId" id="pid"
												value="${proList.productid}" hidden=""> <input
												type="text" id="stock" value="${proList.stock}" hidden="">
											<button type="button" class=""
												name='subtract${proList.productid}'
												onclick='javascript: subtractQty0(${proList.productid});'
												value='-'>-</button>
											<input readonly type="text" name='quantity'
												value="${map.value.quantity}"
												id="product-quantity${proList.productid}"
												style="width: 50px" />
											<button type="button" class="" name='add${proList.productid}'
												onclick='javascript: subtractQty(${proList.productid});'
												value='+'>+</button>
											&nbsp;
											<button type="submit" class="btn btn-primary">Add-to-cart</button>
										</form>
									</div>
								</div>

								<div class="sticker sticker-sale"></div>
							</div>
						</div>
					</div>
					<!-- END fast view of a product -->

					<!-- PRODUCT ITEM END -->
				</c:forEach>
			</div>

			<!-- END PRODUCT LIST -->

		</div>
		<!-- END CONTENT -->

	</div>
</div>
<script src="//ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(window).scroll(
			function() {
				if ($(window).scrollTop() + $(window).height() >= $(document)
						.height()) {
					loadAjax();
				}
			});
	function loadAjax() {
		var amount = document.getElementsByClassName("product").length;
		$.ajax({
			url : "/shoponline/product/loadAjax",
			type : "get",
			data : {
				exits : amount
			},
			success : function(data) {
				$("#content").append(data);
			},
			error : function(xhr) {

			}
		});
	};
</script>


