document.addEventListener("keydown", function (event) {
    const paginaActual = window.location.pathname.split("/").pop();

    const rutas = {
        "index.html": {
            siguiente: "Pagines/Classificacio.html",
            anterior: "Pagines/Resultats.html"
        },
        "Classificacio.html": {
            siguiente: "NouJugador.html",
            anterior: "../index.html"
        },
        "NouJugador.html": {
            siguiente: "Plantilles.html",
            anterior: "Classificacio.html"
        },
        "Plantilles.html": {
            siguiente: "Resultats.html",
            anterior: "NouJugador.html"
        },
        "Resultats.html": {
            siguiente: "../index.html",
            anterior: "Plantilles.html"
        }
    };

    if (!rutas[paginaActual]) return;

    if (event.key === "ArrowRight") {
        const siguiente = rutas[paginaActual].siguiente;

        if (siguiente) {
            event.preventDefault();
            window.location.href = siguiente;
        }
    }

    if (event.key === "ArrowLeft") {
        const anterior = rutas[paginaActual].anterior;

        if (anterior) {
            event.preventDefault();
            window.location.href = anterior;
        }
    }
});