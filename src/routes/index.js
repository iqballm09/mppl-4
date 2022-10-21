const router = require('express').Router();
const verify = require("../utils/verifyToken");

// Import controllers
const createUser = require("../controllers/user/createUser");
const { getUserByEmail, getAllUsers } = require('../controllers/user/readUser');
const updateUser = require("../controllers/user/updateUser");
const readPost = require("../controllers/posts/readPost");
const createPin = require("../controllers/inputPin");

// Create endpoints
router.post('/users/register', createUser);
router.post('/users/register/inputPin', createPin);
router.post('/users/login', getUserByEmail);
router.get('/users', getAllUsers);
router.get('/posts', verify, readPost);
router.post('/users/edit', verify, updateUser);

module.exports = router;