<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../../header.jsp" />
<jsp:include page="../../navbar.jsp" />    

      <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="checkout_details_area mt-50 clearfix">
                            <div class="cart-title">
                                <h2>Create product</h2>
                            </div>

                            <form action="createproduct" method="post">
                                <div class="row">
                                    <div class="col-9 mb-3">
                                        <input type="text" class="form-control" id="product_name" name="product_name" placeholder="Product Name" value="">
                                    </div>
                                   	<div class="col-3 mb-3">
                                    	<select class="nice-select" name="product_service">							
												<option value="1">Steam</option>
												<option value="2">Origin</option>
												<option value="3">UPlay</option>
												<option value="4">Other</option> 
										</select>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <input type="number" step="any" class="form-control" name="product_price" placeholder="Price" value="">
                                    </div>
                                    <div class="col-3 mb-3">
                                        <input type="number" step="any" class="form-control" name="product_discount" placeholder="Discount %" value="0">
                                    </div>
                                   	<div class="col-3 mb-3">
                                    	<select class="nice-select" name="product_region">							
												<option value="Europe">Europe</option>
												<option value="Russia">Russia</option>
												<option value="Asia">Asia</option>
												<option value="World">World</option> 
										</select>
                                    </div>
                                   	<div class="col-9 mb-3">
                                        <textarea class="form-control" id="product_description" name="product_description" placeholder="Product Desciption"></textarea>
                                    </div>
                                   	<div class="col-3 mb-3 custom-checkbox">
                                        <input type="checkbox" name="product_isdlc" class="custom-control-input" id="is_dlc">
                                        <label class="custom-control-label" for="is_dlc"> DLC </label>
                                    </div>                            
                                    <div class="col-10">
                                      <div class="cart-btn mt-100">
                                          <input type="submit" class="btn amado-btn w-60 float-right" value="Create Product">
                                      </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
<jsp:include page="../../footer.jsp" />