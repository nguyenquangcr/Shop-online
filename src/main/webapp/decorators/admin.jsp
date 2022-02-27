<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/templates/assets" var="url"></c:url>
<!DOCTYPE html>
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>Metronic | eCommerce - Dashboard</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all"
	rel="stylesheet" type="text/css">
<link href="${url}/global/plugins/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="${url}/global/plugins/simple-line-icons/simple-line-icons.min.css"
	rel="stylesheet" type="text/css">
<link href="${url}/global/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${url}/global/plugins/uniform/css/uniform.default.css"
	rel="stylesheet" type="text/css">
<link
	href="${url}/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${url}/global/css/components.css" id="style_components"
	rel="stylesheet" type="text/css" />
<link href="${url}/global/css/plugins.css" rel="stylesheet"
	type="text/css" />
<link href="${url}/admin/layout2/css/layout.css" rel="stylesheet"
	type="text/css" />
<link id="style_color" href="${url}/admin/layout2/css/themes/grey.css"
	rel="stylesheet" type="text/css" />
<link href="${url}/admin/layout2/css/custom.css" rel="stylesheet"
	type="text/css" />
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css"
	href="${url}/global/plugins/select2/select2.css" />
<link rel="stylesheet" type="text/css"
	href="${url}/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css" />
<!-- END PAGE LEVEL STYLES -->
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico" />
</head>

<body
	class="page-boxed page-header-fixed page-container-bg-solid page-sidebar-closed-hide-logo ">
	<!-- BEGIN HEADER -->
	<%@include file="/common/admin/header.jsp"%>
	<!-- END HEADER -->
	<div class="clearfix"></div>
	<div class="container-fluid">
		<div class="page-container">

			<!-- BEGIN SIDEBAR -->
			<%@include file="/common/admin/left.jsp"%>
			<!-- END SIDEBAR -->

			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<!-- BEGIN CONTAINER -->
					<decorator:body></decorator:body>
					<!-- END CONTAINER -->
				</div>
			</div>
			<!-- END CONTENT -->
		</div>
		<!-- BEGIN FOOTER -->
		<%@include file="/common/admin/footer.jsp"%>
		<!-- END FOOTER -->
	</div>
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<!--[if lt IE 9]>
<script src="${url}/global/plugins/respond.min.js"></script>
<script src="${url}/global/plugins/excanvas.min.js"></script> 
<![endif]-->
	<script src="${url}/global/plugins/jquery.min.js"
		type="text/javascript"></script>
	<script src="${url}/global/plugins/jquery-migrate.min.js"
		type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script
		src="${url}/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js"
		type="text/javascript"></script>
	<script src="${url}/global/plugins/bootstrap/js/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="${url}/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"
		type="text/javascript"></script>
	<script
		src="${url}/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script src="${url}/global/plugins/jquery.blockui.min.js"
		type="text/javascript"></script>
	<script src="${url}/global/plugins/jquery.cokie.min.js"
		type="text/javascript"></script>
	<script src="${url}/global/plugins/uniform/jquery.uniform.min.js"
		type="text/javascript"></script>
	<script
		src="${url}/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"
		type="text/javascript"></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script src="${url}/global/plugins/flot/jquery.flot.js"
		type="text/javascript"></script>
	<script src="${url}/global/plugins/flot/jquery.flot.resize.js"
		type="text/javascript"></script>
	<script src="${url}/global/plugins/flot/jquery.flot.categories.js"
		type="text/javascript"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<script src="${url}/global/scripts/metronic.js" type="text/javascript"></script>
	<script src="${url}/admin/layout2/scripts/layout.js"
		type="text/javascript"></script>
	<script src="${url}/admin/layout2/scripts/demo.js"
		type="text/javascript"></script>
	<script src="${url}/admin/pages/scripts/ecommerce-index.js"></script>
	<!-- DataTable -->
	<script type="text/javascript"
		src="${url}/global/plugins/select2/select2.min.js"></script>
	<script type="text/javascript"
		src="${url}/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript"
		src="${url}/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
	<script src="${url}/admin/pages/scripts/table-managed.js"></script>
	<!-- End DataTable -->
	<!-- END PAGE LEVEL SCRIPTS -->
	<script>
		jQuery(document).ready(function() {
			Metronic.init(); // init metronic core components
			Layout.init(); // init current layout
			Demo.init(); // init demo features
			TableManaged.init();
			EcommerceIndex.init();
		});
	</script>
	<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>