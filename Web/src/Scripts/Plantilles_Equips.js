RutaEquip = "";

const select = document.getElementById("Desplegable_Equips");

select.addEventListener("change", function() {
  const EquipSeleccionat = this.value;
})

switch (EquipSeleccionat) {
    case "FC Barcelona":
        RutaEquip = "../Jugadors-es/JugadoresBarca";
        break;
    case "Real Madrid CF":
        RutaEquip = "../Jugadors-es/JugadoresMadrid";
        break;
    case "Atlético de Madrid":
        RutaEquip = "../Jugadors-es/JugadoresAtleticoMad";
        break;
    case "Sevilla FC":
        RutaEquip = "../Jugadors-es/JugadoresSevilla";
        break;
    case "Real Sociedad":
        RutaEquip = "../Jugadors-es/JugadoresRealSociedad";
        break;
    case "Real Betis":
        RutaEquip = "../Jugadors-es/JugadoresBetis";
        break;
    case "Athletic Club":
        RutaEquip = "../Jugadors-es/JugadoresAthletic";
        break;
    case "Villarreal CF":
        RutaEquip = "../Jugadors-es/JugadoresVillarreal";
        break;
    case "Valencia CF":
        RutaEquip = "../Jugadors-es/JugadoresValencia";
        break;
    case "RC Celta de Vigo":
        RutaEquip = "../Jugadors-es/JugadoresCeltaDeVigo";
        break;
    case "RCD Mallorca":
        RutaEquip = "../Jugadors-es/JugadoresMallorca";
        break;
    case "Girona FC":
        RutaEquip = "../Jugadors-es/JugadoresGirona";
        break;
    case "CA Osasuna":
        RutaEquip = "../Jugadors-es/JugadoresOsasuna";
        break;
    case "Getafe CF":
        RutaEquip = "../Jugadors-es/JugadoresGetafe";
        break;
    case "UD Las Palmas":
        RutaEquip = "../Jugadors-es/JugadoresLaspalmas";
        break;
    case "RCD Espanyol":
        RutaEquip = "../Jugadors-es/JugadoresEspañol";
        break;
    case "CD Leganés":
        RutaEquip = "../Jugadors-es/JugadoresLeganes";
        break;
    case "Real Valladolid CF":
        RutaEquip = "../Jugadors-es/JugadoresValladolid";
        break;
    case "Rayo Vallecano":
        RutaEquip = "../Jugadors-es/JugadoresRayoVallecano";
        break;
    case "Deportivo Alavés":
        RutaEquip = "../Jugadors-es/JugadoresAlaves";
        break;
    default:
        RutaEquip = "";
        break;
}