window.addEventListener('load', () => {
    const baseURL = 'http://localhost:8080/';
    const userId = Number.parseInt(document.location.search.replace('?', ''));
    const inputs = document.querySelectorAll('form input');
    const select = document.querySelector('select');
    const textArea = document.querySelector('textarea');
    
    const getDateTime = () => {
        date = new Date();
        y = date.getFullYear();
        m = 1 + date.getMonth();
        d = date.getDate();
        h = date.getHours();
        mi = date.getMinutes();
        s = date.getSeconds();
        if(m < 10) m = '0' + m;
        if(d < 10) d = '0' + d;
        if(h < 10) h = '0' + h;
        if(mi < 10) mi = '0' + mi;
        if(s < 10) s = '0' + s
        return y + '-' + m + '-' + d + 'T' 
                + h + ':' + mi + ':' + s;
    }

    const getFormData = () => {
        return {
            category: select.value,
            title: inputs[0].value,
            content: textArea.value,
            user: {id: userId},
            date: getDateTime()
        }
    }
    
    document.querySelector('form').addEventListener('submit', (e) => {
        e.preventDefault();
        console.log(getFormData());
        doPost(baseURL + 'save_post', getFormData())
        .then(response => {
            if(response.ok) alert('Post salvo com sucesso!')
            if(response.status == 401) alert('Acesso não autorizado!');
            window.location.href = './app.html';
        });

    });

    doGet(baseURL + 'is_authenticate').then(response => {
        console.log(response.status) 
        if(response.status == 401) {
            alert('Acesso não autorizado!');
            window.location.href = './index.html';
        }
    });
    
})