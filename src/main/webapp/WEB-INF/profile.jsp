<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <title>Профиль - ${currentUser.firstName}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" media="all" href="https://cdn2.hexlet.io/assets/application-6c79ff11f91a2bc1f99cd07b254b3f0c28c107706cefef4d910ca3b4ba7f5266.css">
    <link rel="stylesheet" media="screen" href="https://cdn2.hexlet.io/packs/css/41-4310e18e.chunk.css">
    <link rel="alternate" type="application/rss+xml" title="RSS" href="https://ru.hexlet.io/lessons.rss">
    <link id="avast_os_ext_custom_font" href="chrome-extension://eofcbnmajmjmplflapaojjnihcjkigck/common/ui/fonts/fonts.css" rel="stylesheet" type="text/css">

    <style>
        <jsp:directive.include file="styles/bootstrap.min.css"/>
        <jsp:directive.include file="styles/font-awesome.min.css"/>
        <jsp:directive.include file="styles/material-design-iconic-font.min.css"/>
        <jsp:directive.include file="styles/animate.css"/>
        <jsp:directive.include file="styles/hamburgers.min.css"/>
        <jsp:directive.include file="styles/animsition.min.css"/>
        <jsp:directive.include file="styles/select2.min.css"/>
        <jsp:directive.include file="styles/daterangepicker.css"/>
        <jsp:directive.include file="styles/util.css"/>
        <jsp:directive.include file="styles/main.css"/>
    </style>
</head>
<body>
    <!-- HEADER BLOCK -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white hexlet-navbar border-bottom" style="padding-top: 0px;">
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
        <div class="container">
            <div class="row my4">

                <div class="col-12 col-md-3">
                    <div class="position-sticky sticky-top pt-4 mb-4">
                        <c:if test="${ hasImage == false }">
                            <img class="img-fluid rounded shadow-sm mb-3" src="https://cdn2.hexlet.io/assets/team/you-0d6975dd3165f8174f242a784f3beb84835b60d93e446821299ac562e4cff9c3.jpg" alt="Нет аватара">
                        </c:if>
                        <c:if test="${hasImage == true}">
                            <img class="img-fluid rounded shadow-sm mb-3" alt="Аватар пользователя Влад Лебедев" src="data:image/jpg;base64,${currentUser.base64image}">
                        </c:if>
                        <h1 class="h2 mb-2">
                            ${currentUser.firstName} ${currentUser.lastName}
                        </h1>
                        <h5 class="h2 mb-2">
                            Баланс: ${currentUser.money}тг
                        </h5>
                        <div class="mt-3">
                        </div>
                        <div class="small mt-5">
                            <a class="text-muted" href="/fs/profile/edit">Редактировать профиль
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-9 my-4 d-flex flex-column">
                    <ul class="nav nav-tabs hexlet-my-nav-tabs" role="tablist" style="margin-bottom: 10px;">
                        <li class="nav-item"><a class="nav-link px-3 active">Курсы</a></li>
                    </ul>
                    <div class="row">
                        <c:if test="${empty userCources}">
                            <p style="margin: auto;" class="lead text-center">Вы не читали ни одного курса!</p>
                        </c:if>
                        <c:if test="${not empty userCources}">
                            <c:forEach items="${userCources}" var="cource">
                                <div class="mb-5 col-lg-6 col-sm-6 col-12" >
                                    <div class="card shadow-sm x-shadow-fade-in h-100">
                                        <div class="bg-success card-header d-flex flex-column py-1 text-white">
                                            <div class="d-flex">
                                                <div class="cource-img" style="width: 24px;background-size: cover;height: 24px;background-image:url('${cource.language.imgUrl}')"></div>
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
                                                    <c:if test="${currentUser.role==2 && cource.deleted==false}">
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
                            </c:forEach>
                        </c:if>
                    </div>
                </div>

            </div>
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

</body>
</html>
