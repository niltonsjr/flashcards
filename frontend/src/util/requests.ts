export const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? "Flashcards";
const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET ?? "flachcards123";

const basicHeader = () => 'Basic ' + window.btoa(CLIENT_ID + ":" + CLIENT_SECRET);

type LoginData = {
    username: string;
    password: string;
}

export const requestBackendLogin = (loginData: LoginData) => {
    
} 
