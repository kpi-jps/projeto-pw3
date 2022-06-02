const doGet = async (url) => {
    const response = await fetch(url, {
      method: 'GET',
      //: 'no-cors',
      headers: {
        'Content-Type': 'application/json'
        // 'Content-Type': 'application/x-www-form-urlencoded',
      }

    });
    return response;
  }
  
  const doPost = async (url, data) => {
    const response = await fetch(url, {
      method: 'POST',
      //mode: 'no-cors',
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
      credentials: 'include',
      mode: 'cors',
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
      mode: 'cors',
      credentials: 'include'
    });
    return response;
  }