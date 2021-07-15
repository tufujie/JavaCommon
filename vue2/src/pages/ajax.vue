<template>
    <main-layout>
        <h1>{{ info }}</h1>
    </main-layout>
</template>

<script>
    import mainLayout from '../layouts/main.vue'
    import baseData from '../js/baseData';
    import axios from 'axios';
    export default {
        data() {
            baseData.info = '';
            return baseData;
        },
        mounted () {
            axios
                .post('https://www.runoob.com/try/ajax/json_demo.json')
                .then(response => (this.info = response))
                .catch(function (error) {
                    // 请求失败处理
                    console.log(error);
                });
        },
        components: {
            mainLayout
        }
    }
</script>
