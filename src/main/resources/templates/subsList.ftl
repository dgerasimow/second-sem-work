<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">
<#--<head>-->
<#--    <script>-->
<#--        $("create-post-form").submit(function (e) {-->
<#--            e.preventDefault();-->

<#--            var form = $(this);-->
<#--            var url = form.attr('action')-->

<#--            $.ajax({-->
<#--                type: "POST",-->
<#--                url: url,-->
<#--                data: form.serialize(),-->
<#--                success: function(data)-->
<#--                {-->
<#--                    alert(data);-->
<#--                }-->
<#--            });-->
<#--        });-->
<#--    </script>-->
<#--</head>-->
<#macro title>My profile</#macro>
<body>
<#macro content>
<div class="container">

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
                            <img src="http://placehold.it/300x300" alt="" class="img-responsive profile-photo" />
                            <h3>${user.firstName} ${user.secondName}</h3>
                            <p class="text-muted">Хуйлан че</p>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <ul class="list-inline profile-menu">
                            <li><a href="/profile?id=${user.id}">Мои посты</a></li>
                            <li><a href="/subs" class="active">Мои подписки</a></li>
                        </ul>
                        <ul class="follow-me list-inline">
                        </ul>
                    </div>
                </div>
            </div><!--Timeline Menu for Large Screens End-->

        </div>
        <div id="page-contents">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-7">
                    <div class="friend-list">
                        <div class="row">
                            <#if subs??>
                                <#list subs as s>
                                    <div class="col-md-6 col-sm-6">
                                        <div class="friend-card">
                                            <img src="http://placehold.it/1030x360" alt="profile-cover" class="img-responsive cover" />
                                            <div class="card-info">
                                                <img src="http://placehold.it/300x300" alt="user" class="profile-photo-lg" />
                                                <div class="friend-info">
                                                    <h5><a href="/profile?id=${s.id}" class="profile-link">${s.firstName} ${s.secondName}</a></h5>
                                                    <p>твоя подписка</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                            </#if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </#if>

        </#macro>
</body>
</html>
