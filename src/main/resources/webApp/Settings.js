function addAPI(){
    const token = document.getElementById('token').value;
    fetch(`/sql/token?token=${encodeURIComponent(token)}`, {
        method: 'GET',
        headers: {
            'Accept': 'text/plain' //not that it mattered
        }
    }).then()
}
function addBadBrawler(){
    const addBad = document.getElementById('addBad').value;
    fetch(`/sql/brawler?brawler=${encodeURIComponent(addBad)}`, {
        method: 'GET',
        headers: {
            'Accept': 'text/plain'
        }
    }).then()
}
function removePlayers(){
    const request = document.getElementById('removePlayers').id; //lol... would show up on the console log if later implementers accidentally changed the ID
    fetch(`/sql?request=${encodeURIComponent(request)}`, {
        method: 'GET',
        headers: {
            'Accept': 'text/plain'
        }
    }).then()
}

function removeBad(){
    const request = document.getElementById('removeBad').id; //lol... would show up on the console log if later implementers accidentally changed the ID
    fetch(`/sql?request=${encodeURIComponent(request)}`, {
        method: 'GET',
        headers: {
            'Accept': 'text/plain'
        }
    }).then()
}