const tokenKey = "authData";

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

export const saveAuthData = (obj: LoginResponse) => {
    localStorage.setItem(tokenKey, JSON.stringify(obj));
}

export const getAuthData = () => {
    const str = localStorage.getItem(tokenKey) ?? "{}";
    return JSON.parse(str) as LoginResponse;
}

export const removeAuthData = () => {
    localStorage.removeItem(tokenKey);
}
