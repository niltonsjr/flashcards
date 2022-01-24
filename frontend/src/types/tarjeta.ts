export type Tarjeta = {
    id: number;
    frontal: string;
    trasera: string;
    conocida: boolean;
    fechaUltimaRespuesta: string;
    totalConocidas: number;
    totalNoConocidas: number;
    categoriaId: number;
    usuarioId: number;
}