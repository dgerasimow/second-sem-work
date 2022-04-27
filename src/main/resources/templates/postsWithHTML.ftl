<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">
<#macro title>Посты</#macro>
<body>

<#macro content>
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
                                    <h5>${p.firstName}</h5>
                                    <p class="text-muted">Опубликовал в ${p.date}</p>
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
