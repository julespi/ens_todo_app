// https://github.com/axios/axios
// https://es.vuejs.org/v2/cookbook/using-axios-to-consume-apis.html
// https://www.techiediaries.com/vue-axios-tutorial/
import axios from 'axios'

const axiosInstance = axios.create({
  headers: {
    'Access-Control-Allow-Origin': '*'
  }
})

const URL_TODOS = 'http://localhost:8080/api/todo'

class TodoService {
  async getTodos () {
    return axiosInstance.get(`${URL_TODOS}`).then((response) => response)
  }

  async removeTodo (id) {
    return axios.delete(`${URL_TODOS}/${id}`).then((response) => response)
  }

  async newTodo (todo) {
    return axios.post(
      `${URL_TODOS}`,
      todo,
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    ).then(response => {
      return response
    }).catch(error => {
      return Promise.reject(error.response)
    })
  }

  async editTodo (todo) {
    return axios.put(
      `${URL_TODOS}/${todo.id}`,
      todo,
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    ).then(response => {
      return response
    }).catch(error => {
      return Promise.reject(error.response)
    })
  }
}

export default TodoService
