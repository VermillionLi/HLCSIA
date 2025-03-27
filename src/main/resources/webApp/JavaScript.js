function reterieveIndividualStat(){
    const playerID = document.getElementById('playerSearch').value;
    fetch(`/statChecker?playerID=${encodeURIComponent(playerID)}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => {
            if(!response.ok){
                alert("critical error (don't joke around in the search bar) " + response.error)
            }
            return response.json();
        })
        .then(data =>{
            document.getElementById("trophy").innerHTML = data.netTrophy;
            document.getElementById("wins").innerHTML = data.netWins;
            document.getElementById("wlr").innerHTML = data.winLoseRate;

            const streak = data.streaks;

            const tableBody = document.querySelector("#statsTable tbody");
            tableBody.innerHTML = "";
            streak.forEach(streak => {
                const row = document.createElement("tr");
                const cell = document.createElement("td")
                cell.textContent = streak;
                row.appendChild(cell);
                tableBody.appendChild(row);
            });
           })
        .catch(error =>{
            alert("something went wrong  (is playerID spelled right (without # sign), is your API token corrct? (check setting), is SQL table made? (check setting)" + error)
    })
}


function getWall(x){
        fetch(`/walls?WallType=${encodeURIComponent(x)}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => {
            if(!response.ok){
                alert("you shouldn't see this (getWall function in JS has failed)")
            }
            return response.json();
        })
        .then(data =>{
            document.getElementById("numberOfPlayer").innerHTML = data.totalPeople;
            //because it's an ID, statsTable has to be preceded by a #
            const tableBody = document.querySelector("#statsTable tbody");
            tableBody.innerHTML = "";
            data.items.forEach(row => {
                let tr = document.createElement("tr");

                row.forEach(cell => {
                    let td = document.createElement("td");
                    td.textContent = cell;
                    tr.appendChild(td);
                });

                tableBody.appendChild(tr);
            });

        })
        .catch(error =>{
            alert("table building failed: " + error);
        })
}
/* for future versions
function reterieveLogs(){
    const playerID = document.getElementById('playerSearch').value;
    fetch(`/battleLog?playerID=${encodeURIComponent(playerID)}`, {
        method: 'GET',
        headers: {
            'Accept': 'application/json'
        }
    })
        .then(response => {
            if(!response.ok){
                alert("don't joke around in the search bar" + response.error)
            }
            return response.json();
        })
        .then(data =>{
            alert(data.text())
            console.log(data);
            document.getElementById("test").innerHTML = data;

        })
        .catch(error =>{
            alert("something went wrong  " +
                "\n(is playerID spelled right (without # sign), " +
                "\nis your API token corrct? (check setting), " +
                "\nis SQL table made? (check setting)" + error)
        })
}
*/