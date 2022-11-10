const router = require('express').Router();
const verifyUser = require("../middleware/verifyTokenUser");
const verifyMerchant = require("../middleware/verifyTokenMerchant");

// Import controllers
const createUser = require("../controllers/user/createUser");
const { getUserByEmail, getAllUsers, getUserById } = require('../controllers/user/readUser');
const createMerchant = require("../controllers/merchant/createMerchant");
const { getMerchantByEmail, getAllMerchants, getMerchantById } = require("../controllers/merchant/readMerchant")
const { getCard, getAllCards } = require('../controllers/card/readCard');
const updateCard = require("../controllers/card/updateCard");
const updateUser = require("../controllers/user/updateUser");
const createPayment = require("../controllers/payment/createPayment");
const { getPaymentById, getAllPayments } = require("../controllers/payment/readPayment");
const createTopUp = require("../controllers/topup/createTopup");
const { getAllTopUps, getTopUpById } = require("../controllers/topup/readTopup");
const updateMerchant = require('../controllers/merchant/updateMerchant');
const createWithdraw = require("../controllers/withdraw/createWithdraw");
const { getAllWithdraws, getWithdrawById } = require("../controllers/withdraw/readWithdraw");

// User endpoints
/* Register - Login */
router.post('/users/register', createUser);
router.post('/users/login', getUserByEmail);
/* Get and update */
router.get('/users', getAllUsers);
router.get("/users/id", verifyUser, getUserById);
router.put('/users/id/edit', verifyUser, updateUser);

// Merchant endpoints
/* Register - Login */
router.post('/merchants/register', createMerchant);
router.post('/merchants/login', getMerchantByEmail);
/* Get and update */
router.get('/merchants', getAllMerchants);
router.get("/merchants/id", verifyMerchant, getMerchantById);
router.put('/merchants/id/edit', verifyMerchant, updateMerchant);

// Payment endpoints
router.post('/payments/cardID', verifyUser, createPayment);
router.get('/payments/cardID', verifyUser, getAllPayments); // By card id
router.get('/payments/id/cardID', verifyUser, getPaymentById);

// Topup endpoints
router.post('/topups/cardID', verifyUser, createTopUp);
router.get('/topups/cardID', verifyUser, getAllTopUps); // By card id
router.get('/topups/id/cardID', verifyUser, getTopUpById);

// Card endpoints
router.get('/cards', getAllCards);
router.get('/cards/id/userID', verifyUser, getCard);
router.put('/cards/id/userID/edit', verifyUser, updateCard);

// Withdraw endpoints
router.post('/withdraws/merchantID', verifyMerchant, createWithdraw);
router.get('/withdraws/merchantID', verifyMerchant, getAllWithdraws); // By merchant id
router.get('/withdraws/id/merchantID', verifyMerchant, getWithdrawById);

module.exports = router;