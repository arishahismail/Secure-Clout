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
      
      <v-text-field
        flat
        solo-inverted
        prepend-inner-icon="search"
        label="Search"
      ></v-text-field>
      <v-spacer></v-spacer>
      <v-btn left icon>
        <v-icon>notifications</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>person</v-icon>
      </v-btn>
      
    </v-toolbar>

    <v-content>
      <v-container fluid fill-height class="grey lighten-4">
        <v-layout>
          <v-flex>
            <v-card-title> Saved Files
              <v-spacer></v-spacer>
              
              <!--Button to select upload file -->
              
              <v-btn color ="primary darken-2" right fab small flat 
                id="fileInputButton"  @click.native.stop="dialog =!dialog"> 
                <v-icon>more_horiz</v-icon>
              </v-btn>
            </v-card-title>

             <!-- This is table of data files -->
            <v-data-table 
                :headers="headers"
                :items="FileName"
                v-model="selected"
                item-key="name"
                select-all
                class="elevation-1"
              >
              <template slot="headCell" slot-scope="props">
                <v-tooltip bottom>
                  <span slot="activator">
                    {{props.header.text}}
                  </span>
                  <span>
                    {{props.header.text}}
                  </span>
                </v-tooltip>
                </template>
                <template slot="items" slot-scope="props">
                  <td>
                    <v-checkbox v-model="props.selected" primary hide-details></v-checkbox>
                  </td>
                  <td>
                    <v-edit-dialog :returnvalue.sync="props.item.name" lazy
                      @save="save"
                      @open="open"
                      @cancel="cancel"
                      @close="close"> 
                        {{ props.item.name }}
                        <v-text-field
                        slot="input" 
                        v-model="props.item.name"
                        :rules="[lengthtext]"
                        label="Edit"
                        single-line
                        counter></v-text-field>
                        
                      </v-edit-dialog>
                  </td>
                  <td class="text-xs-left">{{ props.item.date }}</td>
                  <td class="text-xs-left">{{ props.item.size }}</td>
                  <td class="xs-right">
                  <v-icon
                    small     
                    class="xs-right"     
                    @click="downloadItem(props.item)">
                    cloud_download
                  </v-icon>
                  <v-icon
                  small
                    @click="deleteItem(props.item)">
                    delete
                  </v-icon>
                </td>
                </template>
              </v-data-table>

          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
  export default {
    data(){
      return {
        lengthtext: (v) => v.length <= 15 || 'Input too long!',
        dialog: false,
        search: '',
        selected: true,
        //this is for data table
        headers: [
          {
            text: 'File Name',
            align: 'left',
            sortable: true,
            value: 'name'
          },
          { text: 'Date', value: 'date' },          
          { text: 'Size', value: 'size', sortable: false },
          { text: '', value: 'actions', sortable: false, align:'right' },
         
        ],
        //this is file data
        FileName: [
          {
            value: false,
            name: '',
            date: 'sdsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsdfsds',            
            size: '38mb',
          },
            {
            value: false,
            name: 'ugh',
            size: '38mb',
            date: '123123',
          },
        ],
        //this is for menu
        drawer: null,
        items: [
          { icon: 'home', text: 'Home' },
          { icon: 'folder_open', text: 'Files' },
          { divider: true },
          { icon: 'settings', text: 'Settings' },
          { icon: 'help', text: 'About' }
        ],
        methods: {
          save () {
            this.snack = true
            this.snackColor = 'success'
            this.snackText = 'Data saved'
          },
          cancel () {
            this.snack = true
            this.snackColor = 'error'
            this.snackText = 'Canceled'
          },
          open () {
            this.snack = true
            this.snackColor = 'info'
            this.snackText = 'Dialog opened'
          },
          close () {
            console.log('Dialog closed')
            }
          }
      } 
  }
}
</script>

