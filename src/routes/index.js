const router = require('express').Router();
const verify = require("../utils/verifyToken");

// Import controllers
const createUser = require("../controllers/user/createUser");
const { getUserByEmail, getAllUsers } = require('../controllers/user/readUser');
const { getCard, getAllCards } = require('../controllers/card/readCard');
const updateCard = require("../controllers/card/updateCard");
const updateUser = require("../controllers/user/updateUser");
const createPayment = require("../controllers/payment/createPayment");
const { getPaymentById, getAllPayments } = require("../controllers/payment/readPayment");

// User endpoints
/* Register - Login */
router.post('/users/register', createUser);
router.get('/users/login', getUserByEmail);
/* Get and update */
router.get('/users', getAllUsers);
router.put('/users/id/edit', verify, updateUser);

// Payment endpoints
router.post('/payments/id/cardID', verify, createPayment); 
router.get('/payments/cardID', verify, getAllPayments); // By card id
router.get('/payments/id/cardID', verify, getPaymentById);

// Card endpoints
router.get('/cards', getAllCards);
router.get('/cards/id/userID', verify, getCard);
router.put('/cards/id/userID/edit', verify, updateCard);

module.exports = router;