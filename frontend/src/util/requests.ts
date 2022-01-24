import axios, { AxiosRequestConfig } from "axios";
import qs from "qs";

export const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? "Flashcards";
const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET ?? "flachcards123";

type LoginData = {
    username: string;
    password: string;
}

export const requestBackendLogin = (loginData: LoginData) => {
    const headers = {
        "Content-Type": "application/x-www-form-urlencoded",
        Authorization: 'Basic ' + window.btoa(CLIENT_ID + ":" + CLIENT_SECRET)
    }

    const data = qs.stringify({
        ...loginData,
        grant_type: "password"
    });

    return axios({ method: "POST", baseURL: BASE_URL, url: "/oauth/token", data, headers });
}

type RegisterData = {
    nombreDeUsuario: string;
    nombre: string;
    apellidos: string;
    email: string;
    contrasena: string;
    confirmaContrasena: string;
}

export const requestBackendRegister = (registerData: RegisterData) => {
    const headers = {
        "Content-Type": "application/json"
    }

    const data = registerData;

    return axios({ method: "POST", baseURL: BASE_URL, url: "/registro", data, headers })
}

export const requestBackend = (config: AxiosRequestConfig) => {
    const headers = config.withCredentials
        ? {
            ...config.headers,
            Authorization: "Bearer " + getAuthData().access_token
        } : { ...config.headers }
    return axios({ ...config, baseURL: BASE_URL, headers });
}

type LoginResponse = {
    access_token: string;
    token_type: string;
    refresh_token: string;
    expires_in: number;
    scope: string
    apellidos: string;
    nombre: string;
    usuarioId: number
}

const tokenKey = "authData";

export const saveAuthData = (obj: LoginResponse) => {
    localStorage.setItem(tokenKey, JSON.stringify(obj));
}

export const getAuthData = () => {
    const str = localStorage.getItem(tokenKey) ?? "{}";
    return JSON.parse(str) as LoginResponse;
}