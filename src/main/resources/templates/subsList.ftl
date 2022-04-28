<!DOCTYPE html>
<html lang="en">
<#include "base.ftl">
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
                            <img src="/images/bogdanov-s-telefonom.jpg" alt="" class="img-responsive profile-photo" />
                            <h3>${user.firstName} ${user.lastName}</h3>
                            <p class="text-muted">Хуйлан че</p>
                        </div>
                    </div>
                    <div class="col-md-9">
                        <ul class="list-inline profile-menu">
                            <li><a href="/profile/${user.id}">Мои посты</a></li>
                            <li><a href="/subscriptions" class="active">Мои подписки</a></li>
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
                            <#if subs?has_content>
                                <#list subs as s>
                                    <div class="col-md-6 col-sm-6">
                                        <div class="friend-card">
                                            <img src="/images/bogdanov-s-telefonom.jpg" alt="profile-cover" class="img-responsive cover" />
                                            <div class="card-info">
                                                <img src="/images/bogdanov-s-telefonom.jpg" alt="user" class="profile-photo-lg" />
                                                <div class="friend-info">
                                                    <h5><a href="/profile/${s.id}" class="profile-link">${s.firstName} ${s.lastName}</a></h5>
                                                    <p>твоя подписка</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </#list>
                                <#else>
                                    <h3>Нет подписок</h3>
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
