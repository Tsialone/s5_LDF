// src/services/employeService.js
import axios from "axios";
import config from "../config";

const API_URL = `${config.BASE_URL}/employes`;

export const getAllEmployees = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

export const getEmployeeById = async (id) => {
  const response = await axios.get(`${API_URL}/byIdEmploye`, {
    params: { idEmploye: id },
  });
  return response.data;
};

export const createEmployee = async (employee, departementId) => {
  const response = await axios.post(API_URL, employee, {
    params: { departementId },
  });
  return response.data;
};

export const updateEmployee = async (id, employee, departementId) => {
  const response = await axios.put(API_URL, employee, {
    params: { id, departementId },
  });
  return response.data;
};

export const deleteEmployee = async (id) => {
  const response = await axios.delete(API_URL, {
    params: { id },
  });
  return response.data;
};
