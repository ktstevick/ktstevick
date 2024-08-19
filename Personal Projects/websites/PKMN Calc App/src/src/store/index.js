import { createStore as _createStore } from 'vuex';

export function createStore() {
    let store = _createStore({
        state: {
            pokemon: [{
                name: '',

                types: [
                    "",
                    ""
                ],

                stats: [
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
                ],

                EVs: [
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
                ],

                nature: '',

                displayMoves: [
                    { name: "Loading..." }, // Only displays before first update
                    { name: "Loading..." },
                    { name: "Loading..." },
                    { name: "Loading..." },
                ]
            },

            {
                name: "",

                types: [
                    "",
                    ""
                ],

                stats: [
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
                ],

                EVs: [
                    0,
                    0,
                    0,
                    0,
                    0,
                    0
                ],

                nature: '',

                displayMoves: [
                    { name: "Loading..." },
                    { name: "Loading..." },
                    { name: "Loading..." },
                    { name: "Loading..." },
                ]
            }
            ]
        },

        mutations: {
            UPDATE_POKEMON(state, payload) {
                let storeIndex = 0;
                if (payload.componentId === 4) {
                    storeIndex = 1;
                }

                state.pokemon[storeIndex] = payload;
            }
        }
    },
    )

    return store;
}