<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">
<#macro title>My profile</#macro>
<body>
<#macro content>
<div class="container">

    <script>
        function like(userId, postId, likes) {
            let request = new XMLHttpRequest()

            request.open("POST", "/like")

            request.setRequestHeader("Content-Type", "application/json")

            let body = {
                "userId":userId,
                "postId":postId
            }

            request.send(JSON.stringify(body))

            document.getElementById("reaction" + postId).innerHTML = "<a class='btn text-green' onclick='dislike(" + userId + "," +
                " " + postId + "," + (likes + 1) + ")'>" +
                "<i class='icon ion-thumbsup'></i><div id='amountOfLikes" + postId + "'>" + likes + "</div></a>"
            document.getElementById("amountOfLikes" + postId).innerHTML = likes + 1
        }

        function dislike(userId, postId, likes) {
            let request = new XMLHttpRequest()

            request.open("POST", "/dislike")

            request.setRequestHeader("Content-Type", "application/json")

            let body = {
                "userId":userId,
                "postId":postId
            }

            request.send(JSON.stringify(body))

            document.getElementById("reaction" + postId).innerHTML = "<a class='btn text-green' onclick='like(" + userId + "," +
                " " + postId + "," + (likes - 1) + ")'>" +
                "<i class='icon ion-thumbsup'></i><div id='amountOfLikes" + postId + "'>" + likes + "</div></a>"
            document.getElementById("amountOfLikes" + postId).innerText = (parseInt(likes) - 1).toString()
        }
    </script>

    <!-- Timeline
    ================================================= -->
    <#if user??>
    <div class="timeline">
        <div class="timeline-cover">

            <!--Timeline Menu for Large Screens-->
            <div class="timeline-nav-bar hidden-sm hidden-xs">
                <div class="row">
                    <div class="col-md-3">
                        <div class="profile-info">
                            <img src="${user.avatarUrl}" alt="" class="img-responsive profile-photo" />
                            <h3>${user.firstName} ${user.lastName}</h3>
                            <p class="text-muted">Пользователь</p>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <ul class="list-inline profile-menu">
                            <li><a href="/profile/${user.id}" class="active">Мои посты</a></li>
                            <li><a href="/subscriptions">Мои подписки</a></li>
                        </ul>
                        <ul class="follow-me list-inline">
                            <li>${amountOfSubs} подписчиков</li>
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
                    <div class="create-post">
                        <div class="row">
                            <form action="/posts" method="POST" id="create-post-form" name="newPost_form">
                                <div class="col-md-7 col-sm-7">
                                    <div class="form-group">
                                        <img src="${user.avatarUrl}" alt="" class="profile-photo-md" />
                                        <input name="userId" id="userId" type="hidden" value="${user.id}">
                                        <textarea name="post-textarea" id="post-textarea" cols="30" rows="1" class="form-control" placeholder="Write what you wish" required form="create-post-form"></textarea>
                                    </div>
                                </div>
                                <div class="col-md-5 col-sm-5">
                                    <div class="tools">
                                        <input type="submit" class="btn btn-primary pull-right" id="create-post-button" form="create-post-form" value="Опубликовать"/>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- Post Create Box End-->


                    <div id="post"></div>
                    <#if posts?has_content>
                        <#list posts as p>
                            <div class="post-content">
                                <div class="post-container">
                                    <img src="${p.avatarUrl}" alt="user" class="profile-photo-md pull-left" />
                                    <div class="post-detail">
                                        <div class="user-info">
                                            <h5><a href="/profile/${p.userId}" class="profile-link">${user.firstName} ${user.lastName}</a> <span class="following">following</span></h5>
                                            <p class="text-muted">${p.date}</p>
                                        </div>
                                        <div class="reaction" id="reaction${p.id}">
                                            <#assign amount = p.likes?size>
                                            <#if p.isLiked>
                                                <a class="btn text-green" onclick="dislike(${user.id}, ${p.id}, ${amount})"><i class="icon ion-thumbsup"></i><div id="amountOfLikes${p.id}">${amount}</div></a>
                                            <#else>
                                                <a class="btn text-green" onclick="like(${user.id}, ${p.id}, ${amount})"><i class="icon ion-thumbsup"></i><div id="amountOfLikes${p.id}">${amount}</div></a>
                                            </#if>
                                        </div>
                                        <div class="line-divider"></div>
                                        <div class="post-text">
                                            <p>${p.text}</p>
                                        </div>
                                        <div class="line-divider"></div>
                                        <#list p.comments as comment>
                                            <div class="post-comment">
                                                <img src="${comment.avatarUrl}" alt="" class="profile-photo-sm" />
                                                <p><a href="/profile/${comment.userId}" class="profile-link">${comment.userFirstName} </a>${comment.text}</p>
                                            </div>
                                            <div class="line-divider"></div>
                                        </#list>
                                        <div id="last-divider"></div>
                                        <form method="POST" id="add-new-comment">
                                            <div class="post-comment">
                                                <img src="${user.avatarUrl}" alt="" class="profile-photo-sm" />
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
