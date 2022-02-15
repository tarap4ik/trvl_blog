<template>
    <div>
        <input type="text" placeholder="Write smth" v-model="text" />
        <input type="button" value="Save" @click="save" />
    </div>
</template>

<script>
    const axios = require('axios').default;
    function getIndex(list, id) {
        for (var i = 0; i < list.length; i++ ) {
            if (list[i].id === id) {
                return i
            }
        }
        return -1
    }
    export default {
        props: ['articles', 'articleAttribute'],
        data: function () {
            return {
                text: '',
                id: ''
            }
        },
        watch: {
            articleAttribute: function (valueNew, valueOld) {
                this.text = valueNew.text;
                this.id = valueNew.id;
            }
        },
        methods: {
            save() {
                const article = { text: this.text }
                if (this.id) {
                    axios.put('/articles/'+this.id,{id: this.id, text:this.text}).then(result =>
                        {
                            const index = getIndex(this.articles, result.data.id)
                            this.articles.splice(index, 1, result.data)
                            this.text = ''
                            this.id = ''
                        }
                    )
                } else {
                    axios.post('/articles', article).then(result =>
                        {
                            this.articles.push(result.data)
                            this.text = ''
                        }
                    )
                }
            }
        }
    }
</script>

<style>
</style>