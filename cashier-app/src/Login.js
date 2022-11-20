import './App.css';
import { React, useState } from "react";
import { Button, Alert, Form, Card } from "react-bootstrap";
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import validator from 'validator';

function Login() {
    // Handle input and output
    const [isShowRed, setShowRed] = useState(false);
    const [isShowGreen, setShowGreen] = useState(false);

    const handleSubmit = async(event) => {
        let userEmail = event.target.userEmail.value;
        let password = event.target.password.value;
        event.preventDefault();
        if (validator.isEmail(userEmail) || password.length === 0) {
            setShowGreen(false);
            setShowRed(true);
        } else {
            setShowGreen(true);
            setShowRed(false);
        }
        console.log({ userEmail, password })
        const res = await axios.post("https://foodpayapi.up.railway.app/api/merchants/login", {
            email: userEmail,
            password: password
        });
        let parsed_data = JSON.parse(JSON.stringify(res));
        let token = parsed_data.data.token;
        let merchantId = parsed_data.data.id;
    };

    return (
        <div className="App">
            <header className="App-header">
                <Alert variant="success" show={isShowRed} onClose={() => setShowRed(false)} dismissible>Login Successfully!</Alert>
                <Alert variant="danger" show={isShowGreen} onClose={() => setShowGreen(false)} dismissible>Invalid Email or Password!</Alert>
                <Card style={{ color: "#000" }}>
                    <Card.Header>Foodpay App - Log In </Card.Header>
                    <Card.Body>
                        <Form onSubmit={handleSubmit}>
                            <Form.Group>
                                <Form.Label>Email Address</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Example@gmail.com"
                                    name="userEmail"
                                />
                            </Form.Group>
                            <br></br>
                            <Form.Group>
                                <Form.Label>Password</Form.Label>
                                <Form.Control
                                    type="password"
                                    name="password"
                                />
                            </Form.Group>
                            <br></br>
                            <Button variant="danger" type="submit" name="submitButton" size="lg"> Sign In </Button>
                        </Form>
                    </Card.Body>
                </Card>
            </header>
        </div>
    );
}

export default Login;
