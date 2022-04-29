<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">
<#macro title>Посты</#macro>
<body>

<#macro content>
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
<div id="page-contents">
    <div class="container">
        <div class="row">
            <#if user??>
            <!-- Newsfeed Common Side Bar Left
            ================================================= -->
                <br>
                <br>
                <br>
            <div class="col-md-3 static">
                <div class="profile-card">
                    <img src="/images/bogdanov-s-telefonom.jpg" alt="user" class="profile-photo" />
                    <h5><a href="/profile" class="text-white">${user.firstName} ${user.lastName}</a></h5>
                    <a href="/subs" class="text-white"><i class="ion ion-android-person-add"></i> подписчики</a>
                </div><!--profile card ends-->
            </div>
            </#if>
            <div class="col-md-7">
                <!-- Post Content
                ================================================= -->
                <#if posts??>
                <#list posts as p>
                <div class="post-content">
                    <img src="/images/img.png" alt="post-image" width="150" height="150" class="img-responsive post-image" />
                    <div class="post-container">
                        <img src="/images/bogdanov-s-telefonom.jpg" alt="user" class="profile-photo-md pull-left" />

                                    <div class="post-detail">
                                    <div class="user-info">
                                        <h5><a href="/profile/${p.userId}">${p.firstName}</a></h5>
                                    <p class="text-muted">Опубликовал в ${p.date}</p>
                                </div>
                                        <div class="reaction" id="reaction${p.id}">
                                            <#assign amount = p.likes?size>
                                            <a class="btn text-green" onclick="like(${user.id}, ${p.id}, ${amount})"><i class="icon ion-thumbsup"></i><div id="amountOfLikes${p.id}">${amount}</div></a>
                                        </div>
                                    <div class="line-divider"></div>
                                    <div class="post-text">
                                        <p>${p.text}</p>
                                    </div>
                                    <div class="line-divider"></div>
                                    </div>

                        <#list p.comments as comment>
                            <div class="post-comment">
                                <img src="/images/bogdanov-s-telefonom.jpg" alt="" class="profile-photo-sm" />
                                <p><a href="/profile/${comment.userId}" class="profile-link">${comment.userFirstName} </a>${comment.text}</p>
                            </div>
                            </#list>
                        <div id="last-divider"></div>
                        <form method="POST" id="add-new-comment">
                            <div class="post-comment">
                                <input type="hidden" id="post-id" value="${p.id}">
                                <input type="hidden" id="user-id" value="${user.id}">
                                <input type="text" id="comment-text" name="comment-text" class="form-control" placeholder="Post a comment">
                                <input type="submit" class="btn btn-primary pull-right" value="Написать">
                            </div>
                        </form>
                        </div>
                    </div>
            </#list>
            </#if>
            </div>
        </div>
    </div>
</div>
</#macro>
</body>
</html>
