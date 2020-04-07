<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored = "false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>NCB</title>
    <link rel="stylesheet" media="all" href="https://cdn2.hexlet.io/assets/application-6c79ff11f91a2bc1f99cd07b254b3f0c28c107706cefef4d910ca3b4ba7f5266.css">
    <link rel="stylesheet" media="screen" href="https://cdn2.hexlet.io/packs/css/41-4310e18e.chunk.css">
    <link id="avast_os_ext_custom_font" href="chrome-extension://eofcbnmajmjmplflapaojjnihcjkigck/common/ui/fonts/fonts.css" rel="stylesheet" type="text/css">
    <style>
        <jsp:directive.include file="styles/cource.css"/>
        <jsp:directive.include file="styles/main.css"/>
    </style>
</head>
<body>
<!-- HEADER BLOCK -->
<nav class="navbar navbar-expand-lg navbar-light bg-white hexlet-navbar border-bottom">
    <a aria-hidden="true" class="navbar-brand mr-4" >
        Nice Cources Bro
    </a>
    <button aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation" class="navbar-toggler" data-target="#navbarResponsive" data-toggle="collapse" type="button">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link hexlet-navbar-link px-3 active" href="/fs/cources">
                    <div class="my-2">Курсы</div>
                </a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li class="nav-item">

                <a class="nav-link hexlet-navbar-link px-3 " href="/fs/profile">
                    <div class="my-2">${currentUser.firstName} ${currentUser.lastName}</div>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link hexlet-navbar-link px-3 " href="/fs/logout">
                    <div class="my-2" style="color: red;">Выйти</div>
                </a>
            </li>
        </ul>
    </div>
</nav>
<!-- HEADER BLOCK -->
<main>
    <div class="container mt4">
        <!-- FILTER BLOCK -->
        <div class="row justify-content-center">
            <div class="col-12 col-md-7 col-lg-6 border-left">
                <div class="h5 text-center">По языку</div>
                <div class="row mt-4">
                    <div class="col-2">
                        <ul class="list-unstyled">
                            <li class="m-2">
                                <b>Все</b>
                            </li>
                        </ul>
                    </div>
                    <div class="col-10">
                        <div class="row">
                            <div class="col">
                                <ul class="list-unstyled">
                                    <li class="m-2">
                                        <a id="Java" class="x-link-without-decoration" href="/fs/cources?language_eq=Java">Java</a>
                                    </li>
                                    <li class="m-2">
                                        <a id="JavaScript" class="x-link-without-decoration" href="/fs/cources?language_eq=JavaScript">JavaScript</a>
                                    </li>
                                    <li class="m-2">
                                        <a id="PHP" class="x-link-without-decoration" href="/fs/cources?language_eq=PHP">PHP</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col">
                                <ul class="list-unstyled">
                                    <li class="m-2">
                                        <a id="Ruby" class="x-link-without-decoration" href="/fs/cources?language_eq=Ruby">Ruby</a>
                                    </li>
                                    <li class="m-2">
                                        <a id="SQL" class="x-link-without-decoration" href="/fs/cources?language_eq=SQL">SQL</a>
                                    </li>
                                    <li class="m-2">
                                        <a id="Shell (Utils)" class="x-link-without-decoration" href="/fs/cources?language_eq=Shell (Utils)">Shell (Utils)</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col">
                                <ul class="list-unstyled">
                                    <li class="m-2">
                                        <a id="HTML CSS" class="x-link-without-decoration" href="/fs/cources?language_eq=HTML CSS">HTML & CSS</a>
                                    </li>
                                    <li class="m-2">
                                        <a id="Racket" class="x-link-without-decoration" href="/fs/cources?language_eq=Racket">Racket</a>
                                    </li>
                                    <li class="m-2">
                                        <a id="Python" class="x-link-without-decoration" href="/fs/cources?language_eq=Python">Python</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-12 col-md-5 col-lg-4 mt-4 mt-md-0 border-left border-right">
                <div class="h5 text-center">По стоимости</div>
                <div class="row mt-4">
                    <div class="col-2">
                        <ul class="list-unstyled">
                            <li class="m-2">
                                <b>Все</b>
                            </li>
                        </ul>
                    </div>
                    <div class="col-10">
                        <div class="row">
                            <div class="col">
                                <ul class="list-unstyled">
                                    <li class="m-2">
                                        <a id="paid" class="x-link-without-decoration" href="/fs/cources?pricing_type_eq=paid">Платные</a>
                                    </li>
                                    <li class="m-2">
                                        <a id="free" class="x-link-without-decoration" href="/fs/cources?pricing_type_eq=free">Бесплатные</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- FILTER BLOCK -->
        <!-- COURSES BLOCK -->
        <div class="mt-4">
            <h1 class="mb-3 h4">Все курсы</h1>
            <div class="row">
                <c:if test="${not empty cources}">
                    <c:forEach items="${cources}" var="cource">
                        <c:if test="${cource.deleted == false}">
                            <div class="mb-5 col-lg-4 col-sm-6 col-12">
                                <div class="card shadow-sm x-shadow-fade-in h-100">
                                    <div class="bg-success card-header d-flex flex-column py-1 text-white">
                                        <div class="d-flex">
                                            <div class="cource-img" style="background-image:url('${cource.language.imgUrl}')"></div>
                                            <div class="ml-auto my-auto">
                                                   ${cource.duration} ${cource.getHour(cource.duration)}
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body d-flex flex-column">
                                        <div class="row flex-wrap-reverse px-3">
                                            <h5 class="col-auto col-sm-10 mr-auto p-0">${cource.title}</h5>
                                            <div>
                                                <span class="badge badge-light p-0">${cource.price}тг</span>
                                            </div>
                                        </div>
                                        <div class="text-muted mb-2">${fn:substring(cource.description, -1, 60)}...</div>
                                        <div class="mt-auto d-flex">
                                            <div class="ml-auto">
                                                <a class="x-link-without-decoration <c:if test="${currentUser.role != 2}">stretched-link</c:if>" href="/fs/cource?courceId=${cource.id}">Посмотреть</a>
                                                <c:if test="${currentUser.role==2}">
                                                    <form action="/fs/delete" method="post" style="display: inline-block;">
                                                        <input type="hidden" value="${cource.id}" name="courceId">
                                                        <button class="x-link-without-decoration" style="color: red;font-size: 13px;" type="submit">Удалить</button>
                                                    </form>

                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:if>
                <c:if test="${empty cources}">
                    <h3 style="margin:auto;padding-bottom: 3em;">Ничего не найдено!</h3>
                </c:if>
            </div>
        </div>

        <!-- COURSES BLOCK -->
    </div>
