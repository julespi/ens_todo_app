<template >
    <b-row>
      <b-col cols="1"></b-col>
      <b-col cols="4">
        <b-form @submit="onSubmit">
          <b-form-group id="new-todo" label="Agregar Todo" label-for="new-todo">
            <b-form-input
              id="input-2"
              v-model="form.name"
              placeholder="Agregar una tarea"
            ></b-form-input>
          </b-form-group>
          <br>
          <b-button type="submit" variant="primary">Agregar</b-button>
        </b-form>
      </b-col>
       <b-col cols="6">
        <b-form>
        <table class="table" v-if="true">
          <tbody>
            <TodoItem v-for="todo in todos" :key="todo.id" v-bind:itemId="todo.id" v-bind:name="todo.name" v-bind:completed="todo.completed"/>
          </tbody>
        </table>
        </b-form>
      </b-col>
    </b-row>
</template>

<script>

import TodoItem from './TodoItem'
// PROVIDERS
import TodoService from '@/services/todo.services.js'

const todoService = new TodoService()

export default {
  name: 'TodoView',
  components: {
    TodoItem
  },
  data () {
    return {
      form: {
        name: ''
      },
      todos: [],
      allSelected: false,
      indeterminate: false
    }
  },
  methods: {
    doSomething (checked) {
      console.log('we didd it')
    },
    onSubmit (event) {
      event.preventDefault()
      console.log('submitting...')
      todoService
        .newTodo(this.form)
        .then((response) => {
          console.log(response)
          this.todos.push(response.data)
        })
        .catch((error) => {
          /* var message = ''
          for (const [key, err] of Object.entries(error.data.errors)) {
            message += `${key}: ${err}`
          } */
          console.log(error)
        })
    },
    getTodos () {
      todoService.getTodos().then((data) => {
        console.log(...data.data)
        this.todos = [...data.data]
      })
    }
  },
  mounted () {
    this.getTodos()
  }
}
</script>
