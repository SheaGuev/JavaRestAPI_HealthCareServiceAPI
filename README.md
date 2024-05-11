
# Health System API

## Overview
The Health System API is designed to manage various aspects of a healthcare system, including patient management, appointment scheduling, medical record maintenance, prescription handling, and billing services. This project is part of the coursework for 5COSC022W Client-Server Architectures at the University of Westminster.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
  - [Patients](#patients)
  - [Doctors](#doctors)
  - [Appointments](#appointments)
  - [Medical Records](#medical-records)
  - [Prescriptions](#prescriptions)
  - [Billing](#billing)
- [Contact](#contact)
- [Acknowledgements](#acknowledgements)

## Features
- **Patient Management:** Register, update, and manage patient information.
- **Doctor Management:** Add and manage doctor profiles including their specializations.
- **Appointment Scheduling:** Schedule and manage appointments between patients and doctors.
- **Medical Records:** Maintain comprehensive medical records for each patient.
- **Prescription Management:** Manage prescriptions including medication details and dosages.
- **Billing:** Handle billing and financial transactions related to medical services.

## Technologies
- Java
- JAX-RS (Java API for RESTful Web Services)
- Apache Tomcat
- Maven

## Getting Started

### Prerequisites
- JDK 1.8 or later
- Maven
- Apache Tomcat Server

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/sheaguev/HealthSystemAPI.git
   ```
2. Navigate to the project directory:
   ```bash
   cd HealthSystemAPI
   ```
3. Build the project with Maven:
   ```bash
   mvn clean install
   ```
4. Deploy the generated `.war` file to Apache Tomcat.

## Usage
Start the server and navigate to `http://localhost:8080/HealthCareSystem_RESTAPI` to access the API. Use Postman or any other API client to interact with the available endpoints.

## API Endpoints

### Patients
- **GET /patients** - Retrieve all patients
- **POST /patients** - Create a new patient
- **GET /patients/{id}** - Retrieve a patient by ID
- **PUT /patients/{id}** - Update a patient's information
- **DELETE /patients/{id}** - Delete a patient

### Doctors
- **GET /doctors** - Retrieve all doctors
- **POST /doctors** - Create a new doctor
- **GET /doctors/{id}** - Retrieve a doctor by ID
- **PUT /doctors/{id}** - Update a doctor's information
- **DELETE /doctors/{id}** - Delete a doctor

..........................................................

## Contributing
Contributions are welcome! For major changes, please open an issue first to discuss what you would like to change. Please ensure to update tests as appropriate.

## Contact
Shea Sexton - [sheasexton02@gmail.com](mailto:sheasexton02@gmail.com)

## Acknowledgements
- JAX-RS
- Apache Tomcat
- University of Westminster



