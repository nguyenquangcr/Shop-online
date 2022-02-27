<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@include file="/common/taglib.jsp"%>
<c:url value="/templates/assets" var="url"></c:url>

<html lang="en">
<!--<![endif]-->

<!-- Head BEGIN -->
<head>
<meta charset="utf-8">
<title>Metronic Shop UI</title>

<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<meta content="Metronic Shop UI description" name="description">
<meta content="Metronic Shop UI keywords" name="keywords">
<meta content="keenthemes" name="author">

<meta property="og:site_name" content="-CUSTOMER VALUE-">
<meta property="og:title" content="-CUSTOMER VALUE-">
<meta property="og:description" content="-CUSTOMER VALUE-">
<meta property="og:type" content="website">
<meta property="og:image" content="-CUSTOMER VALUE-">
<!-- link to image for socio -->
<meta property="og:url" content="-CUSTOMER VALUE-">

<link rel="shortcut icon" href="favicon.ico">

<!-- Fonts START -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all"
	rel="stylesheet" type="text/css">
<!--- fonts for slider on the index page -->
<!-- Fonts END -->

<!-- Global styles START -->
<link href="${url}/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${url}/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Global styles END -->

<!-- Page level plugin styles START -->
<link href="${url}/global/plugins/fancybox/source/jquery.fancybox.css"
	rel="stylesheet">
<link
	href="${url}/global/plugins/carousel-owl-carousel/owl-carousel/owl.carousel.css"
	rel="stylesheet">
<link
	href="${url}/global/plugins/slider-layer-slider/css/layerslider.css"
	rel="stylesheet">
<!-- Page level plugin styles END -->

<!-- Theme styles START -->
<link href="${url}/global/css/components.css" rel="stylesheet">
<link href="${url}/frontend/layout/css/style.css" rel="stylesheet">
<link href="${url}/frontend/pages/css/style-shop.css" rel="stylesheet"
	type="text/css">
<link href="${url}/frontend/pages/css/style-layer-slider.css"
	rel="stylesheet">
<link href="${url}/frontend/layout/css/style-responsive.css"
	rel="stylesheet">
<link href="${url}/frontend/layout/css/themes/red.css" rel="stylesheet"
	id="style-color">
<link href="${url}/frontend/layout/css/custom.css" rel="stylesheet">
<!-- Theme styles END -->
</head>
<!-- Head END -->

<!-- Body BEGIN -->
<body class="ecommerce">

	<%@include file="/common/web/header.jsp"%>

	<!-- BEGIN CONTAINER -->

	<decorator:body></decorator:body>

	<!-- END CONTAINER -->



	<!-- BEGIN FOOTER -->
	<%@include file="/common/web/footer.jsp"%>
	<!-- END FOOTER -->

	<!-- BEGIN fast view of a product -->
	<div id="product-pop-up" style="display: none; width: 700px;">
		<div class="product-page product-pop-up">
			<div class="row">
				<div class="col-md-6 col-sm-6 col-xs-3">
					<div class="product-main-image">
						<img src="${url}/frontend/pages/img/products/model7.jpg"
							alt="Cool green dress with red bell" class="img-responsive">
					</div>
					<div class="product-other-images">
						<a href="#" class="active"><img alt="Berry Lace Dress"
							src="${url}/frontend/pages/img/products/model3.jpg"></a> <a
							href="#"><img alt="Berry Lace Dress"
							src="${url}/frontend/pages/img/products/model4.jpg"></a> <a
							href="#"><img alt="Berry Lace Dress"
							src="${url}/frontend/pages/img/products/model5.jpg"></a>
					</div>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-9">
					<h2>Cool green dress with red bell</h2>
					<div class="price-availability-block clearfix">
						<div class="price">
							<strong><span>$</span>47.00</strong> <em>$<span>62.00</span></em>
						</div>
						<div class="availability">
							Availability: <strong>In Stock</strong>
						</div>
					</div>
					<div class="description">
						<p>Lorem ipsum dolor ut sit ame dolore adipiscing elit, sed
							nonumy nibh sed euismod laoreet dolore magna aliquarm erat
							volutpat Nostrud duis molestie at dolore.</p>
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
						<div class="product-quantity">
							<input id="product-quantity" type="text" value="1" readonly
								name="product-quantity" class="form-control input-sm">
						</div>
						<button class="btn btn-primary" type="submit">Add to cart</button>
						<a href="shop-item.html" class="btn btn-default">More details</a>
					</div>
				</div>

				<div class="sticker sticker-sale"></div>
			</div>
		</div>
	</div>
	<!-- END fast view of a product -->

	<!-- Load javascripts at bottom, this will reduce page load time -->
	<!-- BEGIN CORE PLUGINS (REQUIRED FOR ALL PAGES) -->
	<!--[if lt IE 9]>
    <script src="${url}/global/plugins/respond.min.js"></script>  
    <![endif]-->
	<script src="${url}/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="${url}/global/plugins/jquery-migrate.min.js"
		type="text/javascript"></script>
	<script src="${url}/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script src="${url}/frontend/layout/scripts/back-to-top.js"
		type="text/javascript"></script>
	<script
		src="${url}/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
	<script
		src="${url}/global/plugins/fancybox/source/jquery.fancybox.pack.js"
		type="text/javascript"></script>
	<!-- pop up -->
	<script
		src="${url}/global/plugins/carousel-owl-carousel/owl-carousel/owl.carousel.min.js"
		type="text/javascript"></script>
	<!-- slider for products -->
	<script src='${url}/global/plugins/zoom/jquery.zoom.min.js'
		type="text/javascript"></script>
	<!-- product zoom -->
	<script
		src="${url}/global/plugins/bootstrap-touchspin/bootstrap.touchspin.js"
		type="text/javascript"></script>
	<!-- Quantity -->

	<!-- BEGIN LayerSlider -->
	<script src="${url}/global/plugins/slider-layer-slider/js/greensock.js"
		type="text/javascript"></script>
	<!-- External libraries: GreenSock -->
	<script
		src="${url}/global/plugins/slider-layer-slider/js/layerslider.transitions.js"
		type="text/javascript"></script>
	<!-- LayerSlider script files -->
	<script
		src="${url}/global/plugins/slider-layer-slider/js/layerslider.kreaturamedia.jquery.js"
		type="text/javascript"></script>
	<!-- LayerSlider script files -->
	<script src="${url}/frontend/pages/scripts/layerslider-init.js"
		type="text/javascript"></script>
	<!-- END LayerSlider -->

	<script src="${url}/frontend/layout/scripts/layout.js" type="text/javascript"></script>
	<script src="${url}/frontend/quantity.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function() {
			Layout.init();
			Layout.initOWL();
			LayersliderInit.initLayerSlider();
			Layout.initImageZoom();
			Layout.initTouchspin();
			Layout.initTwitter();

			Layout.initFixHeaderWithPreHeader();
			Layout.initNavScrolling();
		});
	</script>
	<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>