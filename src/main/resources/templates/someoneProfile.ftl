<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">
<#macro title>My profile</#macro>
<body>
<#macro content>
<div class="container">
    <script>
        function subscribe (userId, currentUserId) {
            let request = new XMLHttpRequest()

            request.open("POST","/subscription")

            let body = {
                "userItself":userId,
                "userToSubscribe":currentUserId
            }
            request.setRequestHeader('Content-Type', 'application/json')
            request.send(JSON.stringify(body))

            document.getElementById("sub box").innerHTML = "<input type='hidden' name='userId' id='userId' value=${profileUser.id}>" +
            "<input type='hidden' name='currentUserId' id='currentUserId' value=${user.id}>" +
                "<button type='submit' class='btn-primary'  onclick='unsubscribe(${user.id}, ${profileUser.id})'>" +
                "отписаться</button>"

            document.getElementById("amountOfSubs").innerHTML =
                (parseInt(document.getElementById("amountOfSubs").innerHTML.valueOf())) + 1
        }
    </script>
    <script>
        function unsubscribe (userId, currentUserId) {
            let request = new XMLHttpRequest()

            request.open("POST","/subscription/unsubscribe")

            let body = {
                "userItself":userId,
                "userToSubscribe":currentUserId
            }
            request.setRequestHeader('Content-Type', 'application/json')
            request.send(JSON.stringify(body))

            document.getElementById("sub box").innerHTML = "<input type='hidden' name='userId' id='userId' value=${profileUser.id}>" +
                "<input type='hidden' name='currentUserId' id='currentUserId' value=${user.id}>" +
                "<button type='submit' class='btn-primary'  onclick='subscribe(${user.id}, ${profileUser.id})'>" +
                "подписаться</button>"

            document.getElementById("amountOfSubs").innerHTML =
                (parseInt(document.getElementById("amountOfSubs").innerHTML.valueOf())) - 1
        }
    </script>
    <!-- Timeline
    ================================================= -->
    <#if profileUser??>
    <div class="timeline">
        <div class="timeline-cover">

            <!--Timeline Menu for Large Screens-->
            <div class="timeline-nav-bar hidden-sm hidden-xs">
                <div class="row">
                    <div class="col-md-3">
                        <div class="profile-info">
                            <img src="/images/bogdanov-s-telefonom.jpg" alt="" class="img-responsive profile-photo" />
                            <h3>${profileUser.firstName} ${profileUser.lastName}</h3>
                            <p class="text-muted">Хуйлан че</p>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <ul class="list-inline profile-menu">
                            <#if userSubscribedToCurrentUser??>
                                <li>Подписан</li>
                            <#else>
                                    <li>Не подписан</li>
                            </#if>
                        </ul>
                        <ul class="follow-me list-inline">
                            <li id="amountOfSubs">${amountOfSubs}</li>
                            <li>подписчиков</li>
                            <#if subscribed??>
                                <li id="sub box">
                                    <input type="hidden" name="userId" id="userId" value=${profileUser.id}>
                                    <input type="hidden" name="currentUserId" id="currentUserId" value=${user.id}>
                                    <button type="submit" class="btn-primary"  onclick="unsubscribe(${user.id}, ${profileUser.id})">
                                        Отписаться</button>
                                </li>
                                <#else>
                                <li id="sub box">
                                    <input type="hidden" name="userId" id="userId" value=${profileUser.id}>
                                    <input type="hidden" name="currentUserId" id="currentUserId" value=${user.id}>
                                    <button type="submit" class="btn-primary"  onclick="subscribe(${user.id}, ${profileUser.id})">
                                        Подписаться</button>
                                </li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div><!--Timeline Menu for Large Screens End-->


        </div>
        <div id="page-contents">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-7">

                    <!-- Post Create Box
                    ================================================= -->
<#--                    <div class="create-post">-->
<#--                        <div class="row">-->
<#--                            <form action="/posts" method="POST" id="create-post-form" name="newPost_form">-->
<#--                                <div class="col-md-7 col-sm-7">-->
<#--                                    <div class="form-group">-->
<#--                                        <img src="/images/bogdanov-s-telefonom.jpg" alt="" class="profile-photo-md" />-->
<#--                                        <input name="userId" id="userId" type="hidden" value="${user.id}">-->
<#--                                        <textarea name="post-textarea" id="post-textarea" cols="30" rows="1" class="form-control" placeholder="Write what you wish" required form="create-post-form"></textarea>-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                                <div class="col-md-5 col-sm-5">-->
<#--                                    <div class="tools">-->
<#--                                        <input type="submit" class="btn btn-primary pull-right" id="create-post-button" form="create-post-form" value="Опубликовать"/>-->
<#--                                    </div>-->
<#--                                </div>-->
<#--                            </form>-->
<#--                        </div>-->
<#--                    </div>-->
                    <!-- Post Create Box End-->

                    <div id="post"></div>
                    <#if posts??>
                        <#list posts as p>
                            <div class="post-content">
                                <img src="/images/img.png" alt="post-image" class="img-responsive post-image" />
                                <div class="post-container">
                                    <img src="/images/bogdanov-s-telefonom.jpg" alt="user" class="profile-photo-md pull-left" />
                                    <div class="post-detail">
                                        <div class="user-info">
                                            <h5><a href="/profile/${p.userId}" class="profile-link">${p.firstName} ${p.lastName}</a> <span class="following">following</span></h5>
                                            <p class="text-muted">${p.date}</p>
                                        </div>
                                        <div class="reaction">
                                            <a class="btn text-green"><i class="icon ion-thumbsup"></i> 13</a>
                                            <a class="btn text-red"><i class="fa fa-thumbs-down"></i> 0</a>
                                        </div>
                                        <div class="line-divider"></div>
                                        <div class="post-text">
                                            <p>${p.text}</p>
                                        </div>
                                        <div class="line-divider"></div>
                                        <#list p.comments as comment>
                                            <div class="post-comment">
                                                <img src="/images/bogdanov-s-telefonom.jpg" alt="" class="profile-photo-sm" />
                                                <p><a href="/profile/${comment.userId}" class="profile-link">${comment.userFirstName} </a>${comment.text}</p>
                                            </div>
                                            <div class="line-divider"></div>
                                        </#list>
                                        <div id="last-divider"></div>
                                        <form method="POST" id="add-new-comment">
                                            <div class="post-comment">
                                                <img src="/images/bogdanov-s-telefonom.jpg" alt="" class="profile-photo-sm" />
                                                <input type="hidden" id="post-id" value="${p.id}">
                                                <input type="hidden" id="user-id" value="${user.id}">
                                                <input type="text" id="comment-text" name="comment-text" class="form-control" placeholder="Post a comment">
                                                <input type="submit" class="btn btn-primary pull-right" value="Написать">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </#list>
                    </#if>

                </div>
            </div>
        </div>
        </#if>
    </div>
    </#macro>
</div>
</body>

</html>
