import { Role } from "util/auth";

export type Usuario = {
    id: number;
    nombreDeUsuario: string;
    nombre: string;
    apellidos: string;
    email: string;
    roles: Role[]; 
}