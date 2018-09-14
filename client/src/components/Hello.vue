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
                <v-card flat v-show="!showthisBaby">
                  <v-card-text>Login
                    <v-text-field label="Email" prepend-icon="email"
                    v-model="emailLogin"
                    :rules="[rules.required, rules.email, rules.emailRules]"                                   
                    ></v-text-field>
                    <v-text-field label="Password" prepend-icon="vpn_key"
                    v-model="passwordLogin" @click:append="showPass =!showPass"
                    :type="showPass ? 'showPass' : 'password'"
                    :append-icon="showPass? 'visibility_off' : 'visibility'"
                    :rules="[rules.required]">
                    >
                    </v-text-field>

                    <v-btn color="blue-grey lighten-4" @click="showthisBaby = true">Next</v-btn>
                  </v-card-text>

                </v-card>
                <v-card flat v-show="showthisBaby">
                  <v-card-text>Enter the code sent to your email address
                    <v-text-field solo v-model="otp"
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
                    v-model="name" 
                    :rules="[rules.required]">
                    </v-text-field>
                    <v-text-field label="Email" prepend-icon="email"
                    v-model="emailRegister"
                    :rules="[rules.required, rules.email, rules.emailRules]"                    
                    ></v-text-field>
                   <v-text-field label="Password" prepend-icon="vpn_key"
                    v-model="passwordRegister" @click:append="showPass =!showPass"
                    :type="showPass ? 'showPass' : 'password'"
                    :append-icon="showPass? 'visibility_off' : 'visibility'"
                    :rules="[rules.required, rules.passLength, rules.passLetter, rules.passNumber]">
                    </v-text-field>
                    <v-btn color="blue-grey lighten-4">Sign Up</v-btn>
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
  export default {
    data () {
      return {
        otp:'',
        showthisBaby: false,
        showPass: false,
        passwordLogin:'',
        passwordRegister:'',
        emailLogin: '',
        emailRegister:'',
        name: '',

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
          },
        }   
      }
    }
  }
</script>
