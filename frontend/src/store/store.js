import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    strict: true, //state can only be changed through action
    state: { 
        email: null, 
    }, //keeping track of changes

    mutations: {
        setemail (state, email){
            state.email = email
        },
    },
    actions: {
        setemail ({commit}, email) {
            commit('setemail', email)
        },
    } 
})