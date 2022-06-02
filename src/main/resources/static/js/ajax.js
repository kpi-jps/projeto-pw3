const doGet = (url, calbackFunction) => {
    $.ajax({
        url: url, 
        type: 'GET',
        
        success: (res) => {
            calbackFunction(res);
        },
        error: (res) => {
            calbackFunction(res);
        }
    });     
}

const doPost = (url, data, calbackFunction) => {
    $.ajax({
        url: url, 
        type: 'POST',
        crossDomain: true,
        
        data: JSON.stringify(data),
        contentType: 'application/json',
       
        success: (res) => {
            calbackFunction(res);
        },
        error: (res) => {
            calbackFunction(res);
        }
    });
}