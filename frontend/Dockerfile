# frontend/Dockerfile

# 개발에 사용한 버전에 맞춰서 버전 값을 수정해주세요.
FROM node:14 as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:stable-alpine as production-stage
COPY  ./nginx/nginx.conf /etc/nginx/conf.d/default.conf

COPY --from=build-stage /app/dist /usr/share/nginx/html

EXPOSE 5500
CMD ["nginx", "-g", "daemon off;"]