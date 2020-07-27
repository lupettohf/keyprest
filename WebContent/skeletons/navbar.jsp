<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <!-- Search Wrapper Area Start -->
    <div class="search-wrapper section-padding-100">
        <div class="search-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="search-content">
                        <form action="#" method="get">
                            <input type="search" name="search" id="search" placeholder="Type your keyword...">
                            <button type="submit"><img src="img/core-img/search.png" alt=""></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Search Wrapper Area End -->

    <!-- ##### Main Content Wrapper Start ##### -->
    <div class="main-content-wrapper d-flex clearfix">

        <!-- Mobile Nav (max width 767px)-->
        <div class="mobile-nav">
            <!-- Navbar Brand -->
            <div class="keyprest-navbar-brand">
                <a href="index"><img src="https://i.imgur.com/OiZ24CL.png" alt=""></a>
            </div>
            <!-- Navbar Toggler -->
            <div class="keyprest-navbar-toggler">
                <span></span><span></span><span></span>
            </div>
        </div>

        <!-- Header Area Start -->
        <header class="header-area clearfix">
            <!-- Close Icon -->
            <div class="nav-close">
                <i class="fa fa-close" aria-hidden="true"></i>
            </div>
            <!-- Logo -->
            <div class="logo">
                <a href="index.html"><img src="https://i.imgur.com/OiZ24CL.png" alt=""></a>
            </div>
            <!-- keyprest Nav -->
			
			<jsp:include page="_includes/navbarLinks.jsp" />
			
            <!-- Button Group 
            <div class="keyprest-btn-group mt-30 mb-100">
                <a href="#" class="btn keyprest-btn mb-15">%Discount%</a>
                <a href="#" class="btn keyprest-btn active">New this week</a>
            </div>-->
            
        </header>
        <!-- Header Area End -->