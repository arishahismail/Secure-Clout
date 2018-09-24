Main page register user

<template>
    <v-app>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
         
          <v-flex xs12 sm8 md4>
            <v-tabs
              class="elevation-12"
              centered
              color="primary"
              slider-color ="red" dark
              icons-and-text>

              <v-tab href="#tabLogin">
                Login<v-icon>create</v-icon>
              </v-tab>

              <v-tab href="#tabRegister" @click="showthisBaby=false, clearForm">
                Register<v-icon>person_add</v-icon>
              </v-tab>

              <v-tab-item
                :id="'tabLogin'">
                <v-card flat v-show="!login.showthisBaby">
                  <v-card-text>Login
                    <v-text-field label="Email" prepend-icon="email"
                    v-model="login.email"
                    :rules="[rules.required, rules.email, rules.emailRules]"                                   
                    ></v-text-field>
                    <v-text-field label="Password" prepend-icon="vpn_key"
                    v-model="login.password" @click:append="showPass =!showPass"
                    :type="showPass ? 'showPass' : 'password'"
                    :append-icon="showPass? 'visibility_off' : 'visibility'"
                    :rules="[rules.required]">
                    >
                    </v-text-field>

                    <v-btn color="blue-grey lighten-4" @click="login.showthisBaby = true">Next</v-btn>
                  </v-card-text>

                </v-card>
                <v-card flat v-show="showthisBaby">
                  <v-card-text>Enter the code sent to your email address
                    <v-text-field solo v-model="login.otp"
                    ></v-text-field>
                    <v-btn color="blue-grey lighten-4" @click="showthisBaby = false">Lul It works</v-btn>
                  </v-card-text>
                </v-card>
              </v-tab-item>

              <v-tab-item
                :id="'tabRegister'">
                <v-card flat>
                  <v-card-text>Register
                    <v-text-field label="Full Name" prepend-icon="person"
                    v-model="userReg.name" 
                    :rules="[rules.required]">
                    </v-text-field>
                    <v-text-field label="Email" prepend-icon="email"
                    v-model="userReg.email"
                    :rules="[rules.required, rules.email, rules.emailRules]"                    
                    ></v-text-field>
                   <v-text-field label="Password" prepend-icon="vpn_key"
                    v-model="userReg.password" @click:append="showPass =!showPass"
                    :type="showPass ? 'showPass' : 'password'"
                    :append-icon="showPass? 'visibility_off' : 'visibility'"
                    :rules="[rules.required, rules.passLength, rules.passLetter, rules.passNumber]">
                    </v-text-field>
                    <v-btn color="blue-grey lighten-4" @click="createUser()">Sign Up</v-btn>
                  </v-card-text>
                </v-card>
              </v-tab-item>
            </v-tabs>


          </v-flex>
        </v-layout>
      </v-container>
    </v-app>
</template>

<script>
  import {AXIOS} from './http-common'

  export default {
    data () {
      return {
        showPass: false,
        login:{
          emailLogin: '',
          passwordLogin:'',
          showthisBaby: false,
          otp:'',
        },
        userReg:{
          email:'',
          name: '',
          password:'',
          id: 0,
        },
        rules: {
          required: value => !!value || 'Required',
          passLength: v=> v.length >=10 || 'min 10 characters',
          passLetter: value => {
            const uppercase = /[A-Z]/g
            return uppercase.test(value) || 'Needs uppercase letter'
          },
          passNumber: value => {
            const number = /[0-9]/g 
            return number.test(value) || 'Needs numerical value'
          },
          emailRules: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Invalid e-mail.'
          }
        }   
      }
    },
    methods: {
      // Fetches posts when the component is created.
      createUser () {
        var params = new URLSearchParams()
        params.append('name', this.userReg.name)
        params.append('email', this.userReg.email)
        params.append('password', this.userReg.password)

        AXIOS.post(`/RegisterUser`, params)
          .then(response => {
            // JSON responses are automatically parsed.
            this.response = response.data
            this.user.id = response.data
            console.log(response.data)
            this.showResponse = true
          })
          .catch(e => {
            this.errors.push(e)
          })
          }
        }
  }
</script>
