import axios from "axios";

const API_BASE_URL = "http://localhost:8080";

const publicProductApi = axios.create({
  baseURL: API_BASE_URL,
});

export const getAllProducts = async () => {
  const response = await publicProductApi.get("/api/products");
  return response.data;
};

export const getProductById = async (productId) => {
  const response = await publicProductApi.get(`/api/products/${productId}`);
  return response.data;
};

export const searchProducts = async (keyword) => {
  const response = await publicProductApi.get(`/api/products/search?keyword=${encodeURIComponent(keyword)}`);
  return response.data;
};

export const getProductsByCategory = async (categoryId) => {
  const response = await publicProductApi.get(`/api/products/category/${categoryId}`);
  return response.data;
};

export const getCategories = async () => {
  const response = await publicProductApi.get("/api/categories");
  return response.data;
};