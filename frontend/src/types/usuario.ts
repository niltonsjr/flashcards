import { Rol } from "./rol";

export type Usuario = {
    id: number;
    nombreDeUsuario: string;
    nombre: string;
    apellidos: string;
    email: string;
    roles: Rol[]; 
}