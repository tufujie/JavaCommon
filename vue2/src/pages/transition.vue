<template>
    <main-layout>
        <h1>Vue.js 过渡 & 动画</h1>
        <button v-on:click = "showV1 = !showV1">点我V1</button>
        <transition name = "fade">
            <p v-show = "showV1" v-bind:style = "styleobj">动画实例</p>
        </transition>
        <button v-on:click = "showV2 = !showV2">点我V2</button>
        <transition name="slide-fade">
            <p v-if="showV2">{{site}}</p>
        </transition>
        <button v-on:click = "showV3 = !showV3">点我V3</button>
        <transition name="bounce">
            <p v-if="showV3">{{site}}</p>
        </transition>
        <button v-on:click = "showV4 = !showV4">点我V4</button>
        <transition
                name="custom-classes-transition"
                enter-active-class="animated tada"
                leave-active-class="animated bounceOutRight"
        >
            <p v-if="showV4">{{site}}</p>
        </transition>
        <button v-on:click = "showV5 = !showV5">点我V5</button>
        <transition
                v-on:before-enter="beforeEnter"
                v-on:enter="enter"
                v-on:leave="leave"
                v-bind:css="false"
        >
            <p v-if="showV5">{{site}}</p>
        </transition>
    </main-layout>
</template>

<script>
    import mainLayout from '../layouts/main.vue'
    import baseData from '../js/baseData';
    import animate from '../css/animate.css@3.5.1.css';
    import velocity from '../js/velocity.min';
    export default {
        data() {
            baseData.showV5 = true;
            return baseData;
        },
        methods: {
            beforeEnter: function (el) {
                el.style.opacity = 0;
                el.style.transformOrigin = 'left'
            },
            enter: function (el, done) {
                Velocity(el, { opacity: 1, fontSize: '1.4em' }, { duration: 300 });
                Velocity(el, { fontSize: '1em' }, { complete: done })
            },
            leave: function (el, done) {
                Velocity(el, { translateX: '15px', rotateZ: '50deg' }, { duration: 600 });
                Velocity(el, { rotateZ: '100deg' }, { loop: 2 });
                Velocity(el, {
                    rotateZ: '45deg',
                    translateY: '30px',
                    translateX: '30px',
                    opacity: 0
                }, { complete: done })
            }
        },
        components: {
            mainLayout
        }
    }
</script>