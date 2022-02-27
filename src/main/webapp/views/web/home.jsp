<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
<c:url value="/templates/assets" var="url"></c:url>
<!-- BEGIN SLIDER -->
<div>
	<div class="page-slider margin-bottom-35">
		<!-- LayerSlider start -->
		<div id="layerslider"
			style="width: 100%; height: 494px; margin: 0 auto;">
			<c:forEach items="${slidesList }" var="slide">
				<c:if test="${slide.slidestyle==1 }">
					<!-- slide one start -->
					<div class="ls-slide ls-slide1"
						data-ls="offsetxin: right; slidedelay: 7000; transition2d: 24,25,27,28;">

						<img
							src='<c:url value="/image?fname=slides/${slide.slideimages}"></c:url>'
							class="ls-bg" alt="${slide.slidename}">

						<div class="ls-l ls-title"
							style="top: 96px; left: 35%; white-space: nowrap;"
							data-ls="
            fade: true; 
            fadeout: true; 
            durationin: 750; 
            durationout: 750; 
            easingin: easeOutQuint; 
            rotatein: 90; 
            rotateout: -90; 
            scalein: .5; 
            scaleout: .5; 
            showuntil: 4000;
          ">
							${slide.slidename}</div>

						<div class="ls-l ls-mini-text"
							style="top: 338px; left: 35%; white-space: nowrap;"
							data-ls="
          fade: true; 
          fadeout: true; 
          durationout: 750; 
          easingin: easeOutQuint; 
          delayin: 300; 
          showuntil: 4000;
          ">${slide.slidedescription }</div>
					</div>
					<!-- slide one end -->
				</c:if>
			</c:forEach>
		</div>
		<!-- LayerSlider end -->
	</div>
	<!-- END SLIDER -->

	<div class="main">
		<div class="container-fluid">
			<!-- BEGIN SALE PRODUCT & NEW ARRIVALS -->
			<div class="row margin-bottom-40">
				<!-- BEGIN SALE PRODUCT -->
				<div class="col-md-12 sale-product">
					<h2>New Arrivals</h2>
					<div class="owl-carousel owl-carousel5">
						<c:forEach items="${productListNew}" var="proList">
							<div>
								<div class="product-item">
									<div class="pi-img-wrapper">
										<img
											src='<c:url value="/image?fname=products/${proList.images}"></c:url>'
											class="img-responsive" alt="Berry Lace Dress">
										<div>
											<a
												href="<c:url value="/image?fname=products/${proList.images}"></c:url>"
												class="btn btn-default fancybox-button">Zoom</a> <a
												href="#product-pop-uppp${proList.productid}"
												class="btn btn-default fancybox-fast-view">View</a>
										</div>
									</div>
									<h3>
										<a href="shop-item.html">${proList.productname}</a>
									</h3>
									<div class="pi-price">${proList.price}</div>
									<a href='<c:url value="/member/cart/add?pId=${proList.productid}&quantity=1"></c:url>' class="btn btn-default add2cart">Add
										to cart</a>
									<div class="sticker sticker-new"></div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- END SALE PRODUCT -->
			</div>
			<!-- END SALE PRODUCT & NEW ARRIVALS -->

			<!-- BEGIN SIDEBAR & CONTENT -->
			<div class="row margin-bottom-40 ">
				<!-- BEGIN SIDEBAR -->
				<div class="sidebar col-md-3 col-sm-4">
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


				</div>
				<!-- END SIDEBAR -->
				<!-- BEGIN CONTENT -->
				<div class="col-md-9 col-sm-8">
					<h2>Three items</h2>
					<div class="owl-carousel owl-carousel3">
						<c:forEach items="${productListTopAmount}" var="proList">

							<div>
								<div class="product-item">
									<div class="pi-img-wrapper">
										<img
											src='<c:url value="/image?fname=products/${proList.images}"></c:url>'
											class="img-responsive" alt="Berry Lace Dress">
										<div>
											<a
												href="<c:url value="/image?fname=products/${proList.images}"></c:url>"
												class="btn btn-default fancybox-button">Zoom</a> <a
												href="#product-pop-uppp${proList.productid}"
												class="btn btn-default fancybox-fast-view">View</a>
										</div>
									</div>
									<h3>
										<a href="shop-item.html">${proList.productname}</a>
									</h3>
									<div class="pi-price">${proList.price}</div>
