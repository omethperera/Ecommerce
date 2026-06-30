import axios from "axios";
import { getAccessToken, getUserId } from "../utils/auth";

const BASE_URL = "http://localhost:8080/api/orders";

const authHeaders = () => ({
  Authorization: `Bearer ${getAccessToken()}`,
});

export const addToCart = async (userId, data) => {
  return axios.post(`${BASE_URL}/cart/items`, data, {
    headers: {
      ...authHeaders(),
      "X-User-Id": userId,
    },
  });
};

export const getCart = async (userId) => {
  return axios.get(`${BASE_URL}/cart`, {
    headers: {
      ...authHeaders(),
      "X-User-Id": userId,
    },
  });
};

export const updateCartItem = async (userId, itemId, data) => {
  return axios.put(`${BASE_URL}/cart/items/${itemId}`, data, {
    headers: {
      ...authHeaders(),
      "X-User-Id": userId,
    },
  });
};

export const removeCartItem = async (userId, itemId) => {
  return axios.delete(`${BASE_URL}/cart/items/${itemId}`, {
    headers: {
      ...authHeaders(),
      "X-User-Id": userId,
    },
  });
};

export const clearCart = async (userId) => {
  return axios.delete(`${BASE_URL}/cart`, {
    headers: {
      ...authHeaders(),
      "X-User-Id": userId,
    },
  });
};

export const checkout = async (userId, data) => {
  return axios.post(`${BASE_URL}/checkout`, data, {
    headers: {
      ...authHeaders(),
      "X-User-Id": userId,
    },
  });
};

export const getCurrentOrders = async (userId) => {
  return axios.get(`${BASE_URL}/my/current`, {
    headers: {
      ...authHeaders(),
      "X-User-Id": userId,
    },
  });
};

export const getOrderHistory = async (userId) => {
  return axios.get(`${BASE_URL}/my/history`, {
    headers: {
      ...authHeaders(),
      "X-User-Id": userId,
    },
  });
};

export const cancelOrder = async (userId, orderId) => {
  return axios.put(`${BASE_URL}/${orderId}/cancel`, {}, {
    headers: {
      ...authHeaders(),
      "X-User-Id": userId,
    },
  });
};

export const getAdminOrders = async () => {
  return axios.get(`${BASE_URL}/admin`, {
    headers: {
      ...authHeaders(),
      "X-Seller-Id": getUserId(),
    },
  });
};

export const updateAdminOrderStatus = async (orderId, data) => {
  return axios.put(`${BASE_URL}/admin/${orderId}/status`, data, {
    headers: {
      ...authHeaders(),
      "X-Seller-Id": getUserId(),
    },
  });
};