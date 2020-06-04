<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Keyprest</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="static/css/main.css" rel="stylesheet"> 
<style>body{background:#141414;color:#e0e0e0;font-family:'Roboto Mono',monospace}form{background:#212121;margin:0 auto;padding:10px}.navbar-xs{min-height:22px;border-radius:0}.navbar-xs .navbar-brand{padding:2px 8px;font-size:14px;line-height:14px}.navbar-xs .navbar-nav>li>a{border-right:1px solid #ddd;padding-top:2px;padding-bottom:2px;line-height:16px}.grid-container{display:grid;grid-template-columns:1fr 1fr 1fr 1fr;grid-template-rows:.2fr 2.6fr .2fr;gap:1px 1px;grid-template-areas:"header-lft header-lft header-rght header-rght" "store-face store-face store-face store-face" ". . footer-links footer-links"}.header-lft{grid-area:header-lft}.header-rght{grid-area:header-rght}.store-face{grid-area:store-face}.footer-links{grid-area:footer-links}button.submit,button.submit:active,button.submit:focus,button.submit:hover,button.submit:visited{background:#212121;color:#e0e0e0;border:0;font-weight:700;display:block;margin:0 auto;margin-top:50px}input.form-control{background:#212121;border:0;border-bottom:1px solid #e0e0e0;border-radius:0}input.form-control:focus{box-shadow:none;border-bottom:1px solid #e0e0e0}input[type=text].email{padding:10px}:root{--main-color:#212121}.product-grid{border:1px solid transparent;font-family:'Roboto Mono',monospace;padding:10px 10px;transition:all .5s}.product-grid:hover{border:2px solid #212121}.product-grid .product-image{position:relative;overflow:hidden}.product-grid .product-image a.image{display:block}.product-grid .product-image img{width:100%;height:auto}.product-grid .add-to-cart{color:#fff;background:#222;font-size:17px;font-weight:500;text-align:center;padding:10px 0;display:block;position:absolute;left:0;bottom:-50px;right:0;transition:all ease-in-out .35s}.product-grid:hover .add-to-cart{bottom:0}.product-grid .add-to-cart:after{content:"\f101";color:#fff;font-family:'Font Awesome 5 Free';font-weight:900;display:inline-block;opacity:0;transition:all .5s}.product-grid .add-to-cart:hover:after{padding-left:10px;opacity:1}.product-grid .product-content{position:relative}.product-grid .product-name{color:#06bcc1;font-size:font-size: calc(1vw + 1vh);font-weight:100;line-height:35px;border-bottom:1px solid #212121;display:block;margin:0 0 15px}.product-grid .icon{padding:0;margin:0;list-style:none;opacity:0;position:absolute;top:-15px;right:0;transition:all ease-in-out .35s}.product-grid:hover .icon{opacity:1;top:8px}.product-grid .icon li{display:inline-block}.product-grid .icon li a{color:#fff;font-size:16px;margin:0 4px}.product-grid .icon li a:hover{color:var(--main-color)}.product-grid .title{font-size:20px;font-weight:600;text-transform:capitalize;margin:0 0 5px}.product-grid .title a{color:#fff;transition:all .5s ease-out 0s}.product-grid .title a:hover{color:var(--main-color)}.product-grid .region{font-size:18px;margin:0 0 3px;display:block}.product-grid .region a{color:#fff;transition:all .3s ease 0s}.product-grid .region a:hover{color:var(--main-color)}.product-grid .price{color:#fff;font-size:24px;font-weight:500;display:inline-block}.product-grid .rating{padding:0;margin:0;list-style:none;float:right}.product-grid .rating li{color:#ffb14b;font-size:13px;display:inline-block}.product-grid .rating li.disable{color:#c1c1c1}@media only screen and (max-width:990px){.product-grid{margin-bottom:30px}}</style>
</head>
<body>
<div class="container">
  <nav class="navbar navbar-default navbar-xs" role="navigation">
  <div class="navbar-header">
    <a class="navbar-brand" href="/"><b>Key</b>Prest</a>
  </div>
  <div class="collapse navbar-collapse">
    <ul class="nav navbar-nav">
      <li><a href="cart"><i class="glyphicon glyphicon-shopping-cart"></i></a></li>
      <li><a href="user"><i class="glyphicon glyphicon-user"></i></a></li>
    </ul>
  </div>
</nav>
