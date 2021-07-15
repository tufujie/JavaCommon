<template xmlns:v-gouzi="http://www.w3.org/1999/xhtml">
    <main-layout>
        <h1>Vue.js 组件</h1>
        <div>
            <input v-model="parentMsg">
            <br>
            <child v-bind:message="parentMsg"></child>
        </div>
        <ol>
            <site-item v-for="item in sites" v-bind:siteItem="item"></site-item>
        </ol>
        <validateComponent v-bind:validateNum="123"></validateComponent>
        <h2>自定义事件组件</h2>
        <p>{{ total }}</p>
        <!-- 每次触发，本身+1，total加1，所以total是全部之和 -->
        <button-counter v-on:increment="incrementTotal"></button-counter>
        <button-counter v-on:increment="incrementTotal"></button-counter>
        <button-counter2></button-counter2>
        <button-counter2></button-counter2>
        <h1>Vue.js 自定义指令</h1>
        <p v-gouzi:hello.a.b="message">
        </p>
        <p v-gouziV2="{color: 'blue', text: 'Hello Jef'}">
        </p>
        <br/>
        <helloCompnent message="Hello Vue!"></helloCompnent>
        <authorComponent></authorComponent>

    </main-layout>
</template>

<script>
    import mainLayout from '../layouts/main.vue'
    import baseData from '../js/baseData';
    import Vue from 'vue';
    let authorComponent = {template: ' <a> author by Jef </a>' };
    export default {
        data() {
            return baseData;
        },
        methods: {
            incrementTotal: function () {
                this.total += 1
            }
        },
        //  组件形式为：'authorComponent': authorComponent，如果不修改名称的前提下可以直接用authorComponent
        components: {
            mainLayout, authorComponent
        }
    }
    // 注册
    Vue.component('helloCompnent', {
        // 声明 props
        props: ['message'],
        // 同样也可以在 vm 实例中像 “this.message” 这样使用
        template: '<span>{{ message }}</span>'
    });
    Vue.component('child', {
        // 声明 props
        props: ['message'],
        // 同样也可以在 vm 实例中像 “this.message” 这样使用
        template: '<span>{{ message }}</span>'
    });
    Vue.component('site-item', {
        props: ['siteItem'],
        template: '<li>{{ siteItem.name }}</li>'
    });
    Vue.component('validateComponent', {
        props: {
            validateNum: {
                type: Number,
                default: 100
            }
        },
        template: '<input type="text" name="validateText" v-bind:validateNum="123">'
    });
    // 自定义事件组件，用于计数
    Vue.component('button-counter', {
        template: '<button v-on:click="incrementHandler">{{ counter }}</button>',
        data: function () {
            return {
                // data 选项不是一个对象，不会影响到其他实例
                counter: 0
            }
        },
        methods: {
            incrementHandler: function () {
                this.counter += 1;
                this.$emit('increment')
            }
        },
    });
    var buttonCounter2Data = {
        count: 0
    };
    Vue.component('button-counter2', {
        data: function () {
            // data 选项是一个对象，会影响到其他实例
            return buttonCounter2Data;
        },
        template: '<button v-on:click="count++">点击了 {{ count }} 次。</button>'
    });
    // 注册一个全局自定义指令 v-focus
    Vue.directive('focus', {
        // 当绑定元素插入到 DOM 中。
        inserted: function (el) {
            // 聚焦元素
            el.focus()
        }
    });
    // 注册局部指令
    /* new Vue({
       el: '#app',
       directives: {
         // 注册一个局部的自定义指令 v-focus
         focus: {
           // 指令的定义
           inserted: function (el) {
             // 聚焦元素
             el.focus()
           }
         }
       }
     });*/
    Vue.directive('gouzi', {
        bind: function (el, binding, vnode) {
            var s = JSON.stringify
            el.innerHTML =
                'name: '       + s(binding.name) + '<br>' +
                'expression: ' + s(binding.expression) + '<br>' +
                'value: '      + s(binding.value) + '<br>' +
                'argument: '   + s(binding.arg) + '<br>' +
                'modifiers: '  + s(binding.modifiers) + '<br>' +
                'vnode keys: ' + Object.keys(vnode).join(', ');
            el.style.backgroundColor = binding.value.color;
        }
    });
    Vue.directive('gouziV2', {
        bind: function (el, binding) {
            // 简写方式设置文本及背景颜色
            el.innerHTML = binding.value.text;
            el.style.backgroundColor = binding.value.color;
        }
    });
</script>
