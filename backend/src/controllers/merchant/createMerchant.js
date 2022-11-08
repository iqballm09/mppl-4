const Merchant = require("../../models/Merchant");
const bcrypt = require("bcryptjs");
const dotenv = require("dotenv");

dotenv.config();

// Create user
const createMerchant = async (req, res) => {
    try {
        // Check if email has been saved in storage
        const emailExist = await Merchant.findOne({ 
            where: { email: req.body.email } 
        });
        if (emailExist) return res.status(400).send(`Email: ${req.body.email} has been used!`);
        // Hash password
        const saltUser = await bcrypt.genSalt(15);
        const hashPassword = await bcrypt.hash(req.body.password, saltUser);
        // Create new user
        const user = await User.create({
            name: req.body.name,
            email: req.body.email,
            password: hashPassword,
            phoneNumber: req.body.phoneNumber
        });
        await user.save();
        // Insert pin number
        const saltCard = await bcrypt.genSalt(16);
        const hashPin = await bcrypt.hash(req.body.pinNumber, saltCard);
        // Create card
        const card = await Card.create({
            UserID: user.id,
            pinNumber: hashPin
        });
        await card.save();
        return res.status(201).json({ user, card });
    } catch (error) {
        return res.status(500).json({ error: error.message });
    }
}

module.exports = createMerchant;