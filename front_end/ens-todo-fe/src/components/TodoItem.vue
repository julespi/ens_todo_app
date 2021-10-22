<template>
<tr>
    <td>
      <b-form-checkbox v-model="enabled" name="check-button" switch  @change="doSomething">
        <span class="p-2">
        {{name}}
        </span>
        </b-form-checkbox>
    </td>
    <td>
      <b-button v-b-modal="''+itemId">Editar</b-button>
      <b-modal v-bind:id="''+itemId" v-bind:title="'Todo' + itemId">
        <p class="my-4">
          <b-form @submit="onSubmit">
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
        name: this.name
      },
      enabled: this.completed
    }
  },
  methods: {
    doSomething (checked) {
      console.log('we didd it', this.itemId)
    },
    onSubmit (event) {
      event.preventDefault()
      alert(JSON.stringify(this.form))
    },
    remove () {
      this.$destroy()
      // remove the element from the DOM
      this.$el.parentNode.removeChild(this.$el)
    }
  }
}
</script>
