# PKMN Calculator App
![COVER IMAGE](https://i.imgur.com/X2EK31z.png)

## About

### Purpose
Utilizing the official [Pokemon API](https://pokeapi.co/), this application aims to allow users to view Pokemon **data** and check **damage calculation**s for both standard and custom sets.

### Context
This app is a more user friendly, web oriented version of my original [Pokemon Calculator App](https://github.com/ktstevick/ktstevick/tree/main/Personal%20Projects/PKMN%20Damage%20Calculator). The original project was the very first personal project I undertook - an object-oriented Command Line Interface that returned accurate results, but was very primitive in its interactive scope and data management systems. As I learned about Relational Database Management and API development, I'd always hoped to expand the project to incorporate the Pokemon API itself. When the time came to update the User Interface, rather than do it all in Java, it seemed like a no-brainer to develop an accessible web app instead.

## Installation

None required - This application is viewable [here!](https://ktstevick.github.io/)

## Navigation
![BASIC NAVIGATION](https://i.imgur.com/QTZh2n2.png)
```
Home - Website loads here, but can also be reached by clicking the Home button or the icon above the navigation
List - Click the List link
Calculator - Click the Calc link, but also reachable by clicking the large image on the Details page (pictured below)
```

![DETAILS PAGE](https://i.imgur.com/hIGmg2M.png)
```
Details - Reached by clicking cards on the List page, or by clicking either Pokemon's image in the Calculator page
```

![EXTERNAL NAVIGATION](https://i.imgur.com/CsATHTj.png)
```
Bulbapedia - Link available on Home page
Smogon - Likewise

GIT - GIT link on website footer
LinkedIn - Likewise
Instagram - Likewise
```

## Future Plans
- fully implemented Battle System
- mobile app refinement (currently only minor style changes)
- reactive Bulbapedia links from Details
- API implementation, authenticated users
- the ability to save custom sets
- Smogon compatible exports

## Technical Details

### Notes
1. The Pokemon API itself is broken up into many small pieces. For example, each Pokemon has a full list of moves it can learn - these moves, however, consist only of a name and a link. The API must be called **again**, this time for each individual move to get the relevant data (it's power, damage catrgory, type, et alia). As a result, throughout this project I aim to do so as little as possible.

2. The Calculator page loads as "/calc/1" by default - this is so that it loads in Bulbasaur (ID: 1) as the default. If the Calculator view is accessed via Details, it passes along the number in the route param of that Details page and the Calculator loads in with THAT Pokemon as the initial custom.

3. There are several partially defined attributes (such as name: "") throughout the project - these are in place to keep literals from bricking before the data can be received from the API.

### Version Differences
```
Version 0.9.0 - Base state, no changes
```

## Other Images

### List View:
![LIST VIEW](https://i.imgur.com/sWOItrH.png)
### Details Gallery:
![GALLERY VIEW](https://i.imgur.com/G719JO0.png)
### Phone View Example:
![TABLET VIEW](https://i.imgur.com/UEP7nV7.png)