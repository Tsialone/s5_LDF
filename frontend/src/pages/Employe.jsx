// src/pages/Employees.jsx
import React, { useEffect, useState } from "react";
import { Table, Button, Alert, Container, Spinner } from "react-bootstrap";
import { getAllEmployees, deleteEmployee } from "../services/employeService";

export default function Employe() {
    const [employees, setEmployees] = useState([]);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    const fetchEmployees = async () => {
        setLoading(true);
        try {
            const data = await getAllEmployees();
            setEmployees(data);
            setError(null);
        } catch (err) {
            console.error(err);
            setError(err.response?.data || "Erreur lors de la récupération des employés");
        }
        finally {
            setLoading(false);
        }
    };

    const handleDelete = async (id) => {
        setLoading(true);
        try {
            await deleteEmployee(id);
            fetchEmployees();
        } catch (err) {
            console.error(err);
            setError(err.response?.data || "Erreur lors de la suppression de l'employé");
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchEmployees();
    }, []);

    return (

        
        <Container className="mt-3 bg-dark text-light p-4 rounded">
             <div className="d-flex align-items-center mb-3">
                <h3 className="me-3">Liste des employés</h3>
                {loading && <Spinner animation="border" variant="light" size="sm" />}
            </div>
            {error && <Alert variant="danger">{error}</Alert>}
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