</main>
<!-- FOOTER BLOCK -->
<footer class="footer hexlet-footer bg-light border-top pb-5 mt-5">
    <div class="container">
        <div class="row">
            <div class="col-12 col-lg-4 mt-5">
                <div class="h2">
                    NCB 2020
                </div>
                <div class="pr-2">
                    <hr>
                </div>
                <ul class="list-unstyled">
                    <li>
                        <a class="x-link-only-hover" href="/fs/about">О проекте</a>
                    </li>
                    <li>
                        <a class="x-link-only-hover" href="/fs/tos">Условия использования</a>
                    </li>
                    <li>
                        <a class="x-link-only-hover" href="/fs/privacy">Политика конфиденциальности</a>
                    </li>
                </ul>
            </div>
            <div class="col-12 col-sm-6 col-lg-3 mt-5">
                <div class="h5 mt-5 mb-3" style="margin-top: 0px !important;">Подписка</div>
                <ul class="list-unstyled">
                    <li>
                        <a class="x-link-only-hover" target="_blank" rel="noopener noreferrer" href="/fs/subscription">Что такое подписка?</a>
                    </li>
                    <li>
                        <a class="x-link-only-hover" href="/fs/pricing">Цены</a>
                    </li>
                </ul>
            </div>
            <div class="col-12 col-sm-6 col-lg-3 mt-5">
                <div class="h5 mb-3">Компаниям</div>
                <ul class="list-unstyled">
                    <li>
                        <a class="x-link-only-hover" href="/fs/teams">Обзор возможностей</a>
                    </li>
                </ul>
            </div>
            <div class="col-12 col-sm-6 col-lg-2 mt-5">
                <div class="h5 mb-3">Помощь</div>
                <ul class="list-unstyled">
                    <li>
                        <a class="x-link-only-hover" href="/fs/knowledge">База знаний</a>
                    </li>
                    <li>
                        <a class="x-link-only-hover" href="/fs/community">Топики</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</footer>
<!-- FOOTER BLOCK -->
<script type="text/javascript">

    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);

    console.log(queryString);

    const pricing_type = urlParams.get("pricing_type_eq");
    const language = urlParams.get("language_eq");

    console.log(pricing_type);
    console.log(language);

    if(language){
        document.getElementById("${language}").style.fontWeight = "bold";

    } else if(pricing_type){
        document.getElementById("${pricingType}").style.fontWeight = "bold";
    }


</script>
</body>
</html>
