<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../header.jsp" />
<jsp:include page="../navbar.jsp" />    
    
      <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="checkout_details_area mt-50 clearfix">
                            <div class="cart-title">
                                <h2>Register to Keyprest</h2>
                            </div>

                            <form action="register" method="post">
                                <div class="row">
                                    <div class="col-10 mb-3">
                                        <input type="text" class="form-control" id="username" name="username" placeholder="Username" value="">
                                    </div>
                                    <div class="col-10 mb-3">
                                        <input type="email" class="form-control" id="e_mail" name=e_mail placeholder="E-Mail" value="">
                                    </div>
                                    <div class="col-5 mb-3">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" value="">
                                    </div>
                                    <div class="col-5 mb-3">
                                        <input type="password" class="form-control" id="password_confirm" name="password_confirm" placeholder="Repeat Password" value="">
                                    </div>
                                    <div class="col-10">
                                      <div class="cart-btn mt-100">
                                          <input type="submit" class="btn amado-btn w-60 float-right" value="Register">
                                      </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
             
<jsp:include page="../footer.jsp" />