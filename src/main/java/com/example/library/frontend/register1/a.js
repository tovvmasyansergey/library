const toggleButton = document.getElementById("toggleButton");
const menu = document.getElementById("menu");
const closeButton = document.getElementById("closeButton");
const mainLink = document.getElementById("main");
const mainContent = document.getElementById("mainContent");

toggleButton.addEventListener("click", () => {
    menu.classList.toggle("open");
});

closeButton.addEventListener("click", () => {
    menu.classList.remove("open");
});

// Toggle the visibility of the "Главное" content below the menu
mainLink.addEventListener("click", (e) => {
    e.preventDefault(); // Prevent link from navigating
    mainContent.classList.toggle("expandable-content"); // Toggle content visibility
});
