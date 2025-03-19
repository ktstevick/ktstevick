START TRANSACTION;

DROP TABLE IF EXISTS team, pokemon, note CASCADE;

CREATE TABLE team (
    team_id serial PRIMARY KEY NOT NULL,
    name varchar(50) NOT NULL,
    roster varchar(500) NOT NULL
);

CREATE TABLE note (
    note_id serial PRIMARY KEY NOT NULL,
    team_id int NOT NULL,
    text varchar(500) NOT NULL
);

CREATE TABLE pokemon (
    pokemon_id serial PRIMARY KEY NOT NULL,
    team_id int NOT NULL,
    name varchar(50) NOT NULL,
    nickname varchar(50) NULL,
    item varchar(50) NULL,
    ability varchar(50) NOT NULL,
    tera_type varchar(50) NOT NULL,
    evs varchar(50) NULL,
    ivs varchar(50) NULL,
    nature varchar(50) NOT NULL,
    move_1 varchar(20) NULL,
    move_2 varchar(20) NULL,
    move_3 varchar(20) NULL,
    move_4 varchar(20) NULL,
    is_shiny BOOLEAN DEFAULT FALSE NOT NULL
);

ALTER TABLE note ADD FOREIGN KEY (team_id) REFERENCES team (team_id);
ALTER TABLE pokemon ADD FOREIGN KEY (team_id) REFERENCES team (team_id);

/* Test values */
INSERT INTO team (name, roster) VALUES
    ('TestTeam', 'Miraidon, Farigiraf, Iron Hands, Ogerpon-H, Urshifu-R, Whimsicott'),
    ('FakeTeam', 'Mewtwo, Snorlax, Rhydon, Nidoking, Chansey, Venusaur');

INSERT INTO note (team_id, text) VALUES
    ('1', 'Test text. This is a test note.'),
    ('1', 'This is another test note. Take two, if you will.');

INSERT INTO pokemon (team_id, name, ability, tera_type, nature) VALUES
    ('1', 'Miraidon', 'Hadron Engine', 'Electric', 'Modest'),
    ('1', 'Farigiraf', 'Armor Tail', 'Dark', 'Bold'),
    ('1', 'Iron Hands', 'Quark Drive', 'Bug', 'Adamant'),
    ('2', 'Mewtwo', 'Pressure', 'Normal', 'Modest'),
    ('2', 'Snorlax', 'Immunity', 'Ghost', 'Bold'),
    ('2', 'Rhydon', 'Lightningrod', 'Flying', 'Adamant');

INSERT INTO pokemon (team_id, name, nickname, item, ability, tera_type, evs, ivs, nature, move_1, move_2, move_3, move_4, is_shiny) VALUES
    ('1', 'Whimsicott', 'Ice Spice', 'Covert Cloak', 'Prankster', 'Ghost', '252 HP / 252 Def / 4 SpDef', '0 Atk', 'Bold', 'Tailwind', 'Encore', 'Moonblast', 'Protect', TRUE),
    ('1', 'Urshifu-R', 'Big Meme', 'Focus Sash', 'Unseen Fist', 'Stellar', '252 HP / 252 Def / 4 SpDef', '0 Atk', 'Jolly', 'Tailwind', 'Encore', 'Moonblast', 'Protect', FALSE),
    ('1', 'Ogerpon-H', 'Also Ice Spice', 'Hearthflame Mask', 'Mold Breaker', 'Fire', '252 HP / 252 Def / 4 SpDef', '0 Atk', 'Adamant', 'Tailwind', 'Encore', 'Moonblast', 'Protect', TRUE),
    ('2', 'Nidoking', 'Niddy', 'Life Orb', 'Sheer Force', 'Stellar', '252 HP / 252 Def / 4 SpDef', '0 Atk', 'Bold', 'Tailwind', 'Encore', 'Moonblast', 'Protect', FALSE),
    ('2', 'Chansey', 'Eggy', 'Eviolite', 'Natural Cure', 'Ghost', '252 HP / 252 Def / 4 SpDef', '0 Atk', 'Bold', 'Tailwind', 'Encore', 'Moonblast', 'Protect', TRUE),
    ('2', 'Venusaur', 'Froggy', 'Life Orb', 'Chlorophyll', 'Poison', '252 HP / 252 Def / 4 SpDef', '0 Atk', 'Bold', 'Tailwind', 'Encore', 'Moonblast', 'Protect', FALSE);

COMMIT;



