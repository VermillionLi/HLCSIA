function reterieveData(){
    const playerID = document.getElementById('playerSearch').value;
    fetch(`/statChecker?playerID=${encodeURIComponent(playerID)}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => {
            alert("sent stuff to:" +  `/battleLog?playerID=${encodeURIComponent(playerID)}`)
            if(!response.ok){
                alert(response.error)
            }
            return response.json();
        })
        .then(data =>{
            alert(data.test)
            document.getElementById("test").innerHTML = data.test;
            alert(data.test + "aewffawef")
           })
}