<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored = "false" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>HIdeas.kz</title>

    <style>
        <jsp:directive.include file="styles/main.css"/>
        <jsp:directive.include file="styles/bootstrap.min.css"/>
    </style>

</head>
<body>
    <header>
        <div class="navbar navbar-dark bg-dark shadow-sm">
            <div class="container d-flex justify-content-between">
                <div class="row" style="width: 100%;">
                    <div class="col-sm-3">
                        <a href="#" class="navbar-brand d-flex align-items-center">
                            <strong>HIdeas</strong>
                        </a>
                    </div>
                    <div class="col-sm-9" >
                        <span class="page-name">Идеи</span>
                        <div class="head_search">
                            <form action="/fs/ideas" method="get" >
                                <input type="text" class="search" name="title" value="" autocomplete="off" id="lm_search_field" placeholder="Поиск" >
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <main>
        <div class="container">
            <div class="row">
                <div class="col-3">
                    <div class="navigation-button-profile">
                        <div class="img-block">
                            <img src="https://m.vk.com/images/camera_100.png?ava=1" class="navigation-profile-image">
                        </div>
                        <div class="profile-name">
                            <span class="navigation-text">${currentUser.name}</span>
                        </div>
                    </div>
                    <div class="navigation-button active-button">
                        <img src="https://image.flaticon.com/icons/svg/427/427735.svg" class="navigation-img">
                        <span class="navigation-text">Идеи</span>
                        <a href="/fs/ideas" class="absolute-link"></a>
                    </div>
                    <div class="navigation-button">
                        <img src="https://image.flaticon.com/icons/svg/1250/1250615.svg" class="navigation-img">
                        <span class="navigation-text">Создать идею</span>
                        <a href="/fs/create-idea" class="absolute-link"></a>
                    </div>
                    <div class="navigation-button">
                        <img src="https://image.flaticon.com/icons/svg/2089/2089827.svg" class="navigation-img">
                        <span class="navigation-text">Мои идеи</span>
                        <a href="/fs/my-ideas" class="absolute-link"></a>
                    </div>
                </div>
                <div class="col-9" style="height: 1000px;background-color: white;">

                </div>
            </div>
        </div>
    </main>

</body>
</html>