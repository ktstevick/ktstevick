<template>
  <main>
  <div class="container">
    <div class="box1">FORMAL DESCRIPTION</div>
    <div class="box2">SELFIE</div>
    <div class="box3">MISSION STATEMENT</div>
    <div class="box4">SELF PORTRAIT</div>
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

.box1 {
background-color: hsl(19, 100%, 86%);
height: 20vh;
width: auto;
margin-top: 10px;
grid-area: formal;
}

.box2 {
  background-color: hsl(19, 100%, 83%);
height: 47vh;
margin-top: 10px;
grid-area: selfie;
}

.box3 {
  background-color: hsl(19, 100%, 79%);
height: 20vh;
margin-top: 10px;
grid-area: mission;
}

.box4 {
  background-color: hsl(19, 100%, 75%);
height: 47vh;
margin-top: 10px;
grid-area: portrait;
}

.box5 {
  background-color: hsl(19, 100%, 69%);
height: 20vh;
margin-top: 10px;
grid-area: informal;
}

main {
/* background-color: pink; */
margin-left: 3vw;
margin-top: auto;
margin-bottom: auto;
}
</style>
