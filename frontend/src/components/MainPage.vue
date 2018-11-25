<template>
  <div id="app" class="back">
    <v-app id="inspire">
    <v-container fluid fill-height>
      <v-layout align-center justify-center>
        <v-flex xs12 sm8 md4>
          <v-container fluid>
            <v-layout>
              <v-flex>
                <v-card flat class="elevation-12">
                  <img src="@/assets/CLOUT9LOGO_400x.png" />
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
          <v-tabs class="elevation-12" centered color="primary" slider-color="red" dark icons-and-text>

            <v-tab href="#tabLogin">
              Login<v-icon>create</v-icon>
            </v-tab>

            <v-tab href="#tabRegister" @click="showthisBaby=false">
              Register<v-icon>person_add</v-icon>
            </v-tab>

            <v-tab-item :id="'tabLogin'">
              <v-card flat v-show="!showthisBaby">
                <v-card-text>Login
                  <v-text-field label="Email" prepend-icon="email" v-model="login.mail" :rules="[rules.required]"></v-text-field>
                  <v-text-field label="Password" prepend-icon="vpn_key" v-model="login.pass" @click:append="showPass =!showPass"
                    :type="showPass ? 'showPass' : 'password'" :append-icon="showPass? 'visibility_off' : 'visibility'"
                    :rules="[rules.required]">
                    >
                  </v-text-field>

                  <v-btn color="blue-grey lighten-4" @click="signIn()">Next</v-btn>
                </v-card-text>

              </v-card>
              <v-card flat v-show="showthisBaby">
                <v-card-text>Enter the code sent to your email address
                  <v-text-field solo v-model="login.otp"></v-text-field>
                  <v-btn color="blue-grey lighten-4" @click="sendOTP">Send Code</v-btn>
                  <v-btn color="blue-grey lighten-4" @click="showthisBaby = false">Submit</v-btn>
                </v-card-text>
              </v-card>
            </v-tab-item>

            <v-tab-item :id="'tabRegister'">
              <v-card flat>
                <v-card-text>Register
                  <v-text-field label="Full Name" prepend-icon="person" v-model="userReg.name" :rules="[rules.required]">
                  </v-text-field>
                  <v-text-field label="Email" prepend-icon="email" v-model="userReg.email" :rules="[rules.required, rules.email.Rules]"></v-text-field>
                  <v-text-field label="Password" prepend-icon="vpn_key" v-model="userReg.password" @click:append="showPass =!showPass"
                    :type="showPass ? 'showPass' : 'password'" :append-icon="showPass? 'visibility_off' : 'visibility'"
                    :rules="[rules.required, rules.pass.Length, rules.pass.Letter, rules.pass.Number, rules.pass.Special]">
                  </v-text-field>
                  <v-btn color="blue-grey lighten-4" @click="createUser()">Sign Up</v-btn>
                </v-card-text>
                <v-snackbar v-model="snackbar" :timeout="timeout">
                  {{snackText}}
                  <v-btn dark flat color="red" @click="snackbar = false"> Close</v-btn>
                </v-snackbar>
              </v-card>
            </v-tab-item>
          </v-tabs>


        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
  </div>
</template>

<script>
  import {
    AXIOS
  } from './http-common'
  export default {
    data() {
      return {
        snackbar: false,
        snackText: '',
        timeout: 6000,
        showPass: false,
        showthisBaby: false,
        login: {
          mail: '',
          pass: '',
          otp: '',
        },
        userReg: {
          email: '',
          name: '',
          password: '',
          id: 0,
        },
        rules: {
          required: value => !!value || 'Required',
          pass: {
            Length: v => v.length >= 10 || 'min 10 characters',
            Letter: value => {
              const uppercase = /[A-Z]/g
              return uppercase.test(value) || 'Needs uppercase letter'
            },
            Special: value => {
              const specialchar = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{7,}/g
              return specialchar.test(value) || 'Needs special character'
            },
            Number: value => {
              const number = /[0-9]/g
              return number.test(value) || 'Needs numerical value'
            }
          },
          email: {
            Rules: value => {
              const pattern =
                /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
              return pattern.test(value) || 'Invalid e-mail.'
            }
          }
        }
      }
    },
    methods: {
      createUser() {
        var data = {
          name: this.userReg.name,
          email: this.userReg.email,
          password: this.userReg.password
        };
        AXIOS.post("/signup/", data)
          .then(response => {
            console.log("Successfully registered user");
            console.log(response.data);
            this.userReg = {};
            this.$router.push({
              name: 'MainPage'
            })
            this.snackbar = true,
              this.snackText = "Successfully registered user"
          })
          .catch(e => {
            console.log(e);
            this.snackbar = true,
            this.snackText = "Error occured when registering. Try again?"
            this.userReg.name=''
            this.userReg.email=''
            this.userReg.password=''
          });
      },
      signIn() {
        this.showthisBaby = true
      },
      sendOTP() {
        this.$router.push({
          name: 'Home'
        })
      }
    }
  }

</script>

<style>
.back {
    background-image: url(https://picsum.photos/1920/1080/?random);
    background-size: cover;
  }

  #inspire {
    background: none;
  }
</style>