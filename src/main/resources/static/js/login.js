window.addEventListener('load', () => {
    const baseURL = 'http://localhost:8080/';


    const getFormData = () => {
        const inputs = document.querySelectorAll('form input');
        return {
            email: inputs[0].value,
            pass: inputs[1].value
        }
    }
    
    document.querySelector('form').addEventListener('submit', (e) => {
        e.preventDefault();
        console.log(getFormData());
        doPost(baseURL + 'login', getFormData()).then((response) => {
            if(response.status == 401) alert('Acesso negado!');

            if(response.ok) window.location.assign('./app.html');
        });

    });
   
    doGet(baseURL + 'is_authenticate').then((response) => {
        console.log(response.status) 
        if(response.ok) window.location.assign('./app.html');
    });
    
})