import { rest } from "msw";
import { setupServer } from "msw/node";
import { BASE_URL } from "util/requests";

export const categoriaResponse = {
  id: 1,
  nombre: "Nueva categoria",
  usuarioId: 1,
  tarjetas: [],
};

export const mockedServer = setupServer(
  rest.post(`${BASE_URL}/categorias`, (_req, res, ctx) => {
    return res(ctx.status(201), ctx.json(categoriaResponse));
  }),
  rest.put(`${BASE_URL}/categorias/:categoriaId`, (_req, res, ctx) => {
    return res(ctx.status(200), ctx.json(categoriaResponse));
  }),
  rest.get(`${BASE_URL}/categorias/:categoriaId`, (_req, res, ctx) => {
    return res(ctx.status(200), ctx.json(categoriaResponse));
  })
);
