const router = require('express').Router();
const verify = require("../utils/verifyToken");

// Import controllers
const createUser = require("../controllers/user/createUser");
const { getUserByEmail, getAllUsers } = require('../controllers/user/readUser');
const { getCardByUserID, getAllCards } = require('../controllers/card/readCard');
const updateCard = require("../controllers/card/updateCard");
const updateUser = require("../controllers/user/updateUser");
const createCard = require("../controllers/card/createCard");

// Create User Endpoints
router.post('/users/register', createUser);
router.get('/users/login', getUserByEmail);
router.get('/users', getAllUsers);
router.put('/users/edit', verify, updateUser);

// Create Card Endpoints
router.post('/users/register/createCard', createCard);
router.get('/cards', getAllCards);
router.get('/cards/UserID', getCardByUserID);
router.put('/cards/UserID/edit', verify, updateCard);


module.exports = router;