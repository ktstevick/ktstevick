<template>
    <div>

        <!-- MY GARDENS -->
        <div class="gardenCard" v-for="garden in this.userGardens" v-bind:key="garden.garden_id">
            <h2>{{ this.$store.state.user.username }}'s Gardens</h2>
            <h3 class="gardenName"> {{ garden.garden_name }}</h3>
        <img class="cardImage" src="../assets/garden.jpg"  v-on:click="this.$router.push({ name: 'gardenView', params: {id: garden.garden_id}});"/>
        </div>

        <div class="home">
            <button v-if="!showForm && this.$store.state.userGardens === {}" v-on:click="createGarden" >Add New Garden</button>
        </div>

        <form v-on:submit.prevent="submitForm" class="cardForm" v-if="showForm">
            <hr>
            <fieldset class="mandatory">
                <label for="title">Garden Nickname:</label>
                <input type="text" id="title" v-model="addedGarden.garden_name" />
            </fieldset>

            <fieldset class="optional">
                <label for="address">Address:</label>
                <input type="text" id="address" v-model="addedGarden.street_address" placeholder="Optional" />
            </fieldset>
            <fieldset class="optional">
                <label for="city">City:</label>
                <input type="text" id="city" v-model="addedGarden.garden_city" placeholder="Optional" />
            </fieldset>
            <fieldset class="optional">
                <label for="state">State:</label>
                <input type="text" id="state" v-model="addedGarden.garden_state" placeholder="Optional" />
            </fieldset>
            <fieldset class="optional">
                <label for="zip">ZIP:</label>
                <input type="text" id="zip" v-model="addedGarden.garden_zip" placeholder="Optional" />
            </fieldset>
            <fieldset class="optional">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" v-model="addedGarden.phone_number" placeholder="Optional"/>
            </fieldset>

            <fieldset class="mandatory">
                <label for="isPublic">Would you like the garden publicly displayed?</label>
                <select v-model="addedGarden.is_public" id="isPublic">
                    <option value="true">Yes</option>
                    <option value="false">No</option>
                </select>
            </fieldset>
            <fieldset class="mandatory">
                <label for="gardenType">What type of garden?:</label>
                <select v-model="addedGarden.garden_type" id="gardenType">
                    <option value="Personal">Personal</option>
                    <option v-if="this.$store.state.user.role == 'ROLE_ADMIN'" value="Community">Community</option>
                </select>
            </fieldset>
            <fieldset>
                <p>placeholder enter custom image</p>
            </fieldset>

            <button v-on:click="clearForm">CLEAR</button>
            <button v-on:click="submitGarden">SUBMIT</button>
        </form>

        <!-- MY MARKETPLACE -->

        <!-- [SET] REMINDERS -->

        <!-- [SET] EVENTS -->

        <!-- VOLUNTEER -->

    </div>
</template> 
<script>
import plantService from '../services/PlantService.js';

export default {
    data() {
        return {
            showForm: false,
            addedGarden: {
                user_id: this.$store.state.user.id
            },
            userGardens: []
        }
    },

    methods: {
        createGarden() {
            this.showForm = true;
        },
        clearForm() {
            this.showForm = false;
            this.garden = {};
        },
        submitGarden() {
            alert(`Thank you! ` + this.addedGarden.garden_name + ' has been added');
            plantService.addGarden(this.addedGarden);
            this.showForm = false;
            this.addedGarden = {};
        },
        getUserGardens() {
            plantService.getGardenByUserId(this.$store.state.user.id)
                .then(response => {
                    this.userGardens = response.data;
                })
        },
        pushToGardenById() {
            
        }
    },
    created() {
    plantService.getGardenByUserId(this.$store.state.user.id)
      .then( response => {
        this.gardenArray = response.data;
        this.$store.commit('SET_USER_GARDEN', this.gardenArray[0]);
        
        plantService.getPlantsByGarden(this.$store.state.user_garden.garden_id)
          .then( response => {
          this.$store.commit('SET_USER_PLANTS', response.data);
        });
      })
      this.getUserGardens();
  }
};
</script>

<style scoped>
div {
    text-align: center;
}

fieldset {
    margin: auto;
    text-align: center;
    width: 25vw;
}

button {
    width: 12.5vw;
}

.cardForm {
    width: 80vw;
}

.mandatory {
    background-color: rgb(241, 227, 227);
}

.cardImage {
    width: 80vw;
    height: auto;
    margin: 10px;
}

.gardenName {
    text-align: center;
}
</style>
  