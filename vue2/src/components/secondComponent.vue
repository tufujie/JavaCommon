<template>
  <div id="secondComponent">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <h2 style="line-height: 36px; color: #20A0FF">豆瓣电影排行榜</h2>
      </div>
      <div v-for="article in articles" class="text item">
        {{article.title}}
      </div>
    </el-card>
    <h2>我是第 2 个子页面，author2 by {{author2}}</h2>
  </div>
</template>

<script type="text/javascript">
  export default {
    data () {
      return {
        author2: "Ran",
        articles: []
      }
    },
    mounted: function() {
      /*this.$http.jsonp('https://api.douban.com/v2/movie/top250?count=10', {}, {
        headers: {

        },
        emulateJSON: true
      }).then(function(response) {
        // 这里是处理正确的回调
        this.articles = response.data.subjects;
        // this.articles = response.data["subjects"] 也可以
      }, function(response) {
        // 这里是处理错误的回调
        console.log(response)
      });*/
      let subjects = [], subject = {};
      subject.title = '好好学习.mp4';
      subjects.push(subject);
      this.articles = subjects;
    }

  }
</script>

<style>
</style>
