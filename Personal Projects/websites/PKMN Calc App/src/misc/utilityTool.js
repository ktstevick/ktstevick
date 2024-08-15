import { ref } from 'vue';

export default {
    // DEBUG
    test() {
        alert('utilityTool.js wired and running!');
    },

    // STYLE
    useMatchMedia(query) {
        const match = window.matchMedia(query);
        const isMatching = ref(match.matches);

        match.addEventListener('change', e => isMatching.value = e.matches);

        return isMatching;
    },
    nameFormatter(name) {
        let firstLetter = name.substring(0, 1);
        return firstLetter.toUpperCase() + name.substring(1);
    },
    indexFormatter(dexNo) {
        let idString = "";

        if (dexNo < 10) {
            idString = "000" + dexNo;
        } else if (dexNo < 100) {
            idString = "00" + dexNo;
        } else if (dexNo < 1000) {
            idString = "0" + dexNo;
        } else {
            idString = dexNo;
        }

        return idString;
    },
    percentFix(num) {
        return (Math.round(num * 10) / 10).toFixed(1);
    },

    // DATA
    pokemonIdSelector(url) {
        let pokemonIndex = url.indexOf('pokemon');
        let pokemonStr = url.substring(pokemonIndex);
        let slashIndex = pokemonStr.indexOf('/');

        return pokemonStr.substring(slashIndex + 1, pokemonStr.length - 1);
    },
    moveIdSelector(url) {
        let moveIndex = url.indexOf('move');
        let moveStr = url.substring(moveIndex);
        let slashIndex = moveStr.indexOf('/');

        return moveStr.substring(slashIndex + 1, moveStr.length - 1);
    },
    natureIdSelector(url) {
        let natureIndex = url.indexOf('nature');
        let natureStr = url.substring(natureIndex);
        let slashIndex = natureStr.indexOf('/');

        return natureStr.substring(slashIndex + 1, natureStr.length - 1);
    },
    abilityIdSelector(url) {
        let abilityIndex = url.indexOf('ability');
        let abilityStr = url.substring(abilityIndex);
        let slashIndex = abilityStr.indexOf('/');

        return abilityStr.substring(slashIndex + 1, abilityStr.length - 1);
    },

    // POKEMON
    categoryReference(category) {
        let statsIndex = [1, 2]; // Attack and Defense stat indices
        if (category === "Special") {
            statsIndex[0] = 3; // Special Attack index
            statsIndex[1] = 4; // Special Defense index
        }

        return statsIndex;
    },

    basicStatCalc(baseStat, EVs, natureMultiplier) {
        let IV = 31;
        let LEVEL = 100;

        return (((((2 * baseStat) + IV + (EVs / 4)) * LEVEL) / 100) + 5) * natureMultiplier;
    },

    hpStatCalc(baseHP, EVs) {
        let IV = 31;
        let LEVEL = 100;

        return ((((2 * baseHP) + IV + (EVs / 4)) * LEVEL) / 100) + LEVEL + 10;
    },

    damageCalc(move, aStat, dStat, typeMultiplier) {
        let rollArray = [];
        let movePower = move.power;

        let LEVEL = 100;
        let RANDOM = 1;
        let OTHER = 1;
        let STAB_MULTIPLIER = 1;

        if (move.stab) {
            STAB_MULTIPLIER = 1.5;
        }

        for (let i = 85; i < 101; i++) {
            RANDOM = i * .01;
            let damage = ((((((2 * LEVEL) / 5) + 2) * movePower * (aStat / dStat)) / 50) + 2) * typeMultiplier * STAB_MULTIPLIER * OTHER * RANDOM;
            rollArray.push(Math.floor(damage));
        }

        return rollArray;
    },

    getTypeMultiplier(moveType, type1, type2) {
        let finalMultiplier = 1;
        let types = [type1, type2];

        // TYPE CHART
        for (let i = 0; i < types.length; i++) {
            if (moveType === 'Normal') {
                if (types[i] === 'Rock' || types[i] === 'Steel') {
                    finalMultiplier *= 0.5;
                }
                if (types[i] === 'Ghost') {
                    finalMultiplier *= 0;
                }
            }
            if (moveType === 'Fighting') {
                if (types[i] === 'Normal' || types[i] === 'Rock' || types[i] === 'Steel' || types[i] === 'Ice' || types[i] === 'Dark') {
                    finalMultiplier *= 2; // Fighting Weaknesses
                }
                if (types[i] === 'Flying' || types[i] === 'Poison' || types[i] === 'Bug' || types[i] === 'Psychic' || types[i] === 'Fairy') {
                    finalMultiplier *= 0.5; // Fighting Resistances
                }
                if (types[i] === 'Ghost') {
                    finalMultiplier *= 0; // Fighting Immunities
                }
            }
            if (moveType === 'Flying') {
                if (types[i] === 'Fighting' || types[i] === 'Bug' || types[i] === 'Grass') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Rock' || types[i] === 'Steel' || types[i] === 'Electric') {
                    finalMultiplier *= 0.5;
                }
            }
            if (moveType === 'Poison') {
                if (types[i] === 'Grass' || types[i] === 'Fairy') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Poison' || types[i] === 'Ground' || types[i] === 'Rock' || types[i] === 'Ghost') {
                    finalMultiplier *= 0.5;
                }
                if (types[i] === 'Steel') {
                    finalMultiplier *= 0;
                }
            }
            if (moveType === 'Ground') {
                if (types[i] === 'Poison' || types[i] === 'Rock' || types[i] === 'Steel' || types[i] === 'Fire' || types[i] === 'Electric') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Bug' || types[i] === 'Grass') {
                    finalMultiplier *= 0.5;
                }
                if (types[i] === 'Flying') {
                    finalMultiplier *= 0;
                }
            }
            if (moveType === 'Rock') {
                if (types[i] === 'Flying' || types[i] === 'Bug' || types[i] === 'Fire' || types[i] === 'Ice') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Fighting' || types[i] === 'Ground' || types[i] === 'Steel') {
                    finalMultiplier *= 0.5;
                }
            }
            if (moveType === 'Bug') {
                if (types[i] === 'Grass' || types[i] === 'Psychic' || types[i] === 'Dark') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Fighting' || types[i] === 'Flying' || types[i] === 'Poison' || types[i] === 'Ghost' || types[i] === 'Steel' || types[i] === 'Fire' || types[i] === 'Fairy') {
                    finalMultiplier *= 0.5;
                }
            }
            if (moveType === 'Ghost') {
                if (types[i] === 'Ghost' || types[i] === 'Psychic') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Dark') {
                    finalMultiplier *= 0.5;
                }
                if (types[i] === 'Normal') {
                    finalMultiplier *= 0;
                }
            }
            if (moveType === 'Steel') {
                if (types[i] === 'Rock' || types[i] === 'Ice' || types[i] === 'Fairy') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Steel' || types[i] === 'Fire' || types[i] === 'Water' || types[i] === 'Electric') {
                    finalMultiplier *= 0.5;
                }
            }
            if (moveType === 'Fire') {
                if (types[i] === 'Bug' || types[i] === 'Steel' || types[i] === 'Grass' || types[i] === 'Ice') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Rock' || types[i] === 'Fire' || types[i] === 'Water' || types[i] === 'Dragon') {
                    finalMultiplier *= 0.5;
                }
            }
            if (moveType === 'Water') {
                if (types[i] === 'Ground' || types[i] === 'Rock' || types[i] === 'Fire') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Water' || types[i] === 'Grass' || types[i] === 'Dragon') {
                    finalMultiplier *= 0.5;
                }
            }
            if (moveType === 'Grass') {
                if (types[i] === 'Ground' || types[i] === 'Rock' || types[i] === 'Water') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Flying' || types[i] === 'Poison' || types[i] === 'Bug' || types[i] === 'Steel' || types[i] === 'Fire' || types[i] === 'Grass' || types[i] === 'Dragon') {
                    finalMultiplier *= 0.5;
                }
            }
            if (moveType === 'Electric') {
                if (types[i] === 'Flying' || types[i] === 'Water') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Grass' || types[i] === 'Electric' || types[i] === 'Dragon') {
                    finalMultiplier *= 0.5;
                }
                if (types[i] === 'Ground') {
                    finalMultiplier *= 0;
                }
            }
            if (moveType === 'Psychic') {
                if (types[i] === 'Fighting' || types[i] === 'Poison') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Steel' || types[i] === 'Psychic') {
                    finalMultiplier *= 0.5;
                }
                if (types[i] === 'Dark') {
                    finalMultiplier *= 0;
                }
            }
            if (moveType === 'Ice') {
                if (types[i] === 'Flying' || types[i] === 'Ground' || types[i] === 'Grass') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Steel' || types[i] === 'Fire' || types[i] === 'Water' || types[i] === 'Ice') {
                    finalMultiplier *= 0.5;
                }
            }
            if (moveType === 'Dragon') {
                if (types[i] === 'Dragon') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Steel') {
                    finalMultiplier *= 0.5;
                }
                if (types[i] === 'Fairy') {
                    finalMultiplier *= 0;
                }
            }
            if (moveType === 'Dark') {
                if (types[i] === 'Ghost' || types[i] === 'Psychic') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Fighting' || types[i] === 'Dark' || types[i] === 'Fairy') {
                    finalMultiplier *= 0.5;
                }
            }
            if (moveType === 'Fairy') {
                if (types[i] === 'Fighting' || types[i] === 'Dark' || types[i] === 'Dragon') {
                    finalMultiplier *= 2;
                }
                if (types[i] === 'Poison' || types[i] === 'Steel' || types[i] === 'Fire') {
                    finalMultiplier *= 0.5;
                }
            }
        }

        return finalMultiplier;
    },

    getNatureMultiplier(nature, statIndex) {
        let natureMultiplier = 1;

        // HP_INDEX would be 0, but is unaffected by Natures
        const ATTACK_INDEX = 1;
        const DEFENSE_INDEX = 2;
        const SPECIAL_ATTACK_INDEX = 3;
        const SPECIAL_DEFENSE_INDEX = 4;
        const SPEED_INDEX = 5;

        // NATURE CHART
        if(statIndex === ATTACK_INDEX) { 
            if(nature === 'Lonely' || nature === 'Brave' || nature === 'Adamant' || nature === 'Naughty') {
                natureMultiplier = 1.1; // Increasing Natures
            }
            if(nature === 'Bold' || nature === 'Timid' || nature === 'Modest' || nature === 'Calm') {
                natureMultiplier = 0.9; // Reducing Natures
            }
        }
        if(statIndex === DEFENSE_INDEX) { 
            if(nature === 'Bold' || nature === 'Relaxed' || nature === 'Impish' || nature === 'Lax') {
                natureMultiplier = 1.1;
            }
            if(nature === 'Lonely' || nature === 'Hasty' || nature === 'Mild' || nature === 'Gentle') {
                natureMultiplier = 0.9;
            }
        }
        if(statIndex === SPECIAL_ATTACK_INDEX) { 
            if(nature === 'Modest' || nature === 'Mild' || nature === 'Quiet' || nature === 'Rash') {
                natureMultiplier = 1.1;
            }
            if(nature === 'Adamant' || nature === 'Impish' || nature === 'Jolly' || nature === 'Careful') {
                natureMultiplier = 0.9;
            }
        }
        if(statIndex === SPECIAL_DEFENSE_INDEX) { 
            if(nature === 'Calm' || nature === 'Gentle' || nature === 'Sassy' || nature === 'Careful') {
                natureMultiplier = 1.1;
            }
            if(nature === 'Naughty' || nature === 'Lax' || nature === 'Naive' || nature === 'Rash') {
                natureMultiplier = 0.9;
            }
        }
        if(statIndex === SPEED_INDEX) { 
            if(nature === 'Timid' || nature === 'Hasty' || nature === 'Jolly' || nature === 'Naive') {
                natureMultiplier = 1.1;
            }
            if(nature === 'Brave' || nature === 'Relaxed' || nature === 'Quiet' || nature === 'Sassy') {
                natureMultiplier = 0.9;
            }
        }

        return natureMultiplier;
    }
}