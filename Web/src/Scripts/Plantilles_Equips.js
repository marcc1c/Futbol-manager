console.log("Script carregat correctament");
alert("JS s'ha carregat!"); 

let EquipJSON = [];
const contenidor = document.querySelector(".targetes-jugadors");
const select = document.getElementById("Desplegable_Equips");

fetch("../src/JSON/jugadorsMasculins.json")
  .then(response => {
      if (!response.ok) throw new Error("Fitxer JSON no trobat: " + response.status);
      return response.json();
  })
  .then(data => {
      EquipJSON = data;
      console.log("JSON carregat:", EquipJSON);

      select.addEventListener("change", function() {
          if (EquipJSON.length === 0) {
              alert("JSON encara no carregat, intenta-ho en un moment");
              return;
          }

          const EquipSeleccionat = this.value;
          contenidor.innerHTML = "";

          if (!EquipSeleccionat) return;

          const EquipObj = EquipJSON.find(e => e.equip === EquipSeleccionat);
          if (!EquipObj) {
              alert("Equip no trobat: " + EquipSeleccionat);
              return;
          }

          EquipObj.jugadors.forEach(jugador => {
              const targeta = document.createElement("div");
              targeta.classList.add("targeta-jugador");

              const imgFondo = document.createElement("img");
              imgFondo.classList.add("imatge-fondo");
              imgFondo.src = "../src/img/pngtree-empty-gold-player-card-rating-vector-png-image_12575430.png";
              targeta.appendChild(imgFondo);

              const imgJugador = document.createElement("img");
              imgJugador.classList.add("imatge-a-dalt");
              imgJugador.src = jugador.foto || "../src/img/Persona Default.png";
              targeta.appendChild(imgJugador);

              const nom = document.createElement("h3");
              nom.classList.add("Nom");
              nom.textContent = jugador.nomPersona;
              targeta.appendChild(nom);

              const info = document.createElement("p");
              info.classList.add("info");
              info.innerHTML = `Dorsal: ${jugador.dorsal}<br>Posició: ${jugador.posicio}<br>Qualitat: ${jugador.qualitat}`;
              targeta.appendChild(info);

              contenidor.appendChild(targeta);
          });
      });
  })
  .catch(error => {
      alert("Error carregant JSON: " + error.message);
      console.error(error);
  });