<a href='<c:url value="/member/cart/add?pId=${proList.productid}&quantity=1"></c:url>' class="btn btn-default add2cart">Add to cart</a>
									<div class="sticker sticker-new"></div>
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
											<h2>${proList.productname}</h2>
											<h4>Category: ${proList.category.categoryname} - Shop:
												${proList.seller.sellername}</h4>
											<div class="price-availability-block clearfix">
												<div class="price">
													<strong><span>$</span>${proList.price}</strong> <em>$<span>${proList.price *1.1}</span></em>
												</div>
												<div class="availability">
													Số lượng tồn: <strong>${proList.stock}</strong> - Đã bán: <strong>${proList.amount}</strong>
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
						                    	<form name="f1" method="get" action='<c:url value="/member/cart/add"></c:url>'>
						                    	    <input type="text" name="pId" id="pid" value="${proList.productid}" hidden="">	
						                    		<input type="text" id="stock" value="${proList.stock}" hidden="">	
						                    		<button type="button" class="" name='subtract${proList.productid}' onclick='javascript: subtractQty0(${proList.productid});'
						                    		value='-'>-</button>
						                    		<input readonly type="text" name='quantity' value="${map.value.quantity}" id="product-quantity${proList.productid}" style="width:50px" />
						                    	    <button type="button" class="" name='add${proList.productid}' 
						                    	    onclick='javascript: subtractQty(${proList.productid});'
						                    		value='+'>+</button>
						                    		&nbsp;
						                    		<button type="submit" class="btn btn-primary">Add-to-cart</button>
						                    	</form>			
												<hr>
												<a
													href='<c:url value="/product/detail?productid=${ proList.productid}"></c:url>'
													class="btn btn-default">More details</a>
											</div>
										</div>

										<div class="sticker sticker-sale"></div>
									</div>
								</div>
							</div>
							<!-- END fast view of a product -->
						</c:forEach>
					</div>
				</div>
				<!-- END CONTENT -->
			</div>
			<!-- END SIDEBAR & CONTENT -->

			<!-- BEGIN TWO PRODUCTS & PROMO -->
			<div class="row margin-bottom-35 ">
				<!-- BEGIN TWO PRODUCTS -->
				<div class="col-md-6 two-items-bottom-items">
					<h2>Two items</h2>
					<div class="owl-carousel owl-carousel2">
						<c:forEach items="${productListNew}" var="proList">
							<div>
								<div class="product-item">
									<div class="pi-img-wrapper">
										<img
											src="<c:url value="/image?fname=products/${proList.images}"></c:url>"
											class="img-responsive" alt="Berry Lace Dress">
										<div>
											<a href="${url}/frontend/pages/img/products/k4.jpg"
												class="btn btn-default fancybox-button">Zoom</a> <a
												href="#product-pop-uppp${proList.productid}"
												class="btn btn-default fancybox-fast-view">View</a>
										</div>
									</div>
									<h3>
										<a href="shop-item.html">${proList.productname}</a>
									</h3>
									<div class="pi-price">${proList.price}</div>
								<a href='<c:url value="/member/cart/add?pId=${proList.productid}&quantity=1"></c:url>' class="btn btn-default add2cart">Add to cart</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- END TWO PRODUCTS -->
				<!-- BEGIN PROMO -->
				<div class="col-md-6 shop-index-carousel">
					<div class="content-slider">
						<div id="myCarousel" class="carousel slide" data-ride="carousel">
							<!-- Indicators -->
							<ol class="carousel-indicators">
								<c:forEach items="${slidesList}" var="slide1">
									<c:if test="${slide1.slidestyle==2}">
										<li data-target="#myCarousel"
											data-slide-to="${slide1.slideid}"
											class="${slide1.slideid == slide_id?'active':'' }"></li>
									</c:if>
								</c:forEach>
							</ol>
							<div class="carousel-inner" style="overflow: inherit;">
								<c:forEach items="${slidesList}" var="slide">
									<c:if test="${slide.slidestyle==2}">
										<div class="item ${slide.slideid == slide_id?'active':'' }">
											<img
												src="<c:url value="/image?fname=slides/${slide.slideimages}"/>"
												class="img-responsive" alt="${slide.slidename}">
										</div>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<!-- END PROMO -->
			</div>
			<!-- END TWO PRODUCTS & PROMO -->
		</div>
	</div>

	<!-- BEGIN BRANDS -->
	<div class="brands">
		<div class="container">
			<div class="owl-carousel owl-carousel6-brands">
				<c:forEach items="${slidesList}" var="slide">
					<c:if test="${slide.slidestyle==3 }">
						<a href="${slide.slidelink }"><img
							src="<c:url value="/image?fname=slides/${slide.slideimages}"></c:url>"
							alt="canon" title="canon"></a>
					</c:if>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- END BRANDS -->

	<!-- BEGIN STEPS -->
	<div class="steps-block steps-block-red">
		<div class="container">
			<div class="row">
				<div class="col-md-4 steps-block-col">
					<i class="fa fa-truck"></i>
					<div>
						<h2>Free shipping</h2>
						<em>Express delivery withing 3 days</em>
					</div>
					<span>&nbsp;</span>
				</div>
				<div class="col-md-4 steps-block-col">
					<i class="fa fa-gift"></i>
					<div>
						<h2>Daily Gifts</h2>
						<em>3 Gifts daily for lucky customers</em>
					</div>
					<span>&nbsp;</span>
				</div>
				<div class="col-md-4 steps-block-col">
					<i class="fa fa-phone"></i>
					<div>
						<h2>477 505 8877</h2>
						<em>24/7 customer care available</em>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- END STEPS -->