# Module 3 Capstone - Plants
![COVER IMAGE](https://i.imgur.com/yVvBn21.png)

## About

### Purpose
Using the Agile Development cycle, go from concept to completion of a user focused website for plants in two weeks' time.

### Context
This was our final project at Tech Elevator, and I worked on a team of four developers - two focused on the front end and two on the back. We went through three cycles, meeting with our Product Owner and Scrum Master before and/or after each stage of development. I focused mostly on the front end application (built with Vue), but worked closely with the back end team to refine architecture and strategy, as well as code fixes.

Each team of four had their own Product Owner and product, by the way - ours was the Plants project, but others included things like making a mobile game, or a pothole tracker, or coffee shop app, et alia. As such the MVP for each project was different, and ours was confirmed by staff to be outrageously difficult compared to the others. We managed to meet and exceed these expectations, even while our Product Owner threw new requirements and conflicting ideas into the equation.

This project utilizes a Database, a RESTful API coded from scratch, a third party Plants API, and of course our own front end designs.

It was intended to allow authenticated users to create gardens, interact on forums, search for plants and ask for advice regarding diseases in plants. This application also allows users to create accounts, send private messages to one another, sell/buy plants in a marketplace, and more.

This project was a ton of fun, and a learning experience I'm truly grateful for. This is the kind of thing I think I could do for the rest of my life.

## Installation
Download and run the seed script for the Database first, then start the Server. After that, open the Vue folder and <code>npm run dev</code> to host the site locally. Fire it up and create an account, log in, and have fun!

## Navigation
![BASIC NAVIGATION](https://i.imgur.com/wA1o3KA.png)
```
Login - Website loads here. A user must be authenticated to see anything else

Home - Proper splash page after Login. The navigation is consistent throughout the app, and Home can be returned to via link at any time
Search - Accessed by button
Forum - Likewise
```

![OTHER NAVIGATION](https://i.imgur.com/COoHqfj.png)
```
My Garden - Accessible by button on top of the page
Messages - Likewise

Logout - Likewise
```

## Notes
1. The 3rd party Plants API had several limitations we had to work around, mostly involving call limits and size, but also included oddities like inaccurate plant information and some clunky JSON organization
2. The original proprietary README is not included here, but most of the project was defined on call with the Product Owner herself
3. We spent an hour presenting this project at our bootcamp graduation. I'd love to include a link here, but it is unfortunately not publicly available.
