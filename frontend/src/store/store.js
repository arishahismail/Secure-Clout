import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    strict: true, //state can only be changed through action
    state: { 
        cust_id: null, 
        port: null, //cache or mini database 
    }, //keeping track of changes

    mutations: {
        setID (state, cust_id){
            state.cust_id = cust_id
        },
        setPort (state, port){
            state.port = port
        },
    },
    actions: {
        setID ({commit}, cust_id) {
            commit('setID', cust_id)
        },
        setPort ({commit}, port) {
            commit('setPort', port)  
        }
    } 
})