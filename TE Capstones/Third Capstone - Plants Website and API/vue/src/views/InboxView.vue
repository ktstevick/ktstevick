<template>
<h2>My Inbox:</h2>
<div id="inbox" v-for="message in this.$store.state.userMessages" v-bind:key="message.message_id">
    <message-preview v-bind:message="message" />
</div>
</template>

<script>
import messageService from '../services/MessageService.js';
import plantService from '../services/PlantService.js';
import messagePreview from '../components/MessagePreview.vue';

export default {

components: {
    messagePreview
},

created() {
    messageService.getUserMessages(this.$store.state.user.id).then ( response => {
      this.$store.commit('SET_USER_MESSAGES', response.data)
    });

    plantService.getGardenByUserId(this.$store.state.user.id)
            .then(response => {
                this.gardenArray = response.data;
                this.$store.commit('SET_USER_GARDEN', this.gardenArray[0]);

                plantService.getPlantsByGarden(this.$store.state.user_garden.garden_id)
                    .then(response => {
                        this.$store.commit('SET_USER_PLANTS', response.data);
                    });
            });

        messageService.getUserMessages(this.$store.state.user.id)
            .then(response => {
                this.$store.commit('SET_USER_MESSAGES', response.data)
            })
}
}

</script>

<style scoped>
</style>