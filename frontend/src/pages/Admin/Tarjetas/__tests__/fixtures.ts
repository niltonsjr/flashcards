import { rest } from "msw";
import { setupServer } from "msw/node";
import { BASE_URL } from "util/requests";

export const tarjetaResponse = {
  id: 1,
  frontal: "Frontal de la Tarjeta",
  trasera: "Trasera de la Tarjeta",
  conocida: false,
  fechaUltimaRespuesta: null,
  totalConocidas: 0,
  totalNoConocidas: 0,
  categoria: { id: 2, nombre: "Historia de la Filosofía", usuarioId: 1 },
  usuarioId: 1,
};

export const findCategoriasReponse = {
  content: [
    {
      id: 1,
      nombre: "Vocabulario Inglés",
      usuarioId: 1,
      tarjetas: [],
    },
    {
      id: 2,
      nombre: "Historia de la Filosofía",
      usuarioId: 1,
      tarjetas: [],
    },
    {
      id: 3,
      nombre: "Ecología de los Seres Vivos",
      usuarioId: 1,
      tarjetas: [],
    },
  ],
  pageable: {
    sort: {
      empty: false,
      sorted: true,
      unsorted: false,
    },
    offset: 0,
    pageNumber: 0,
    pageSize: 10,
    paged: true,
    unpaged: false,
  },
  last: true,
  totalElements: 3,
  totalPages: 1,
  size: 10,
  number: 0,
  sort: {
    empty: false,
    sorted: true,
    unsorted: false,
  },
  first: true,
  numberOfElements: 3,
  empty: false,
};

export const mockedServer = setupServer(
  rest.get(`${BASE_URL}/categorias`, (_req, res, ctx) => {
    return res(ctx.status(200), ctx.json(findCategoriasReponse));
  }),
  rest.get(`${BASE_URL}/tarjetas/:tarjetaId`, (_req, res, ctx) => {
    return res(ctx.status(200), ctx.json(tarjetaResponse));
  }),
  rest.put(`${BASE_URL}/tarjetas/:tarjetaId`, (_req, res, ctx) => {
    return res(ctx.status(200), ctx.json(tarjetaResponse));
  }),
  rest.post(`${BASE_URL}/tarjetas/:tarjetaId`, (_req, res, ctx) => {
    return res(ctx.status(200), ctx.json(tarjetaResponse));
  }),
  rest.post(`${BASE_URL}/tarjetas`, (_req, res, ctx) => {
    return res(ctx.status(200), ctx.json(tarjetaResponse));
  })
);
