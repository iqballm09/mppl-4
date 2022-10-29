const User = require("../../models/User");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
dotenv = require("dotenv");

dotenv.config();

// Create user
const createUser = async (req, res) => {
    try {
        // Check if email has been saved in storage
        const emailExist = await User.findOne({ 
            where: { email: req.body.email } 
        });
        if (emailExist) return res.status(400).send("Email has been used!");
        // Hash password
        const salt = await bcrypt.genSalt(15);
        const hashPassword = await bcrypt.hash(req.body.password, salt);
        // Create new user
        const user = await User.create({
            name: req.body.name,
            email: req.body.email,
            password: hashPassword,
            phoneNumber: req.body.phoneNumber
        });
        // Create register token
        const token = jwt.sign({ email: user.email }, process.env.REGISTER_TOKEN);
        res.header('reg-token', token);
        return res.status(201).json({ user });
    } catch (error) {
        return res.status(500).json({ error: error.message });
    }
}

module.exports = createUser;