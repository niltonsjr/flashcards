import { Categoria } from "./categoria";

export type Tarjeta = {
    id: number;
    frontal: string;
    trasera: string;
    conocida: boolean;
    fechaUltimaRespuesta: string;
    totalConocidas: number;
    totalNoConocidas: number;
    categoria: Categoria;
    usuarioId: number;
}