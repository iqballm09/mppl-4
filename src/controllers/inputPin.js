const dotenv = require("dotenv");
const jwt = require("jsonwebtoken");
const User = require("../models/User");
const bcrypt = require("bcryptjs");

dotenv.config();

// Create user
const createPin = async (req, res) => {
    // Get payload
    const token = req.header('reg-token');
    if(!token) return res.status(401).send("Access denied!");
    const email = jwt.verify(token, process.env.REGISTER_TOKEN).email;
    // Read user
    const user = await User.findOne({ 
        where: { email: email }
    });
    // Insert pin number
    const salt = await bcrypt.genSalt(16);
    const hashPin = await bcrypt.hash(req.body.pinNumber, salt);
    user.pinNumber = hashPin;
    await user.save();
    return res.json({ user });
}

module.exports = createPin