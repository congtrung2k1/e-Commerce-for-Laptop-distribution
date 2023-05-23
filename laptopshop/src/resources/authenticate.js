import axios from 'axios'

const authenticateUrl = `http://localhost:8080/authenticate`;

const postAuthentication = async (username, password) => {
    const result = await axios.post(authenticateUrl, {
        username: username,
        password: password
    });

    return result.data;
};

export { postAuthentication }