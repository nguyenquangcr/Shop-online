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
			<div class="row product-list">
				<c:forEach items="${productListNew}" var="proList">
					<!-- PRODUCT ITEM START -->
					<div class="col-md-4 col-sm-6 col-xs-12">
						<div class="product-item">
							<div class="pi-img-wrapper">
								<img
									src='<c:url value="/image?fname=products/${proList.images }"></c:url>'
									class="img-responsive" alt="Berry Lace Dress">
								<div>
									<a
										href="<c:url value="/image?fname=products/${proList.images }"></c:url>"
										class="btn btn-default fancybox-button">Zoom</a> <a
										href="#product-pop-up"
										class="btn btn-default fancybox-fast-view">View</a>
								</div>
							</div>
							<h3>
								<a
									href="<c:url value="/product/detail?productid=${proList.productid}"></c:url>">${proList.productname}</a>
							</h3>
							<div class="pi-price">${proList.price}</div>
							<a href="#" class="btn btn-default add2cart">Add to cart</a>
						</div>
					</div>
					<!-- PRODUCT ITEM END -->
				</c:forEach>
			</div>

			<!-- END PRODUCT LIST -->
			<!-- BEGIN PAGINATOR -->
			<c:if test="${cateid==0 && sellid==0 }">
				<div class="row">
					<div class="col-md-4 col-sm-4 items-info"></div>
					<div class="col-md-8 col-sm-8">
						<ul class="pagination pull-right">
							<c:if test="${tag>1}">
								<li><a
									href="${pageContext.request.contextPath}/product/list?index=${tag-1}&categoryid=0&sellerid=0"><<</a></li>
							</c:if>
							<c:forEach begin="1" end="${endP}" var="i">
								<li class='${tag==i?"active":"" }'><a
									href="${pageContext.request.contextPath}/product/list?index=${i}&categoryid=0&sellerid=0">${i}</a></li>
							</c:forEach>
							<c:if test="${tag<endP}">
								<li><a
									href="${pageContext.request.contextPath}/product/list?index=${tag+1}&categoryid=0&sellerid=0">>></a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</c:if>
			<c:if test="${cateid!=0 && sellid==0 }">
				<div class="row">
					<div class="col-md-4 col-sm-4 items-info"></div>
					<div class="col-md-8 col-sm-8">
						<ul class="pagination pull-right">
							<c:if test="${tag>1}">
								<li><a
									href="${pageContext.request.contextPath}/product/list?index=${tag-1}&categoryid=${cateid}&sellerid=0"><<</a></li>
							</c:if>
							<c:forEach begin="1" end="${endP}" var="i">
								<li class='${tag==i?"active":"" }'><a
									href="${pageContext.request.contextPath}/product/list?index=${i}&categoryid=${cateid}&sellerid=0">${i}</a></li>
							</c:forEach>
							<c:if test="${tag<endP}">
								<li><a
									href="${pageContext.request.contextPath}/product/list?index=${tag+1}&categoryid=${cateid}&sellerid=0">>></a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</c:if>
			<c:if test="${cateid==0 && sellid!=0 }">
				<div class="row">
					<div class="col-md-4 col-sm-4 items-info"></div>
					<div class="col-md-8 col-sm-8">
						<ul class="pagination pull-right">
							<c:if test="${tag>1}">
								<li><a
									href="${pageContext.request.contextPath}/product/list?index=${tag-1}&categoryid=0&sellerid=${sellid}"><<</a></li>
							</c:if>
							<c:forEach begin="1" end="${endP}" var="i">
								<li class='${tag==i?"active":"" }'><a
									href="${pageContext.request.contextPath}/product/list?index=${i}&categoryid=0&sellerid=${sellid}">${i}</a></li>
							</c:forEach>
							<c:if test="${tag<endP}">
								<li><a
									href="${pageContext.request.contextPath}/product/list?index=${tag+1}&categoryid=0&sellerid=${sellid}">>></a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</c:if>
			<!-- END PAGINATOR -->
		</div>
		<!-- END CONTENT -->

	</div>
</div>