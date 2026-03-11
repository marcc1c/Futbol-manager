const equips = [
"FC Barcelona",
"Real Madrid",
"Atlético de Madrid",
"Sevilla FC",
"Real Sociedad",
"Valencia CF"
];

const select = document.getElementById("selectEquip");

equips.forEach(equip => {

    const option = document.createElement("option");
    option.value = equip;
    option.textContent = equip;

    select.appendChild(option);

});