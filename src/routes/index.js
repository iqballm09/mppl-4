const router = require('express').Router();
const verify = require("../verifyToken");

// Import controllers
const createUser = require("../controllers/createUser");
const { getUserByEmail, getAllUsers } = require('../controllers/readUser');
const readPost = require("../controllers/readPost");
const updateUser = require("../controllers/updateUser");

// Create endpoints
router.post('/users/register', createUser);
router.post('/users/login', getUserByEmail);
router.get('/users', getAllUsers);
router.get('/posts/:userID', verify, readPost);
router.get('/users/edit/:userID', verify, updateUser);

module.exports = router;