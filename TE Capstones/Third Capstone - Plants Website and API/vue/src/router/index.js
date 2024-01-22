import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import PlantDetailView from '../views/PlantDetailView.vue';
import SearchView from '../views/SearchView.vue';
import GardenView from '../views/GardenView.vue';
import AccountView from '../views/AccountView.vue';
import InboxView from '../views/InboxView.vue';
import MessageView from '../views/MessageView.vue';
import ForumView from '../views/ForumView.vue';
import DBPlantDetailView from '../views/DBPlantDetailView.vue';
import ForumDetailView from '../views/ForumDetailView.vue';
import PostView from '../views/PostView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/plantdetail/:id",
    name: "detailById",
    component: PlantDetailView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/plantdetail",
    name: "detailSearch",
    component: SearchView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/gardens/:id",
    name: "gardenView",
    component: GardenView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/account",
    name: "accountView",
    component: AccountView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/inbox",
    name: "inbox",
    component: InboxView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/inbox/:id",
    name: "message",
    component: MessageView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/forum/",
    name: "forum",
    component: ForumView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:'/forum/:id',
    name: 'forumById',
    component: ForumDetailView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path:'/forum/:id/:postid',
    name: 'post',
    component: PostView,
    meta: {
      requiresAuth: true
    }
  },
  {
    path: "/userplantdetail/:id",
    name: "dbPlantDetail",
    component: DBPlantDetailView,
    meta: {
      requiresAuth: true
    }
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
