const tipus = document.getElementById("tipusPersona");
const posicions = document.getElementById("posicions");

tipus.addEventListener("change", () => {

if(tipus.value === "entrenador"){

posicions.style.display = "none";

}else{

posicions.style.display = "block";

}

});