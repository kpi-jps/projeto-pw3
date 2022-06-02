window.addEventListener('load', () => {
    const baseURL = 'http://localhost:8080/';
    
    doGet(baseURL + 'get_user')
        .then((response) => {
        console.log(response.status) 
        if(response.status == 200) response.json();
        }).then((data) => {
            console.log(data);
        });
})