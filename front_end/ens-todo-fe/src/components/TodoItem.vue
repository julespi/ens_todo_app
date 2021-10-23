<template>
<tr>
    <td>
      <b-form-checkbox v-model="enabled" name="check-button" switch  @change="updateComplete">
        <span class="p-2">
        {{name}}
        </span>
        </b-form-checkbox>
    </td>
    <td>
      <b-button v-b-modal="''+itemId">Editar</b-button>
      <b-modal v-bind:id="''+itemId" v-bind:title="'Todo' + itemId">
        <p class="my-4">
          <b-form @submit="onSubmitEdit">
          <b-form-group id="edit-todo" label="Editar Todo" label-for="edit-todo">
            <b-form-input
              id="input-2"
              v-model="form.name"
              required
            ></b-form-input>
          </b-form-group>
          <br>
          <b-button type="submit" variant="primary">Editar</b-button>
        </b-form>
        </p>
      </b-modal>
    </td>
    <td>
      <b-button variant="danger" v-on:click="remove">Eliminar</b-button>
    </td>
</tr>
</template>

<script>
// PROVIDERS
import TodoService from '@/services/todo.services.js'
const todoService = new TodoService()

export default {
  name: 'TodoItem',
  components: {},
  props: {
    itemId: Number,
    name: String,
    completed: Boolean
  },
  data () {
    return {
      form: {
        id: this.itemId,
        name: this.name,
        completed: this.completed

      },
      enabled: this.completed
    }
  },
  methods: {
    updateComplete (checked) {
      this.form.completed = checked
      todoService
        .editTodo(this.form)
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {
          console.log(error)
        })
    },
    onSubmitEdit (event) {
      event.preventDefault()
      todoService
        .editTodo(this.form)
        .then((response) => {
          console.log(response)
          this.name = this.form.name
        })
        .catch((error) => {
          console.log(error)
        })
    },
    remove () {
      this.$destroy()
      todoService
        .removeTodo(this.itemId)
        .then((response) => {
          console.log(response)
        })
        .catch((error) => {
          console.log(error)
        })
      this.$el.parentNode.removeChild(this.$el)
    }
  }
}
</script>
