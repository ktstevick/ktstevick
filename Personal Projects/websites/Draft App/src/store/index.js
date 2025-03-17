import { createStore as _createStore } from "vuex";

// Hard coded, will change later
import gardenia_icon from "../assets/images/Gardenia.png";

// My team
import icon0 from "../assets/icons/icon0.png";
import icon1 from "../assets/icons/icon1.png";
import icon2 from "../assets/icons/icon2.png";
import icon3 from "../assets/icons/icon3.png";
import icon4 from "../assets/icons/icon4.png";
import icon5 from "../assets/icons/icon5.png";
import icon6 from "../assets/icons/icon6.png";
import icon7 from "../assets/icons/icon7.png";
import icon8 from "../assets/icons/icon8.png";
import icon9 from "../assets/icons/icon9.png";
import icon10 from "../assets/icons/icon10.png";
import icon11 from "../assets/icons/icon11.png";

// Logan's
import icon12 from "../assets/icons/icon12.png";
import icon13 from "../assets/icons/icon13.png";
import icon14 from "../assets/icons/icon14.png";
import icon15 from "../assets/icons/icon15.png";
import icon16 from "../assets/icons/icon16.png";
import icon17 from "../assets/icons/icon17.png";
import icon18 from "../assets/icons/icon18.png";
import icon19 from "../assets/icons/icon19.png";
import icon20 from "../assets/icons/icon20.png";
import icon21 from "../assets/icons/icon21.png";
import icon22 from "../assets/icons/icon22.png";
import icon23 from "../assets/icons/icon23.png";

export function createStore() {
  return _createStore({
    state: {
      // The organization of this store is going to be key.
      // Currently it's just set up to functionally mimic the Doc

      currentWeek: 1, // Manually updated

      // Might be implementing notes by week, since it's just for me

      // First draft. What do we need?
      coaches: [
        // Me first!
        {
          id: 5,
          name: "the coldest mess",
          avatar: gardenia_icon,

          roster: [
            "Diancie",
            "M-Diancie",
            "Beedrill",
            "M-Beedrill",
            "Pyukumuku",
            "Talonflame",
            "Magnezone",
            "Latias",
            "Slowbro",
            "Nidoking",
            "Sableye",
            "Lickitung",
          ],

          summary: "M-Beedrill go BZZZZZZ",

          teams: [
            [ "M-Diancie", "Pyukumuku", "Latias", "Magnezone", "Slowbro", "Nidoking"],
            [ "Diancie", "M-Beedrill", "Pyukumuku", "Latias", "Talonflame", "Magnezone"],
            [ "M-Diancie", "Pyukumuku", "Latias", "Talonflame", "Magnezone", "Nidoking"],
          ]
        },

        // Other coaches, organized in the order I fought
        {
          id: 4,
          name: "Logan",
          avatar: gardenia_icon,

          roster: [
            "Pelipper",
            "Clefable",
            "Nihilego",
            "Kommo-o",
            "Swampert",
            "M-Swampert",
            "Araquanid",
            "Kingdra",
            "Aggron",
            "M-Aggron",
            "Silvally", 
            "Infernape"
          ],

          summary: [
            "Logan's team? Rainy."
          ],

          teams: [
            ["Pelipper", "Kingdra", "Silvally", "M-Swampert", "Nihilego", "Araquanid"]
          ]
        },

        {
            id: 10,
            name: "Daryan",
            avatar: gardenia_icon,
  
            roster: [
              "Ferrothorn",
              "Greninja",
              "Togekiss",
              "Blacephalon",
              "Mimikyu",
              "Cresselia",
              "Venusaur",
              "M-Venusaur",
              "Pinsir",
              "M-Pinsir",
              "Diggersby", 
              "Vaporeon"
            ],
  
            summary: [
              "Bog babushka. This was a fun match."
            ],
  
            teams: [
              [],
              ["Greninja", "Ferrothorn", "Blacephalon", "Cresselia", "M-Venusaur", "Diggersby"]
            ]
          },

          {
            id: 9,
            name: "Tyler",
            avatar: gardenia_icon,
  
            roster: [
              "Alakazam",
              "M-Alakazam",
              "Toxapex",
              "Serperior",
              "Rotom",
              "Hawlucha",
              "Crawdaunt",
              "Galvantula",
              "Mamoswine",
              "Cobalion",
              "Doublade"
            ],
  
            summary: [
              "L OF THE CENTURY"
            ],
  
            teams: [
              [],
              [],
              ["Mamoswine", "M-Alakazam", "Toxapex", "Cobalion", "Rotom", "Serperior"]
            ]
          }
      ],
    },



    mutations: {},
  });
}
