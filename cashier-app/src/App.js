import './App.css';
import { React, useState } from "react";
import { Button, Alert, Form, Card } from "react-bootstrap";
import useFetch from 'react-fetch-hook';
import 'bootstrap/dist/css/bootstrap.min.css'
import validator from 'validator';

function App() {
  // Fetch data from api
  const { isLoading, error, data } = useFetch();

  // Handle input and output
  const [amount, setAmount] = useState('');
  const [isShowRed, setShowRed] = useState(false);
  const [isShowGreen, setShowGreen] = useState(false);

  const handleChange = event => {
    setAmount(event.target.value);
  };

  const handleClick = event => {
    event.preventDefault();
    setAmount(event.target.value);
  };

  const handleSubmit = event => {
    let userEmail = event.target.userEmail.value;
    let inputAmount = event.target.inputAmount.value;
    event.preventDefault();
    if (validator.isEmail(userEmail)) {
      setShowGreen(false);
      setShowRed(true);
    } else {
      setShowGreen(true);
      setShowRed(false);
    }
    console.log(userEmail);
    console.log(inputAmount);
  };

  return (
    <div className="App">
      <header className="App-header">
        <Alert variant="success" show={isShowRed} onClose={() => setShowRed(false)} dismissible>Top up is successful</Alert>
        <Alert variant="danger" show={isShowGreen} onClose={() => setShowGreen(false)} dismissible>Email is empty or invalid</Alert>
        <Card style={{ color: "#000" }}>
          <Card.Header>Foodpay App - Top Up </Card.Header>
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
                <Form.Label>Amount of Topup</Form.Label>
                <Form.Control
                  type="text"
                  onChange={handleChange}
                  value={amount}
                  name="inputAmount"
                />
              </Form.Group>
              <br></br>
              <Button onClick={handleClick} variant="light" value="25000"> 25000 </Button>{' '}
              <Button onClick={handleClick} variant="light" value="50000"> 50000 </Button>{' '}
              <Button onClick={handleClick} variant="light" value="100000"> 100000 </Button>{' '}
              <Button onClick={handleClick} variant="light" value="200000"> 200000 </Button>
              <p></p>
              <Button variant="danger" type="submit" name="submitButton" size="lg"> Top Up </Button>
            </Form>
          </Card.Body>
        </Card>
      </header>
    </div>
  );
}

export default App;
