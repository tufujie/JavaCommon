<template>
    <main-layout>
        <span>{{ message }}</span>
        <a> author by {{author}} </a>
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

      },

      components: { mainLayout },
  };

</script>
<style>
</style>
