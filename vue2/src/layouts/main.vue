<template>
    <div class="container">
        <img src="../assets/logo.png">
        <ul>
          <li>
            <v-link href="/">主页</v-link>
            <v-link href="/element">元素</v-link>
            <v-link href="/form">表单</v-link>
            <v-link href="/component">组件</v-link>
            <v-link href="/transition">动画</v-link>
            <v-link href="/mixins">混入</v-link>
            <v-link href="/ajax">Ajax</v-link>
            <v-link href="/about">关于</v-link>
          </li>
        </ul>
    <slot></slot>
    </div>
</template>

<script>
    import vLink from '../components/vLink.vue';
    import baseCss from '../css/base.css';
    export default {
        data() {
            return {
            }
        },
        components: {
            vLink
        }
    }
</script>
<style>

</style>
