// Inicializa o mapa
var map = L.map('map').setView([-8.059694, -34.951917], 13);

// Adiciona o tile layer ao mapa
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

// Adiciona um marcador nas coordenadas fornecidas
var marker = L.marker([-8.059694, -34.951917]).addTo(map);

// Texto do marcador
marker.bindPopup("<b>PLACA 01</b><br>8°03'34.9\"S 34°57'06.9\"W").openPopup();
