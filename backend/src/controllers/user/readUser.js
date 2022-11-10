const User = require("../../models/User");
const bcrypt = require("bcryptjs");
const jwt = require('jsonwebtoken');
const dotenv = require("dotenv");

dotenv.config();

// Read all users
const getAllUsers = async (req, res) => {
    try {
        const users = await User.findAll();
        return res.status(200).json({ users });
    } catch (error) {
        return res.status(500).send(error.message);
    }
}

// Get user by email --> Login
const getUserByEmail = async (req, res) => {
    // Check if email is already exists
    const user = await User.findOne({ 
        where: { email: req.body.email }
    });
    if (!user) return res.status(404).send(`User with email: ${req.body.email} is not found`);
    // Checking if password is correct
    const validPassword = await bcrypt.compare(req.body.password, user.password);
    if (!validPassword) return res.status(400).send("Invalid password!");
    // Create and assign token
    const token = jwt.sign({ id: user.id, email: user.email }, process.env.TOKEN_SECRET);
    res.header('auth-token', token).status(202).send("Logged In!");
}

// Get user by id
const getUserById = async (req, res) => {
    // Get payload
    const userID = req.user.id;
    // Get user by id
    const user = await User.findOne({
        where: { id: userID }
    });
    return res.json({ user });
}

module.exports = {
    getAllUsers,
    getUserByEmail,
    getUserById
}