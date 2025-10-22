# Multi-stage build for React application
FROM node:18-alpine AS build
WORKDIR /app
COPY ewcm-app/package*.json ./
RUN npm install
COPY ewcm-app/ ./
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
