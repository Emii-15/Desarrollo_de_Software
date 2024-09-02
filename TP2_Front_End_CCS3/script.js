document.getElementById('toggleSidebar').addEventListener('click', function() {
    document.getElementById('sidebar').classList.toggle('open');
});

document.getElementById('closeSidebar').addEventListener('click', function() {
    document.getElementById('sidebar').classList.remove('open');
});
document.getElementById('miBoton').addEventListener('click', function() {
    // Agrega aquí el código que deseas ejecutar cuando se hace clic en el botón
    alert('¡Has hecho clic en el botón!');
});