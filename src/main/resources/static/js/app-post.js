window.addEventListener('load', () => {
    const baseURL = 'http://localhost:8080/';
    const linkInfo = document.location.search.replace('?', '').split('u');
    const postId = Number.parseInt(linkInfo[0]);
    const userId = Number.parseInt(linkInfo[1]);
    const commentsContainer = document.querySelector("#comments");
    const loadingContainer = document.querySelector('#loading');
    const main = document.querySelector('main');
    const loading = setInterval(() => {
        let content = loadingContainer.innerText;
        content += '.'
        loadingContainer.innerText = content;
    }, 200);
    main.style.display = 'none';
    loadingContainer.innerHTML = 'Carregando';
    //console.log(postId);
    

    const returnCategoryInfo = (category) => {
        switch (category) {
            case 'SPORTS':
                return 'Esporte';
            case 'GENERAL_CULTURE':
                return 'Cultura Geral';
            case 'POLITICS':
                return 'Política';
            case 'MUSIC':
                return 'Música';
            case 'VIDEO_GAMES':
                return 'Video game';
            case 'RELIGION':
                return 'Relegião';
            case 'SCIENCE':
                    return 'Ciência';
        }
        return 'Outros';
    }

    const returnTimeStamp = (datetime) => {
        const datetimeArray = datetime.split('T');
        const date = datetimeArray[0].split('-'); 
        return date[2] + '/' + date[1] + '/' + date[0] + '-' + datetimeArray[1];
    }

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

    //getting a post
    doGet(baseURL + 'post/' + postId) 
        .then(response => response.json())
        .then(data => {
            console.log(data);
            main.style.display = '';
            clearInterval(loading);
            loadingContainer.innerHTML = '';
            if(data.user.id == userId) {
                const editContainer = document.querySelector('#edit');
                const delContainer = document.querySelector('#del');
                let edit = document.createElement('a');
                let del = document.createElement('a');
                edit.setAttribute('href', './app-edit-post.html?' + postId);
                del.setAttribute('href', '#');
                edit.innerText = 'Editar';
                del.innerText = 'Deletar';
                del.addEventListener('click', e => {
                    e.preventDefault();
                    if(confirm('Tem certeza que quer deletar esse post?')) {
                        doDelete(baseURL + 'delete_post/' + postId)
                        .then(response => {
                            if(response.ok) {
                                alert('Post deletado com sucesso!');
                                document.location.href = './app.html';
                            }
                        })
                    }
                })
                editContainer.append(edit);
                delContainer.append(del);
            }

            document.querySelector("#post-title").innerText = data.title;
            document.querySelector("#post-category").innerText = 'Categoria: ' + returnCategoryInfo(data.category);
            document.querySelector("#post-info").innerText = 'Autor: ' + data.user.name + 
                ' - postado em: ' + returnTimeStamp(data.date);
            document.querySelector("#post-content").innerText = data.content;
            getComments();
        })
        .catch(error => {
            alert('Acesso não autorizado!');
            document.location.href = './index.html';
        });
    //deleting comment
    const deleteComment = (id) => {
        
    }
    //getting comments
    const populateHtmlWithComments = (arrayOfComments) => {
        commentsContainer.innerHTML = '';
        //console.log(arrayOfComments.length)
        if(arrayOfComments.length != 0) { 
            let div, p, h5;
            for (comment of arrayOfComments) {
                //console.log(comment)
                div = document.createElement('div');
                p =  document.createElement('p');
                h5 = document.createElement('h4');
                p.innerText = comment.comment;
                h5.innerText = 'Autor: ' + comment.user.name +
                    ' - postado em: ' + returnTimeStamp(comment.date);
                div.append(h5);
                div.append(p);
                if(comment.user.id == userId) {
                    let del = document.createElement('a');
                    del.setAttribute('href', '#');
                    del.innerText = 'Deletar comentario';
                    del.addEventListener('click', e  => {
                        e.preventDefault();
                        console.log("deletando comentário " + comment.id)
                        doDelete(baseURL + 'delete_comment/' + comment.id)
                            .then(response => {
                                if(response.ok)  {
                                alert('Comentário deletado com sucesso!');
                                    getComments();
                                }
                            })
                    })
                    div.append(del);
                }
                commentsContainer.append(div);
            }
            return;
        }
        commentsContainer.innerText = 'Não há comentários para este post!'
    }

    const getComments = () => {
        doGet(baseURL + 'get_post_comments/'+ postId)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            populateHtmlWithComments(data);
        })
        .catch(error => {
            console.log(error);
        });
    } 
    
    //saving comment
    const getData = () => {
        return {
            comment : document.querySelector('textarea').value,
            date : getDateTime(),
            user : {id : userId},
            post : {id : postId}
        }
    }
    document.querySelector('form').addEventListener('submit', e => {
        e.preventDefault();
        console.log(userId);
        console.log(postId);
        console.log(getData());
        doPost(baseURL + 'save_comment', getData()).then(response =>{
            if(response.ok) {
                alert('Commentário salvo com sucesso');
                document.querySelector('textarea').value = '';
                getComments();
            }
        })
    })
    
})