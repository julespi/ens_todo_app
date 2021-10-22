// https://github.com/axios/axios
// https://es.vuejs.org/v2/cookbook/using-axios-to-consume-apis.html
// https://www.techiediaries.com/vue-axios-tutorial/
import axios from 'axios'

const axiosInstance = axios.create({
  headers: {
    'Access-Control-Allow-Origin': '*'
  }
})

const URL_CENTER = 'http://localhost:8080/api/todo'

class TodoService {
  async getTodos () {
    return axiosInstance.get(`${URL_CENTER}`).then((response) => response)
  }

  async newTodo (todo) {
    return axios.post(
      `${URL_CENTER}`,
      todo,
      {
        headers: { 'Content-Type': 'multipart/form-data' }
      }
    ).then(response => {
      return response
    }).catch(error => {
      return Promise.reject(error.response)
    })
  }
}

export default TodoService
