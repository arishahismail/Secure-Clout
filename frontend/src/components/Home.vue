<template>
  <v-app id="inspire">
  <v-navigation-drawer
      v-model="drawer" fixed clipped class="grey lighten-5" app >
      <v-list dense class="grey lighten-5">
        <v-img position="left left"
              src="https://cdn.shopify.com/s/files/1/2638/2340/files/CLOUT9LOGO_400x.png?v=1521701817"
              alt="Clout"> </v-img>
        <template v-for="(item, i) in items">
          <v-layout v-if="item.heading" :key="i" row align-left></v-layout>
          <v-divider v-else-if="item.divider" :key="i" dark class="my-3"></v-divider>
          <v-list-tile v-else :key="i">
            <v-list-tile-action>
              <v-icon >{{ item.icon }}</v-icon>     
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title >
                {{ item.text }}
              </v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </template>
      </v-list>
    </v-navigation-drawer>

    <!--Toolbar -->
    <v-toolbar color="primary" app absolute clipped-left>
      <v-toolbar-side-icon @click.native="drawer = !drawer"></v-toolbar-side-icon>
      
      <span class="title ml-3 mr-5">Secure&nbsp;<span class="font-weight-light">Clout</span></span>
      <v-spacer></v-spacer>
      
      <v-spacer></v-spacer>
      <v-btn icon>
        <v-icon>person</v-icon>
      </v-btn>
      
    </v-toolbar>

    <v-content>
      <v-container fluid fill-height class="grey lighten-4">
        <v-layout>
          <v-flex>
            <v-card-title class="title"> Saved Files
              <v-spacer></v-spacer>
              
              <!--Button to select upload file
                onclick="document.getElementById('filedialog').click()"
              -->
              <v-btn color ="primary darken-2" right small round 
                id="fileInputButton"  @click.native.stop="dialog =!dialog"> 
                <v-icon>add</v-icon> Upload New File
              </v-btn>

             <!--This is floating box when click new file upload -->
              <v-dialog v-model="dialog" max-width="500px">
                <v-card>
                      <v-card-title class="title">Upload file</v-card-title>
                      <div class='dropbox'>
                          <input class='input-file' type="file" multiple 
                            
                            v-file="selectedFile"
                             id= 'filedialog'
                            accept="/*" >
                      </div>
                      <v-card-text class="text">File name: <v-text-field v-model="file_name"></v-text-field></v-card-text>

                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn flat color="primary" @click="upload()">Submit</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>

            </v-card-title>
             <!-- This is table of data files -->
            <v-data-table 
                :headers="headers"
                :items="FileName"
                v-model="selected"
                hide-actions
                item-key="name"
                class="elevation-1"
              >
                <template slot="header" slot-scope="props">
                  <v-tooltip bottom>
                    <span slot="activator">
                      {{ props.header.text }}
                    </span>
                    <span>
                      {{ props.header.text }}
                    </span>
                  </v-tooltip>
                </template>
                <template slot="items" slot-scope="props">
                  <td class="text-xs-left">{{ props.item.name }}</td>
                  <td class="text-xs-left">{{ props.item.date }}</td>
                </template>
              </v-data-table>
          <v-snackbar v-model="snackbar" :timeout="timeout">
                  {{snackText}}
                  <v-btn dark flat color="red" @click="snackbar = false"> Close</v-btn>
                </v-snackbar>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import {
    AXIOS
  } from './http-common'
  export default {
    data(){
      return {
        snackbar: false,
        snackText: '',
        timeout: 6000,
        dialog: false,
        search: '',
        file_name: '',
        selectedFile: '',
        selected: [],
        headers: [
          {
            text: 'File Name',
            align: 'left',
            sortable: false,
            value: 'name'
          },
          { text: 'Date', value: 'date' },          
        
        ],
        FileName: [
          {
            value: false,
            name: 'Test',
            date: '2018-09-28',            
          },
            {
            value: false,
            name: 'ugh',
            date: '2018-09-28',
          },
        ],
        drawer: null,
        items: [
          { icon: 'home', text: 'Home' },
          { icon: 'folder_open', text: 'Files' },
          { divider: true },
          { icon: 'help', text: 'About' }
        ],
        snackbar:false,
        y:'top',
        x:null,
        mode: '',
        timeout:6000,
        text:'File being scanned and uploaded'
        
        }
      },
      methods: {
          // filesChange(event){
          //   console.log(event.target.name)
          //   this.selected = event.target.name
          // },
          upload() {
            AXIOS.post(`/upload/` + this.$store.state.email + `/` + this.selectedFile + `/` + this.file_name)
              .then(response => {
                console.log("halo issa call")
                console.log(this.$store.state.email)
                if (response.data == "success") {
                  this.snackbar = true
                  this.snackText = "Successfull encrypted and uploaded file"
                  this.dialog = false
                } 
                else {
                  this.snackbar = true
                  this.snackText = "Upload fail. Try again."
                }
              })
              .catch(e => {
                console.log(e);
              })
            console.log(this.selectedFile)
            console.log(this.file_name)
            
          },
          
    }
    
  }
  
</script>

