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
<style>body{background:#141414;color:#e0e0e0;font-family:'Roboto Mono',monospace}form{background:#212121;margin:0 auto;padding:10px}.navbar-xs{min-height:22px;border-radius:0}.navbar-xs .navbar-brand{padding:2px 8px;font-size:14px;line-height:14px}.navbar-xs .navbar-nav>li>a{border-right:1px solid #ddd;padding-top:2px;padding-bottom:2px;line-height:16px}.grid-container{display:grid;grid-template-columns:1fr 1fr 1fr 1fr;grid-template-rows:.2fr 2.6fr .2fr;gap:1px 1px;grid-template-areas:"header-lft header-lft header-rght header-rght" "store-face store-face store-face store-face" ". . footer-links footer-links"}.header-lft{grid-area:header-lft}.header-rght{grid-area:header-rght}.store-face{grid-area:store-face}.footer-links{grid-area:footer-links}button.submit,button.submit:active,button.submit:focus,button.submit:hover,button.submit:visited{background:#212121;color:#e0e0e0;border:0;font-weight:700;display:block;margin:0 auto;margin-top:50px}input.form-control{background:#212121;border:0;border-bottom:1px solid #e0e0e0;border-radius:0}input.form-control:focus{box-shadow:none;border-bottom:1px solid #e0e0e0}input[type=text].email{padding:10px}body{background:#141414;color:#e0e0e0;font-family:'Roboto Mono',monospace}form{background:#212121;margin:0 auto;padding:10px}.products-section{grid-column-start:2;grid-row-start:2;display:grid;height:100%;grid-template-rows:auto 1fr auto;grid-template-columns:repeat(auto-fill,minmax(300px,1fr));grid-gap:60px}.product-card{grid-column-start:span 1;display:grid;grid-template-rows:240px auto auto;grid-gap:16px}img{width:100%;height:100%;object-fit:cover;border-radius:3px}.product-card-footer{display:grid;grid-template-columns:repeat(2,1fr)}.product-category{color:#fff;font-size:12px;font-weight:700}.product-name{color:#fff;font-size:18px;font-weight:600;margin:8px 0}.product-description{color:#fff;font-size:14px;font-weight:400;margin:0;line-height:1.3rem;overflow:hidden;text-overflow:ellipsis;display:-webkit-box;-webkit-line-clamp:2;-webkit-box-orient:vertical}.product-additional-wrapper{font-weight:700}.product-quantity{font-size:13px;color:#555;margin-top:4px}button{background-color:#6b0543;color:#fff;border:0;border-radius:2px;font-size:14px;text-transform:uppercase;font-weight:600;font-family:"Fira Sans";max-width:160px;width:100%}@media(max-width:480px){body{grid-template-columns:auto auto auto;grid-template-rows:auto auto auto}}</style>
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
