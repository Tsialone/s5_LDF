// src/pages/Employees.jsx
import React, { useEffect, useState } from "react";
import { Table, Button, Container } from "react-bootstrap";
import { getAllEmployees, deleteEmployee } from "../services/employeService";

export default function Employe() {
  const [employees, setEmployees] = useState([]);

  const fetchEmployees = async () => {
    const data = await getAllEmployees();
    setEmployees(data);
  };

  const handleDelete = async (id) => {
    await deleteEmployee(id);
    fetchEmployees();
  };

  useEffect(() => {
    fetchEmployees();
  }, []);

  return (
    <Container className="mt-4 bg-dark text-light p-4 rounded">
      <h3>Liste des employés</h3>
      <Table striped bordered hover variant="dark">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {employees.map((emp) => (
            <tr key={emp.idEmploye}>
              <td>{emp.idEmploye}</td>
              <td>{emp.nom}</td>
              <td>{emp.email}</td>
              <td>
                <Button variant="danger" size="sm" onClick={() => handleDelete(emp.idEmploye)}>
                  Supprimer
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}
