<template>
  <main>
  <div class="container">
    <div class="box1">FORMAL DESCRIPTION</div>

    <div class="box2">
      <img class="selfie" src="../assets/images/selfie.jpg" title="A man in search of a vision." />
    </div>

    <div class="box3">MISSION STATEMENT</div>

    <div class="box4">
      <img class="self-portrait" src="../assets/images/selfie2.jpg" title="Candor in the face of adversity."/>
    </div>

    <div class="box5">INFORMAL DESCRIPTION</div>
  </div>
</main>
</template>

<script>

</script>

<style scoped>
.container {
display: grid;
grid-template-columns: 1fr 1fr 1fr;
grid-template-areas: 
  "formal formal selfie"
  "portrait mission selfie"
  "portrait informal informal";
gap: 6vh;
}

.selfie {
  height: 47vh;
  width: 100%;
  object-fit: cover;
}

.self-portrait {
  height: 47vh;
  width: 100%;
  object-fit: cover;
}

.box1 {
background-color: hsl(19, 100%, 86%);
height: 20vh;
width: auto;

grid-area: formal;
}

.box2 {
  background-color: hsl(19, 100%, 83%);
height: 47vh;

grid-area: selfie;
}

.box3 {
  background-color: hsl(19, 100%, 79%);
height: 20vh;

grid-area: mission;
}

.box4 {
  background-color: hsl(19, 100%, 75%);
height: 47vh;

grid-area: portrait;
}

.box5 {
  background-color: hsl(19, 100%, 69%);
height: 20vh;

grid-area: informal;
}

main {
/* background-color: pink; */
margin-left: 3vw;
margin-top: auto;
margin-bottom: auto;
}
</style>
