<template>
    <div style="position: relative; width: 300px;">
        <article-form :articles="articles" :articleAttribute="article"/>
        <article-row v-for="article in articles"
                     :key="article.id"
                     :article="article"
                     :articles="articles"
                     :editArt="editArt"
                     :deleteArt="deleteArt"></article-row>
    </div>
</template>

<script>
    import ArticleRow from 'components/articles/ArticleRow.vue'
    import ArticleForm from 'components/articles/ArticleForm.vue'
    const axios = require('axios').default;
    export default {
        props: ['articles'],
        components: {
            ArticleRow,
            ArticleForm
        },
        data() {
            return {
                article: null
            }
        },
        methods: {
            editArt(article) {
                this.article = article;
            },
            deleteArt(article) {
                axios.delete('/articles/'+article.id).then(result => {
                    if (result.status) {
                        this.articles.splice(this.articles.indexOf(article), 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>