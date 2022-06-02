window.addEventListener('load', () => {
    const baseURL = 'http://localhost:8080/';


    const getFormData = () => {
        const inputs = document.querySelectorAll('form input');
        return {
            name: inputs[0].value,
            email: inputs[1].value,
            pass: inputs[2].value
        }
    }
    
    document.querySelector('form').addEventListener('submit', (e) => {
        e.preventDefault();
        console.log(getFormData());
        doPost(baseURL + 'save_user', getFormData()).then((response) => {
            console.log(response.status);
            if(response.ok) {
                alert('Usuário cadastrado com sucesso!');
                window.location.assign('./index.html');
            }
        });

    });
    
    
})