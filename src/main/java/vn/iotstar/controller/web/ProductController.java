package vn.iotstar.controller.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.iotstar.model.Category;
import vn.iotstar.model.Product;
import vn.iotstar.model.Seller;
import vn.iotstar.service.ICategoryService;
import vn.iotstar.service.IProductService;
import vn.iotstar.service.ISellerService;
import vn.iotstar.service.impl.CategorySeviceImpl;
import vn.iotstar.service.impl.ProductServiceImpl;
import vn.iotstar.service.impl.SellerServiceImpl;

@WebServlet(urlPatterns = { "/product/list", "/product/detail", "/product/loadAjax" })
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 5889168824989045500L;

	ICategoryService cateService = new CategorySeviceImpl();
	IProductService productService = new ProductServiceImpl();
	ICategoryService categoryService = new CategorySeviceImpl();
	ISellerService sellerService = new SellerServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURI().toString();

		if (url.contains("product/list")) {
			productPage(request, response);
		}
		if (url.contains("product/detail")) {
			productDetail(request, response);
		}
		if (url.contains("product/loadAjax")) {
			productLoadAjax(request, response);
		}
	}

	private void productLoadAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("html/text");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String amount = request.getParameter("exits");
		int imount = Integer.parseInt(amount);
		List<Product> productListNew = productService.findAllPage(imount);

		PrintWriter out = response.getWriter();
		for (Product p : productListNew) {
			out.println("					<!-- PRODUCT ITEM START -->\r\n"
					+ "					<div class=\"product col-md-4 col-sm-6 col-xs-12\">\r\n"
					+ "						<div class=\"product-item\">\r\n"
					+ "							<div class=\"pi-img-wrapper\">\r\n"
					+ "								<img\r\n"
					+ "									src=\"/shoponline/image?fname=products/" + p.getImages()
					+ "\"\r\n" + "									class=\"img-responsive\" alt=\""
					+ p.getProductname() + "\">\r\n" + "								<div>\r\n"
					+ "									<a\r\n"
					+ "										href=\"/shoponline/image?fname=products/" + p.getImages()
					+ "\"\r\n"
					+ "										class=\"btn btn-default fancybox-button\">Zoom</a> <a\r\n"
					+ "												href=\"#product-pop-uppp" + p.getProductid()
					+ "\"\r\n"
					+ "												class=\"btn btn-default fancybox-fast-view\">View</a>\r\n"
					+ "								</div>\r\n" + "							</div>\r\n"
					+ "							<h3>\r\n" + "								<a\r\n"
					+ "									href=\"/shoponline/product/detail?productid=" + p.getProductid()
					+ "\">" + p.getProductname() + "</a>\r\n" + "							</h3>\r\n"
					+ "							<div class=\"pi-price\">" + p.getPrice() + "</div>\r\n"
					+ "							<a href=\"#\" class=\"btn btn-default add2cart\">Cho vào giỏ hàng</a>\r\n"
					+ "						</div>\r\n" + "					</div>\r\n" + "					\r\n"
					+ "					<!-- BEGIN fast view of a product -->\r\n"
					+ "							<div id=\"product-pop-uppp" + p.getProductid() + "\"\r\n"
					+ "								style=\"display: none; width: 700px;\">\r\n"
					+ "								<div class=\"product-page product-pop-uppp" + p.getProductid()
					+ "\">\r\n" + "									<div class=\"row\">\r\n"
					+ "										<div class=\"col-md-6 col-sm-6 col-xs-3\">\r\n"
					+ "											<div class=\"product-main-image\">\r\n"
					+ "												<img\r\n"
					+ "													src=\"/shoponline/image?fname=products/"
					+ p.getImages() + "\"\r\n"
					+ "													alt=\"Cool green dress with red bell\" class=\"img-responsive\">\r\n"
					+ "											</div>\r\n"
					+ "											<div class=\"product-other-images\">\r\n"
					+ "												<a\r\n"
					+ "													href=\"/shoponline/image?fname=products/"
					+ p.getImages() + "\" \r\n"
					+ "													class=\"active\"><img alt=\"Berry Lace Dress\"\r\n"
					+ "													src=\"/shoponline/image?fname=products/"
					+ p.getImages() + "\" ></a>\r\n" + "												<a\r\n"
					+ "													href=\"/shoponline/image?fname=products/"
					+ p.getImages() + "\" ><img\r\n"
					+ "													alt=\"Berry Lace Dress\"\r\n"
					+ "													src=\"/shoponline/image?fname=products/"
					+ p.getImages() + "\" ></a>\r\n" + "												<a\r\n"
					+ "													href=\"/shoponline/image?fname=products/"
					+ p.getImages() + "\" ><img\r\n"
					+ "													alt=\"Berry Lace Dress\"\r\n"
					+ "													src=\"/shoponline/image?fname=products/"
					+ p.getImages() + "\" ></a>\r\n" + "											</div>\r\n"
					+ "										</div>\r\n"
					+ "										<div class=\"col-md-6 col-sm-6 col-xs-9\">\r\n"
					+ "											<h1>" + p.getProductname() + "</h1>\r\n"
					+ "											<div class=\"price-availability-block clearfix\">\r\n"
					+ "												<div class=\"price\">\r\n"
					+ "													<strong><span>$</span>" + p.getPrice()
					+ "</strong> <em>$<span>" + p.getPrice() * 1.5 + "</span></em>\r\n"
					+ "												</div>\r\n"
					+ "												<div class=\"availability\">\r\n"
					+ "													Số lượng tồn: <strong>" + p.getStock()
					+ "</strong>\r\n" + "												</div>\r\n"
					+ "											</div>\r\n"
					+ "											<div class=\"description\">\r\n"
					+ "												<p>${proList.description}</p>\r\n"
					+ "											</div>\r\n"
					+ "											<div class=\"product-page-options\">\r\n"
					+ "												<div class=\"pull-left\">\r\n"
					+ "													<label class=\"control-label\">Size:</label> <select\r\n"
					+ "														class=\"form-control input-sm\">\r\n"
					+ "														<option>L</option>\r\n"
					+ "														<option>M</option>\r\n"
					+ "														<option>XL</option>\r\n"
					+ "													</select>\r\n"
					+ "												</div>\r\n"
					+ "												<div class=\"pull-left\">\r\n"
					+ "													<label class=\"control-label\">Color:</label> <select\r\n"
					+ "														class=\"form-control input-sm\">\r\n"
					+ "														<option>Red</option>\r\n"
					+ "														<option>Blue</option>\r\n"
					+ "														<option>Black</option>\r\n"
					+ "													</select>\r\n"
					+ "												</div>\r\n"
					+ "											</div>\r\n"
					+ "											<div class=\"product-page-cart\">\r\n"
					+ "												<div class=\"product-quantity\">\r\n"
					+ "													<input id=\"product-quantity\" type=\"text\" value=\"1\" readonly\r\n"
					+ "														name=\"product-quantity\" class=\"form-control input-sm\">\r\n"
					+ "												</div>\r\n"
					+ "												<button class=\"btn btn-primary\" type=\"submit\">Add\r\n"
					+ "													to cart</button>\r\n"
					+ "												<a\r\n"
					+ "													href='/shoponline/product/detail?productid="
					+ p.getProductid() + "\'\r\n"
					+ "													class=\"btn btn-default\">More details</a>\r\n"
					+ "											</div>\r\n"
					+ "										</div>\r\n" + "\r\n"
					+ "										<div class=\"sticker sticker-sale\"></div>\r\n"
					+ "									</div>\r\n" + "								</div>\r\n"
					+ "							</div>\r\n"
					+ "							<!-- END fast view of a product -->\r\n"
					+ "							\r\n" + "					<!-- PRODUCT ITEM END -->");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private void productDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productid = request.getParameter("productid");
		// lay du lieu day len view
		Product product = productService.findOne(Integer.parseInt(productid));
		request.setAttribute("product", product);

		List<Category> categoryList = categoryService.findAll();
		request.setAttribute("categoryList", categoryList);
//		List<Product> productListTopAmount = productService.findAllTopAmount();
//		request.setAttribute("productListTopAmount", productListTopAmount);

		request.getRequestDispatcher("/views/web/product-detail.jsp").forward(request, response);
	}

	private void productPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String categoryid = request.getParameter("categoryid");
		String sellerid = request.getParameter("sellerid");

		String indexPage = request.getParameter("index");

		// phan trang
		// khoi tao trang dau
		if (indexPage == null) {
			indexPage = "1";
		}
		if (categoryid == null) {
			categoryid = "0";
		}
		if (sellerid == null) {
			sellerid = "0";
		}
		int indexp = Integer.parseInt(indexPage);
		// Get data tu DAO
		int countP = productService.countAll();
		int countCid = productService.countCID(Integer.parseInt(categoryid));
		int countSid = productService.countSell(Integer.parseInt(sellerid));
		// chia trang cho count
		int endPage = countP / 3;
		if (countP % 3 != 0) {
			endPage++;
		}

		if (Integer.parseInt(categoryid) == 0 && Integer.parseInt(sellerid) == 0) {
			List<Product> productListNew = productService.findAllPage(indexp - 1);
			request.setAttribute("productListNew", productListNew);
			request.setAttribute("countProductAll", countP);
		} else if (Integer.parseInt(categoryid) != 0) {
			List<Product> productListNew = productService.findAllByCID(Integer.parseInt(categoryid), indexp - 1);
			request.setAttribute("productListNew", productListNew);
			request.setAttribute("countProductAll", countCid);
		} else {
			List<Product> productListNew = productService.findAllBySeller(Integer.parseInt(sellerid), indexp - 1);
			request.setAttribute("productListNew", productListNew);
			request.setAttribute("countProductAll", countSid);
		}

		// lay du lieu va day len view
		List<Seller> sellerList = sellerService.findAll();
		request.setAttribute("sellerList", sellerList);
		List<Category> categoryList = categoryService.findAll();
		request.setAttribute("categoryList", categoryList);

		// truyen len JSP
		request.setAttribute("endP", endPage);
		request.setAttribute("tag", indexp);
		request.setAttribute("cateid", categoryid);
		request.setAttribute("sellid", sellerid);
		request.getRequestDispatcher("/views/web/product-list-ajax.jsp").forward(request, response);
	}
}
