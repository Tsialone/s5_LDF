// src/pages/Home.jsx
import React from "react";
import { Container, Alert } from "react-bootstrap";

export default function Home() {
  return (
    <Container className="mt-4">
      <Alert variant="primary">
        <h1 className="text-center">Bienvenue sur la page Home</h1>
      </Alert>
      <p className="text-center">
        Ceci est un exemple de page d'accueil avec React-Bootstrap.
      </p>
    </Container>
  );
}
