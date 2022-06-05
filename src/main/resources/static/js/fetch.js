const doGet = async (url) => {
    const response = await fetch(url, {
      method: 'GET',
      credentials: 'same-origin'
    });
    return response;
  }
  
  const doPost = async (url, data) => {
    const response = await fetch(url, {
      method: 'POST',
      credentials: 'same-origin',
      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      }
    });
    return response;
  }
  
  const doPut = async (url, data) => {
    const response = await fetch(url, {
      method: 'PUT',

      body: JSON.stringify(data),
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      }
    });
    return response;
  }
  
  const doDelete = async (url) => {
    const response = await fetch(url, {
      method: 'DELETE',
    });
    return response;
  }