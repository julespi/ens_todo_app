<template>
  <b-container id="app">
  <b-row >
    <b-col><TodoView/></b-col>
  </b-row>
</b-container>
</template>

<script>
import TodoView from './components/TodoView'

export default {
  name: 'App',
  components: {
    TodoView
  }
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
