<template>
  <div id="capstone-app">
    <div class="topBar">
      <h1 id="header">The Green Thumb</h1>

      <div id="userNav" v-if="$store.state.token != ''">
        <div class="userNavButton" v-on:click="gardenPush">
          <img class="navIconUser" src="./assets/garden_icon.png"/>
          <router-link class="routerLinkClassUser" v-bind:to="{ name: 'accountView'}">MyGarden</router-link>
        </div>
        <div class="userNavButton" v-on:click="messagesPush">
          <img class="navIconUser" src="./assets/messages_icon.png"/>
          <router-link class="routerLinkClassUser" v-bind:to="{ name: 'inbox' }">Messages</router-link>
        </div>
        <div class="userNavButton" v-on:click="logoutPush">
          <img class="navIconUser" src="./assets/logout_icon.png"/>
          <router-link class="routerLinkClassUser" v-bind:to="{ name: 'logout' }">Logout</router-link>
        </div>
      </div>
    </div>

    <!-- SECOND NAV BAR -->
    <div class="pageBody">
      <div id="siteNav" v-if="$store.state.token != ''">
        <div class="siteNavButton" v-on:click="homePush">
          <router-link class="routerLinkClassSite" v-bind:to="{ name: 'home' }">Home</router-link>
          <img class="navIconSite" src="./assets/home_icon.png"/>
        </div>
        <div class="siteNavButton" v-on:click="searchPush">
          <router-link class="routerLinkClassSite" v-bind:to="{ name: 'detailSearch' }">Search</router-link>
          <img class="navIconSite" src="./assets/search_icon.png"/>
        </div>
        <div class="siteNavButton" v-on:click="forumPush">
          <router-link class="routerLinkClassSite" v-bind:to="{ name: 'forum' }">Forum</router-link>
          <img class="navIconSite" src="./assets/forum_icon.png"/>
        </div>
        <!-- <div class="siteNavButton" v-on:click="eventsPush">
          <router-link class="routerLinkClassSite" v-bind:to="{ name: 'home' }">Events</router-link>
          <img class="navIconSite" />
        </div> -->
      </div>

      <div id="view">
        <router-view />
      </div>
    </div>
  </div>
</template>

<script>
import router from './router';

export default {
  // These methods are all router pushes of the appropriate kind, coded only to the icons/div at the moment
  methods: {
    homePush() {
      router.push({ name: 'home'});
    },
    searchPush() {
      router.push({ name: 'detailSearch'});
    },
    forumPush() {
      router.push({ name: 'forum'});
    },
    gardenPush() {
      router.push({ name: 'gardenView', params: { id: this.$store.state.user_garden.garden_id } });
    },
    messagesPush() {
      router.push({name: 'inbox'});
    },
    logoutPush() {
      router.push({name: 'logout'});
    }
  }
}
</script>

<!-- NOTES FOR LATER - vertical column flex for card consolidation -->

<style>
/* MOBILE FIRST DESIGN */
@media only screen and (max-width: 600px) {
  .topBar {
    background-color: rgb(255, 213, 220);
    position: fixed;
    top: 0px;
    left: 0px;
    width: 100vw;
    height: 15vh;
    border-bottom: 2px solid rgb(209, 149, 159);
}
#userNav {
  position: fixed;
  top: 9vh;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  text-align: center;
}
h1 {
  text-align: center;
  font-size: 2.2em;
}
#view {
  display: flex;
  justify-content: center;
  align-content: center;
  flex-wrap: wrap;

  padding-top: 15vh;
  padding-bottom: 12vh;
}

#siteNav {
  position: fixed;

  width: 100vw;
  height: 10vh;

  top: 90vh;
  left: 0px;

  display: grid;
  grid-template-columns: 1fr 1fr 1fr;

  background-color: pink;
  border-top: 2px solid rgb(194, 101, 116);

  font-weight: bold;
  color:white;
}


.routerLinkClassSite {
  visibility: hidden;
}

.routerLinkClassUser {
  font-size: 1.3em;
  text-decoration: none;
  display: block;
  /* color: white; */
}

.navIconUser {
  visibility: hidden;
  height: 0px;
  width: 33vw;
}

.navIconSite {
    height: 7vh;
    width: 15vw;
  }

  .siteNavButton {
    padding-top: 1vh;
    display: flex;
    justify-items: center;
  }

}

/* TABLET AND ABOVE */
@media screen and (min-width: 600px) {
  /* #capstone-app {
    display: grid;
    grid-template-areas:
      "header header header"
      "sitenav usernav usernav"
      "sitenav view view"
    ;
    grid-template-columns: 50px auto auto;
    grid-template-rows: 30px 50px auto;
    height: 100vh;
  }

  #header {
    grid-area: header;
    background-color: pink;
  }

  #userNav {
    grid-area: usernav;
    text-align: end;
    position: absolute;
    top: 70px;
    width: 97vw;
  }

  #siteNav {
    display: flex;
    grid-area: sitenav;
    flex-direction: column;
    font-size: 15px;
  }

  #view {
    grid-area: view;
    justify-content: center;
    align-content: start;
  } */

  .topBar {
    display: grid;
    grid-template-columns: 10fr 2fr 1fr;

    background-color: pink;
    position: fixed;
    top: 0px;
    left: 0px;
    width: 100vw;
    height: 12.1vh;

    border-bottom: 2px solid rgb(100, 29, 29);
  }

  #userNav {
    display: inline-flex;
  }

  #header {
    flex-basis: 33%;

    display: inline-block;
    align-self: flex-start;

    text-align: left;
    padding-left: 1vw;
    padding-top: 1vh;

    text-decoration: underline;
    font-size: 2.5em;
  }

  #headerBufferSpace {
    flex-basis: 33%;
  }

  .pageBody {
    padding-top: 11.3vh;
    top: 0px;
    left: 0px;
    display: flex;
  }

  #siteNav {
    position: fixed;
    left: 0px;
    background-color: pink;
    width: 10vw;
    height: 87vh;

    border-top: 1px solid rgb(100, 29, 29);
    border-right: 1px solid rgb(100, 29, 29);
  }

  .routerLinkClassUser {
    text-decoration: none;
    font-weight: bold;
    width: 5vw;
  }

  .routerLinkClassSite {
    text-decoration: none;
    font-weight: bold;
    width: 5vw;
  }

  .siteNavButton {

    height: 6vh;
    width: 10vw;
    text-align: right;

    display: grid;
    grid-template-columns: 4fr 2fr;

    justify-items: end;
    align-items: center;

    padding-top: 1vh;
  }

  .siteNavButton:hover {
    background-color: rgb(238, 142, 158);
    cursor: pointer;
  }

  .navIconSite {
    height: 5vh;
    width: 5vh;

    margin-bottom: 1vh;
    margin-right: 1vh;
    margin-left: 1vh;
  }

  .userNavButton {
    left: 0px;
    padding-top: 2.4vh;
    text-decoration: none;

    width: 5vw;
    text-align: center;
  }

  .userNavButton:hover {
    background-color: rgb(238, 142, 158);
    cursor: pointer;
  }

  .navIconUser {
    height: 5vh;
    width: 5vh;

    margin-bottom: 1vh;
    margin-right: 1vh;
    margin-left: 1vh;
  }

  #view {
    /* text-align: center; */
    padding-left: 10vw;
    padding-top: 2.5vh;
  }
}
</style>
