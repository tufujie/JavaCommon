<template>
    <main-layout>
        <h1>Vue.js 表单</h1>
        <!-- 插值修饰符，修饰符是以半角句号 . 指明的特殊后缀，用于指出一个指令应该以特殊方式绑定。
        例如，.prevent 修饰符告诉 v-on 指令对于触发的事件调用 event.preventDefault()： -->
        <form v-on:submit.prevent="onSubmit">
            <input type="text" name="userName" placeholder="用户名" v-model="userName" />
            <input type="text" name="password" placeholder="密码" v-model="password" />
            <button type="submit">登录</button>
        </form>
        <!-- 多行文本输入 -->
        <textarea v-model="message2" placeholder="多行文本输入……"></textarea>
        <!-- 用户输入，在 input 输入框中我们可以使用 v-model 指令来实现双向数据绑定，
        修改了v-model="userName"的值后，this.userName会进行修改，从而实现了双向绑定-->
        <!-- v-model 指令用来在 input、select、textarea、checkbox、radio 等表单控件元素上创建双向数据绑定，
        根据表单上的值，自动更新绑定的元素的值。按钮的事件我们可以使用 v-on 监听事件，并对用户的输入进行响应。
        以下实例在用户点击按钮后对字符串进行反转操作：-->
        <!-- 复选框，复选框如果是一个为逻辑值，如果是多个则绑定到同一个数组： -->
        <p>单个复选框：</p>
        <input type="checkbox" id="checkbox" v-model="checked">
        <label for="checkbox">{{ checked }}</label>

        <p>多个复选框：</p>
        <input type="checkbox" id="baidu_checkbox" value="Baidu" v-model="checkedNames">
        <label for="baidu_checkbox">Baidu</label>
        <input type="checkbox" id="alibaba_checkbox" value="Alibaba" v-model="checkedNames">
        <label for="alibaba_checkbox">Alibaba</label>
        <input type="checkbox" id="tencent_checkbox" value="Tencent" v-model="checkedNames">
        <label for="tencent_checkbox">Tencent</label>
        <br/>
        <span>选择的值为: {{ checkedNames }}</span><br/>
        <input type="radio" id="baidu" value="Baidu" v-model="picked">
        <label for="baidu">Baidu</label>
        <br/>
        <input type="radio" id="tencent" value="Tencent" v-model="picked">
        <label for="tencent">Tencent</label>
        <br/>
        <input type="radio" id="alibaba" value="Alibaba" v-model="picked">
        <label for="alibaba">Alibaba</label>
        <br/>
        <span>选中值为: {{ picked }}</span>
        <br/>
        <select v-model="selected" name="fruit">
            <option value="">选择一个网站</option>
            <option value="www.baidu.com">Baidu</option>
            <option value="www.alibaba.com">Alibaba</option>
            <option value="www.qq.com">Tencent</option>
        </select>
        <div id="output">
            选择的网站是: {{selected}}
        </div>
    </main-layout>
</template>

<script>
    import mainLayout from '../layouts/main.vue';
    import baseData from '../js/baseData';
    export default {
        data() {
            return baseData;
        },
        methods: {
            onSubmit() {
                // this.userName = $.trim($('input[name="userName"]').val());
                // 提交触发事件
                if (this.userName == 'Jef') {
                    alert(this.userName + '登录成功');
                } else {
                    alert(this.userName + '登录失败');
                }
            },
        },
        components: {
            mainLayout
        }
    }
</script>
