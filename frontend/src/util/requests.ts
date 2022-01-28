import axios, { AxiosRequestConfig } from "axios";
import qs from "qs";
import history from "./history";
import { getAuthData } from "./storage";

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

// Add a request interceptor
axios.interceptors.request.use(function (config) {
    // Do something before request is sent
    return config;
}, function (error) {
    // Do something with request error
    return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
}, function (error) {
    if (error.response.status === 401) {
        history.push("/");
    }
    return Promise.reject(error);
});