window.addEventListener('load', () => {
    const baseURL = 'http://localhost:8080/';
    const categoryElement = document.querySelector('select');
    const postsContainer = document.querySelector("#posts");
    const loadingContainer = document.querySelector('#loading');
    const main = document.querySelector('main');
    const loading = setInterval(() => {
        let content = loadingContainer.innerText;
        content += '.'
        loadingContainer.innerText = content;
    }, 200);

    let listUserPost = true;
    let userId;
    main.style.display = 'none';
    loadingContainer.innerHTML = 'Carregando';
    
    doGet(baseURL + 'get_user') 
        .then(response => response.json())
        .then(data => {
            main.style.display = '';
            clearInterval(loading);
            loadingContainer.innerHTML = '';
            //console.log(data);
            userId = data.id;
            document.querySelector("#user").innerText = data.name;
            getUserPosts();
        })
        .catch(error => {
            alert('Acesso não autorizado!');
            document.location.href = './index.html';
        });
    
    //logout
    document.querySelector("#logout").addEventListener('click', e => {
        e.preventDefault();
        doGet(baseURL + 'logout')
            .then(response => {
                if(response.ok) document.location.href = './index.html';
            })
    });
    //create post
    document.querySelector('#create-post').addEventListener('click', e => {
        e.preventDefault();
        document.location.href = './app-register-post.html?' + userId;
    });

    //list all post by category
    document.querySelector('#list-posts').addEventListener('click', e => {
        e.preventDefault();
        document.querySelector('#post-list-title').innerHTML = 'Todos os posts';
        listUserPost = false;
        getPosts();
    });

    //list user posts 
    document.querySelector('#list-my-posts').addEventListener('click', e => {
        e.preventDefault();
        document.querySelector('#post-list-title').innerHTML = 'Meus posts';
        listUserPost = true;
        getUserPosts();
    });
    const populateHtmlWithPosts = (arrayOfPost) => {
        postsContainer.innerHTML = '';
        //console.log(arrayOfPost.length)
        if(arrayOfPost.length != 0) { 
            let anchor, div, href;
            for (post of arrayOfPost) {
                //console.log(post)
                href = './app-post.html?' + post.id + 'u' + userId;
                anchor = document.createElement('a');
                div = document.createElement('div');
                anchor.setAttribute('href', href);
                anchor.innerText = post.title;
                div.append(anchor);
                postsContainer.append(div);
            }
            return;
        }
        postsContainer.innerText = 'Não há posts cadastrados pra esta categoria.'
    }

    const getPosts = () => {
        doGet(baseURL + 'get_posts_by_category/' + categoryElement.value)
        .then(response => response.json())
        .then(data => {
            //console.log(data);
            populateHtmlWithPosts(data);
        })
        .catch(error => {
            console.log(error);
        });
    } 

    const getUserPosts = () => {
        doGet(baseURL + 'get_user_posts/'+ userId + '/' + categoryElement.value)
        .then(response => response.json())
        .then(data => {
            //console.log(data);
            populateHtmlWithPosts(data);
        })
        .catch(error => {
            console.log(error);
        });
    } 
    //attching functionality to select html element
    document.querySelector('select').addEventListener('change', e => {
        if(listUserPost) getUserPosts();
        else getPosts();
    })
    
})