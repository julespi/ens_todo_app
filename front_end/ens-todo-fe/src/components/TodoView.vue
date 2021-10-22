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
              required
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
            <!-- REPETIR CON INFO DB -->
            <TodoItem v-for="todo in todos" :key="todo.id" v-bind:itemId="todo.id" v-bind:name="todo.name"/>
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
      console.log('we didd it')
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
