<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url value="/templates/assets" var="url"></c:url>

    <div class="main">
      <div class="container-fluid">
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
          <!-- BEGIN CONTENT -->
          <div class="col-md-12 col-sm-12">
            <h1>Shopping cart</h1>
            <div class="goods-page">
              <div class="goods-data clearfix">
                <div class="table-wrapper-responsive">
                <table summary="Shopping cart">
                  <tr>
                    <th class="goods-page-image">Image</th>
                    <th class="goods-page-description">Description</th>
                    <th class="goods-page-ref-no">Ref No</th>
                    <th class="goods-page-quantity">Quantity</th>
                    <th class="goods-page-price">Unit price</th>
                    <th class="goods-page-total" colspan="2">Total</th>
                  </tr>
                  <c:forEach items="${sessionScope.cart}" var="map">
                  <tr>
                    <td class="goods-page-image">
                      <a href='<c:url value="/product/detail?productid=${map.value.product.productid}"></c:url>'><img src='<c:url value="/image?fname=products/${map.value.product.images}"></c:url>' alt="${map.value.product.productname}"></a>
                    </td>
                    <td class="goods-page-description">
                      <h3><a href="<c:url value="/product/detail?productid=${map.value.product.productid}"></c:url>">${map.value.product.productname}</a></h3>
                      <p><strong>${map.value.product.productid}</strong></p>
                      <em>${map.value.product.description}</em>
                    </td>
                    <td class="goods-page-ref-no">
                      ${map.value.product.seller.sellername}
                    </td>
                    <td class="goods-page-quantity" style="width: 250px">
                    	<form name="f1" method="get" action='<c:url value="/member/cart/updatequantity"></c:url>'>
                    	    <input type="text" name="pId" id="pid" value="${map.value.product.productid}" hidden="">	
                    		<input type="text" id="stock" value="${map.value.product.stock}" hidden="">	
                    		<button type="button" class="quantity-button" name='subtract${map.value.product.productid}' onclick='javascript: subtractQty0(${map.value.product.productid});'
                    		value='-'>-</button>
                    		<input readonly type="text" name='quantity' value="${map.value.quantity}" id="product-quantity${map.value.product.productid}" style="width:40px" />
                    	    <button type="button" class="quantity-button" name='add${map.value.product.productid}' 
                    	    onclick='javascript: subtractQty(${map.value.product.productid});'
                    		value='+'>+</button>
                    		&nbsp;
                    		<button type="submit" class="btn btn-danger">Cập nhật</button>
                    	</form>                    
                    </td>
                    <td class="goods-page-price">
                      <strong><span>$</span>${map.value.product.price}</strong>
                    </td>
                    <td class="goods-page-total">
                      <strong><span>$</span>${map.value.product.price * map.value.quantity}</strong>
                    </td>
                    <td class="del-goods-col">
                      <a class="del-goods" href="${pageContext.request.contextPath}/member/cart/remove?pId=${map.value.product.productid}">&nbsp;</a>
                    </td>
                  </tr>
                  </c:forEach>
                 </table>
                </div>

                <div class="shopping-total">
                  <ul>
                    <li>
                      <em>Sub total</em>
                      <c:set var="total" value="${0}"></c:set>
                      <c:forEach items="${sessionScope.cart}" var="map">
                      	<c:set var="total" value="${total + map.value.quantity * map.value.product.price }"></c:set>
                      </c:forEach>
                      <strong class="price"><span>$</span>${total}</strong>
                    </li>
                    <li>
                      <em>Shipping cost</em>
                      <c:set var="ship" value="${total*0.005}">
                      </c:set>
                      <strong class="price"><span>$</span>${ship}</strong>
                    </li>
                    <li class="shopping-total-price">
                      <em>Total</em>
                      <strong class="price"><span>$</span>${total + ship}</strong>
                    </li>
                  </ul>
                </div>
              </div>
              <a href="${pageContext.request.contextPath}/home" class="btn btn-default">Continue shopping <i class="fa fa-shopping-cart"></i></a>
              <a class="btn btn-primary" href="${pageContext.request.contextPath}/member/order">Checkout <i class="fa fa-check"></i></a>
            </div>
          </div>
          <!-- END CONTENT -->
        </div>
        <!-- END SIDEBAR & CONTENT -->

        <!-- BEGIN SIMILAR PRODUCTS -->
        <div class="row margin-bottom-40">
          <div class="col-md-12 col-sm-12">
            <h2>Most popular products</h2>
            <div class="owl-carousel owl-carousel4">
              <div>
                <div class="product-item">
                  <div class="pi-img-wrapper">
                    <img src="${url}/frontend/pages/img/products/k1.jpg" class="img-responsive" alt="Berry Lace Dress">
                    <div>
                      <a href="${url}/frontend/pages/img/products/k1.jpg" class="btn btn-default fancybox-button">Zoom</a>
                      <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">View</a>
                    </div>
                  </div>
                  <h3><a href="shop-item.html">Berry Lace Dress</a></h3>
                  <div class="pi-price">$29.00</div>
                  <a href="#" class="btn btn-default add2cart">Add to cart</a>
                  <div class="sticker sticker-new"></div>
                </div>
              </div>
              <div>
                <div class="product-item">
                  <div class="pi-img-wrapper">
                    <img src="${url}/frontend/pages/img/products/k2.jpg" class="img-responsive" alt="Berry Lace Dress">
                    <div>
                      <a href="${url}/frontend/pages/img/products/k2.jpg" class="btn btn-default fancybox-button">Zoom</a>
                      <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">View</a>
                    </div>
                  </div>
                  <h3><a href="shop-item.html">Berry Lace Dress2</a></h3>
                  <div class="pi-price">$29.00</div>
                  <a href="#" class="btn btn-default add2cart">Add to cart</a>
                </div>
              </div>
              <div>
                <div class="product-item">
                  <div class="pi-img-wrapper">
                    <img src="${url}/frontend/pages/img/products/k3.jpg" class="img-responsive" alt="Berry Lace Dress">
                    <div>
                      <a href="${url}/frontend/pages/img/products/k3.jpg" class="btn btn-default fancybox-button">Zoom</a>
                      <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">View</a>
                    </div>
                  </div>
                  <h3><a href="shop-item.html">Berry Lace Dress3</a></h3>
                  <div class="pi-price">$29.00</div>
                  <a href="#" class="btn btn-default add2cart">Add to cart</a>
                </div>
              </div>
              <div>
                <div class="product-item">
                  <div class="pi-img-wrapper">
                    <img src="${url}/frontend/pages/img/products/k4.jpg" class="img-responsive" alt="Berry Lace Dress">
                    <div>
                      <a href="${url}/frontend/pages/img/products/k4.jpg" class="btn btn-default fancybox-button">Zoom</a>
                      <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">View</a>
                    </div>
                  </div>
                  <h3><a href="shop-item.html">Berry Lace Dress4</a></h3>
                  <div class="pi-price">$29.00</div>
                  <a href="#" class="btn btn-default add2cart">Add to cart</a>
                  <div class="sticker sticker-sale"></div>
                </div>
              </div>
              <div>
                <div class="product-item">
                  <div class="pi-img-wrapper">
                    <img src="${url}/frontend/pages/img/products/k1.jpg" class="img-responsive" alt="Berry Lace Dress">
                    <div>
                      <a href="${url}/frontend/pages/img/products/k1.jpg" class="btn btn-default fancybox-button">Zoom</a>
                      <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">View</a>
                    </div>
                  </div>
                  <h3><a href="shop-item.html">Berry Lace Dress5</a></h3>
                  <div class="pi-price">$29.00</div>
                  <a href="#" class="btn btn-default add2cart">Add to cart</a>
                </div>
              </div>
              <div>
                <div class="product-item">
                  <div class="pi-img-wrapper">
                    <img src="${url}/frontend/pages/img/products/k2.jpg" class="img-responsive" alt="Berry Lace Dress">
                    <div>
                      <a href="${url}/frontend/pages/img/products/k2.jpg" class="btn btn-default fancybox-button">Zoom</a>
                      <a href="#product-pop-up" class="btn btn-default fancybox-fast-view">View</a>
                    </div>
                  </div>
                  <h3><a href="shop-item.html">Berry Lace Dress6</a></h3>
                  <div class="pi-price">$29.00</div>
                  <a href="#" class="btn btn-default add2cart">Add to cart</a>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- END SIMILAR PRODUCTS -->
      </div>
    </div>