<template>
    <div>
        <div v-if="!profile">Необходимо авторизоваться через <a href="/oauth2/authorization/google">GOOGLE</a></div>
        <div v-else>
            <div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>
            <articles-list :articles="articles"/>
        </div>
    </div>
</template>

<script>
    import ArticlesList from 'components/articles/ArticleList.vue'

    export default {
        components: {
            ArticlesList
        },
        data() {
            return {
                articles: frontendData.articles,
                profile: frontendData.profile
            }
        }
    }
</script>

<style>
</style>