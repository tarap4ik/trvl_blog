<template>
    <div>
        <i>({{ article.id }})</i> {{ article.text }}
        <span style="position: absolute; right: 0">
            <input type="button" value="Edit" @click="edit" />
            <input type="button" value="X" @click="del" />
        </span>
    </div>
</template>

<script>
    export default {
        props: ['article', 'articles', 'editArt', 'deleteArt'],
        methods: {
            edit() {
                this.editArt(this.article)
            },
            del() {
                this.deleteArt(this.article)
            }
        }
    }
</script>

<style>
</style>