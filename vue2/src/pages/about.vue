<template>
    <main-layout>
        <h1>About page</h1>
    </main-layout>
</template>

<script>
    import mainLayout from '../layouts/main.vue'
    import baseData from '../js/baseData';
    export default {
        data() {
            return baseData;
        },
        components: {
            mainLayout
        }
    }
</script>
