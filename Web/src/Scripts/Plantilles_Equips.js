$(document).ready(function() {
    console.log("JS carregat correctament");

    // --- Select2 con imágenes ---
    function formatOption(option) {
        if (!option.id) return option.text;
        var img = $(option.element).data('img');
        if (!img) return option.text;
        return $('<span><img src="' + img + '" width="50" style="margin-right:8px;"> ' + option.text + '</span>');
    }

    $('#Desplegable_Equips').select2({
        templateResult: formatOption,
        templateSelection: formatOption
    });

    // --- Función para cargar plantillas ---
    function carregarPlantilla() {
        const contenidor = document.querySelector(".targetes-jugadors");
        const select = document.getElementById("Desplegable_Equips");

        fetch("../src/JSON/jugadorsMasculins.json")
            .then(resp => {
                if (!resp.ok) throw new Error("Fitxer JSON no trobat: " + resp.status);
                return resp.json();
            })
            .then(data => {
                function renderEquip(equip) {
                    contenidor.innerHTML = "";
                    if (!equip) return;
                    const obj = data.find(e => e.equip === equip);
                    if (!obj) return;

                    obj.jugadors.forEach(jugador => {
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
                }

                // Use jQuery change handler so it works with Select2
                $(select).on('change', function() {
                    renderEquip(this.value);
                });

                // If an option is already selected when the page loads, render it
                if (select.value) {
                    renderEquip(select.value);
                }
            })
            .catch(err => console.error("Error carregant JSON:", err));
    }

    carregarPlantilla();
});