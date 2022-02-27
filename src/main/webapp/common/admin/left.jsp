<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/templates/assets" var="url"></c:url>

<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
	<div class="page-sidebar navbar-collapse collapse">
		<ul class="page-sidebar-menu page-sidebar-menu-hover-submenu "
			data-keep-expanded="false" data-auto-scroll="true"
			data-slide-speed="200">
			<li class="start ${tag == 'adminhome' ? 'active open': '' }"><a
				href="<c:url value="/admin/home"></c:url>"> <i class="icon-home"></i>
					<span class="title">Dashboard</span>
			</a></li>
			<li class="${tag=='cate' ? 'active open' : '' }"><a
				href="<c:url value="/admin/category"></c:url>"> <i
					class="icon-basket"></i> <span class="title">Category</span> <span
					class="${tag=='cate' ? 'selected' : '' }"></span> <span
					class="arrow ${tag=='cate' ? 'open' : '' }"></span>
			</a></li>
			<li class="${tag=='use' ? 'active open' : '' }"><a
				href="<c:url value="/admin/user"></c:url>"> <i
					class="icon-basket"></i> <span class="title">User</span> <span
					class="${tag=='use' ? 'selected' : '' }"></span> <span
					class="arrow ${tag=='use' ? 'open' : '' }"></span>
			</a></li>
			<li class="${tag=='rol' ? 'active open' : '' }"><a
				href="<c:url value="/userrole"></c:url>"> <i class="icon-basket"></i>
					<span class="title">User Roles</span> <span
					class="${tag=='rol' ? 'selected' : '' }"></span> <span
					class="arrow ${tag=='rol' ? 'open' : '' }"></span>
			</a></li>
			<li class="${tag=='pro' ? 'active open' : '' }"><a
				href="<c:url value="/admin/product"></c:url>"> <i
					class="icon-basket"></i> <span class="title">Product</span> <span
					class="${tag=='pro' ? 'selected' : '' }"></span> <span
					class="arrow ${tag=='pro' ? 'open' : '' }"></span>
			</a></li>
			<li class="${tag=='sell' ? 'active open' : '' }"><a
				href="<c:url value="/admin/seller"></c:url>"> <i
					class="icon-basket"></i> <span class="title">Seller</span> <span
					class="${tag=='sell' ? 'selected' : '' }"></span> <span
					class="arrow ${tag=='sell' ? 'open' : '' }"></span>
			</a></li>
						<li class="${tag=='slid' ? 'active open' : '' }"><a
				href="<c:url value="/admin/slides"></c:url>"> <i
					class="icon-basket"></i> <span class="title">Slides</span> <span
					class="${tag=='slid' ? 'selected' : '' }"></span> <span
					class="arrow ${tag=='slid' ? 'open' : '' }"></span>
			</a></li>
		</ul>
		<!-- END SIDEBAR MENU -->
	</div>
</div>
<!-- END SIDEBAR -->