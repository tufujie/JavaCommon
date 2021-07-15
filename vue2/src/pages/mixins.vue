<template>
    <main-layout>
        <h1>Vue.js 混入</h1>
    </main-layout>
</template>

<script>
    import mainLayout from '../layouts/main.vue'
    import baseData from '../js/baseData';
    import Vue from 'vue';
    export default {
        data() {
            return baseData;
        },
        components: {
            mainLayout
        }
    }
    // 定义一个混入对象
    let myMixinV1 = {
        created: function () {
            this.startmixin()
        },
        methods: {
            startmixin: function () {
                alert("欢迎使用混入");
            }
        }
    };
    let Component = Vue.extend({
        mixins: [myMixinV1]
    });
    let component = new Component();
    let myMixinV2 = {
        created: function () {
            alert('混入调用');
        }
    };
    new Vue({
        mixins: [myMixinV2],
        created: function () {
            alert('组件调用');
        }
    });
    let myMixinV3 = {
        methods: {
            hellworldMethod: function () {
                alert('Helloworld 方法');
            },
            sameMethod: function () {
                alert('same 相同方法名');
            }
        }
    };
    let vm = new Vue({
        mixins: [myMixinV3],
        methods: {
            startMethod: function () {
                alert('start 方法');
            },
            sameMethod: function () {
                alert('Main 相同方法名');
            }
        }
    });
    vm.hellworldMethod();
    vm.startMethod();
    vm.sameMethod();
    Vue.mixin({
        created: function () {
            let myOption = this.$options.myOption;
            if (myOption) {
                alert(myOption);
            }
        }
    });

    new Vue({
        myOption: 'hello Vue!'
    })
</script>
