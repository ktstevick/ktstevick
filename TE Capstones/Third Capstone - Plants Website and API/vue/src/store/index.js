import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      plants: [ // Plant object array for testing
        
      ],
      userPlants: [

      ],
      // Ultimately this DOES need to be a two way binding
      user_garden: {},
      transferPlantJSON: {
        garden_id: 0
      },
      userMessages: [],
      activeMessage: {},
      marketplacePreview: {},
      seasonalPreview: {},
      diseasePreview: {},
      activePlant: {}
      },
      
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },

      STORE_PLANT(state, plant) {
        state.plants.unshift(plant);
      },

      DELETE_PLANT_FROM_GARDEN(state, plant) {
        let index = state.user_garden.indexOf(plant);
        state.user_garden.splice(index, 1);
      },
      SET_USER_GARDEN(state, garden) {
        state.user_garden = garden;
      },
      SET_USER_PLANTS(state, plantsArray) {
        state.userPlants = plantsArray;
      },
      MODIFY_PLANT_FOR_ADDITION(state, plant) {
        if( plant.scientific_name != null) {
          plant.scientific_name = plant.scientific_name[0];
        }

        if( plant.other_name != null) {
          plant.other_name = plant.other_name[0];
        }

        if( plant.default_image != null) {
          plant.regular_img_url = plant.default_image.original_url;
        }

        plant.api_plant_id = plant.id;
        plant.plant_description = plant.description;
        plant.is_active = true;
        state.transferPlantJSON = plant;
      },

      SET_USER_MESSAGES(state, messages) {
        state.userMessages = messages;
      },
      SET_ACTIVE_MESSAGE(state, message) {
        state.activeMessage = message;
      },
      SET_MARKETPLACEPREVIEW(state, post) {
        state.marketplacePreview = post;
      },
      SET_SEASONALPREVIEW(state, post) {
        state.seasonalPreview = post;
      },
      SET_DISEASEPREVIEW(state, post) {
        state.diseasePreview = post;
      },
      SET_ACTIVE_PLANT(state, plant) {
        state.activePlant = plant;
      }
    },
  });
  return store;
}
