const contenidor = document.querySelector(".Resultats_Partits");

fetch("../src/JSON/partitsMasculins.json")
  .then(resp => {
    if (!resp.ok) throw new Error("Fitxer JSON no trobat: " + resp.status);
    return resp.json();
  })
  .then(data => {
    let i = 1; // numeración de partidos

    data.forEach(partit => {
        
        const divPartits = document.createElement("div");

        const divPartit = document.createElement("div");
        divPartit.classList.add("Partit");
        divPartits.appendChild(divPartit);

        const numPartit = document.createElement("h3");
        numPartit.textContent = `Partit: ${i}`;
        divPartit.appendChild(numPartit);;

        const divEquips = document.createElement("div");
        divEquips.classList.add("Equips_Partit");
        divPartit.appendChild(divEquips);

        const imgLocal = document.createElement("img");
        imgLocal.src = partit.equip_local.escut || "../src/img/escudos/default.png";
        divEquips.appendChild(imgLocal);

        const nomLocal = document.createElement("p");
        nomLocal.textContent = partit.equip_local.nom;
        divEquips.appendChild(nomLocal);

        const vs = document.createElement("p");
        vs.textContent = "vs";
        divEquips.appendChild(vs);

        const nomVisitant = document.createElement("p");
        nomVisitant.textContent = partit.equip_visitant.nom;
        divEquips.appendChild(nomVisitant);

        const imgVisitant = document.createElement("img");
        imgVisitant.src = partit.equip_visitant.escut || "../src/img/escudos/default.png";
        divEquips.appendChild(imgVisitant);

        const resultat = document.createElement("p");
        resultat.textContent = `Resultat: ${partit.resultat}`;
        divPartit.appendChild(resultat);

        // Añadir el partido completo al contenedor
        contenidor.appendChild(divPartit);

        i++;
    });
  })
  .catch(err => console.error(err));